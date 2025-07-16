package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que implementa uma conta corrente bancária com operações básicas e cheque especial.
 * Implementa a interface Conta com funcionalidades específicas para contas correntes.
 *
 * Atributos:
 * - numeroContaCompleto: String - Número completo da conta com prefixo (final)
 * - numeroConta: int - Número da conta sem prefixo (final)
 * - nomeCliente: String - Nome do titular da conta (final)
 * - saldo: double - Saldo atual da conta
 * - limiteChequeEspecial: double - Limite disponível do cheque especial
 * - agencia: String - Agência bancária (final)
 * - jurosChequeEspecial: Juros - Classe interna para cálculo de juros
 *
 * Métodos Públicos:
 * - consultarSaldo(): void - Exibe saldo e alerta sobre juros pendentes (implementação da interface)
 * - realizarDeposito(double valor): void - Realiza depósito e quita juros primeiro (implementação da interface)
 * - realizarSaque(double valor): void - Realiza saque usando saldo ou cheque especial (implementação da interface)
 * - realizarPagamento(double valor): void - Realiza pagamento (implementação da interface)
 * - consultarLimiteChequeEspecial(): void - Exibe informações detalhadas do cheque especial
 * - getters: Implementações dos métodos da interface Conta + getters específicos
 *
 * Métodos Privados:
 * - calcularLimiteChequeEspecial(double saldoAtual): void - Calcula limite baseado no saldo
 * - aplicarValorNoSaldo(double valor): void - Lógica centralizada para aplicação de valores
 *
 * Classe Interna:
 * - Juros: Gerencia cálculo de juros do cheque especial (data de início, valor utilizado e taxa)
 */
public class ContaCorrente implements Conta {
    // Número completo da conta (ex: "cc12345")
    private final String numeroContaCompleto;

    // Número da conta sem prefixo
    private final int numeroConta;

    // Nome do titular da conta
    private final String nomeCliente;

    // Saldo atual da conta
    private double saldo;

    // Limite do cheque especial
    private double limiteChequeEspecial;

    // Agência bancária
    private final String agencia;

    // Gerenciador de juros do cheque especial
    private final Juros jurosChequeEspecial;

    /**
     * Construtor da conta corrente
     * @param numeroCompleto Número completo com prefixo (ex: "cc123")
     * @param numeroConta Número da conta sem prefixo
     * @param nomeCliente Nome do titular
     * @param saldoInicial Valor inicial da conta
     * @param agencia Agência bancária
     */
    public ContaCorrente(String numeroCompleto, int numeroConta, String nomeCliente,
                         double saldoInicial, String agencia) {
        this.numeroContaCompleto = numeroCompleto;
        this.numeroConta = numeroConta;
        this.nomeCliente = nomeCliente;
        this.saldo = saldoInicial;
        this.agencia = agencia;
        this.jurosChequeEspecial = new Juros();
        calcularLimiteChequeEspecial(saldoInicial);
    }

    /**
     * Calcula o limite do cheque especial baseado no saldo atual
     * @param saldoAtual Saldo atual da conta
     */
    private void calcularLimiteChequeEspecial(double saldoAtual) {
        this.limiteChequeEspecial = saldoAtual <= 500 ?
                saldoAtual * 0.2 : // 20% para saldos baixos
                saldoAtual * 0.5;   // 50% para saldos maiores
        System.out.printf("Limite de cheque especial definido: R$ %.2f%n",
                this.limiteChequeEspecial);
    }

    /**
     * Exibe o saldo atual e alerta sobre juros pendentes
     */
    @Override
    public void consultarSaldo() {
        double juros = jurosChequeEspecial.calcularJuros();
        if (juros > 0) {
            System.out.printf("Atenção: Juros pendentes de R$ %.2f%n", juros);
        }
        System.out.printf("Saldo atual: R$ %.2f%n", this.saldo);
    }

    /**
     * Realiza depósito, priorizando quitação de juros
     * @param valor Valor a ser depositado
     */
    @Override
    public void realizarDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido!");
            return;
        }

        double juros = jurosChequeEspecial.calcularJuros();

        if (juros > 0) {
            System.out.printf("Juros pendentes: R$ %.2f%n", juros);
            double valorRestante = valor - juros;

            if (valorRestante >= 0) {
                jurosChequeEspecial.reduzirUtilizacao(juros);
                System.out.println("Juros quitados com sucesso!");

                if (valorRestante > 0) {
                    aplicarValorNoSaldo(valorRestante);
                }
            } else {
                jurosChequeEspecial.reduzirUtilizacao(valor);
                System.out.printf("Valor aplicado nos juros. Restante: R$ %.2f%n",
                        juros - valor);
            }
        } else {
            aplicarValorNoSaldo(valor);
        }

        calcularLimiteChequeEspecial(this.saldo);
    }

    /**
     * Aplica valor no saldo, priorizando redução do cheque especial utilizado
     * @param valor Valor a ser aplicado
     */
    private void aplicarValorNoSaldo(double valor) {
        double utilizacaoCheque = jurosChequeEspecial.getValorUtilizado();

        if (utilizacaoCheque > 0) {
            double valorParaCheque = Math.min(valor, utilizacaoCheque);
            jurosChequeEspecial.reduzirUtilizacao(valorParaCheque);
            this.saldo += (valor - valorParaCheque);
            System.out.printf("R$ %.2f aplicado no cheque especial e R$ %.2f no saldo%n",
                    valorParaCheque, (valor - valorParaCheque));
        } else {
            this.saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso!%n", valor);
        }
    }

    /**
     * Realiza saque usando saldo ou cheque especial
     * @param valor Valor a ser sacado
     */
    @Override
    public void realizarSaque(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido!");
            return;
        }

        if (valor <= this.saldo) {
            this.saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado! Novo saldo: R$ %.2f%n",
                    valor, this.saldo);
        } else {
            double diferenca = valor - this.saldo;
            double limiteDisponivel = this.limiteChequeEspecial -
                    jurosChequeEspecial.getValorUtilizado();

            if (diferenca <= limiteDisponivel) {
                this.saldo = 0;
                jurosChequeEspecial.registrarUtilizacao(diferenca);
                System.out.printf("Saque realizado usando cheque especial!%n" +
                        "Limite utilizado: R$ %.2f%n", diferenca);
            } else {
                System.out.println("Saldo e limite insuficientes para o saque!");
            }
        }
    }

    /**
     * Realiza pagamento (delega para método de saque)
     * @param valor Valor do pagamento
     */
    @Override
    public void realizarPagamento(double valor) {
        realizarSaque(valor);
    }

    /**
     * Exibe informações detalhadas do cheque especial
     */
    public void consultarLimiteChequeEspecial() {
        double valorUtilizado = jurosChequeEspecial.getValorUtilizado();
        double juros = jurosChequeEspecial.calcularJuros();

        System.out.println("\n=== CHEQUE ESPECIAL ===");
        System.out.printf("Limite total: R$ %.2f%n", this.limiteChequeEspecial);
        System.out.printf("Utilizado: R$ %.2f%n", valorUtilizado);
        System.out.printf("Juros acumulados: R$ %.2f%n", juros);
        System.out.printf("Disponível: R$ %.2f%n",
                (this.limiteChequeEspecial - valorUtilizado));

        if (jurosChequeEspecial.getDataInicioUtilizacao() != null) {
            System.out.println("Data de início da utilização: " +
                    jurosChequeEspecial.getDataInicioUtilizacao());
        }
    }

    // Implementação dos métodos da interface Conta
    @Override
    public String getNumeroContaCompleto() {
        return this.numeroContaCompleto;
    }

    @Override
    public String getTipoConta() {
        return "cc"; // Retorna "cc" para conta corrente
    }

    @Override
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    // Getters específicos
    public double getLimiteChequeEspecial() {
        return this.limiteChequeEspecial;
    }

    public String getAgencia() {
        return this.agencia;
    }

    /**
     * Classe interna para cálculo de juros do cheque especial
     */
    private static class Juros {
        private LocalDate dataInicioUtilizacao;
        private double valorUtilizado;
        private static final double TAXA_JUROS_DIARIA = 0.20; // 20% ao dia

        public void registrarUtilizacao(double valor) {
            if (this.valorUtilizado == 0) {
                this.dataInicioUtilizacao = LocalDate.now();
            }
            this.valorUtilizado += valor;
        }

        public void reduzirUtilizacao(double valor) {
            this.valorUtilizado = Math.max(0, this.valorUtilizado - valor);
            if (this.valorUtilizado == 0) {
                this.dataInicioUtilizacao = null;
            }
        }

        public double calcularJuros() {
            if (valorUtilizado == 0 || dataInicioUtilizacao == null) {
                return 0;
            }

            long dias = ChronoUnit.DAYS.between(dataInicioUtilizacao, LocalDate.now());
            return valorUtilizado * Math.pow(1 + TAXA_JUROS_DIARIA, dias) - valorUtilizado;
        }

        public double getValorUtilizado() {
            return valorUtilizado;
        }

        public LocalDate getDataInicioUtilizacao() {
            return dataInicioUtilizacao;
        }

        public double getTotalDevido() {
            return valorUtilizado + calcularJuros();
        }
    }
}
package service;

/**
 * Classe que implementa uma conta poupança bancária com operações básicas e rendimento mensal.
 * Implementa a interface Conta, fornecendo comportamento específico para contas poupança.
 *
 * Atributos:
 * - numeroContaCompleto: String - Número completo da conta com prefixo (final)
 * - numeroConta: int - Número da conta sem prefixo (final)
 * - nomeCliente: String - Nome do titular da conta (final)
 * - saldo: double - Saldo atual da conta
 * - agencia: String - Agência bancária (final)
 * - taxaRendimento: double - Taxa de rendimento mensal (0.5%, constante final)
 *
 * Métodos Públicos:
 * - aplicarRendimento(): void - Aplica o rendimento mensal na conta
 * - consultarSaldo(): void - Exibe o saldo atual (implementação da interface)
 * - realizarDeposito(double valor): void - Realiza depósito (implementação da interface)
 * - realizarSaque(double valor): void - Realiza saque (implementação da interface)
 * - realizarPagamento(double valor): void - Realiza pagamento (implementação da interface)
 * - getters: Implementações dos métodos da interface Conta + getters específicos
 *
 * Princípios SOLID aplicados:
 * - L (Liskov Substitution): Pode substituir qualquer instância de Conta
 * - S (Single Responsibility): Gerencia apenas operações de conta poupança
 */
public class ContaPoupanca implements Conta {
    // Número completo da conta (ex: "cp12345")
    private final String numeroContaCompleto;

    // Número da conta sem prefixo
    private final int numeroConta;

    // Nome do titular da conta
    private final String nomeCliente;

    // Saldo atual da conta
    private double saldo;

    // Agência bancária
    private final String agencia;

    // Taxa de rendimento mensal fixa (0.5%)
    private final double taxaRendimento = 0.005;

    /**
     * Construtor da conta poupança
     * @param numeroCompleto Número completo com prefixo (ex: "cp123")
     * @param numeroConta Número da conta sem prefixo
     * @param nomeCliente Nome do titular
     * @param saldoInicial Valor inicial da conta
     * @param agencia Agência bancária
     */
    public ContaPoupanca(String numeroCompleto, int numeroConta, String nomeCliente,
                         double saldoInicial, String agencia) {
        this.numeroContaCompleto = numeroCompleto;
        this.numeroConta = numeroConta;
        this.nomeCliente = nomeCliente;
        this.saldo = saldoInicial;
        this.agencia = agencia;
    }

    /**
     * Aplica o rendimento mensal na conta poupança
     * Calcula 0.5% sobre o saldo atual e credita na conta
     */
    public void aplicarRendimento() {
        double rendimento = this.saldo * taxaRendimento;
        this.saldo += rendimento;
        System.out.printf("Rendimento aplicado: R$ %.2f | Novo saldo: R$ %.2f%n",
                rendimento, this.saldo);
    }

    /**
     * Exibe o saldo atual formatado
     */
    @Override
    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", this.saldo);
    }

    /**
     * Realiza depósito na conta
     * @param valor Valor a ser depositado (deve ser positivo)
     */
    @Override
    public void realizarDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido!");
            return;
        }
        this.saldo += valor;
        System.out.printf("Depósito de R$ %.2f realizado! Novo saldo: R$ %.2f%n",
                valor, this.saldo);
    }

    /**
     * Realiza saque na conta
     * @param valor Valor a ser sacado (deve ser positivo e menor que saldo)
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
            System.out.println("Saldo insuficiente para saque!");
        }
    }

    /**
     * Realiza pagamento (utiliza mesma lógica de saque)
     * @param valor Valor do pagamento
     */
    @Override
    public void realizarPagamento(double valor) {
        realizarSaque(valor); // Delega para o método de saque
    }

    // Métodos de acesso implementados da interface Conta

    @Override
    public String getNumeroContaCompleto() {
        return this.numeroContaCompleto;
    }

    @Override
    public String getTipoConta() {
        return "cp"; // Retorna "cp" para conta poupança
    }

    @Override
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    // Métodos de acesso específicos

    public int getNumeroConta() {
        return this.numeroConta;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public double getTaxaRendimento() {
        return this.taxaRendimento;
    }
}
package service;

/**
 * Classe que implementa uma conta poupança bancária com operações básicas e rendimento mensal.
 */
public class ContaPoupanca implements Conta {
    private final String numeroContaCompleto;
    private final int numeroConta;
    private final String nomeCliente;
    private double saldo;
    private final String agencia;
    private final double taxaRendimento = 0.005; // 0.5% ao mês

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
     */
    public void aplicarRendimento() {
        double rendimento = this.saldo * taxaRendimento;
        this.saldo += rendimento;
        System.out.printf("Rendimento aplicado: R$ %.2f | Novo saldo: R$ %.2f%n",
                rendimento, this.saldo);
    }

    @Override
    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", this.saldo);
    }

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

    @Override
    public void realizarPagamento(double valor) {
        realizarSaque(valor); // Lógica idêntica ao saque
    }

    // Getters implementados da interface Conta
    @Override
    public String getNumeroContaCompleto() {
        return this.numeroContaCompleto;
    }

    @Override
    public String getTipoConta() {
        return "cp";
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
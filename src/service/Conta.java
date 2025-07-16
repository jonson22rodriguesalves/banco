package service;

/**
 * Interface que define o contrato básico para todas as contas bancárias.
 *
 * Atributos:
 *   Interfaces em Java não podem ter atributos de instância, apenas constantes (public static final)
 *
 * Métodos:
 * - getNumeroContaCompleto(): String - Obtém o número completo da conta (incluindo prefixo)
 * - getTipoConta(): String - Retorna o tipo da conta ("cc" para corrente, "cp" para poupança)
 * - getNomeCliente(): String - Retorna o nome do titular da conta
 * - getSaldo(): double - Retorna o saldo atual da conta
 * - consultarSaldo(): void - Exibe o saldo atual (implementação pode variar)
 * - realizarDeposito(double valor): void - Realiza operação de depósito
 * - realizarSaque(double valor): void - Realiza operação de saque
 * - realizarPagamento(double valor): void - Realiza operação de pagamento
 *
 * Princípios SOLID aplicados:
 * - I (Interface Segregation): Define apenas métodos essenciais para contas bancárias
 * - D (Dependency Inversion): Classes de alto nível dependem desta abstração
 *
 * Padrões de projeto relacionados:
 * - Strategy: Permite diferentes implementações dos métodos para diferentes tipos de conta
 * - Template Method: Define a estrutura básica de operações bancárias
 */
public interface Conta {
    /**
     * Retorna o número completo da conta (incluindo prefixo)
     * @return Número completo da conta (ex: "cc12345")
     */
    String getNumeroContaCompleto();

    /**
     * Retorna o tipo da conta
     * @return "cc" para conta corrente, "cp" para conta poupança
     */
    String getTipoConta();

    /**
     * Retorna o nome do titular da conta
     * @return Nome do cliente
     */
    String getNomeCliente();

    /**
     * Retorna o saldo atual da conta
     * @return Valor do saldo
     */
    double getSaldo();

    /**
     * Exibe o saldo atual da conta
     * Implementação pode variar entre tipos de conta
     */
    void consultarSaldo();

    /**
     * Realiza operação de depósito na conta
     * @param valor Valor a ser depositado
     */
    void realizarDeposito(double valor);

    /**
     * Realiza operação de saque na conta
     * @param valor Valor a ser sacado
     */
    void realizarSaque(double valor);

    /**
     * Realiza operação de pagamento na conta
     * @param valor Valor do pagamento
     */
    void realizarPagamento(double valor);
}
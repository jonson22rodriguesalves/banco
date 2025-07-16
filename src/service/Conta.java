package service;

/**
 * Interface que define os métodos básicos que todas as contas bancárias devem implementar.
 */
public interface Conta {
    String getNumeroContaCompleto();
    String getTipoConta();
    String getNomeCliente();
    double getSaldo();
    void consultarSaldo();
    void realizarDeposito(double valor);
    void realizarSaque(double valor);
    void realizarPagamento(double valor);
}
package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe responsável por calcular e gerenciar os juros do cheque especial.
 * Mantém registro do valor utilizado e da data de início da utilização para cálculo dos juros.
 */
public class Juros {
    private static final double TAXA_JUROS_DIARIA = 0.20; // 20% ao dia
    private LocalDate dataInicioUtilizacao;
    private double valorUtilizado;

    /**
     * Construtor que inicializa os atributos com valores padrão
     */
    public Juros() {
        this.dataInicioUtilizacao = null;
        this.valorUtilizado = 0;
    }

    /**
     * Registra a utilização do cheque especial e define a data de início se for a primeira utilização
     * @param valor Valor utilizado do cheque especial
     */
    public void registrarUtilizacao(double valor) {
        if (this.valorUtilizado == 0) {
            this.dataInicioUtilizacao = LocalDate.now();
        }
        this.valorUtilizado += valor;
    }

    /**
     * Reduz o valor utilizado do cheque especial e zera a data se o valor chegar a zero
     * @param valor Valor a ser reduzido do cheque especial
     */
    public void reduzirUtilizacao(double valor) {
        this.valorUtilizado -= valor;
        if (this.valorUtilizado <= 0) {
            this.valorUtilizado = 0;
            this.dataInicioUtilizacao = null;
        }
    }

    /**
     * Calcula os juros acumulados com base no tempo de utilização e taxa diária
     * @return Valor dos juros acumulados
     */
    public double calcularJuros() {
        if (valorUtilizado == 0 || dataInicioUtilizacao == null) {
            return 0;
        }

        long dias = ChronoUnit.DAYS.between(dataInicioUtilizacao, LocalDate.now());
        if (dias <= 0) {
            return 0;
        }

        return valorUtilizado * Math.pow(1 + TAXA_JUROS_DIARIA, dias) - valorUtilizado;
    }

    /**
     * Retorna o total devido (valor utilizado + juros acumulados)
     * @return Total devido
     */
    public double getTotalDevido() {
        return valorUtilizado + calcularJuros();
    }

    // Getters
    public double getValorUtilizado() {
        return valorUtilizado;
    }

    public LocalDate getDataInicioUtilizacao() {
        return dataInicioUtilizacao;
    }
}
package service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um banco com nome e lista de contas associadas.
 */
public class Banco {
    // Instância única do Banco (parte do padrão Singleton)
    private static Banco instance;
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    // Método estático para obter a instância
    public static Banco getInstance() {
        if (instance == null) {
            instance = new Banco("Banco Digital");
        }
        return instance;
    }

    /**
     * Adiciona uma conta à lista de contas do banco
     * @param conta Objeto Conta a ser adicionado
     */
    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    /**
     * Remove uma conta da lista de contas do banco
     * @param conta Objeto Conta a ser removido
     * @return true se a conta foi removida, false caso contrário
     */
    public boolean removerConta(Conta conta) {
        return this.contas.remove(conta);
    }

    /**
     * Busca uma conta pelo número completo da conta (incluindo prefixo)
     * @param numeroContaCompleto Número completo da conta (ex: "cc123")
     * @return Objeto Conta encontrado ou null se não existir
     */
    public Conta buscarConta(String numeroContaCompleto) {
        for (Conta conta : contas) {
            if (conta.getNumeroContaCompleto().equals(numeroContaCompleto)) {
                return conta;
            }
        }
        return null;
    }

    /**
     * Lista todas as contas do banco
     */
    public void listarContas() {
        System.out.println("\n=== Contas do Banco " + nome + " ===");
        for (Conta conta : contas) {
            System.out.println("Número: " + conta.getNumeroContaCompleto() +
                    " | Tipo: " + conta.getTipoConta() +
                    " | Cliente: " + conta.getNomeCliente() +
                    " | Saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }
}
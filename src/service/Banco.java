package service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um banco com nome e lista de contas associadas.
 * Implementa o padrão Singleton para garantir uma única instância.
 *
 * Atributos:
 * - instance: Banco - Instância única do banco (privado e estático, parte do padrão Singleton)
 * - nome: String - Nome do banco (privado)
 * - contas: List<Conta> - Lista de contas bancárias (privado)
 *
 * Métodos Públicos:
 * - getInstance(): Banco - Retorna a instância única do banco (Singleton)
 * - adicionarConta(Conta conta): void - Adiciona uma nova conta ao banco
 * - removerConta(Conta conta): boolean - Remove uma conta existente
 * - buscarConta(String numeroContaCompleto): Conta - Localiza conta pelo número completo
 * - listarContas(): void - Exibe todas as contas cadastradas
 * - getNome(): String - Getter para o nome do banco
 * - setNome(String nome): void - Setter para o nome do banco
 * - getContas(): List<Conta> - Getter para a lista de contas
 *
 * Padrões de projeto utilizados:
 * - Singleton: Garante uma única instância do banco
 * - Repository: Atua como repositório para as contas bancárias
 */
public class Banco {
    // Instância única do Banco (parte do padrão Singleton)
    private static Banco instance;

    // Nome do banco
    private String nome;

    // Lista de contas bancárias
    private List<Conta> contas;

    /**
     * Construtor privado (parte do padrão Singleton)
     * @param nome Nome do banco
     */
    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    /**
     * Método estático para obter a instância única (Singleton)
     * @return Instância do Banco
     */
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
     * Lista todas as contas do banco com seus principais dados
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

    /**
     * Retorna o nome do banco
     * @return Nome do banco
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do banco
     * @param nome Novo nome do banco
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a lista de contas do banco
     * @return Lista de contas
     */
    public List<Conta> getContas() {
        return contas;
    }
}
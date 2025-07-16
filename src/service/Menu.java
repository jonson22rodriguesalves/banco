package service;

import java.util.Scanner;

/**
 * Classe Menu - Responsável pela interação com o usuário e controle dos fluxos do sistema bancário
 *
 * Atributos:
 * - scanner: Scanner - Objeto para leitura de inputs do usuário (privado e estático)
 * - banco: Banco - Instância única do banco para operações (privado e estático)
 *
 * Métodos Públicos:
 * - exibirMenuInicial(): void - Exibe o menu principal e gerencia as opções selecionadas
 *
 * Métodos Privados:
 * - exibirMenuTipoConta(): void - Menu para seleção do tipo de conta a ser criada
 * - criarContaCorrente(): void - Fluxo de criação de conta corrente
 * - criarContaPoupanca(): void - Fluxo de criação de conta poupança
 * - acessarContaExistente(): void - Fluxo para acessar uma conta existente
 * - exibirMenuOperacoes(Conta conta): void - Menu de operações para conta específica
 * - realizarDeposito(Conta conta): void - Executa operação de depósito
 * - realizarSaque(Conta conta): void - Executa operação de saque
 * - realizarPagamento(Conta conta): void - Executa operação de pagamento
 * - removerConta(): void - Fluxo para remoção de conta
 *
 * Padrões de projeto utilizados:
 * - Singleton: Para a instância do Banco
 * - MVC: Esta classe atua como controlador na interação com o usuário
 */
public class Menu {
    // Scanner para leitura de inputs do usuário
    private static Scanner scanner = new Scanner(System.in);

    // Instância única do banco (Singleton)
    private static Banco banco = Banco.getInstance();

    /**
     * Exibe e gerencia o menu principal do sistema
     * Contém loop até que usuário selecione sair (opção 0)
     */
    public static void exibirMenuInicial() {
        int opcao;
        do {
            System.out.println("\n=== MENU INICIAL ===");
            System.out.println("1 - Criar nova conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Excluir conta");
            System.out.println("4 - Acessar conta existente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    exibirMenuTipoConta();
                    break;
                case 2:
                    banco.listarContas();
                    break;
                case 3:
                    removerConta();
                    break;
                case 4:
                    acessarContaExistente();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Exibe menu para seleção do tipo de conta a ser criada
     * Direciona para o fluxo específico de criação
     */
    private static void exibirMenuTipoConta() {
        System.out.println("\n=== TIPO DE CONTA ===");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                criarContaCorrente();
                break;
            case 2:
                criarContaPoupanca();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Fluxo completo para criação de nova conta corrente
     * Valida existência da conta antes de criar
     */
    private static void criarContaCorrente() {
        System.out.println("\n=== NOVA CONTA CORRENTE ===");
        System.out.print("Número da conta (somente números): ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        String numeroCompleto = " cc " + numero;

        if (banco.buscarConta(numeroCompleto) != null) {
            System.out.println("Conta já existe!");
            return;
        }

        System.out.print("Agência: ");
        String agencia = scanner.nextLine();

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Depósito inicial: ");
        double deposito = scanner.nextDouble();

        ContaCorrente novaConta = new ContaCorrente(numeroCompleto, numero, nome, deposito, agencia);
        banco.adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    /**
     * Fluxo completo para criação de nova conta poupança
     * Valida existência da conta antes de criar
     */
    private static void criarContaPoupanca() {
        System.out.println("\n=== NOVA CONTA POUPANÇA ===");
        System.out.print("Número da conta (somente números): ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        String numeroCompleto = " cp " + numero;

        if (banco.buscarConta(numeroCompleto) != null) {
            System.out.println("Conta já existe!");
            return;
        }

        System.out.print("Agência: ");
        String agencia = scanner.nextLine();

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Depósito inicial: ");
        double deposito = scanner.nextDouble();

        ContaPoupanca novaConta = new ContaPoupanca(numeroCompleto, numero, nome, deposito, agencia);
        banco.adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    /**
     * Fluxo para acesso a conta existente
     * Busca conta no banco e, se encontrada, exibe menu de operações
     */
    private static void acessarContaExistente() {
        System.out.print("\nDigite o número da conta (com prefixo cc/cp): ");
        String numero = scanner.nextLine();

        Conta conta = banco.buscarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        exibirMenuOperacoes(conta);
    }

    /**
     * Exibe e gerencia menu de operações para uma conta específica
     * @param conta Conta a ser operada
     */
    private static void exibirMenuOperacoes(Conta conta) {
        int opcao;
        do {
            System.out.println("\n=== MENU DE OPERAÇÕES ===");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Realizar pagamento");

            if (conta.getTipoConta().equals("cc")) {
                System.out.println("5 - Consultar cheque especial");
            } else {
                System.out.println("5 - Aplicar rendimento");
            }

            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    conta.consultarSaldo();
                    break;
                case 2:
                    realizarDeposito(conta);
                    break;
                case 3:
                    realizarSaque(conta);
                    break;
                case 4:
                    realizarPagamento(conta);
                    break;
                case 5:
                    if (conta.getTipoConta().equals("cc")) {
                        ((ContaCorrente) conta).consultarLimiteChequeEspecial();
                    } else {
                        ((ContaPoupanca) conta).aplicarRendimento();
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (true);
    }

    /**
     * Realiza operação de depósito na conta especificada
     * @param conta Conta que receberá o depósito
     */
    private static void realizarDeposito(Conta conta) {
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        conta.realizarDeposito(valor);
    }

    /**
     * Realiza operação de saque na conta especificada
     * @param conta Conta de onde será debitado o valor
     */
    private static void realizarSaque(Conta conta) {
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        conta.realizarSaque(valor);
    }

    /**
     * Realiza operação de pagamento na conta especificada
     * @param conta Conta de onde será debitado o valor
     */
    private static void realizarPagamento(Conta conta) {
        System.out.print("Valor do pagamento: ");
        double valor = scanner.nextDouble();
        conta.realizarPagamento(valor);
    }

    /**
     * Fluxo completo para remoção de conta
     * Busca conta no banco e, se encontrada, solicita sua remoção
     */
    private static void removerConta() {
        System.out.print("\nNúmero da conta a remover (com prefixo): ");
        String numero = scanner.nextLine();

        Conta conta = banco.buscarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        if (banco.removerConta(conta)) {
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Falha ao remover conta!");
        }
    }
}
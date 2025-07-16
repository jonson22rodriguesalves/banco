# Santander Boot Camp 2025

* 🏦 Banco Digital 
* 🎬 Sistema de Gerenciamento Bancário
* Sistema Java que simula um banco digital com contas corrente e poupança, implementando:
* Criação e exclusão de contas bancárias
* Operações financeiras básicas (depósito, saque, pagamento)
* Cheque especial com cálculo de juros
* Rendimento para conta poupança
* Menu hierárquico interativo para todas as funcionalidades
* Gerenciamento de estado para cada conta

* 🛠️ Tecnologias Utilizadas
* Java 17+
* Scanner para entrada de dados
* Programação orientada a objetos
* Padrão Singleton para instância única do banco
* Encapsulamento de propriedades
* Menu interativo com switch-case aninhados
* Cálculo de juros diários para cheque especial

* 📚 Pré-requisitos
* Java JDK 17 ou superior
* Conhecimento básico de compilação Java
* Terminal/Console para execução

* 🚀 Como Executar
* Compile o programa:
* bash
* javac Main.java
* Execute o programa:
* bash
* java Main
* Siga o fluxo interativo:

* === MENU INICIAL ===
1. Criar nova conta
2. Listar contas
3. Excluir conta
4. Acessar conta existente
0. Sair
* Escolha uma opção:

* 🎯 Funcionalidades Implementadas
* Conta Corrente
✔️ Depósitos e saques
✔️ Pagamentos
✔️ Cheque especial com juros diários (20% ao dia)
✔️ Cálculo automático de limite (20-50% do saldo)

* Conta Poupança
✔️ Depósitos e saques
✔️ Rendimento mensal (0.5% ao mês)
✔️ Aplicação automática de rendimentos

* Sistema Bancário
✔️ Cadastro de contas (corrente/poupança)
✔️ Listagem completa de contas
✔️ Exclusão de contas
✔️ Persistência em memória durante execução

* 📝 Exemplo de Uso
 
* === MENU INICIAL ===
* Escolha uma opção: 1
 
* === TIPO DE CONTA ===
1. Conta Corrente
2. Conta Poupança
0. Voltar
 
* Escolha: 1
* Número da conta: 123
* Agência: 001
* Nome: João Silva
* Depósito inicial: 1000.00
* Conta criada com sucesso!

* === MENU OPERAÇÕES ===
1. Consultar saldo
2. Depositar
3. Sacar
4. Realizar pagamento
5. Consultar cheque especial
0. Voltar
 
* Escolha: 5
* Limite total: R$ 500.00
* Utilizado: R$ 0.00
* Disponível: R$ 500.00

* ⚠️ Importante
* O sistema inicia sem contas cadastradas
* Dados persistem apenas durante a execução
* Validações básicas de saldo são implementadas
* Juros do cheque especial são calculados diariamente
  
````mermaid
classDiagram
    direction TB
    
    class Banco {
        -nome: String
        -contas: List~Conta~
        +getInstance() Banco
        +adicionarConta(Conta)
        +removerConta(Conta) boolean
        +buscarConta(String) Conta
        +listarContas()
    }

    class Conta {
        <<interface>>
        +getNumeroContaCompleto() String
        +getTipoConta() String
        +getNomeCliente() String
        +getSaldo() double
        +consultarSaldo()
        +realizarDeposito(double)
        +realizarSaque(double)
        +realizarPagamento(double)
    }

    class ContaCorrente {
        -numeroContaCompleto: String
        -saldo: double
        -limiteChequeEspecial: double
        -jurosChequeEspecial: Juros
        +consultarLimiteChequeEspecial()
        +calcularLimiteChequeEspecial(double)
    }

    class ContaPoupanca {
        -numeroContaCompleto: String
        -saldo: double
        -taxaRendimento: double
        +aplicarRendimento()
    }

    class Juros {
        -dataInicioUtilizacao: LocalDate
        -valorUtilizado: double
        +calcularJuros() double
        +registrarUtilizacao(double)
        +reduzirUtilizacao(double)
    }

    class Menu {
        -scanner: Scanner
        -banco: Banco
        +exibirMenuInicial()
        +exibirMenuTipoConta()
        +exibirMenuOperacoes(Conta)
    }

    Banco "1" *-- "*" Conta
    Conta <|-- ContaCorrente
    Conta <|-- ContaPoupanca
    ContaCorrente *-- Juros
    Menu --> Banco
    Menu --> Conta
 
````
 
* 🔄 Fluxograma Básico

[Início]

↓

[Menu Principal]

↓

├─ 1. Criar Conta → Submenu de Tipo de Conta

├─ 2. Listar Contas → Exibe todas as contas

├─ 3. Excluir Conta → Remove conta existente

├─ 4. Acessar Conta → Submenu de Operações

└─ 0. Sair → Encerra programa

Submenu de Operações:

[Menu Operações]

↓

├─ 1. Consultar Saldo

├─ 2. Depositar

├─ 3. Sacar

├─ 4. Pagamento

├─ 5. Cheque Especial/Rendimento

└─ 0. Voltar
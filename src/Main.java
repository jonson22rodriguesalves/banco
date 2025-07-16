import service.Menu;

/**
 * Classe Main - Ponto de entrada do programa
 *
 * Atributos:
 * Nenhum atributo declarado - esta classe serve apenas como ponto de entrada
 *
 * Métodos:
 * - main(String[] args): Método estático que inicia a execução do programa
 *   Parâmetros: args - argumentos de linha de comando (não utilizado neste caso)
 *   Retorno: void
 *   Funcionalidade: Chama o método exibirMenuInicial() da classe Menu para iniciar a aplicação
 *
 * Padrão de projeto utilizado:
 * - Esta classe segue o padrão de delegar a lógica principal para outras classes (Menu),
 *   mantendo o ponto de entrada simples e desacoplado
 */
public class Main {
    /**
     * Método principal que inicia a aplicação
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Delega a exibição do menu inicial para a classe Menu
        Menu.exibirMenuInicial();
    }
}
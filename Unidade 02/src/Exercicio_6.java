
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Solução para o Exercício 6 da Unidade 02 de Algoritmos e Estrutura de Dados.
 *
 * Descrição: Crie um sistema que simule uma fila de espera de pacientes em uma
 * clínica, permitindo adicionar, remover e exibir a ordem de atendimento.
 *
 * "Além da solução padrão solicitada, criarei também uma versão
 * `Exercicio_6_Dialogo.java` com interface gráfica (JOptionPane) para
 * demonstrar a aplicação do algoritmo em um contexto com interação visual com o
 * usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_6 {

    // Scanner e Fila declarados como estáticos para serem acessíveis em toda a classe.
    private static final Scanner scanner = new Scanner(System.in);
    private static final Queue<String> filaDePacientes = new LinkedList<>();

    public static void main(String[] args) {
        boolean executando = true;

        // Loop principal do menu. Continua executando até que o usuário escolha sair.
        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarPaciente();
                    break;
                case 2:
                    chamarProximo();
                    break;
                case 3:
                    exibirFila();
                    break;
                case 4:
                    executando = false;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, escolha um número entre 1 e 4.");
            }
        }

        System.out.println("\n=================================================");
        System.out.println("   Sistema de Fila da Clínica Finalizado");
        System.out.println("=================================================");
        System.out.println();
        scanner.close(); // Fecha o scanner para liberar recursos.
    }

    /**
     * Exibe o menu de opções formatado no console.
     */
    private static void exibirMenu() {
        System.out.println("\n=================================================");
        System.out.println("      Sistema de Fila de Espera da Clínica");
        System.out.println("=================================================");
        System.out.println("1. Adicionar novo paciente à fila");
        System.out.println("2. Chamar próximo paciente para atendimento");
        System.out.println("3. Exibir fila de pacientes atual");
        System.out.println("4. Sair do sistema");
        System.out.println("=================================================");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Lê e valida a entrada numérica do usuário para a opção do menu.
     *
     * @return O número da opção escolhida, ou -1 se a entrada for inválida.
     */
    private static int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha pendente.
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println("\nErro: Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpa o buffer do scanner para a próxima leitura.
            return -1; // Retorna um valor inválido para repetir o loop.
        }
    }

    /**
     * Solicita o nome de um paciente e o adiciona ao final da fila.
     */
    private static void adicionarPaciente() {
        System.out.print("\nDigite o nome completo do paciente: ");
        String nome = scanner.nextLine();
        filaDePacientes.add(nome); // Adiciona o paciente ao FINAL da fila.
        System.out.println("Paciente \"" + nome + "\" foi adicionado à fila com sucesso.");
    }

    /**
     * Remove e exibe o próximo paciente do início da fila.
     */
    private static void chamarProximo() {
        if (filaDePacientes.isEmpty()) {
            System.out.println("\nA fila de espera está vazia. Não há pacientes para chamar.");
        } else {
            // O método poll() remove e retorna o elemento no INÍCIO da fila.
            String proximo = filaDePacientes.poll();
            System.out.println("\nPróximo paciente chamado para atendimento: \"" + proximo + "\"");
        }
    }

    /**
     * Exibe todos os pacientes na fila na ordem de chegada, sem removê-los.
     */
    private static void exibirFila() {
        System.out.println("\n----------- FILA DE ESPERA ATUAL -----------");
        if (filaDePacientes.isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            int posicao = 1;
            // Itera sobre a fila para mostrar cada paciente e sua posição.
            for (String paciente : filaDePacientes) {
                System.out.println(posicao + ". " + paciente);
                posicao++;
            }
        }
        System.out.println("--------------------------------------------");
    }
}

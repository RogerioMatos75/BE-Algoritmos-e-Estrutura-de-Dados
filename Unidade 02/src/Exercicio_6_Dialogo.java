import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Queue;

public class Exercicio_6_Dialogo {

    // Fila declarada como estática para ser acessível em toda a classe.
    private static final Queue<String> filaDePacientes = new LinkedList<>();

    public static void main(String[] args) {
        boolean executando = true;

        // Loop principal do menu. Continua executando até que o usuário escolha sair.
        while (executando) {
            int opcao = exibirMenuEObterOpcao();

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
                case -1: // Caso o usuário feche a caixa de diálogo do menu
                    executando = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha um número entre 1 e 4.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema de Fila da Clínica Finalizado.", "Fim", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe o menu de opções e lê a entrada do usuário usando JOptionPane.
     *
     * @return O número da opção escolhida, ou -1 se a entrada for inválida ou cancelada.
     */
    private static int exibirMenuEObterOpcao() {
        String menu = "========================================\n" +
                      "      Sistema de Fila de Espera da Clínica  \n" +
                      "========================================\n" +
                      "1. Adicionar novo paciente à fila  \n" +
                      "2. Chamar próximo paciente para atendimento  \n" +
                      "3. Exibir fila de pacientes atual  \n" +
                      "4. Sair do sistema  \n" +
                      "========================================\n" +
                      "Escolha uma opção:";

        String input = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.PLAIN_MESSAGE);

        if (input == null) {
            return -1; // Usuário fechou a caixa de diálogo
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Por favor, digite um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            return 0; // Retorna um valor que cairá no 'default' do switch
        }
    }

    /**
     * Solicita o nome de um paciente e o adiciona ao final da fila.
     */
    private static void adicionarPaciente() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome completo do paciente:", "Adicionar Paciente", JOptionPane.QUESTION_MESSAGE);
        
        if (nome != null && !nome.trim().isEmpty()) {
            filaDePacientes.add(nome.trim()); // Adiciona o paciente ao FINAL da fila.
            JOptionPane.showMessageDialog(null, "Paciente \"" + nome.trim() + "\" foi adicionado à fila com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else if (nome != null) { // Apenas mostra o erro se o usuário não clicou em 'cancelar'
            JOptionPane.showMessageDialog(null, "O nome do paciente não pode ser vazio.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Remove e exibe o próximo paciente do início da fila.
     */
    private static void chamarProximo() {
        if (filaDePacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A fila de espera está vazia. Não há pacientes para chamar.", "Fila Vazia", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // O método poll() remove e retorna o elemento no INÍCIO da fila.
            String proximo = filaDePacientes.poll();
            JOptionPane.showMessageDialog(null, "Próximo paciente chamado para atendimento: \n\n" + proximo, "Chamando Paciente", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Exibe todos os pacientes na fila na ordem de chegada, sem removê-los.
     */
    private static void exibirFila() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------- FILA DE ESPERA ATUAL -----------\n\n");

        if (filaDePacientes.isEmpty()) {
            sb.append("A fila está vazia.");
        } else {
            int posicao = 1;
            // Itera sobre a fila para mostrar cada paciente e sua posição.
            for (String paciente : filaDePacientes) {
                sb.append(posicao).append(". ").append(paciente).append("\n");
                posicao++;
            }
        }
        
        sb.append("\n--------------------------------------------");

        JOptionPane.showMessageDialog(null, sb.toString(), "Fila de Pacientes", JOptionPane.PLAIN_MESSAGE);
    }
}

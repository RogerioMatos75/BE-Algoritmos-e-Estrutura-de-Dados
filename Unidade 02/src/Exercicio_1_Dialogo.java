import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Solução para o Exercício 1 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um programa que inverta os elementos de uma lista de
 * tarefas (ArrayList) sem utilizar métodos prontos da linguagem, dando ao
 * usuário a opção de ver a lista do fim para o começo.
 *
 * Autor: Rogério
 */
public class Exercicio_1_Dialogo {

    public static void main(String[] args) {
        // 1. Criamos uma lista de tarefas original.
        List<String> tarefas = new ArrayList<>();
        tarefas.add("Estudar Algoritmos");
        tarefas.add("Fazer o exercício 1");
        tarefas.add("Preparar o jantar");
        tarefas.add("Ler um livro");
        tarefas.add("Assistir a uma aula");

        // 2. Formatamos a lista original para exibição em HTML.
        StringBuilder mensagemOriginal = new StringBuilder("<html><body>");
        mensagemOriginal.append("<h2>App de Tarefas - Lista Original</h2><hr>");
        mensagemOriginal.append("<ul>");
        for (String tarefa : tarefas) {
            mensagemOriginal.append("<li>").append(tarefa).append("</li>");
        }
        mensagemOriginal.append("</ul></body></html>");

        // 3. Exibimos a lista original em uma caixa de diálogo.
        JOptionPane.showMessageDialog(null, mensagemOriginal.toString(), "Lista de Tarefas", JOptionPane.INFORMATION_MESSAGE);

        // 4. Perguntamos ao usuário se ele deseja inverter a lista.
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja ver a lista de tarefas invertida?", "Confirmar Ação", JOptionPane.YES_NO_OPTION);

        // 5. Se a resposta for "Sim", invertemos e exibimos a lista invertida.
        if (resposta == JOptionPane.YES_OPTION) {
            // Chamamos o método para inverter a lista.
            List<String> tarefasInvertidas = inverterLista(tarefas);

            // Formatamos a lista invertida para exibição em HTML.
            StringBuilder mensagemInvertida = new StringBuilder("<html><body>");
            mensagemInvertida.append("<h2>App de Tarefas - Lista Invertida</h2><hr>");
            mensagemInvertida.append("<ul>");
            for (String tarefa : tarefasInvertidas) {
                mensagemInvertida.append("<li>").append(tarefa).append("</li>");
            }
            mensagemInvertida.append("</ul></body></html>");

            // Exibimos a nova lista, agora invertida.
            JOptionPane.showMessageDialog(null, mensagemInvertida.toString(), "Lista de Tarefas Invertida", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Inverte uma lista de Strings criando uma nova lista.
     * 
     * @param listaOriginal A lista a ser invertida.
     * @return Uma nova lista contendo os elementos da lista original em ordem
     *         inversa.
     */
    public static List<String> inverterLista(List<String> listaOriginal) {
        List<String> listaInvertida = new ArrayList<>();
        for (int i = listaOriginal.size() - 1; i >= 0; i--) {
            listaInvertida.add(listaOriginal.get(i));
        }
        return listaInvertida;
    }
}

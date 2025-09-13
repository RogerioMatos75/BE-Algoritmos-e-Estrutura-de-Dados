import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Demonstração do Exercício 2 com saída em uma caixa de diálogo (JOptionPane).
 * 
 * Autor: Rogério
 */
public class Exercicio_2_Dialogo {

    public static void main(String[] args) {
        // 1. Criamos a lista de contatos com duplicatas.
        List<String> contatos = new ArrayList<>();
        contatos.add("Ana");
        contatos.add("Bruno");
        contatos.add("Ana");
        contatos.add("Carlos");
        contatos.add("Daniela");
        contatos.add("Bruno");
        contatos.add("Ana");

        // 2. Chamamos o método para remover os elementos duplicados.
        List<String> contatosSemDuplicatas = removerDuplicatas(contatos);

        // 3. Preparamos a mensagem para ser exibida na caixa de diálogo.
        // Usamos um StringBuilder para construir a String de forma eficiente.
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("<html><body>"); // Usar HTML permite mais formatação, como quebras de linha.
        mensagem.append("<b>Lista de Contatos Original:</b><br>");
        mensagem.append(contatos.toString()).append("<br><br>");
        mensagem.append("<b>Lista Corrigida (sem duplicatas):</b><br>");
        for (String contato : contatosSemDuplicatas) {
            mensagem.append("- ").append(contato).append("<br>");
        }
        mensagem.append("</body></html>");

        // 4. Exibimos a caixa de diálogo.
        // O programa ficará em pausa até que o usuário clique em "OK".
        JOptionPane.showMessageDialog(
            null, // O componente pai, null para centralizar na tela.
            mensagem.toString(), // A mensagem que construímos.
            "Sistema de Contatos - Resultado", // O título da janela.
            JOptionPane.INFORMATION_MESSAGE // O ícone a ser exibido (neste caso, um 'i' de informação).
        );
    }

    /**
     * Remove elementos duplicados de uma lista de Strings usando iteração manual.
     *
     * @param listaOriginal A lista contendo possíveis duplicatas.
     * @return Uma nova lista contendo apenas elementos únicos, na ordem em que
     *         apareceram pela primeira vez.
     */
    public static List<String> removerDuplicatas(List<String> listaOriginal) {
        List<String> listaSemDuplicatas = new ArrayList<>();
        for (String elemento : listaOriginal) {
            if (!listaSemDuplicatas.contains(elemento)) {
                listaSemDuplicatas.add(elemento);
            }
        }
        return listaSemDuplicatas;
    }
}

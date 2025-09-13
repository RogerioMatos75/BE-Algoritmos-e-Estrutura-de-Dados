import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Exercicio_3_Dialogo {

    public static void main(String[] args) {
        // 1. Criamos as duas listas de convidados.
        List<String> convidadosLista1 = new ArrayList<>();
        convidadosLista1.add("Carlos");
        convidadosLista1.add("Sandra");
        convidadosLista1.add("Roberto");

        List<String> convidadosLista2 = new ArrayList<>();
        convidadosLista2.add("Mariana");
        convidadosLista2.add("Felipe");
        convidadosLista2.add("Juliana");
        convidadosLista2.add("Ricardo");
        convidadosLista2.add("Patrícia");

        // 2. Formatamos e exibimos as listas originais em uma única caixa de diálogo.
        StringBuilder mensagemOriginais = new StringBuilder("<html><body>");
        mensagemOriginais.append("<h2>Listas Originais de Convidados</h2><hr>");
        
        mensagemOriginais.append("<h3>Lista 1:</h3><ul>");
        for (String convidado : convidadosLista1) {
            mensagemOriginais.append("<li>").append(convidado).append("</li>");
        }
        mensagemOriginais.append("</ul>");

        mensagemOriginais.append("<h3>Lista 2:</h3><ul>");
        for (String convidado : convidadosLista2) {
            mensagemOriginais.append("<li>").append(convidado).append("</li>");
        }
        mensagemOriginais.append("</ul></body></html>");

        JOptionPane.showMessageDialog(null, mensagemOriginais.toString(), "Listas de Convidados", JOptionPane.INFORMATION_MESSAGE);

        // 3. Chamamos o método para intercalar as listas.
        List<String> listaFinal = intercalarListas(convidadosLista1, convidadosLista2);

        // 4. Formatamos e exibimos a lista final intercalada.
        StringBuilder mensagemFinal = new StringBuilder("<html><body>");
        mensagemFinal.append("<h2>Lista Final de Convidados (Intercalada)</h2><hr>");
        mensagemFinal.append("<ul>");
        for (String convidado : listaFinal) {
            mensagemFinal.append("<li>").append(convidado).append("</li>");
        }
        mensagemFinal.append("</ul></body></html>");

        JOptionPane.showMessageDialog(null, mensagemFinal.toString(), "Resultado da Intercalação", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Intercala os elementos de duas listas em uma nova lista.
     *
     * A intercalação ocorre adicionando um elemento da lista1, seguido por um da
     * lista2, até que todos os elementos de ambas as listas tenham sido adicionados.
     *
     * @param lista1 A primeira lista de convidados.
     * @param lista2 A segunda lista de convidados.
     * @return Uma nova lista com os elementos de lista1 e lista2 intercalados.
     */
    public static List<String> intercalarListas(List<String> lista1, List<String> lista2) {
        List<String> listaIntercalada = new ArrayList<>();
        int tamanhoMaximo = Math.max(lista1.size(), lista2.size());

        for (int i = 0; i < tamanhoMaximo; i++) {
            // Adiciona o elemento da primeira lista, se ainda houver.
            if (i < lista1.size()) {
                listaIntercalada.add(lista1.get(i));
            }
            // Adiciona o elemento da segunda lista, se ainda houver.
            if (i < lista2.size()) {
                listaIntercalada.add(lista2.get(i));
            }
        }

        return listaIntercalada;
    }
}

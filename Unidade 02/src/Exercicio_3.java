import java.util.ArrayList;
import java.util.List;

/**
 * Solução para o Exercício 3 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Implemente uma função que receba duas listas de convidados e
 * retorne uma nova lista com os elementos intercalados.
 * 
 * "Além da solução padrão solicitada, criei também uma versão `Exercicio_3_Dialogo.java` com interface
 * gráfica (JOptionPane) para demonstrar a aplicação do algoritmo em um contexto com interação visual
 * com o usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_3 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial

        // 1. Criamos as duas listas de convidados.
        // A Lista 2 é intencionalmente maior para testar a lógica.
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

        // 2. Exibimos as listas originais.
        System.out.println("===========================================");
        System.out.println("         Lista de Convidados 1");
        System.out.println("===========================================");
        for (String convidado : convidadosLista1) {
            System.out.println("- " + convidado);
        }
        System.out.println("===========================================");
        System.out.println();

        System.out.println("===========================================");
        System.out.println("         Lista de Convidados 2");
        System.out.println("===========================================");
        for (String convidado : convidadosLista2) {
            System.out.println("- " + convidado);
        }
        System.out.println("===========================================");
        System.out.println();

        // 3. Chamamos o método para intercalar as listas.
        List<String> listaFinal = intercalarListas(convidadosLista1, convidadosLista2);

        // 4. Exibimos a lista final intercalada.
        System.out.println("===========================================");
        System.out.println("       Lista Final de Convidados");
        System.out.println("           (Listas Intercaladas)");
        System.out.println("===========================================");
        for (String convidado : listaFinal) {
            System.out.println("- " + convidado);
        }
        System.out.println("===========================================");
        System.out.println();
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

import java.util.ArrayList;
import java.util.List;

/**
 * Solução para o Exercício 10 da Unidade 02 de Algoritmos e Estrutura de Dados.
 *
 * Descrição: Implementa um algoritmo de ordenação (Merge Sort) para classificar
 * uma lista de produtos com base no número de vendas, em ordem decrescente.
 *
 * "Além da solução padrão solicitada, criarei também uma versão
 * `Exercicio_10_Dialogo.java` com interface gráfica (JOptionPane) para
 * demonstrar a aplicação do algoritmo."
 *
 * Autor: Rogério
 */
public class Exercicio_10 {

    /**
     * Classe interna para representar um Produto com nome e número de vendas.
     */
    static class Produto {
        String nome;
        int vendas;

        public Produto(String nome, int vendas) {
            this.nome = nome;
            this.vendas = vendas;
        }

        @Override
        public String toString() {
            return String.format("%20s | Vendas: %d", nome, vendas);
        }
    }

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial

        // 1. Cria uma lista de produtos desordenada.
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Smart TV 50\"", 120));
        produtos.add(new Produto("Notebook Gamer", 85));
        produtos.add(new Produto("Smartphone Pro", 350));
        produtos.add(new Produto("Cadeira de Escritório", 210));
        produtos.add(new Produto("Fone Bluetooth", 400));
        produtos.add(new Produto("Teclado Mecânico", 150));

        System.out.println("========================================================");
        System.out.println("      Sistema de Classificação de Produtos Populares");
        System.out.println("========================================================");
        System.out.println();

        // 2. Exibe a lista original.
        exibirLista("Lista de Produtos Original (Desordenada)", produtos);

        // 3. Ordena a lista usando Merge Sort.
        List<Produto> produtosOrdenados = mergeSort(produtos);

        // 4. Exibe a lista ordenada.
        exibirLista("Produtos Mais Populares (Ordenados por Vendas)", produtosOrdenados);

        System.out.println("========================================================");
        System.out.println("               Classificação Concluída");
        System.out.println("========================================================");
        System.out.println();
    }

    /**
     * Ordena uma lista de Produtos usando o algoritmo Merge Sort (recursivo).
     *
     * @param lista A lista a ser ordenada.
     * @return Uma nova lista ordenada em ordem decrescente de vendas.
     */
    public static List<Produto> mergeSort(List<Produto> lista) {
        // Caso base: se a lista tem 1 ou 0 elementos, já está ordenada.
        if (lista.size() <= 1) {
            return lista;
        }

        // Divide a lista em duas metades.
        int meio = lista.size() / 2;
        List<Produto> metadeEsquerda = new ArrayList<>(lista.subList(0, meio));
        List<Produto> metadeDireita = new ArrayList<>(lista.subList(meio, lista.size()));

        // Chama recursivamente o mergeSort para cada metade.
        metadeEsquerda = mergeSort(metadeEsquerda);
        metadeDireita = mergeSort(metadeDireita);

        // Combina (merge) as duas metades ordenadas.
        return merge(metadeEsquerda, metadeDireita);
    }

    /**
     * Combina duas listas já ordenadas em uma única lista ordenada.
     *
     * @param esquerda Lista da esquerda, já ordenada.
     * @param direita  Lista da direita, já ordenada.
     * @return A lista combinada e totalmente ordenada.
     */
    private static List<Produto> merge(List<Produto> esquerda, List<Produto> direita) {
        List<Produto> resultado = new ArrayList<>();
        int i = 0, j = 0; // Ponteiros para as listas da esquerda e direita

        // Compara os elementos das duas listas e adiciona o maior ao resultado.
        while (i < esquerda.size() && j < direita.size()) {
            // Para ordem DECRESCENTE, comparamos se o da esquerda é MAIOR que o da direita.
            if (esquerda.get(i).vendas >= direita.get(j).vendas) {
                resultado.add(esquerda.get(i));
                i++;
            } else {
                resultado.add(direita.get(j));
                j++;
            }
        }

        // Adiciona os elementos restantes de qualquer uma das listas (se houver).
        while (i < esquerda.size()) {
            resultado.add(esquerda.get(i));
            i++;
        }
        while (j < direita.size()) {
            resultado.add(direita.get(j));
            j++;
        }

        return resultado;
    }

    /**
     * Método auxiliar para exibir a lista de produtos de forma formatada.
     */
    private static void exibirLista(String titulo, List<Produto> produtos) {
        System.out.println("--------------------------------------------------------");
        System.out.println("            " + titulo);
        System.out.println("--------------------------------------------------------");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }
}

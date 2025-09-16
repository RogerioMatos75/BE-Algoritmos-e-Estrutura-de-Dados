import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 * Solução com interface gráfica para o Exercício 10 da Unidade 02.
 *
 * Descrição: Demonstra a ordenação de uma lista de produtos (usando Merge Sort)
 * e exibe o resultado em caixas de diálogo. Este arquivo é autônomo e não
 * depende de outras classes do projeto.
 *
 * Autor: Rogério
 */
public class Exercicio_10_Dialogo {

    // --- Lógica e Estruturas de Dados (agora contidas neste arquivo) ---

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
            return String.format("%-20s | Vendas: %d", nome, vendas);
        }
    }

    /**
     * Ordena uma lista de Produtos usando o algoritmo Merge Sort (recursivo).
     */
    public static List<Produto> mergeSort(List<Produto> lista) {
        if (lista.size() <= 1) {
            return lista;
        }
        int meio = lista.size() / 2;
        List<Produto> metadeEsquerda = new ArrayList<>(lista.subList(0, meio));
        List<Produto> metadeDireita = new ArrayList<>(lista.subList(meio, lista.size()));
        metadeEsquerda = mergeSort(metadeEsquerda);
        metadeDireita = mergeSort(metadeDireita);
        return merge(metadeEsquerda, metadeDireita);
    }

    /**
     * Combina duas listas já ordenadas em uma única lista ordenada.
     */
    private static List<Produto> merge(List<Produto> esquerda, List<Produto> direita) {
        List<Produto> resultado = new ArrayList<>();
        int i = 0, j = 0;
        while (i < esquerda.size() && j < direita.size()) {
            if (esquerda.get(i).vendas >= direita.get(j).vendas) {
                resultado.add(esquerda.get(i));
                i++;
            } else {
                resultado.add(direita.get(j));
                j++;
            }
        }
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
     * Formata uma lista de produtos para uma String legível em caixa de diálogo.
     */
    private static String formatarLista(String titulo, List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(titulo).append(" ---\n\n");
        if (produtos.isEmpty()) {
            sb.append("A lista de produtos está vazia.");
        } else {
            for (Produto produto : produtos) {
                sb.append(produto.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // --- Interface Gráfica (main) ---

    public static void main(String[] args) {
        // 1. Cria uma lista de produtos desordenada (pré-definida).
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Smart TV 50\"", 120));
        produtos.add(new Produto("Notebook Gamer", 85));
        produtos.add(new Produto("Smartphone Pro", 350));
        produtos.add(new Produto("Cadeira de Escritório", 210));
        produtos.add(new Produto("Fone Bluetooth", 400));
        produtos.add(new Produto("Teclado Mecânico", 150));

        // 2. Exibição da lista original.
        JOptionPane.showMessageDialog(null,
                "Vamos ordenar uma lista de produtos por popularidade (vendas).\nClique em OK para ver a lista original.",
                "Sistema de Classificação de Produtos",
                JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null,
                formatarLista("Lista de Produtos Original (Desordenada)", produtos),
                "Lista Original",
                JOptionPane.PLAIN_MESSAGE);

        // 3. Ordena a lista chamando o método local.
        List<Produto> produtosOrdenados = mergeSort(produtos);

        // 4. Exibição da lista ordenada.
        JOptionPane.showMessageDialog(null,
                "Agora, veja a lista ordenada com os produtos mais vendidos no topo.",
                "Resultado da Ordenação",
                JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null,
                formatarLista("Produtos Mais Populares (Ordenados por Vendas)", produtosOrdenados),
                "Lista Ordenada",
                JOptionPane.PLAIN_MESSAGE);

        // 5. Mensagem de finalização.
        JOptionPane.showMessageDialog(null,
                "Classificação concluída! Todos os exercícios foram finalizados.",
                "Fim da Sessão",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
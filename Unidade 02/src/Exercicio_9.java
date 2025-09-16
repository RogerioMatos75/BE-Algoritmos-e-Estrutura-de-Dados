import java.util.HashMap;
import java.util.Map;

/**
 * Solução para o Exercício 9 da Unidade 02 de Algoritmos e Estrutura de Dados.
 *
 * Descrição: Implemente um método para mesclar dois mapas (HashMaps) de
 * produtos, cuidando para tratar possíveis conflitos de chaves (somando os
 * valores).
 *
 * "Além da solução padrão solicitada, criarei também uma versão
 * `Exercicio_9_Dialogo.java` com interface gráfica (JOptionPane) para
 * demonstrar a aplicação do algoritmo em um contexto com interação visual com o
 * usuário."
 *
 * Autor: Rogério
 */
public class Exercicio_9 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial no terminal

        // 1. Criação dos inventários das duas lojas.
        Map<String, Integer> inventarioLojaA = new HashMap<>();
        inventarioLojaA.put("Maçã", 50);
        inventarioLojaA.put("Banana", 30);
        inventarioLojaA.put("Laranja", 25);

        Map<String, Integer> inventarioLojaB = new HashMap<>();
        inventarioLojaB.put("Banana", 40); // Conflito de chave
        inventarioLojaB.put("Pera", 20);
        inventarioLojaB.put("Maçã", 15);   // Conflito de chave

        // 2. Exibição dos inventários originais.
        System.out.println("========================================================");
        System.out.println("          Sistema de Consolidação de Inventário");
        System.out.println("========================================================");
        System.out.println();

        exibirInventario("Inventário da Loja A", inventarioLojaA);
        exibirInventario("Inventário da Loja B", inventarioLojaB);

        // 3. Mescla os dois inventários.
        Map<String, Integer> inventarioConsolidado = mesclarInventarios(inventarioLojaA, inventarioLojaB);

        // 4. Exibição do inventário consolidado.
        exibirInventario("Inventário Consolidado (Loja A + Loja B)", inventarioConsolidado);
        
        System.out.println("========================================================");
        System.out.println("               Consolidação Concluída");
        System.out.println("========================================================");
        System.out.println();
    }

    /**
     * Mescla dois mapas de inventário, somando as quantidades em caso de chaves duplicadas.
     *
     * @param inventarioA O primeiro inventário.
     * @param inventarioB O segundo inventário.
     * @return Um novo mapa contendo o inventário consolidado.
     */
    public static Map<String, Integer> mesclarInventarios(Map<String, Integer> inventarioA, Map<String, Integer> inventarioB) {
        // Cria um novo mapa já com todos os itens do primeiro inventário.
        Map<String, Integer> inventarioConsolidado = new HashMap<>(inventarioA);

        // Itera sobre o segundo inventário.
        inventarioB.forEach((produto, quantidade) -> {
            // O método merge() simplifica a lógica de conflito:
            // - Se a chave (produto) não existe no mapa consolidado, ela é inserida com sua quantidade.
            // - Se a chave já existe, a função (Integer::sum) é aplicada aos valores (antigo e novo).
            inventarioConsolidado.merge(produto, quantidade, Integer::sum);
        });

        return inventarioConsolidado;
    }

    /**
     * Método auxiliar para exibir o conteúdo de um inventário de forma formatada.
     *
     * @param titulo O título a ser exibido para o inventário.
     * @param inventario O mapa do inventário a ser exibido.
     */
    private static void exibirInventario(String titulo, Map<String, Integer> inventario) {
        System.out.println("--------------------------------------------------------");
        System.out.println("                  " + titulo);
        System.out.println("--------------------------------------------------------");
        if (inventario.isEmpty()) {
            System.out.println("Inventário vazio.");
        } else {
            inventario.forEach((produto, quantidade) -> {
                System.out.printf("- %-10s: %d unidades\n", produto, quantidade);
            });
        }
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }
}

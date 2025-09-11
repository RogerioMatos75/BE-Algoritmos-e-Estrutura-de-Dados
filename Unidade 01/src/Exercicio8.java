import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Solução para o Exercício 8 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra o item (número) mais frequente em uma lista de pedidos
 * informada pelo usuário, utilizando um HashMap para a contagem.
 */
public class Exercicio8 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Integer> listaDePedidos = new ArrayList<>();

            System.out.println();
            System.out.println("==================================================");
            System.out.println("      Analisador de Frequência de Pedidos");
            System.out.println("==================================================");
            System.out.println("\nDigite os códigos dos pedidos um por um.");
            System.out.println("Digite '0' ou um número negativo para finalizar a entrada.");
            System.out.println("--------------------------------------------------");

            int pedido;
            while (true) {
                System.out.print("Digite o código do pedido: ");
                pedido = scanner.nextInt();
                if (pedido <= 0) {
                    break;
                }
                listaDePedidos.add(pedido);
            }

            if (listaDePedidos.isEmpty()) {
                System.out.println("\nNenhum pedido foi inserido. Encerrando.");
            } else {
                System.out.println("\nLista de pedidos recebida: " + listaDePedidos);
                
                // 1. Criar o HashMap para contar a frequência
                Map<Integer, Integer> frequenciaPedidos = new HashMap<>();
                for (int p : listaDePedidos) {
                    frequenciaPedidos.put(p, frequenciaPedidos.getOrDefault(p, 0) + 1);
                }

                // 2. Encontrar o item com a maior frequência no mapa
                int itemMaisFrequente = -1;
                int maiorFrequencia = 0;
                for (Map.Entry<Integer, Integer> entry : frequenciaPedidos.entrySet()) {
                    if (entry.getValue() > maiorFrequencia) {
                        maiorFrequencia = entry.getValue();
                        itemMaisFrequente = entry.getKey();
                    }
                }

                // 3. Exibir o resultado
                System.out.println("\nAnalisando a frequência...");
                System.out.println("-> O item mais pedido foi o de código '" + itemMaisFrequente + "', que apareceu " + maiorFrequencia + " vez(es).");
            }

            System.out.println("\n==================================================");
            System.out.println();
        }
    }
}
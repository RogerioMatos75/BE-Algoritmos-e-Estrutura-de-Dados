/**
 * Solução para o Exercício 4 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Implemente um algoritmo de busca binária que encontre a posição de um
 * elemento específico em um array ordenado de inteiros, retornando sua posição
 * ou -1 se não for encontrado.
 * 
 * Autor: Rogério
 */

import java.util.Arrays;

public class Exercicio_4 {

    /**
     * Imprime um cabeçalho formatado no console.
     * @param titulo O texto do cabeçalho.
     */
    public static void imprimirCabecalho(String titulo) {
        int largura = 80;
        String linha = "=".repeat(largura);
        int padding = Math.max(0, (largura - titulo.length()) / 2);

        System.out.println(linha);
        System.out.println(" ".repeat(padding) + titulo);
        System.out.println(linha);
    }

    /**
     * Realiza uma busca binária em um array de inteiros ordenado.
     * @param array O array ordenado de inteiros onde a busca será realizada.
     * @param alvo O número a ser buscado.
     * @return O índice (posição) do alvo, ou -1 se não for encontrado.
     */
    public static int buscaBinaria(int[] array, int alvo) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; // Evita overflow para arrays muito grandes

            // Se o alvo está no meio, encontramos
            if (array[meio] == alvo) {
                return meio;
            }

            // Se o alvo é maior, ignora a metade esquerda
            if (array[meio] < alvo) {
                inicio = meio + 1;
            } 
            // Se o alvo é menor, ignora a metade direita
            else {
                fim = meio - 1;
            }
        }

        // Se o loop terminar, o alvo não está no array
        return -1;
    }

    /**
     * Método principal para demonstrar a funcionalidade da busca binária.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 4: Busca Binária em um Array Ordenado");

        int[] dadosOrdenados = { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };

        System.out.println("\nConjunto de dados ordenados: " + Arrays.toString(dadosOrdenados));
        System.out.println();

        // --- Teste 1: Número que existe no array ---
        int numeroBuscado1 = 23;
        System.out.println("Buscando pelo número: " + numeroBuscado1);
        int posicao1 = buscaBinaria(dadosOrdenados, numeroBuscado1);

        if (posicao1 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao1 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado no conjunto de dados.");
        }
        System.out.println(); // Espaçamento

        // --- Teste 2: Número que NÃO existe no array ---
        int numeroBuscado2 = 50;
        System.out.println("Buscando pelo número: " + numeroBuscado2);
        int posicao2 = buscaBinaria(dadosOrdenados, numeroBuscado2);

        if (posicao2 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao2 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado no conjunto de dados.");
        }
        System.out.println(); // Espaçamento
    }
}

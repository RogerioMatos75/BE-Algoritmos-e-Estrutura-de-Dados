/**
 * Solução para o Exercício 3 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um método que realize uma busca linear em um array de inteiros,
 * retornando a posição do número buscado ou -1 caso ele não esteja presente.
 * 
 * Autor: Rogério
 */

import java.util.Arrays;

public class Exercicio_3 {

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
     * Realiza uma busca linear em um array de inteiros.
     * Percorre o array do início ao fim, comparando cada elemento com o alvo.
     * @param array O array de inteiros onde a busca será realizada.
     * @param alvo O número a ser buscado.
     * @return O índice (posição) do primeiro elemento correspondente ao alvo, ou -1 se não for encontrado.
     */
    public static int buscaLinear(int[] array, int alvo) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == alvo) {
                return i; // Retorna a posição imediatamente ao encontrar
            }
        }
        return -1; // Retorna -1 se o loop terminar sem encontrar o alvo
    }

    /**
     * Método principal para demonstrar a funcionalidade da busca linear.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 3: Busca Linear em uma Agenda de Contatos");

        int[] agendaDeContatos = { 987654321, 912345678, 998877665, 965432109 };

        // Constrói a string da agenda com índices para clareza do usuário
        StringBuilder agendaFormatada = new StringBuilder("Agenda de Contatos: [");
        for (int i = 0; i < agendaDeContatos.length; i++) {
            agendaFormatada.append("(").append(i).append("): ").append(agendaDeContatos[i]);
            if (i < agendaDeContatos.length - 1) {
                agendaFormatada.append(", ");
            }
        }
        agendaFormatada.append("]");
        System.out.println("\n" + agendaFormatada.toString());
        System.out.println();

        // --- Teste 1: Número que existe na agenda ---
        int numeroBuscado1 = 998877665;
        System.out.println("Buscando pelo número: " + numeroBuscado1);
        int posicao1 = buscaLinear(agendaDeContatos, numeroBuscado1);

        if (posicao1 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao1 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado na agenda.");
        }
        System.out.println(); // Espaçamento

        // --- Teste 2: Número que NÃO existe na agenda ---
        int numeroBuscado2 = 999999999;
        System.out.println("Buscando pelo número: " + numeroBuscado2);
        int posicao2 = buscaLinear(agendaDeContatos, numeroBuscado2);

        if (posicao2 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao2 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado na agenda.");
        }
        System.out.println(); // Espaçamento
    }
}

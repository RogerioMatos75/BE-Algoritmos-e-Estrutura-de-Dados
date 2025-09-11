/**
 * Solução para o Exercício 3 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Simula a contagem de clientes que entram em uma loja, imprimindo os números de 1 a 10.
 */
public class Exercicio3 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println(); 

        System.out.println("==================================================");
        System.out.println("    Contagem de Clientes na Loja (Simulação)");
        System.out.println("==================================================");
        System.out.println();

        // Laço 'for' para simular a contagem de 10 clientes.
        for (int cliente = 1; cliente <= 10; cliente++) {
            System.out.println("-> Cliente de número " + cliente + " entrou na loja.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("      Fim da simulação da primeira hora.");
        System.out.println("==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println(); 
    }
}

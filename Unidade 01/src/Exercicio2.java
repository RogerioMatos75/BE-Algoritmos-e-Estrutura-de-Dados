import java.util.Scanner;

/**
 * Solução para o Exercício 2 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Lê o código de um produto e verifica se é par ou ímpar.
 * (Versão interativa com entrada do usuário)
 */
public class Exercicio2 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para afastar a execução da linha de comando.
        System.out.println(); 
        
        // Cria um objeto Scanner para ler a entrada do teclado
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("==================================================");
        System.out.println("   Verificador de Código de Produto (Par/Ímpar)");
        System.out.println("==================================================");

        // Solicita ao usuário que insira o código do produto
        System.out.print("\nPor favor, digite o código do produto: ");
        
        // Lê o número inteiro fornecido pelo usuário
        int codigoProduto = scanner.nextInt();

        // Verifica se o código é par ou ímpar e exibe o resultado
        System.out.println("\nVerificando o código: " + codigoProduto);
        if (codigoProduto % 2 == 0) {
            System.out.println("-> Resultado: O código " + codigoProduto + " é PAR.");
        } else {
            System.out.println("-> Resultado: O código " + codigoProduto + " é ÍMPAR.");
        }
        
        System.out.println("\n==================================================");
        System.out.println(); 

        // Fecha o scanner para liberar os recursos
        scanner.close();
    }
}
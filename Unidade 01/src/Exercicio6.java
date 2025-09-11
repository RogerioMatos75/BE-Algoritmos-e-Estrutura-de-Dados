/**
 * Solução para o Exercício 6 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra e exibe todos os números primos entre 1 e 100
 * utilizando o método de verificação por divisão.
 */
public class Exercicio6 {

    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println();

        System.out.println("==================================================");
        System.out.println("    Números Primos para Criptografia (1 a 100)");
        System.out.println("==================================================");
        System.out.println("\nProcurando números primos no intervalo de 1 a 100...");
        System.out.println();

        int contadorDePrimos = 0;
        // Laço principal para percorrer os números de 1 a 100.
        for (int i = 1; i <= 100; i++) {
            if (ehPrimo(i)) {
                // Imprime o número primo encontrado, com um espaço.
                System.out.print(i + " ");
                contadorDePrimos++;
            }
        }

        System.out.println("\n\nTotal de " + contadorDePrimos + " números primos encontrados.");
        System.out.println("\n==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println();
    }

    /**
     * Função auxiliar para verificar se um número é primo.
     * @param numero O número a ser verificado.
     * @return true se o número for primo, false caso contrário.
     */
    private static boolean ehPrimo(int numero) {
        // Números menores ou iguais a 1 não são primos.
        if (numero <= 1) {
            return false;
        }

        // Tenta dividir o número por todos os inteiros de 2 até sua raiz quadrada.
        // Se encontrarmos um divisor, o número não é primo.
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Não é primo.
            }
        }

        // Se o laço terminar sem encontrar divisores, o número é primo.
        return true;
    }
}

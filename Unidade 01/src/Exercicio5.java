import java.util.Arrays;

/**
 * Solução para o Exercício 5 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra a maior nota em um array de notas de alunos
 * utilizando o método de ordenação da biblioteca Arrays.
 */
public class Exercicio5 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println();

        // 1. Declaração e inicialização do array de notas.
        double[] notas = { 8.5, 9.0, 7.2, 6.5, 10.0, 8.8, 7.9, 9.4 };

        System.out.println("==================================================");
        System.out.println("          Analisador de Notas da Turma");
        System.out.println("==================================================");
        
        // Exibe as notas da turma para conferência.
        System.out.println("\nNotas da turma: " + Arrays.toString(notas));

        // 2. Ordena o array em ordem crescente.
        Arrays.sort(notas);

        // 3. A maior nota será o último elemento do array ordenado.
        double maiorNota = notas[notas.length - 1];

        // 4. Exibe o resultado.
        System.out.println("\nAnalisando as notas...");
        System.out.println("-> A maior nota da turma é: " + maiorNota);
        
        System.out.println("\n==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println();
    }
}

/**
 * Solução para o Exercício 1 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 */
public class Exercicio1 {
    public static void main(String[] args) {
        System.out.println(); // Adiciona uma quebra de linha para formatar a saída.
        // 1. Declaração e atribuição das variáveis conforme solicitado.
        int numeroQuarto = 101; // Variável para o número do quarto (inteiro)
        double valorDiaria = 320.50; // Variável para o valor da diária (ponto flutuante)
        String nomeHospede = "Ana Carolina"; // Variável para o nome do hóspede (texto)

        // 2. Exibição dos valores armazenados na tela.
        System.out.println("===========================================");
        System.out.println("   Hotel Unyleya - Detalhes da Reserva");
        System.out.println("===========================================");
        System.out.println("Hóspede: " + nomeHospede);
        System.out.println("Quarto N°: " + numeroQuarto);
        
        // Formata a saída do valor para exibir como moeda (R$)
        System.out.println("Valor da Diária: " + String.format("R$ %.2f", valorDiaria));
        System.out.println("===========================================");
    }
}

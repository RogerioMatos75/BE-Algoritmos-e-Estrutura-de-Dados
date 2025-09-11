import java.util.Scanner;

/**
 * Solução para o Exercício 7 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Implementação de um Jogo da Velha (Tic-Tac-Toe) interativo
 * para dois jogadores em um único arquivo (abordagem procedural com funções auxiliares).
 */
public class Exercicio7 {

    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            inicializarTabuleiro();
            boolean jogoContinua = true;

            System.out.println();
            System.out.println("==================================================");
            System.out.println("             BEM-VINDO AO JOGO DA VELHA");
            System.out.println("==================================================");

            while (jogoContinua) {
                imprimirTabuleiro();
                System.out.println("\nÉ a vez do jogador '" + jogadorAtual + "'.");
                System.out.print("Escolha a linha (1-3) e coluna (1-3), separadas por espaço: ");

                int linha = scanner.nextInt() - 1;
                int coluna = scanner.nextInt() - 1;

                if (movimentoValido(linha, coluna)) {
                    tabuleiro[linha][coluna] = jogadorAtual;

                    if (verificarVencedor()) {
                        imprimirTabuleiro();
                        System.out.println("\n*** PARABÉNS! O JOGADOR '" + jogadorAtual + "' VENCEU! ***");
                        jogoContinua = false;
                    } else if (verificarEmpate()) {
                        imprimirTabuleiro();
                        System.out.println("\n--- O JOGO TERMINOU EM EMPATE! ---");
                        jogoContinua = false;
                    } else {
                        // Troca o jogador para a próxima rodada
                        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("\n*** Movimento inválido! A posição já está ocupada ou fora do tabuleiro. Tente novamente. ***");
                }
            }
            System.out.println("\n==================================================");
            System.out.println("                  FIM DE JOGO");
            System.out.println("==================================================");
            System.out.println();
        }
    }

    /**
     * Preenche o tabuleiro com espaços em branco no início do jogo.
     */
    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    /**
     * Imprime o estado atual do tabuleiro no console.
     */
    private static void imprimirTabuleiro() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
    }

    /**
     * Verifica se a jogada do usuário é válida.
     * @param linha A linha escolhida.
     * @param coluna A coluna escolhida.
     * @return true se a posição está dentro do tabuleiro e vazia.
     */
    private static boolean movimentoValido(int linha, int coluna) {
        return (linha >= 0 && linha < 3) && (coluna >= 0 && coluna < 3) && (tabuleiro[linha][coluna] == ' ');
    }

    /**
     * Verifica todas as condições de vitória para o jogador atual.
     * @return true se o jogador atual venceu.
     */
    private static boolean verificarVencedor() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) ||
                (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)) {
                return true;
            }
        }
        // Verificar diagonais
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
            (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o tabuleiro está completamente preenchido, resultando em empate.
     * @return true se não há mais espaços vazios.
     */
    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda existe espaço vazio, o jogo não empatou.
                }
            }
        }
        return true; // Todos os espaços estão preenchidos.
    }
}

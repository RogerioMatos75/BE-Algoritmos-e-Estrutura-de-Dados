/**
 * Solução para o Exercício 2 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um método que verifique se essa árvore binária é uma
 * árvore binária de busca (BST).
 * 
 * Autor: Rogério
 */

import java.util.ArrayList;
import java.util.List;

public class Exercicio_2 {

    /**
     * Classe aninhada para representar o Nó da árvore.
     * Para este exercício, armazena um valor inteiro.
     */
    static class No {
        int valor;
        No esquerda, direita;

        public No(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    /**
     * Classe que representa a árvore e contém a lógica de validação.
     */
    static class Arvore {
        No raiz;

        /**
         * Valida se a árvore é uma Árvore Binária de Busca (BST).
         * A validação é feita através do percurso em-ordem (in-order traversal).
         * Se o resultado do percurso for uma lista ordenada, a árvore é uma BST.
         * @return true se a árvore for uma BST, false caso contrário.
         */
        public boolean isBST() {
            List<Integer> valoresEmOrdem = new ArrayList<>();
            // 1. Realiza o percurso em-ordem e popula a lista
            percursoEmOrdem(this.raiz, valoresEmOrdem);

            // 2. Verifica se a lista resultante está ordenada
            for (int i = 1; i < valoresEmOrdem.size(); i++) {
                // Se um elemento for menor ou igual ao seu antecessor, a ordem foi violada.
                if (valoresEmOrdem.get(i) <= valoresEmOrdem.get(i - 1)) {
                    return false; // Não é uma BST
                }
            }

            return true; // É uma BST
        }

        /**
         * Método auxiliar recursivo para realizar o percurso em-ordem.
         * @param no O nó atual da recursão.
         * @param lista A lista a ser populada com os valores dos nós.
         */
        private void percursoEmOrdem(No no, List<Integer> lista) {
            if (no == null) {
                return;
            }
            percursoEmOrdem(no.esquerda, lista);
            lista.add(no.valor);
            percursoEmOrdem(no.direita, lista);
        }
    }

    /**
     * Imprime um cabeçalho formatado no console.
     * @param titulo O texto do cabeçalho.
     */
    public static void imprimirCabecalho(String titulo) {
        int largura = 80;
        String linha = "=".repeat(largura);
        int padding = (largura - titulo.length()) / 2;

        System.out.println(linha);
        System.out.println(" ".repeat(padding) + titulo);
        System.out.println(linha);
    }

    /**
     * Método principal para demonstrar a funcionalidade de validação de BST.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 2: Validação de Árvore Binária de Busca (BST)");

        // --- Teste 1: Árvore Válida ---
        Arvore arvoreValida = new Arvore();
        arvoreValida.raiz = new No(10);
        arvoreValida.raiz.esquerda = new No(5);
        arvoreValida.raiz.direita = new No(20);
        arvoreValida.raiz.esquerda.esquerda = new No(3);
        arvoreValida.raiz.esquerda.direita = new No(7);
        arvoreValida.raiz.direita.esquerda = new No(15);

        System.out.println("\nAnalisando a primeira árvore (deve ser uma BST válida)...");
        boolean resultadoValido = arvoreValida.isBST();
        System.out.println("A árvore é uma BST? -> " + resultadoValido);
        System.out.println();

        // --- Teste 2: Árvore Inválida ---
        Arvore arvoreInvalida = new Arvore();
        arvoreInvalida.raiz = new No(10);
        arvoreInvalida.raiz.esquerda = new No(5);
        arvoreInvalida.raiz.direita = new No(20);
        // Erro: Nó com valor 2 deveria estar à esquerda de 5, não à direita.
        arvoreInvalida.raiz.esquerda.direita = new No(2);

        System.out.println("Analisando a segunda árvore (deve ser uma BST inválida)...");
        boolean resultadoInvalido = arvoreInvalida.isBST();
        System.out.println("A árvore é uma BST? -> " + resultadoInvalido);
        System.out.println();
    }
}

/**
 * Solução para o Exercício 8 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um método que encontre o menor valor em uma árvore binária
 * de busca (BST).
 * 
 * Autor: Rogério
 */

import java.util.NoSuchElementException;

public class Exercicio_8 {

    /**
     * Classe aninhada para representar o Nó da árvore.
     */
    static class No {
        int valor; // Representando o preço de um produto
        No esquerda, direita;

        public No(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    /**
     * Classe que implementa a Árvore Binária de Busca.
     */
    static class ArvoreBinariaBusca {
        private No raiz;

        public ArvoreBinariaBusca() {
            this.raiz = null;
        }

        /**
         * Insere um novo valor na BST.
         * @param valor O valor a ser inserido.
         */
        public void inserir(int valor) {
            raiz = inserirRecursivo(raiz, valor);
        }

        private No inserirRecursivo(No atual, int valor) {
            if (atual == null) {
                return new No(valor);
            }

            if (valor < atual.valor) {
                atual.esquerda = inserirRecursivo(atual.esquerda, valor);
            } else if (valor > atual.valor) {
                atual.direita = inserirRecursivo(atual.direita, valor);
            }

            return atual; // Retorna o nó (inalterado)
        }

        /**
         * Encontra o menor valor na árvore (método iterativo).
         * @return O menor valor encontrado.
         * @throws NoSuchElementException se a árvore estiver vazia.
         */
        public int encontrarMinimo() {
            if (raiz == null) {
                throw new NoSuchElementException("A árvore está vazia. Não há valor mínimo.");
            }

            No atual = raiz;
            // Navega para a esquerda até não poder mais
            while (atual.esquerda != null) {
                atual = atual.esquerda;
            }
            return atual.valor;
        }
    }

    public static void imprimirCabecalho(String titulo) {
        int largura = 80;
        String linha = "=".repeat(largura);
        int padding = Math.max(0, (largura - titulo.length()) / 2);
        System.out.println(linha);
        System.out.println(" ".repeat(padding) + titulo);
        System.out.println(linha);
    }

    public static void main(String[] args) {
        imprimirCabecalho("Exercício 8: Encontrar o Valor Mínimo em uma BST");
        System.out.println();

        ArvoreBinariaBusca arvoreDeProdutos = new ArvoreBinariaBusca();

        // Inserindo preços de produtos na árvore
        System.out.println("Inserindo preços na árvore de produtos: 150, 80, 220, 60, 120, 200, 300, 40");
        arvoreDeProdutos.inserir(150); // Raiz
        arvoreDeProdutos.inserir(80);
        arvoreDeProdutos.inserir(220);
        arvoreDeProdutos.inserir(60);
        arvoreDeProdutos.inserir(120);
        arvoreDeProdutos.inserir(200);
        arvoreDeProdutos.inserir(300);
        arvoreDeProdutos.inserir(40); // O menor valor
        System.out.println();

        try {
            int produtoMaisBarato = arvoreDeProdutos.encontrarMinimo();
            System.out.println("Analisando a árvore para encontrar a melhor oferta...");
            System.out.println("-> O produto mais barato custa: R$ " + produtoMaisBarato);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Teste com árvore vazia
        System.out.println("Testando com uma árvore vazia...");
        ArvoreBinariaBusca arvoreVazia = new ArvoreBinariaBusca();
        try {
            arvoreVazia.encontrarMinimo();
        } catch (NoSuchElementException e) {
            System.out.println("-> Resultado: " + e.getMessage());
        }
        System.out.println();
    }
}

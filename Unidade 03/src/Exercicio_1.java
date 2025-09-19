/**
 * Solução para o Exercício 1 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Crie uma classe em Java para representar uma árvore binária, onde cada nó
 * armazenará informações sobre um livro. Implemente métodos que permitam inserir
 * novos livros e percorrer a árvore em pré-ordem, pós-ordem e em ordem para
 * listar todos os livros disponíveis.
 * 
 * Autor: Rogério
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Exercicio_1 {

    /**
     * Classe para representar a entidade Livro.
     * Contém informações sobre o título e o autor.
     */
    static class Livro {
        String titulo;
        String autor;

        public Livro(String titulo, String autor) {
            this.titulo = titulo;
            this.autor = autor;
        }

        @Override
        public String toString() {
            // Versão corrigida e final do método toString.
            return "Livro{titulo='" + this.titulo + "', autor='" + this.autor + "'}";
        }
    }

    /**
     * Classe que implementa a Árvore Binária para armazenar objetos Livro.
     */
    static class ArvoreBinaria {

        private class No {
            Livro livro;
            No esquerda, direita;

            public No(Livro livro) {
                this.livro = livro;
                this.esquerda = null;
                this.direita = null;
            }
        }

        private No raiz;

        public ArvoreBinaria() {
            this.raiz = null;
        }

        public void inserir(Livro livro) {
            No novoNo = new No(livro);
            if (raiz == null) {
                raiz = novoNo;
                return;
            }
            Queue<No> fila = new LinkedList<>();
            fila.add(raiz);
            while (!fila.isEmpty()) {
                No noAtual = fila.poll();
                if (noAtual.esquerda == null) {
                    noAtual.esquerda = novoNo;
                    return;
                } else {
                    fila.add(noAtual.esquerda);
                }
                if (noAtual.direita == null) {
                    noAtual.direita = novoNo;
                    return;
                } else {
                    fila.add(noAtual.direita);
                }
            }
        }

        public List<String> percursoPreOrdem() {
            List<String> resultado = new ArrayList<>();
            percursoPreOrdem(raiz, resultado);
            return resultado;
        }

        private void percursoPreOrdem(No no, List<String> resultado) {
            if (no != null) {
                resultado.add(no.livro.toString());
                percursoPreOrdem(no.esquerda, resultado);
                percursoPreOrdem(no.direita, resultado);
            }
        }

        public List<String> percursoEmOrdem() {
            List<String> resultado = new ArrayList<>();
            percursoEmOrdem(raiz, resultado);
            return resultado;
        }

        private void percursoEmOrdem(No no, List<String> resultado) {
            if (no != null) {
                percursoEmOrdem(no.esquerda, resultado);
                resultado.add(no.livro.toString());
                percursoEmOrdem(no.direita, resultado);
            }
        }

        public List<String> percursoPosOrdem() {
            List<String> resultado = new ArrayList<>();
            percursoPosOrdem(raiz, resultado);
            return resultado;
        }

        private void percursoPosOrdem(No no, List<String> resultado) {
            if (no != null) {
                percursoPosOrdem(no.esquerda, resultado);
                percursoPosOrdem(no.direita, resultado);
                resultado.add(no.livro.toString());
            }
        }
    }

    /**
     * Imprime um título e uma lista de conteúdo dentro de uma caixa de texto formatada.
     * @param titulo O título a ser exibido no topo da caixa.
     * @param linhas A lista de strings a serem exibidas como conteúdo.
     */
    public static void imprimirEmCaixa(String titulo, List<String> linhas) {
        int larguraMaxima = 80;
        if (linhas != null) {
            for (String linha : linhas) {
                if (linha.length() > larguraMaxima - 4) {
                    larguraMaxima = linha.length() + 4;
                }
            }
        }
        if (titulo.length() > larguraMaxima - 4) {
            larguraMaxima = titulo.length() + 4;
        }

        // Top border
        System.out.print("╭");
        for (int i = 0; i < larguraMaxima - 2; i++) System.out.print("─");
        System.out.println("╮");

        // Blank line before title
        System.out.print("│");
        for (int i = 0; i < larguraMaxima - 2; i++) System.out.print(" ");
        System.out.println("│");

        // Title
        int paddingTitulo = (larguraMaxima - 2 - titulo.length()) / 2;
        System.out.print("│");
        for (int i = 0; i < paddingTitulo; i++) System.out.print(" ");
        System.out.print(titulo);
        for (int i = 0; i < larguraMaxima - 2 - titulo.length() - paddingTitulo; i++) System.out.print(" ");
        System.out.println("│");

        // Blank line after title
        System.out.print("│");
        for (int i = 0; i < larguraMaxima - 2; i++) System.out.print(" ");
        System.out.println("│");

        // Separator
        System.out.print("├");
        for (int i = 0; i < larguraMaxima - 2; i++) System.out.print("─");
        System.out.println("┤");

        // Content
        if (linhas != null) {
            for (String linha : linhas) {
                System.out.print("│ ");
                System.out.print(linha);
                for (int i = 0; i < larguraMaxima - 3 - linha.length(); i++) System.out.print(" ");
                System.out.println("│");
            }
        }

        // Bottom border
        System.out.print("╰");
        for (int i = 0; i < larguraMaxima - 2; i++) System.out.print("─");
        System.out.println("╯");
    }

    public static void main(String[] args) {
        ArvoreBinaria biblioteca = new ArvoreBinaria();

        biblioteca.inserir(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));
        biblioteca.inserir(new Livro("Dom Quixote", "Miguel de Cervantes"));
        biblioteca.inserir(new Livro("1984", "George Orwell"));
        biblioteca.inserir(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry"));
        biblioteca.inserir(new Livro("Cem Anos de Solidão", "Gabriel García Márquez"));

        List<String> preOrdem = biblioteca.percursoPreOrdem();
        imprimirEmCaixa("Percurso em Pré-Ordem (Raiz, Esquerda, Direita)", preOrdem);
        System.out.println();

        List<String> emOrdem = biblioteca.percursoEmOrdem();
        imprimirEmCaixa("Percurso em Ordem (Esquerda, Raiz, Direita)", emOrdem);
        System.out.println();

        List<String> posOrdem = biblioteca.percursoPosOrdem();
        imprimirEmCaixa("Percurso em Pós-Ordem (Esquerda, Direita, Raiz)", posOrdem);
        System.out.println();
    }
}
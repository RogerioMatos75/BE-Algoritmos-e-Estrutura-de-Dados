/**
 * Solução para o Exercício 5 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Crie uma classe que represente um grafo em Java, utilizando lista
 * de adjacências, para mapear as conexões entre diferentes locais.
 * 
 * Autor: Rogério
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercicio_5 {

    /**
     * Classe que representa um Grafo utilizando uma Lista de Adjacências.
     * Os vértices são representados por Strings (nomes dos locais).
     */
    static class Grafo {
        // O mapa armazena a lista de adjacências.
        // Chave: String (vértice de origem)
        // Valor: Lista de Strings (vértices de destino)
        private Map<String, List<String>> listaDeAdjacencias;

        public Grafo() {
            this.listaDeAdjacencias = new HashMap<>();
        }

        /**
         * Adiciona um novo vértice (local) ao grafo.
         * @param local O nome do local a ser adicionado.
         */
        public void adicionarVertice(String local) {
            // putIfAbsent garante que não vamos substituir uma lista existente.
            listaDeAdjacencias.putIfAbsent(local, new ArrayList<>());
        }

        /**
         * Adiciona uma aresta (conexão) bidirecional entre dois locais.
         * @param origem O local de origem.
         * @param destino O local de destino.
         */
        public void adicionarAresta(String origem, String destino) {
            // Garante que ambos os vértices existam no grafo antes de adicionar a aresta.
            this.adicionarVertice(origem);
            this.adicionarVertice(destino);

            // Adiciona a conexão de origem para destino
            listaDeAdjacencias.get(origem).add(destino);
            // Adiciona a conexão de destino para origem (bidirecional)
            listaDeAdjacencias.get(destino).add(origem);
        }

        /**
         * Imprime a representação do grafo no console.
         */
        public void imprimirGrafo() {
            System.out.println("Representação do Grafo (Lista de Adjacências):");
            for (String vertice : listaDeAdjacencias.keySet()) {
                System.out.println(vertice + " -> " + listaDeAdjacencias.get(vertice));
            }
        }
    }

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
     * Método principal para demonstrar a representação do grafo.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 5: Representação de Grafo com Lista de Adjacências");
        System.out.println(); // Espaçamento

        Grafo mapaDeNavegacao = new Grafo();

        // Adicionando locais (vértices)
        mapaDeNavegacao.adicionarVertice("São Paulo");
        mapaDeNavegacao.adicionarVertice("Rio de Janeiro");
        mapaDeNavegacao.adicionarVertice("Belo Horizonte");
        mapaDeNavegacao.adicionarVertice("Curitiba");
        mapaDeNavegacao.adicionarVertice("Brasília");

        // Adicionando conexões (arestas)
        mapaDeNavegacao.adicionarAresta("São Paulo", "Rio de Janeiro");
        mapaDeNavegacao.adicionarAresta("São Paulo", "Curitiba");
        mapaDeNavegacao.adicionarAresta("São Paulo", "Belo Horizonte");
        mapaDeNavegacao.adicionarAresta("Rio de Janeiro", "Belo Horizonte");
        mapaDeNavegacao.adicionarAresta("Belo Horizonte", "Brasília");

        // Imprimindo o grafo para visualização
        mapaDeNavegacao.imprimirGrafo();
        System.out.println(); // Espaçamento
    }
}

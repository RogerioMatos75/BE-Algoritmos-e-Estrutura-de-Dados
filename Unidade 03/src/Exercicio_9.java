/**
 * Solução para o Exercício 9 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Encontre o caminho mais curto entre dois pontos em um grafo ponderado
 * utilizando o Algoritmo de Dijkstra.
 * 
 * Autor: Rogério
 */

import java.util.*;

public class Exercicio_9 {

    /**
     * Classe auxiliar para representar um vizinho na lista de adjacências.
     * Armazena o nome do vértice de destino e a distância (peso) da aresta.
     */
    static class Vizinho {
        String nome;
        int distancia;

        Vizinho(String nome, int distancia) {
            this.nome = nome;
            this.distancia = distancia;
        }

        @Override
        public String toString() {
            return nome + "(" + distancia + ")";
        }
    }

    /**
     * Classe que representa um Grafo PONDERADO utilizando uma Lista de Adjacências.
     */
    static class GrafoPonderado {
        private final Map<String, List<Vizinho>> listaDeAdjacencias = new HashMap<>();

        public void adicionarVertice(String local) {
            listaDeAdjacencias.putIfAbsent(local, new ArrayList<>());
        }

        public void adicionarAresta(String origem, String destino, int distancia) {
            this.adicionarVertice(origem);
            this.adicionarVertice(destino);
            // Adiciona a aresta nos dois sentidos (grafo não-direcionado)
            listaDeAdjacencias.get(origem).add(new Vizinho(destino, distancia));
            listaDeAdjacencias.get(destino).add(new Vizinho(origem, distancia));
        }

        /**
         * Encontra o caminho mais curto usando o Algoritmo de Dijkstra.
         * @param inicio O vértice de partida.
         * @param fim O vértice de destino.
         * @return Um Map contendo o caminho (lista de locais) e a distância total.
         */
        public Map<String, Object> encontrarCaminhoMaisCurto(String inicio, String fim) {
            // Mapa para armazenar a menor distância encontrada do início até cada vértice
            Map<String, Integer> distancias = new HashMap<>();
            // Fila de prioridade para decidir qual vértice visitar em seguida (o com menor distância)
            PriorityQueue<Vizinho> filaDePrioridade = new PriorityQueue<>(Comparator.comparingInt(v -> v.distancia));
            // Mapa para reconstruir o caminho no final
            Map<String, String> predecessores = new HashMap<>();
            // Conjunto para não reprocessar vértices
            Set<String> visitados = new HashSet<>();

            // Inicializa todas as distâncias como infinito, exceto a do início
            for (String vertice : listaDeAdjacencias.keySet()) {
                distancias.put(vertice, Integer.MAX_VALUE);
            }
            distancias.put(inicio, 0);

            // Começa pela origem
            filaDePrioridade.add(new Vizinho(inicio, 0));

            while (!filaDePrioridade.isEmpty()) {
                Vizinho atual = filaDePrioridade.poll();
                String verticeAtual = atual.nome;

                if (visitados.contains(verticeAtual)) continue;
                visitados.add(verticeAtual);

                if (verticeAtual.equals(fim)) break; // Otimização: parar se já chegamos ao destino

                for (Vizinho vizinho : listaDeAdjacencias.get(verticeAtual)) {
                    int novaDistancia = distancias.get(verticeAtual) + vizinho.distancia;
                    if (novaDistancia < distancias.get(vizinho.nome)) {
                        distancias.put(vizinho.nome, novaDistancia);
                        predecessores.put(vizinho.nome, verticeAtual);
                        filaDePrioridade.add(new Vizinho(vizinho.nome, novaDistancia));
                    }
                }
            }

            // Reconstrói o caminho
            List<String> caminho = new LinkedList<>();
            String passo = fim;
            if (predecessores.containsKey(passo) || passo.equals(inicio)) {
                while (passo != null) {
                    caminho.add(0, passo);
                    passo = predecessores.get(passo);
                }
            }

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("caminho", caminho);
            resultado.put("distancia", distancias.get(fim));
            return resultado;
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
        imprimirCabecalho("Exercício 9: Dijkstra para Caminho Mais Curto em Grafo Ponderado");
        System.out.println();

        GrafoPonderado mapa = new GrafoPonderado();
        mapa.adicionarAresta("São Paulo", "Rio de Janeiro", 430);
        mapa.adicionarAresta("São Paulo", "Curitiba", 408);
        mapa.adicionarAresta("São Paulo", "Belo Horizonte", 586);
        mapa.adicionarAresta("Rio de Janeiro", "Belo Horizonte", 440);
        mapa.adicionarAresta("Belo Horizonte", "Brasília", 740);
        mapa.adicionarAresta("Curitiba", "Porto Alegre", 710);

        String inicio = "São Paulo";
        String fim = "Brasília";

        System.out.println("Buscando a rota mais eficiente de " + inicio + " para " + fim + "...");
        Map<String, Object> resultado = mapa.encontrarCaminhoMaisCurto(inicio, fim);
        List<String> caminho = (List<String>) resultado.get("caminho");
        int distancia = (int) resultado.get("distancia");

        if (caminho.isEmpty() || distancia == Integer.MAX_VALUE) {
            System.out.println("Não foi encontrado um caminho.");
        } else {
            System.out.println("-> Rota mais curta: " + String.join(" -> ", caminho));
            System.out.println("-> Distância total: " + distancia + " km");
        }
        System.out.println();
    }
}

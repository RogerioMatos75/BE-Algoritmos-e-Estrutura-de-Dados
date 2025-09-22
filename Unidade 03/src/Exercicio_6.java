/**
 * Solução para o Exercício 6 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Implemente o algoritmo de Busca em Largura (BFS) para encontrar o
 * caminho mais curto entre dois pontos em um grafo não ponderado.
 * 
 * Autor: Rogério
 */

import java.util.*;

public class Exercicio_6 {

    /**
     * Classe que representa um Grafo utilizando uma Lista de Adjacências.
     */
    static class Grafo {
        private Map<String, List<String>> listaDeAdjacencias;

        public Grafo() {
            this.listaDeAdjacencias = new HashMap<>();
        }

        public void adicionarVertice(String local) {
            listaDeAdjacencias.putIfAbsent(local, new ArrayList<>());
        }

        public void adicionarAresta(String origem, String destino) {
            this.adicionarVertice(origem);
            this.adicionarVertice(destino);
            listaDeAdjacencias.get(origem).add(destino);
            listaDeAdjacencias.get(destino).add(origem);
        }

        /**
         * Encontra o caminho mais curto entre dois vértices usando BFS.
         * @param inicio O vértice de partida.
         * @param fim O vértice de destino.
         * @return Uma lista de strings representando o caminho mais curto, ou uma lista vazia se não houver caminho.
         */
        public List<String> encontrarCaminhoBFS(String inicio, String fim) {
            // Fila para os nós a serem visitados
            Queue<String> fila = new LinkedList<>();
            // Conjunto para rastrear nós já visitados e evitar ciclos
            Set<String> visitados = new HashSet<>();
            // Mapa para reconstruir o caminho de volta (nó -> seu predecessor)
            Map<String, String> predecessores = new HashMap<>();

            // Validação inicial
            if (!listaDeAdjacencias.containsKey(inicio) || !listaDeAdjacencias.containsKey(fim)) {
                return Collections.emptyList(); // Retorna lista vazia se início ou fim não existem
            }

            // Começa a busca pelo nó inicial
            fila.add(inicio);
            visitados.add(inicio);

            while (!fila.isEmpty()) {
                String atual = fila.poll();

                // Se encontramos o destino, podemos parar a busca
                if (atual.equals(fim)) {
                    break;
                }

                // Itera sobre todos os vizinhos do nó atual
                for (String vizinho : listaDeAdjacencias.get(atual)) {
                    if (!visitados.contains(vizinho)) {
                        visitados.add(vizinho);
                        predecessores.put(vizinho, atual); // Guarda o caminho
                        fila.add(vizinho);
                    }
                }
            }

            // Se o mapa de predecessores não contém o fim, não há caminho
            if (!predecessores.containsKey(fim) && !inicio.equals(fim)) {
                 return Collections.emptyList();
            }

            // Reconstrói o caminho do fim para o início
            List<String> caminho = new LinkedList<>();
            String passo = fim;
            while (passo != null) {
                caminho.add(0, passo); // Adiciona no início da lista
                passo = predecessores.get(passo);
            }
            
            // Se o caminho encontrado não começa com o 'inicio', algo deu errado (não deveria acontecer se há caminho)
            if (!caminho.isEmpty() && caminho.get(0).equals(inicio)) {
                return caminho;
            } else {
                return Collections.emptyList();
            }
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
        imprimirCabecalho("Exercício 6: BFS para Encontrar o Caminho Mais Curto");
        System.out.println();

        Grafo mapa = new Grafo();
        mapa.adicionarAresta("São Paulo", "Rio de Janeiro");
        mapa.adicionarAresta("São Paulo", "Curitiba");
        mapa.adicionarAresta("São Paulo", "Belo Horizonte");
        mapa.adicionarAresta("Rio de Janeiro", "Belo Horizonte");
        mapa.adicionarAresta("Belo Horizonte", "Brasília");
        mapa.adicionarAresta("Curitiba", "Porto Alegre"); // Adicionando mais um nível

        String inicio = "São Paulo";
        String fim = "Brasília";

        System.out.println("Buscando o caminho mais curto de " + inicio + " para " + fim + "...");
        List<String> caminho = mapa.encontrarCaminhoBFS(inicio, fim);

        if (caminho.isEmpty()) {
            System.out.println("Não foi encontrado um caminho.");
        } else {
            System.out.println("-> Caminho mais curto encontrado: " + String.join(" -> ", caminho));
        }
        
        System.out.println();

        // Teste 2: De Curitiba para Brasília
        inicio = "Curitiba";
        System.out.println("Buscando o caminho mais curto de " + inicio + " para " + fim + "...");
        caminho = mapa.encontrarCaminhoBFS(inicio, fim);

        if (caminho.isEmpty()) {
            System.out.println("Não foi encontrado um caminho.");
        } else {
            System.out.println("-> Caminho mais curto encontrado: " + String.join(" -> ", caminho));
        }

        System.out.println();
    }
}

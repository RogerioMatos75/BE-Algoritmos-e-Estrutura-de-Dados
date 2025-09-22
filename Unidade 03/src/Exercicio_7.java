/**
 * Solução para o Exercício 7 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um método para realizar a Busca em Profundidade (DFS) em um
 * grafo. Esse método deve exibir todos os vértices visitados.
 * 
 * Autor: Rogério
 */

import java.util.*;

public class Exercicio_7 {

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
         * Inicia o percurso DFS a partir de um vértice inicial.
         * @param inicio O vértice de onde o percurso deve começar.
         * @return Uma lista com a ordem dos vértices visitados.
         */
        public List<String> percursoDFS(String inicio) {
            List<String> resultado = new ArrayList<>();
            Set<String> visitados = new HashSet<>();
            
            // Validação para garantir que o nó de início existe
            if (!listaDeAdjacencias.containsKey(inicio)) {
                return resultado; // Retorna lista vazia
            }

            dfsRecursivo(inicio, visitados, resultado);
            return resultado;
        }

        /**
         * Método auxiliar recursivo que executa a lógica do DFS.
         * @param atual O vértice atual sendo visitado.
         * @param visitados O conjunto de vértices já visitados.
         * @param resultado A lista para armazenar a ordem da visitação.
         */
        private void dfsRecursivo(String atual, Set<String> visitados, List<String> resultado) {
            // 1. Marca o nó atual como visitado e o adiciona ao resultado
            visitados.add(atual);
            resultado.add(atual);

            // 2. Para cada vizinho do nó atual...
            for (String vizinho : listaDeAdjacencias.get(atual)) {
                // 3. ...se o vizinho ainda não foi visitado, chama a recursão para ele.
                if (!visitados.contains(vizinho)) {
                    dfsRecursivo(vizinho, visitados, resultado);
                }
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
        imprimirCabecalho("Exercício 7: Percurso em Profundidade (DFS) em um Grafo");
        System.out.println();

        Grafo mapa = new Grafo();
        mapa.adicionarAresta("São Paulo", "Rio de Janeiro");
        mapa.adicionarAresta("São Paulo", "Curitiba");
        mapa.adicionarAresta("São Paulo", "Belo Horizonte");
        mapa.adicionarAresta("Rio de Janeiro", "Belo Horizonte");
        mapa.adicionarAresta("Belo Horizonte", "Brasília");
        mapa.adicionarAresta("Curitiba", "Porto Alegre");

        String pontoDePartida = "São Paulo";
        System.out.println("Iniciando a busca em profundidade a partir de: " + pontoDePartida);
        System.out.println();

        List<String> ordemDeVisita = mapa.percursoDFS(pontoDePartida);

        System.out.println("Ordem de visitação dos locais (DFS):");
        System.out.println("-> " + String.join(" -> ", ordemDeVisita));
        System.out.println();
    }
}

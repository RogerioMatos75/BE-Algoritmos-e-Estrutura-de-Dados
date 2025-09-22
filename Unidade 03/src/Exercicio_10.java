/**
 * Solução para o Exercício 10 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Implemente um algoritmo que ordene os vértices de um grafo de acordo
 * com a topologia (Ordenação Topológica), útil para organizar tarefas interdependentes.
 * 
 * Autor: Rogério
 */

import java.util.*;

public class Exercicio_10 {

    /**
     * Classe que representa um Grafo Direcionado.
     */
    static class GrafoDirecionado {
        private final Map<String, List<String>> listaDeAdjacencias = new HashMap<>();
        private final Map<String, Integer> inDegree = new HashMap<>();

        /**
         * Adiciona um vértice ao grafo. Necessário para vértices que não têm arestas.
         */
        public void adicionarVertice(String vertice) {
            listaDeAdjacencias.putIfAbsent(vertice, new ArrayList<>());
            inDegree.putIfAbsent(vertice, 0);
        }

        /**
         * Adiciona uma aresta direcionada (dependência) de origem para destino.
         * @param origem A tarefa que deve ser feita primeiro.
         * @param destino A tarefa que depende da origem.
         */
        public void adicionarAresta(String origem, String destino) {
            this.adicionarVertice(origem);
            this.adicionarVertice(destino);

            listaDeAdjacencias.get(origem).add(destino);
            inDegree.put(destino, inDegree.get(destino) + 1);
        }

        /**
         * Realiza a ordenação topológica do grafo usando o Algoritmo de Kahn.
         * @return Uma lista com os vértices em ordem topológica, ou uma lista vazia se houver um ciclo.
         */
        public List<String> ordenacaoTopologica() {
            // Fila para vértices com in-degree zero (sem dependências)
            Queue<String> fila = new LinkedList<>();
            for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue() == 0) {
                    fila.add(entry.getKey());
                }
            }

            List<String> ordemTopologica = new ArrayList<>();
            int verticesProcessados = 0;

            while (!fila.isEmpty()) {
                String u = fila.poll();
                ordemTopologica.add(u);
                verticesProcessados++;

                // Para cada vizinho de u, "removemos" a aresta
                for (String v : listaDeAdjacencias.get(u)) {
                    inDegree.put(v, inDegree.get(v) - 1);
                    // Se o vizinho ficou sem dependências, adiciona à fila
                    if (inDegree.get(v) == 0) {
                        fila.add(v);
                    }
                }
            }

            // Se o número de vértices processados for menor que o total, há um ciclo.
            if (verticesProcessados != listaDeAdjacencias.size()) {
                System.out.println("Alerta: O grafo contém um ciclo! Ordenação topológica não é possível.");
                return new ArrayList<>(); // Retorna lista vazia para indicar falha
            }

            return ordemTopologica;
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
        imprimirCabecalho("Exercício 10: Ordenação Topológica com Algoritmo de Kahn");
        System.out.println();

        GrafoDirecionado projeto = new GrafoDirecionado();
        System.out.println("Definindo as dependências das tarefas do projeto...");

        // Exemplo: Tarefas para se vestir de manhã
        projeto.adicionarAresta("Vestir meias", "Vestir sapatos");
        projeto.adicionarAresta("Vestir cueca", "Vestir calça");
        projeto.adicionarAresta("Vestir calça", "Colocar cinto");
        projeto.adicionarAresta("Vestir camisa", "Vestir casaco");
        projeto.adicionarAresta("Colocar cinto", "Vestir casaco");
        
        // Tarefa sem dependência explícita (mas é um ponto de partida)
        projeto.adicionarVertice("Vestir camisa");

        System.out.println();
        System.out.println("Calculando a ordem de execução das tarefas...");
        List<String> ordem = projeto.ordenacaoTopologica();

        if (!ordem.isEmpty()) {
            System.out.println("-> Ordem de execução recomendada: " + String.join(" -> ", ordem));
        }
        System.out.println();

        // Exemplo com ciclo
        System.out.println("===============================================================================");
        System.out.println("Testando um grafo com um ciclo de dependências...");
        GrafoDirecionado projetoCiclico = new GrafoDirecionado();
        projetoCiclico.adicionarAresta("Tarefa A", "Tarefa B");
        projetoCiclico.adicionarAresta("Tarefa B", "Tarefa C");
        projetoCiclico.adicionarAresta("Tarefa C", "Tarefa A"); // Ciclo

        List<String> ordemCiclica = projetoCiclico.ordenacaoTopologica();
        if (ordemCiclica.isEmpty()) {
            System.out.println("-> O algoritmo detectou corretamente o ciclo e não pôde gerar uma ordem.");
        }
        System.out.println();
    }
}

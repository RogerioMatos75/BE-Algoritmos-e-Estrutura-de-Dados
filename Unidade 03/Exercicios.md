```java 
javac "src\Exercicio_10.java"

java -cp src Exercicio_10
```

# Árvore Binária e BST (Árvore Binária de Busca) 

## 1 - Implementação da Árvore Binária: 

Imagine que você está desenvolvendo um sistema de gerenciamento de bibliotecas. Crie uma classe em Java para representar uma árvore binária, onde cada nó armazenará informações sobre um livro. Implemente métodos que permitam inserir novos livros e percorrer a árvore em pré-ordem, pós-ordem e em ordem para listar todos os livros disponíveis. 

```java
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
```

## 2 - Validação de BST: 

Suponha que você tenha uma árvore que armazena a hierarquia de categorias de produtos em um e-commerce. Escreva um método que verifique se essa árvore binária é uma árvore binária de busca (BST), garantindo que os produtos estejam organizados corretamente para facilitar a busca e navegação. 
Busca Linear e Binária 
```java
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

```

## 3 - Busca Linear: 

Você está criando um aplicativo de agenda de contatos e precisa encontrar rapidamente a posição de um número específico na lista. Escreva um método que realize uma busca linear em um array de inteiros, retornando a posição do número buscado ou -1 caso ele não esteja presente. 
```java
/**
 * Solução para o Exercício 3 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Escreva um método que realize uma busca linear em um array de inteiros,
 * retornando a posição do número buscado ou -1 caso ele não esteja presente.
 * 
 * Autor: Rogério
 */

import java.util.Arrays;

public class Exercicio_3 {

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
     * Realiza uma busca linear em um array de inteiros.
     * Percorre o array do início ao fim, comparando cada elemento com o alvo.
     * @param array O array de inteiros onde a busca será realizada.
     * @param alvo O número a ser buscado.
     * @return O índice (posição) do primeiro elemento correspondente ao alvo, ou -1 se não for encontrado.
     */
    public static int buscaLinear(int[] array, int alvo) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == alvo) {
                return i; // Retorna a posição imediatamente ao encontrar
            }
        }
        return -1; // Retorna -1 se o loop terminar sem encontrar o alvo
    }

    /**
     * Método principal para demonstrar a funcionalidade da busca linear.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 3: Busca Linear em uma Agenda de Contatos");

        int[] agendaDeContatos = { 987654321, 912345678, 998877665, 965432109 };

        // Constrói a string da agenda com índices para clareza do usuário
        StringBuilder agendaFormatada = new StringBuilder("Agenda de Contatos: [");
        for (int i = 0; i < agendaDeContatos.length; i++) {
            agendaFormatada.append("(").append(i).append("): ").append(agendaDeContatos[i]);
            if (i < agendaDeContatos.length - 1) {
                agendaFormatada.append(", ");
            }
        }
        agendaFormatada.append("]");
        System.out.println("\n" + agendaFormatada.toString());
        System.out.println();

        // --- Teste 1: Número que existe na agenda ---
        int numeroBuscado1 = 998877665;
        System.out.println("Buscando pelo número: " + numeroBuscado1);
        int posicao1 = buscaLinear(agendaDeContatos, numeroBuscado1);

        if (posicao1 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao1 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado na agenda.");
        }
        System.out.println(); // Espaçamento

        // --- Teste 2: Número que NÃO existe na agenda ---
        int numeroBuscado2 = 999999999;
        System.out.println("Buscando pelo número: " + numeroBuscado2);
        int posicao2 = buscaLinear(agendaDeContatos, numeroBuscado2);

        if (posicao2 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao2 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado na agenda.");
        }
        System.out.println(); // Espaçamento
    }
}

```

## 4 - Busca Binária: 

Imagine que você está desenvolvendo um sistema de pesquisa de dados em uma lista de números ordenados. Implemente um algoritmo de busca binária que encontre a posição de um elemento específico em um array ordenado de inteiros, retornando sua posição ou -1 se não for encontrado. 
```java
/**
 * Solução para o Exercício 4 da Unidade 03 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Implemente um algoritmo de busca binária que encontre a posição de um
 * elemento específico em um array ordenado de inteiros, retornando sua posição
 * ou -1 se não for encontrado.
 * 
 * Autor: Rogério
 */

import java.util.Arrays;

public class Exercicio_4 {

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
     * Realiza uma busca binária em um array de inteiros ordenado.
     * @param array O array ordenado de inteiros onde a busca será realizada.
     * @param alvo O número a ser buscado.
     * @return O índice (posição) do alvo, ou -1 se não for encontrado.
     */
    public static int buscaBinaria(int[] array, int alvo) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; // Evita overflow para arrays muito grandes

            // Se o alvo está no meio, encontramos
            if (array[meio] == alvo) {
                return meio;
            }

            // Se o alvo é maior, ignora a metade esquerda
            if (array[meio] < alvo) {
                inicio = meio + 1;
            } 
            // Se o alvo é menor, ignora a metade direita
            else {
                fim = meio - 1;
            }
        }

        // Se o loop terminar, o alvo não está no array
        return -1;
    }

    /**
     * Método principal para demonstrar a funcionalidade da busca binária.
     */
    public static void main(String[] args) {
        imprimirCabecalho("Exercício 4: Busca Binária em um Array Ordenado");

        int[] dadosOrdenados = { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };

        System.out.println("\nConjunto de dados ordenados: " + Arrays.toString(dadosOrdenados));
        System.out.println();

        // --- Teste 1: Número que existe no array ---
        int numeroBuscado1 = 23;
        System.out.println("Buscando pelo número: " + numeroBuscado1);
        int posicao1 = buscaBinaria(dadosOrdenados, numeroBuscado1);

        if (posicao1 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao1 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado no conjunto de dados.");
        }
        System.out.println(); // Espaçamento

        // --- Teste 2: Número que NÃO existe no array ---
        int numeroBuscado2 = 50;
        System.out.println("Buscando pelo número: " + numeroBuscado2);
        int posicao2 = buscaBinaria(dadosOrdenados, numeroBuscado2);

        if (posicao2 != -1) {
            System.out.println("-> Resultado: Número encontrado na posição " + posicao2 + ".");
        } else {
            System.out.println("-> Resultado: Número não encontrado no conjunto de dados.");
        }
        System.out.println(); // Espaçamento
    }
}
```

### Grafos, BFS e DFS 

## 5 - Representação de Grafos: 

Você está desenvolvendo um sistema de roteamento para um aplicativo de navegação. Crie uma classe que represente um grafo em Java, utilizando lista de adjacências ou matriz de adjacências, para mapear as conexões entre diferentes locais. 

```java
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
```

## 6 - BFS em Grafos: 

No contexto do seu aplicativo de navegação, implemente o algoritmo de Busca em Largura (BFS) para encontrar o caminho mais curto entre dois pontos em um grafo não ponderado, ajudando os usuários a escolherem a melhor rota.

```java
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
```

## 7 - DFS em Grafos: 

Continuando no seu aplicativo de navegação, escreva um método para realizar a Busca em Profundidade (DFS) em um grafo. Esse método deve exibir todos os vértices visitados, permitindo que os usuários visualizem as possíveis rotas de maneira mais detalhada. 

```java
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
```

### Aplicações e Desafios Combinados 


## 8 - Mínimo de uma BST: 

Imagine que você está analisando uma árvore binária de busca para identificar o produto mais barato de uma lista. Escreva um método que encontre o menor valor em uma árvore binária de busca, ajudando a determinar a melhor oferta disponível. 

```java
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
```

## 9 - Caminho Mais Curto em Grafo Ponderado: 

Ao modificar seu algoritmo BFS, você precisa encontrar o caminho mais curto entre dois pontos em um grafo ponderado, considerando as distâncias. Esse ajuste é crucial para garantir que os usuários recebam as rotas mais eficientes. 
```java
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
```

## 10 - Ordenação de Grafos: 

Por fim, implemente um algoritmo que ordene os vértices de um grafo de acordo com a topologia das conexões entre eles. Essa ordenação pode ser útil em diversas aplicações, como planejamento de projetos ou organização de tarefas interdependentes. 

```java
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
```


```java 
javac "src\Exercicio_3.java"

java -cp src Exercicio_3
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

### Grafos, BFS e DFS 

## 5 - Representação de Grafos: 

Você está desenvolvendo um sistema de roteamento para um aplicativo de navegação. Crie uma classe que represente um grafo em Java, utilizando lista de adjacências ou matriz de adjacências, para mapear as conexões entre diferentes locais. 

## 6 - BFS em Grafos: 

No contexto do seu aplicativo de navegação, implemente o algoritmo de Busca em Largura (BFS) para encontrar o caminho mais curto entre dois pontos em um grafo não ponderado, ajudando os usuários a escolherem a melhor rota.


## 7 - DFS em Grafos: 

Continuando no seu aplicativo de navegação, escreva um método para realizar a Busca em Profundidade (DFS) em um grafo. Esse método deve exibir todos os vértices visitados, permitindo que os usuários visualizem as possíveis rotas de maneira mais detalhada. 

### Aplicações e Desafios Combinados 


## 8 - Mínimo de uma BST: 

Imagine que você está analisando uma árvore binária de busca para identificar o produto mais barato de uma lista. Escreva um método que encontre o menor valor em uma árvore binária de busca, ajudando a determinar a melhor oferta disponível. 


## 9 - Caminho Mais Curto em Grafo Ponderado: 

Ao modificar seu algoritmo BFS, você precisa encontrar o caminho mais curto entre dois pontos em um grafo ponderado, considerando as distâncias. Esse ajuste é crucial para garantir que os usuários recebam as rotas mais eficientes. 


## 10 - Ordenação de Grafos: 

Por fim, implemente um algoritmo que ordene os vértices de um grafo de acordo com a topologia das conexões entre eles. Essa ordenação pode ser útil em diversas aplicações, como planejamento de projetos ou organização de tarefas interdependentes. 

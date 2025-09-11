## Exercício 1: Explorando Variáveis no Sistema de Reservas de um Hotel

Imagine que você está desenvolvendo um sistema de reservas para um hotel. Comece declarando variáveis que armazenem o número do quarto (número inteiro), o valor da diária (número com ponto flutuante) e o nome do hóspede (string). Atribua valores de exemplo a essas variáveis e exiba-os na tela.

```java 
/**
 * Solução para o Exercício 1 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 */
public class Exercicio1 {
    public static void main(String[] args) {
        System.out.println(); // Adiciona uma quebra de linha para formatar a saída.
        // 1. Declaração e atribuição das variáveis conforme solicitado.
        int numeroQuarto = 101; // Variável para o número do quarto (inteiro)
        double valorDiaria = 320.50; // Variável para o valor da diária (ponto flutuante)
        String nomeHospede = "Ana Carolina"; // Variável para o nome do hóspede (texto)

        // 2. Exibição dos valores armazenados na tela.
        System.out.println("===========================================");
        System.out.println("   Hotel Unyleya - Detalhes da Reserva");
        System.out.println("===========================================");
        System.out.println("Hóspede: " + nomeHospede);
        System.out.println("Quarto N°: " + numeroQuarto);
        
        // Formata a saída do valor para exibir como moeda (R$)
        System.out.println("Valor da Diária: " + String.format("R$ %.2f", valorDiaria));
        System.out.println("===========================================");
    }
}
```

## Exercício 2: Verificador de Características de Um Produto

Em uma loja, os produtos têm códigos específicos. Escreva um programa que, ao ler o código de um produto, indique se o número é par ou ímpar. Imprima o resultado na tela para ajudar o setor de inventário a identificar características básicas dos produtos.

```java
import java.util.Scanner;

/**
 * Solução para o Exercício 2 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Lê o código de um produto e verifica se é par ou ímpar.
 * (Versão interativa com entrada do usuário)
 */
public class Exercicio2 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para afastar a execução da linha de comando.
        System.out.println(); 
        
        // Cria um objeto Scanner para ler a entrada do teclado
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("==================================================");
        System.out.println("   Verificador de Código de Produto (Par/Ímpar)");
        System.out.println("==================================================");

        // Solicita ao usuário que insira o código do produto
        System.out.print("\nPor favor, digite o código do produto: ");
        
        // Lê o número inteiro fornecido pelo usuário
        int codigoProduto = scanner.nextInt();

        // Verifica se o código é par ou ímpar e exibe o resultado
        System.out.println("\nVerificando o código: " + codigoProduto);
        if (codigoProduto % 2 == 0) {
            System.out.println("-> Resultado: O código " + codigoProduto + " é PAR.");
        } else {
            System.out.println("-> Resultado: O código " + codigoProduto + " é ÍMPAR.");
        }
        
        System.out.println("\n==================================================");
        System.out.println(); 

        // Fecha o scanner para liberar os recursos
        scanner.close();
    }
}
```

## Exercício 3: Contagem de Clientes em um Estabelecimento

Imagine que você é responsável por registrar quantos clientes entram na loja a cada hora. Crie um programa que imprima os números de 1 a 10, simulando a contagem de clientes ao longo de uma hora.

```java
/**
 * Solução para o Exercício 3 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Simula a contagem de clientes que entram em uma loja, imprimindo os números de 1 a 10.
 */
public class Exercicio3 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println(); 

        System.out.println("==================================================");
        System.out.println("    Contagem de Clientes na Loja (Simulação)");
        System.out.println("==================================================");
        System.out.println();

        // Laço 'for' para simular a contagem de 10 clientes.
        for (int cliente = 1; cliente <= 10; cliente++) {
            System.out.println("-> Cliente de número " + cliente + " entrou na loja.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("      Fim da simulação da primeira hora.");
        System.out.println("==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println(); 
    }
}
```

## Exercício 4: Organizando uma Lista de Espera Dinâmica

Em um restaurante, há uma lista de espera para os clientes. Implemente um programa que permita adicionar e remover clientes dessa lista dinâmica. A cada alteração, exiba a lista atualizada para que os funcionários saibam quantos clientes estão aguardando.

```java
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solução para o Exercício 4 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Gerencia uma lista de espera de restaurante de forma dinâmica,
 * permitindo adicionar, remover e visualizar clientes.
 */
public class Exercicio4 {
    public static void main(String[] args) {
        // Usando try-with-resources para garantir que o Scanner seja fechado automaticamente.
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> listaDeEspera = new ArrayList<>();
            boolean executando = true;

            // Adiciona uma quebra de linha para limpar o terminal
            System.out.println();

            while (executando) {
                System.out.println("==================================================");
                System.out.println("      GESTÃO DA LISTA DE ESPERA DO RESTAURANTE");
                System.out.println("==================================================");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Remover Cliente");
                System.out.println("3. Visualizar Lista de Espera");
                System.out.println("0. Sair do Sistema");
                System.out.println("==================================================");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente após a leitura do número

                switch (opcao) {
                    case 1:
                        System.out.print("\nDigite o nome do cliente a ser adicionado: ");
                        String nomeCliente = scanner.nextLine();
                        listaDeEspera.add(nomeCliente);
                        System.out.println("-> Cliente '" + nomeCliente + "' adicionado com sucesso!\n");
                        break;

                    case 2:
                        if (listaDeEspera.isEmpty()) {
                            System.out.println("\n-> A lista de espera está vazia. Não há clientes para remover.\n");
                        } else {
                            System.out.println("\n--- Clientes na Fila (digite o número para remover) ---");
                            for (int i = 0; i < listaDeEspera.size(); i++) {
                                System.out.println(i + ": " + listaDeEspera.get(i));
                            }
                            System.out.print("\nDigite o número do cliente a ser removido: ");
                            int indiceRemover = scanner.nextInt();
                            if (indiceRemover >= 0 && indiceRemover < listaDeEspera.size()) {
                                String clienteRemovido = listaDeEspera.remove(indiceRemover);
                                System.out.println("-> Cliente '" + clienteRemovido + "' removido com sucesso!\n");
                            } else {
                                System.out.println("-> Número inválido. Tente novamente.\n");
                            }
                        }
                        break;

                    case 3:
                        if (listaDeEspera.isEmpty()) {
                            System.out.println("\n-> A lista de espera está vazia.\n");
                        } else {
                            System.out.println("\n--- Lista de Espera Atual ---");
                            for (int i = 0; i < listaDeEspera.size(); i++) {
                                System.out.println((i + 1) + ". " + listaDeEspera.get(i));
                            }
                            System.out.println("-----------------------------");
                            System.out.println("Total de " + listaDeEspera.size() + " cliente(s) aguardando.\n");
                        }
                        break;

                    case 0:
                        executando = false;
                        System.out.println("\n-> Encerrando o sistema de gestão. Até logo!\n");
                        break;

                    default:
                        System.out.println("\n-> Opção inválida. Por favor, escolha uma opção do menu.\n");
                        break;
                }
            }
        }
        // Adiciona uma quebra de linha final para limpar o terminal
        System.out.println();
    }
}
```

## Exercício 5: Avaliação de Notas dos Alunos

Você é responsável por calcular o desempenho dos alunos em uma avaliação. Crie uma matriz (array) com as notas dos alunos em uma prova e encontre a maior nota da turma, exibindo-a ao final.

```java
import java.util.Arrays;

/**
 * Solução para o Exercício 5 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra a maior nota em um array de notas de alunos
 * utilizando o método de ordenação da biblioteca Arrays.
 */
public class Exercicio5 {
    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println();

        // 1. Declaração e inicialização do array de notas.
        double[] notas = { 8.5, 9.0, 7.2, 6.5, 10.0, 8.8, 7.9, 9.4 };

        System.out.println("==================================================");
        System.out.println("          Analisador de Notas da Turma");
        System.out.println("==================================================");
        
        // Exibe as notas da turma para conferência.
        System.out.println("\nNotas da turma: " + Arrays.toString(notas));

        // 2. Ordena o array em ordem crescente.
        Arrays.sort(notas);

        // 3. A maior nota será o último elemento do array ordenado.
        double maiorNota = notas[notas.length - 1];

        // 4. Exibe o resultado.
        System.out.println("\nAnalisando as notas...");
        System.out.println("-> A maior nota da turma é: " + maiorNota);
        
        System.out.println("\n==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println();
    }
}

```

## Exercício 6: Seleção de Números Primos para Criptografia

Em um sistema de segurança, números primos são utilizados como base para a criptografia. Escreva um programa que encontre e exiba todos os números primos entre 1 e 100, ajudando a equipe de segurança a selecionar números para esse propósito.

```java
/**
 * Solução para o Exercício 6 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra e exibe todos os números primos entre 1 e 100
 * utilizando o método de verificação por divisão.
 */
public class Exercicio6 {

    public static void main(String[] args) {
        // Adiciona uma quebra de linha inicial para formatação.
        System.out.println();

        System.out.println("==================================================");
        System.out.println("    Números Primos para Criptografia (1 a 100)");
        System.out.println("==================================================");
        System.out.println("\nProcurando números primos no intervalo de 1 a 100...");
        System.out.println();

        int contadorDePrimos = 0;
        // Laço principal para percorrer os números de 1 a 100.
        for (int i = 1; i <= 100; i++) {
            if (ehPrimo(i)) {
                // Imprime o número primo encontrado, com um espaço.
                System.out.print(i + " ");
                contadorDePrimos++;
            }
        }

        System.out.println("\n\nTotal de " + contadorDePrimos + " números primos encontrados.");
        System.out.println("\n==================================================");
        
        // Adiciona uma quebra de linha final para formatação.
        System.out.println();
    }

    /**
     * Função auxiliar para verificar se um número é primo.
     * @param numero O número a ser verificado.
     * @return true se o número for primo, false caso contrário.
     */
    private static boolean ehPrimo(int numero) {
        // Números menores ou iguais a 1 não são primos.
        if (numero <= 1) {
            return false;
        }

        // Tenta dividir o número por todos os inteiros de 2 até sua raiz quadrada.
        // Se encontrarmos um divisor, o número não é primo.
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Não é primo.
            }
        }

        // Se o laço terminar sem encontrar divisores, o número é primo.
        return true;
    }
}
```

## Exercício 7: Jogo da Velha Interativo

Desenvolva um programa de Jogo da Velha (Tic-Tac-Toe) para uma brincadeira entre amigos. Utilize uma matriz para representar o tabuleiro e permita que dois jogadores façam jogadas alternadas, verificando quem será o vencedor!

```java
import java.util.Scanner;

/**
 * Solução para o Exercício 7 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Implementação de um Jogo da Velha (Tic-Tac-Toe) interativo
 * para dois jogadores em um único arquivo (abordagem procedural com funções auxiliares).
 */
public class Exercicio7 {

    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            inicializarTabuleiro();
            boolean jogoContinua = true;

            System.out.println();
            System.out.println("==================================================");
            System.out.println("             BEM-VINDO AO JOGO DA VELHA");
            System.out.println("==================================================");

            while (jogoContinua) {
                imprimirTabuleiro();
                System.out.println("\nÉ a vez do jogador '" + jogadorAtual + "'.");
                System.out.print("Escolha a linha (1-3) e coluna (1-3), separadas por espaço: ");

                int linha = scanner.nextInt() - 1;
                int coluna = scanner.nextInt() - 1;

                if (movimentoValido(linha, coluna)) {
                    tabuleiro[linha][coluna] = jogadorAtual;

                    if (verificarVencedor()) {
                        imprimirTabuleiro();
                        System.out.println("\n*** PARABÉNS! O JOGADOR '" + jogadorAtual + "' VENCEU! ***");
                        jogoContinua = false;
                    } else if (verificarEmpate()) {
                        imprimirTabuleiro();
                        System.out.println("\n--- O JOGO TERMINOU EM EMPATE! ---");
                        jogoContinua = false;
                    } else {
                        // Troca o jogador para a próxima rodada
                        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("\n*** Movimento inválido! A posição já está ocupada ou fora do tabuleiro. Tente novamente. ***");
                }
            }
            System.out.println("\n==================================================");
            System.out.println("                  FIM DE JOGO");
            System.out.println("==================================================");
            System.out.println();
        }
    }

    /**
     * Preenche o tabuleiro com espaços em branco no início do jogo.
     */
    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    /**
     * Imprime o estado atual do tabuleiro no console.
     */
    private static void imprimirTabuleiro() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
    }

    /**
     * Verifica se a jogada do usuário é válida.
     * @param linha A linha escolhida.
     * @param coluna A coluna escolhida.
     * @return true se a posição está dentro do tabuleiro e vazia.
     */
    private static boolean movimentoValido(int linha, int coluna) {
        return (linha >= 0 && linha < 3) && (coluna >= 0 && coluna < 3) && (tabuleiro[linha][coluna] == ' ');
    }

    /**
     * Verifica todas as condições de vitória para o jogador atual.
     * @return true se o jogador atual venceu.
     */
    private static boolean verificarVencedor() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) ||
                (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)) {
                return true;
            }
        }
        // Verificar diagonais
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
            (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o tabuleiro está completamente preenchido, resultando em empate.
     * @return true se não há mais espaços vazios.
     */
    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Ainda existe espaço vazio, o jogo não empatou.
                }
            }
        }
        return true; // Todos os espaços estão preenchidos.
    }
}

```

## Exercício 8: Análise de Frequência de Pedidos no Delivery

Em uma análise dos pedidos feitos por um cliente, você precisa descobrir qual item é mais pedido. Crie um programa que solicite uma lista de números (representando o código dos pedidos) e exiba o item que mais se repete na lista.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Solução para o Exercício 8 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Encontra o item (número) mais frequente em uma lista de pedidos
 * informada pelo usuário, utilizando um HashMap para a contagem.
 */
public class Exercicio8 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Integer> listaDePedidos = new ArrayList<>();

            System.out.println();
            System.out.println("==================================================");
            System.out.println("      Analisador de Frequência de Pedidos");
            System.out.println("==================================================");
            System.out.println("\nDigite os códigos dos pedidos um por um.");
            System.out.println("Digite '0' ou um número negativo para finalizar a entrada.");
            System.out.println("--------------------------------------------------");

            int pedido;
            while (true) {
                System.out.print("Digite o código do pedido: ");
                pedido = scanner.nextInt();
                if (pedido <= 0) {
                    break;
                }
                listaDePedidos.add(pedido);
            }

            if (listaDePedidos.isEmpty()) {
                System.out.println("\nNenhum pedido foi inserido. Encerrando.");
            } else {
                System.out.println("\nLista de pedidos recebida: " + listaDePedidos);
                
                // 1. Criar o HashMap para contar a frequência
                Map<Integer, Integer> frequenciaPedidos = new HashMap<>();
                for (int p : listaDePedidos) {
                    frequenciaPedidos.put(p, frequenciaPedidos.getOrDefault(p, 0) + 1);
                }

                // 2. Encontrar o item com a maior frequência no mapa
                int itemMaisFrequente = -1;
                int maiorFrequencia = 0;
                for (Map.Entry<Integer, Integer> entry : frequenciaPedidos.entrySet()) {
                    if (entry.getValue() > maiorFrequencia) {
                        maiorFrequencia = entry.getValue();
                        itemMaisFrequente = entry.getKey();
                    }
                }

                // 3. Exibir o resultado
                System.out.println("\nAnalisando a frequência...");
                System.out.println("-> O item mais pedido foi o de código '" + itemMaisFrequente + "', que apareceu " + maiorFrequencia + " vez(es).");
            }

            System.out.println("\n==================================================");
            System.out.println();
        }
    }
}
```

## Exercício 9: Gerenciando um Cadastro de Clientes

Em uma agência de viagens, você precisa organizar o cadastro dos clientes. Crie uma classe Pessoa com atributos como nome, idade e endereço, e desenvolva um programa que permita criar e gerenciar uma lista desses clientes.

```java
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solução para o Exercício 9 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 * 
 * Descrição: Programa para gerenciar um cadastro de clientes (objetos da classe Pessoa)
 * em uma lista dinâmica.
 */
public class Exercicio9 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Pessoa> listaDeClientes = new ArrayList<>();
            boolean executando = true;

            System.out.println();
            System.out.println("==================================================");
            System.out.println("         SISTEMA DE CADASTRO DE CLIENTES");
            System.out.println("==================================================");

            while (executando) {
                System.out.println("\n1. Adicionar Cliente");
                System.out.println("2. Remover Cliente");
                System.out.println("3. Visualizar Clientes Cadastrados");
                System.out.println("0. Sair do Sistema");
                System.out.println("==================================================");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        System.out.print("\nDigite o nome do cliente: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a idade do cliente: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha
                        System.out.print("Digite o endereço do cliente: ");
                        String endereco = scanner.nextLine();

                        // Cria um novo objeto Pessoa e o adiciona à lista
                        Pessoa novoCliente = new Pessoa(nome, idade, endereco);
                        listaDeClientes.add(novoCliente);
                        System.out.println("-> Cliente '" + nome + "' cadastrado com sucesso!\n");
                        break;

                    case 2:
                        if (listaDeClientes.isEmpty()) {
                            System.out.println("\n-> Não há clientes para remover.\n");
                        } else {
                            System.out.println("\n--- Clientes Cadastrados (digite o número para remover) ---");
                            for (int i = 0; i < listaDeClientes.size(); i++) {
                                System.out.println(i + ": " + listaDeClientes.get(i).getNome());
                            }
                            System.out.print("\nDigite o número do cliente a ser removido: ");
                            int indiceRemover = scanner.nextInt();
                            if (indiceRemover >= 0 && indiceRemover < listaDeClientes.size()) {
                                Pessoa clienteRemovido = listaDeClientes.remove(indiceRemover);
                                System.out.println("-> Cliente '" + clienteRemovido.getNome() + "' removido com sucesso!\n");
                            } else {
                                System.out.println("-> Número inválido. Tente novamente.\n");
                            }
                        }
                        break;

                    case 3:
                        if (listaDeClientes.isEmpty()) {
                            System.out.println("\n-> Nenhum cliente cadastrado no sistema.\n");
                        } else {
                            System.out.println("\n--- Lista de Clientes Cadastrados ---");
                            for (Pessoa cliente : listaDeClientes) {
                                // O método toString() da classe Pessoa é chamado automaticamente aqui
                                System.out.println(cliente);
                            }
                            System.out.println("-------------------------------------");
                            System.out.println("Total de " + listaDeClientes.size() + " cliente(s) no sistema.\n");
                        }
                        break;

                    case 0:
                        executando = false;
                        System.out.println("\n-> Encerrando o sistema. Até mais!\n");
                        break;

                    default:
                        System.out.println("\n-> Opção inválida. Por favor, escolha uma opção do menu.\n");
                        break;
                }
            }
        }
        System.out.println();
    }
}

Pessoa.java

/**
 * Classe de modelo para representar um Cliente (Pessoa).
 * Usada no Exercício 9.
 * Autor: Rogério
 */
public class Pessoa {
    private String nome;
    private int idade;
    private String endereco;

    // Construtor da classe Pessoa
    public Pessoa(String nome, int idade, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Métodos "getters" para acessar os atributos privados
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    /**
     * Sobrescreve o método toString para fornecer uma representação em texto do objeto.
     * Isso é útil para imprimir os detalhes do cliente de forma legível.
     */
    @Override
    public String toString() {
        return "Cliente: " + nome + " | Idade: " + idade + " | Endereço: " + endereco;
    }
}
```

## Exercício 10: Desafio Final - Sistema de Gerenciamento de Estoque para uma Loja

Desenvolva um sistema completo de gerenciamento de estoque para uma loja. Permita que o usuário adicione, remova e atualize itens no estoque, usando classes para representar os produtos e listas para organizar os itens cadastrados. Esse sistema ajudará na administração dos produtos disponíveis para venda.

 ```java
package Exercicio10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Solução para o Exercício 10 da Unidade 01 de Algoritmos e Estrutura de Dados.
 * Autor: Rogério
 */

public class GerenciadorDeEstoque {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<ItemEstoque> estoque = new ArrayList<>();
            boolean continuar = true;
                // Adiciona uma quebra de linha inicial para afastar a execução da linha de comando.
                System.out.println(); 
            while (continuar) {
                // 2. Exibição dos valores armazenados na tela.
                System.out.println("===========================================");
                System.out.println("   Sistema de Gerenciamento de Estoque");
                System.out.println("===========================================");
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Adicionar item ao estoque");
                System.out.println("2 - Remover item do estoque");
                System.out.println("3 - Atualizar quantidade de um item");
                System.out.println("4 - Mostrar estoque");
                System.out.println("5 - Sair");
                System.out.print("Opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite o nome do item a ser adicionado: ");
                        String nomeNovoItem = scanner.nextLine();
                        System.out.print("Digite a quantidade inicial: ");
                        int quantidadeInicial = scanner.nextInt();
                        ItemEstoque novoItem = new ItemEstoque(nomeNovoItem, quantidadeInicial);
                        estoque.add(novoItem);
                        System.out.println("Item adicionado ao estoque.");
                    }
                    case 2 -> {
                        if (estoque.isEmpty()) {
                            System.out.println("O estoque está vazio. Nada para remover.");
                        } else {
                            System.out.print("Digite o nome do item a ser removido: ");
                            String nomeItemRemover = scanner.nextLine();
                            // Remoção segura para evitar ConcurrentModificationException
                            boolean removido = estoque.removeIf(item -> item.getNome().equalsIgnoreCase(nomeItemRemover));
                            if (removido) {
                                System.out.println("Item removido do estoque.");
                            } else {
                                System.out.println("Item não encontrado no estoque.");
                            }
                        }
                    }
                    case 3 -> {
                        if (estoque.isEmpty()) {
                            System.out.println("O estoque está vazio. Nada para atualizar.");
                        } else {
                            System.out.print("Digite o nome do item a ser atualizado: ");
                            String nomeItemAtualizar = scanner.nextLine();
                            // Busca case-insensitive para melhor UX
                            Optional<ItemEstoque> itemParaAtualizar = estoque.stream()
                                    .filter(item -> item.getNome().equalsIgnoreCase(nomeItemAtualizar))
                                    .findFirst();
                            if (itemParaAtualizar.isPresent()) {
                                ItemEstoque item = itemParaAtualizar.get();
                                System.out.print("Digite a nova quantidade: ");
                                int novaQuantidade = scanner.nextInt();
                                item.setQuantidade(novaQuantidade);
                                System.out.println("Quantidade do item atualizada.");
                            } else {
                                System.out.println("Item não encontrado no estoque.");
                            }
                        }
                    }
                    case 4 -> {
                        if (estoque.isEmpty()) {
                            System.out.println("O estoque está vazio.");
                        } else {
                            System.out.println("Estoque:");
                            for (ItemEstoque item : estoque) {
                                System.out.println(item); // Usa o método toString()
                            }
                        }
                    }
                    case 5 -> {
                        continuar = false;
                        System.out.println("Encerrando o sistema.");
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }
}

ItemEstoque.java

package Exercicio10;

public class ItemEstoque {
    private final String nome;
    private int quantidade;

    public ItemEstoque(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + nome + ", Quantidade: " + quantidade;
    }
}
```

Instruções de Entrega:

- Realize todos os exercícios da lista na linguagem de Programação JAVA e aplique os conceitos solicitados em cada questão.

- Envie o código para análise, pode ser por repositório do GitHub ou a pasta compactada com seu código.
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

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

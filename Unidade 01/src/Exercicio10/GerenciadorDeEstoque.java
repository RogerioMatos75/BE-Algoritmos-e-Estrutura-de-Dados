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

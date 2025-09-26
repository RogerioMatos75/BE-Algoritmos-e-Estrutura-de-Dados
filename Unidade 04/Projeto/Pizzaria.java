package Projeto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Projeto.Pizza.TamanhoPizza;

public class Pizzaria {
    public static void main(String[] args) {
        List<Cliente> listaClientes = new ArrayList<>();
        List<Pedido> listaPedidos = new ArrayList<>();

        // Dados de exemplo para teste
        Cliente clienteTeste = new Cliente("Rogerio", "Rua Teste, 123", "11999998888", "rogerio@teste.com");
        listaClientes.add(clienteTeste);
        List<Pizza> pizzasTeste = new ArrayList<>();
        List<String> saboresTeste = new ArrayList<>();
        saboresTeste.add("Pepperoni");
        saboresTeste.add("Mussarela");
        pizzasTeste.add(new Pizza(saboresTeste, 32.50, TamanhoPizza.GRANDE));
        Pedido pedidoTeste = new Pedido(1, clienteTeste, pizzasTeste, 32.50);
        listaPedidos.add(pedidoTeste);

        boolean continuar = true;
        while (continuar) {
            String menu = "Escolha uma opção:\n"
                        + "1 - Fazer um novo pedido\n"
                        + "2 - Alterar um pedido\n"
                        + "3 - Adicionar um cliente\n"
                        + "4 - Gerar relatório de vendas\n"
                        + "5 - Gerar lista de clientes\n"
                        + "9 - Sair";
            
            String input = JOptionPane.showInputDialog(null, menu, "Pizzaria Zilion Forces", JOptionPane.PLAIN_MESSAGE);

            if (input == null) {
                continuar = false;
                continue;
            }

            try {
                int opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        fazerPedido(listaPedidos, listaClientes); 
                        break;
                    case 2:
                        alterarPedido(listaPedidos);
                        break;
                    case 3:
                        Cliente novoCliente = adicionarCliente(); 
                        if (novoCliente != null) {
                            listaClientes.add(novoCliente);
                            JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
                        }
                        break;
                    case 4:
                        gerarRelatorio();
                        break;
                    case 5:
                        gerarListaClientes(listaClientes);
                        break;
                    case 9:
                        JOptionPane.showMessageDialog(null, "Até amanhã...");
                        continuar = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void fazerPedido(List<Pedido> listaPedidos, List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário cadastrar um cliente antes de fazer um pedido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Selecionar Cliente
        String[] nomesClientes = listaClientes.stream().map(Cliente::getNome).toArray(String[]::new);
        String nomeSelecionado = (String) JOptionPane.showInputDialog(null, "Selecione o cliente:",
                "Fazer Pedido", JOptionPane.QUESTION_MESSAGE, null, nomesClientes, nomesClientes[0]);
        if (nomeSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Pedido cancelado.");
            return;
        }
        Cliente clienteSelecionado = null;
        for (Cliente c : listaClientes) {
            if (c.getNome().equals(nomeSelecionado)) {
                clienteSelecionado = c;
                break;
            }
        }

        // 2. Adicionar Pizzas
        List<Pizza> pizzasDoPedido = new ArrayList<>();
        boolean continuarAdicionando = true;
        while (continuarAdicionando) {
            Pizza novaPizza = criarNovaPizza();
            if (novaPizza != null) {
                pizzasDoPedido.add(novaPizza);
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja adicionar outra pizza a este pedido?", "Fazer Pedido", JOptionPane.YES_NO_OPTION);
                if (resposta != JOptionPane.YES_OPTION) {
                    continuarAdicionando = false;
                }
            } else {
                // Usuário cancelou a criação da pizza
                continuarAdicionando = false;
            }
        }

        // 3. Finalizar Pedido
        if (!pizzasDoPedido.isEmpty()) {
            double valorTotal = somarPizzas(pizzasDoPedido);
            int novoId = listaPedidos.size() + 1;
            Pedido novoPedido = new Pedido(novoId, clienteSelecionado, pizzasDoPedido, valorTotal);
            listaPedidos.add(novoPedido);
            JOptionPane.showMessageDialog(null, "Pedido Nº " + novoId + " realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma pizza foi adicionada. Pedido não foi criado.");
        }
    }

    private static double somarPizzas(List<Pizza> pizzas) {
        double valorTotal = 0;
        for (Pizza pizza : pizzas) {
            valorTotal += pizza.getPreco();
        }
        return valorTotal;
    }

    private static void alterarPedido(List<Pedido> listaPedidos) {
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há pedidos para alterar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] opcoesBusca = {"ID do Pedido", "Nome do Cliente"};
        int tipoBusca = JOptionPane.showOptionDialog(null, "Como deseja buscar o pedido?", "Alterar Pedido", 
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                                                    null, opcoesBusca, opcoesBusca[0]);

        if (tipoBusca == -1) return;

        String valorBusca = JOptionPane.showInputDialog("Digite o " + opcoesBusca[tipoBusca] + ":");
        if (valorBusca == null || valorBusca.trim().isEmpty()) return;

        Pedido pedidoEncontrado = null;
        if (tipoBusca == 0) {
            try {
                int idBusca = Integer.parseInt(valorBusca);
                for (Pedido pedido : listaPedidos) {
                    if (pedido.getId() == idBusca) {
                        pedidoEncontrado = pedido;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            for (Pedido pedido : listaPedidos) {
                if (pedido.getCliente().getNome().equalsIgnoreCase(valorBusca)) {
                    pedidoEncontrado = pedido;
                    break;
                }
            }
        }

        if (pedidoEncontrado != null) {
            boolean continuarAlterando = true;
            while (continuarAlterando) {
                String detalhes = "Pedido Encontrado!\n"
                                + "ID: " + pedidoEncontrado.getId() + "\n"
                                + "Cliente: " + pedidoEncontrado.getCliente().getNome() + "\n"
                                + "Pizzas: " + pedidoEncontrado.getPizzas().size() + "\n"
                                + "Valor Total: R$" + String.format("%.2f", pedidoEncontrado.getValorTotal()) + "\n\n";

                String[] opcoes = {"Adicionar Pizza", "Remover Pizza", "Alterar Sabor da Pizza", "Voltar"};
                int escolha = JOptionPane.showOptionDialog(null, detalhes + "O que você deseja fazer?", 
                                                        "Alterar Pedido", JOptionPane.DEFAULT_OPTION, 
                                                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

                switch (escolha) {
                    case 0: // Adicionar Pizza
                        Pizza novaPizza = criarNovaPizza();
                        if (novaPizza != null) {
                            pedidoEncontrado.getPizzas().add(novaPizza);
                            pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()));
                            JOptionPane.showMessageDialog(null, "Pizza adicionada com sucesso!");
                        }
                        break;
                    case 1: // Remover Pizza
                        if (pedidoEncontrado.getPizzas().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não há pizzas neste pedido para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        List<String> pizzasDescricao = new ArrayList<>();
                        for (int i = 0; i < pedidoEncontrado.getPizzas().size(); i++) {
                            Pizza p = pedidoEncontrado.getPizzas().get(i);
                            pizzasDescricao.add("Pizza " + (i + 1) + ": " + p.getTamanho() + " - " + String.join(", ", p.getSabores()));
                        }
                        String pizzaSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione a pizza para remover:",
                                "Remover Pizza", JOptionPane.QUESTION_MESSAGE, null, pizzasDescricao.toArray(), pizzasDescricao.get(0));
                        if (pizzaSelecionada != null) {
                            int indiceParaRemover = pizzasDescricao.indexOf(pizzaSelecionada);
                            if (indiceParaRemover != -1) {
                                pedidoEncontrado.getPizzas().remove(indiceParaRemover);
                                pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()));
                                JOptionPane.showMessageDialog(null, "Pizza removida com sucesso!");
                            }
                        }
                        break;
                    case 2: // Alterar Sabor da Pizza
                        if (pedidoEncontrado.getPizzas().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não há pizzas neste pedido para alterar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        List<String> pizzasDescricaoAlt = new ArrayList<>();
                        for (int i = 0; i < pedidoEncontrado.getPizzas().size(); i++) {
                            Pizza p = pedidoEncontrado.getPizzas().get(i);
                            pizzasDescricaoAlt.add("Pizza " + (i + 1) + ": " + String.join(", ", p.getSabores()));
                        }
                        String pizzaSelParaAlterar = (String) JOptionPane.showInputDialog(null, "Selecione a pizza para alterar o sabor:",
                                "Alterar Sabor", JOptionPane.QUESTION_MESSAGE, null, pizzasDescricaoAlt.toArray(), pizzasDescricaoAlt.get(0));
                        if (pizzaSelParaAlterar == null) break;
                        int indicePizzaAlterar = pizzasDescricaoAlt.indexOf(pizzaSelParaAlterar);
                        Pizza pizzaParaAlterar = pedidoEncontrado.getPizzas().get(indicePizzaAlterar);
                        if (pizzaParaAlterar.getSabores().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Esta pizza não tem sabores para alterar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        String saborAntigo = (String) JOptionPane.showInputDialog(null, "Selecione o sabor para substituir:",
                                "Alterar Sabor", JOptionPane.QUESTION_MESSAGE, null, pizzaParaAlterar.getSabores().toArray(), pizzaParaAlterar.getSabores().get(0));
                        if (saborAntigo == null) break;
                        Cardapio cardapio = new Cardapio();
                        List<String> saboresDisponiveis = new ArrayList<>(cardapio.getCardapio().keySet());
                        String novoSabor = (String) JOptionPane.showInputDialog(null, "Selecione o novo sabor:",
                                "Alterar Sabor", JOptionPane.QUESTION_MESSAGE, null, saboresDisponiveis.toArray(), saboresDisponiveis.get(0));
                        if (novoSabor == null) break;
                        int indiceSaborAntigo = pizzaParaAlterar.getSabores().indexOf(saborAntigo);
                        pizzaParaAlterar.getSabores().set(indiceSaborAntigo, novoSabor);
                        double novoPrecoPizza = cardapio.getPrecoJusto(pizzaParaAlterar.getSabores());
                        pizzaParaAlterar.setPreco(novoPrecoPizza);
                        pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()));
                        JOptionPane.showMessageDialog(null, "Sabor alterado com sucesso!");
                        break;
                    case 3:
                    case -1:
                        continuarAlterando = false;
                        break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum pedido encontrado com o critério informado.", "Não Encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static Pizza criarNovaPizza() {
        Cardapio cardapio = new Cardapio();
        TamanhoPizza[] tamanhos = TamanhoPizza.values();
        TamanhoPizza tamanhoSelecionado = (TamanhoPizza) JOptionPane.showInputDialog(null, "Selecione o tamanho da pizza:",
                "Nova Pizza", JOptionPane.QUESTION_MESSAGE, null, tamanhos, tamanhos[0]);
        if (tamanhoSelecionado == null) return null;
        Integer[] quantSaboresOptions = {1, 2, 3, 4};
        Integer quantiSabores = (Integer) JOptionPane.showInputDialog(null, "Selecione a quantidade de sabores:",
                "Nova Pizza", JOptionPane.QUESTION_MESSAGE, null, quantSaboresOptions, quantSaboresOptions[0]);
        if (quantiSabores == null) return null;
        List<String> saboresDisponiveis = new ArrayList<>(cardapio.getCardapio().keySet());
        List<String> saboresSelecionados = new ArrayList<>();
        for (int i = 0; i < quantiSabores; i++) {
            String sabor = (String) JOptionPane.showInputDialog(null, "Selecione o " + (i + 1) + "º sabor:",
                    "Nova Pizza", JOptionPane.QUESTION_MESSAGE, null, saboresDisponiveis.toArray(), saboresDisponiveis.get(0));
            if (sabor == null) return null;
            saboresSelecionados.add(sabor);
        }
        double preco = cardapio.getPrecoJusto(saboresSelecionados);
        return new Pizza(saboresSelecionados, preco, tamanhoSelecionado);
    }

    private static Cliente adicionarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        if (nome == null || nome.trim().isEmpty()) return null;
        String endereco = JOptionPane.showInputDialog("Digite o endereço do cliente:");
        if (endereco == null || endereco.trim().isEmpty()) return null;
        String telefone = JOptionPane.showInputDialog("Digite o telefone do cliente:");
        if (telefone == null || telefone.trim().isEmpty()) return null;
        String email = JOptionPane.showInputDialog("Digite o email do cliente:");
        if (email == null || email.trim().isEmpty()) return null;
        return new Cliente(nome, endereco, telefone, email);
    }

    private static void gerarRelatorio() {
        System.out.println("Gerar relatorio (NECESSITA IMPLEMENTAÇÃO)");
    }

    private static void gerarListaClientes(List<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A lista de clientes está vazia.");
            return;
        }
        StringBuilder sb = new StringBuilder("Lista de Clientes:\n\n");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            sb.append("CLIENTE ").append(i + 1).append("\n");
            sb.append("Nome: ").append(c.getNome()).append("\n");
            sb.append("Endereço: ").append(c.getEndereco()).append("\n");
            sb.append("Telefone: ").append(c.getTelefone()).append("\n");
            sb.append("Email: ").append(c.getEmail()).append("\n");
            sb.append("--------------------");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
}

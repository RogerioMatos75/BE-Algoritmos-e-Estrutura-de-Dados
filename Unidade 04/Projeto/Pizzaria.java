package Projeto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Projeto.Pizza.TamanhoPizza;

public class Pizzaria {
    public static void main(String[] args) {
        // Carrega os dados dos arquivos JSON ao iniciar
        List<Cliente> listaClientes = DataManager.carregarClientes();
        List<Pedido> listaPedidos = DataManager.carregarPedidos();

        // Se não houver dados de clientes, adiciona um de exemplo para começar
        if (listaClientes.isEmpty()) {
            Cliente clienteTeste = new Cliente("Admin", "Rua Principal, 0", "00000000000", "admin@pizzaria.com");
            listaClientes.add(clienteTeste);
            JOptionPane.showMessageDialog(null, "Bem-vindo! Como não havia dados, um cliente 'Admin' foi criado para você começar.");
        }

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
                        gerarRelatorio(listaPedidos);
                        break;
                    case 5:
                        gerarListaClientes(listaClientes);
                        break;
                    case 9:
                        // Salva os dados antes de sair
                        DataManager.salvarDados(listaClientes, listaPedidos);
                        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso! Até amanhã...");
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
            double valorPizzas = somarPizzas(pizzasDoPedido);
            
            // --- Início da Lógica de Frete (com validação) ---
            double valorFrete = 0;
            int confirmaFrete = JOptionPane.showConfirmDialog(null, "Deseja incluir entrega (cálculo de frete)?", "Entrega", JOptionPane.YES_NO_OPTION);
            if (confirmaFrete == JOptionPane.YES_OPTION) {
                double distancia = -1;
                while (distancia < 0) {
                    String distanciaInput = JOptionPane.showInputDialog(null, "Digite a distância em km (apenas números, ex: 2,5):", "Cálculo de Frete", JOptionPane.QUESTION_MESSAGE);

                    if (distanciaInput == null) { // Usuário cancelou
                        distancia = -2; // Valor especial para sair do loop
                        valorFrete = 0;
                        JOptionPane.showMessageDialog(null, "Cálculo de frete cancelado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    try {
                        // Limpa a entrada do usuário para extrair apenas os números
                        String entradaLimpa = distanciaInput.trim()
                                                            .toLowerCase()
                                                            .replace("quilometros", "")
                                                            .replace("quilometro", "")
                                                            .replace("km", "")
                                                            .replace(',', '.');
                        
                        distancia = Double.parseDouble(entradaLimpa);

                        if (distancia < 0) {
                            JOptionPane.showMessageDialog(null, "A distância não pode ser um número negativo. Tente novamente.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite apenas o número da distância.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        distancia = -1; // Garante que o loop continue
                    }
                }

                if (distancia >= 0) {
                    valorFrete = calcularFrete(distancia, pizzasDoPedido.size());
                    String mensagemFrete = String.format("Valor do Frete: R$ %.2f\nValor Total (com frete): R$ %.2f", valorFrete, (valorPizzas + valorFrete));
                    JOptionPane.showMessageDialog(null, mensagemFrete, "Custo da Entrega", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            // --- Fim da Lógica de Frete ---

            double valorTotalFinal = valorPizzas + valorFrete;
            int novoId = listaPedidos.stream().mapToInt(Pedido::getId).max().orElse(0) + 1; // ID mais seguro
            Pedido novoPedido = new Pedido(novoId, clienteSelecionado, pizzasDoPedido, valorTotalFinal, valorFrete);
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

    private static double calcularFrete(double distancia, int quantidadePizzas) {
        double taxaFixa = 3.00;
        double custoPorKm = 0.50;
        double custoPorPizza = 0.25;
        return taxaFixa + (distancia * custoPorKm) + (quantidadePizzas * custoPorPizza);
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

        // Bloco de melhoria: Exibir pedidos antes de solicitar o ID
        if (tipoBusca == 0) { // Se a busca for por ID
            StringBuilder sb = new StringBuilder("--- Pedidos Atuais ---\n\n");
            for (Pedido p : listaPedidos) {
                sb.append("ID: ").append(p.getId())
                  .append(", Cliente: ").append(p.getCliente().getNome())
                  .append(", Valor: R$").append(String.format("%.2f", p.getValorTotal()))
                  .append("\n");
            }
            sb.append("\n--------------------------\n");

            JTextArea textArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
        }

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
                            pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()) + pedidoEncontrado.getValorFrete());
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
                                pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()) + pedidoEncontrado.getValorFrete());
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
                        pedidoEncontrado.setValorTotal(somarPizzas(pedidoEncontrado.getPizzas()) + pedidoEncontrado.getValorFrete());
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

    private static void gerarRelatorio(List<Pedido> listaPedidos) {
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há pedidos para gerar um relatório.", "Relatório de Vendas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Relatorio relatorio = new Relatorio(listaPedidos);
        String relatorioConteudo = relatorio.gerar();

        JTextArea textArea = new JTextArea(relatorioConteudo);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Relatório de Vendas", JOptionPane.INFORMATION_MESSAGE);
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

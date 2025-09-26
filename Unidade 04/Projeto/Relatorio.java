package Projeto;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Relatorio {

    private final List<Pedido> pedidos;
    private double faturamentoTotal = 0.0;
    private final Map<String, Integer> contagemSabores = new HashMap<>();
    private final Map<String, Map<String, Integer>> grafoConexoes = new HashMap<>();

    public Relatorio(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String gerar() {
        if (pedidos == null || pedidos.isEmpty()) {
            return "Não há pedidos para gerar um relatório.";
        }

        processarPedidos();
        return formatarRelatorio();
    }

    private void processarPedidos() {
        for (Pedido pedido : pedidos) {
            // 1. Calcular Faturamento
            faturamentoTotal += pedido.getValorTotal();

            for (Pizza pizza : pedido.getPizzas()) {
                List<String> sabores = pizza.getSabores();

                // 2. Contar Sabores
                for (String sabor : sabores) {
                    contagemSabores.put(sabor, contagemSabores.getOrDefault(sabor, 0) + 1);
                }

                // 3. Construir Grafo de Conexões (para pizzas com mais de 1 sabor)
                if (sabores.size() > 1) {
                    for (int i = 0; i < sabores.size(); i++) {
                        for (int j = i + 1; j < sabores.size(); j++) {
                            String sabor1 = sabores.get(i);
                            String sabor2 = sabores.get(j);

                            // Garante que o grafo é não-direcionado
                            incrementarConexao(sabor1, sabor2);
                            incrementarConexao(sabor2, sabor1);
                        }
                    }
                }
            }
        }
    }

    private void incrementarConexao(String saborA, String saborB) {
        grafoConexoes.putIfAbsent(saborA, new HashMap<>());
        Map<String, Integer> conexoes = grafoConexoes.get(saborA);
        conexoes.put(saborB, conexoes.getOrDefault(saborB, 0) + 1);
    }

    private String formatarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório de Vendas ---\n\n");

        // Faturamento
        sb.append(String.format(">> Faturamento Total: R$ %.2f\n\n", faturamentoTotal));

        // Ranking de Sabores
        sb.append(">> Ranking de Sabores Mais Pedidos:\n");
        if (contagemSabores.isEmpty()) {
            sb.append("Nenhum sabor foi pedido.\n");
        } else {
            // Ordena o mapa de sabores por valor (contagem)
            Map<String, Integer> rankingSabores = contagemSabores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            int rank = 1;
            for (Map.Entry<String, Integer> entry : rankingSabores.entrySet()) {
                sb.append(String.format("%d. %s (%d vezes)\n", rank++, entry.getKey(), entry.getValue()));
            }
        }
        sb.append("\n");

        // Conexões de Sabores (Grafo)
        sb.append(">> Conexões de Sabores (Pedidos em Conjunto):\n");
        if (grafoConexoes.isEmpty()) {
            sb.append("Nenhuma conexão entre sabores foi encontrada.\n");
        } else {
            // Para evitar duplicidade na exibição (A-B e B-A), usamos um truque
            List<String> conexoesImpressas = new ArrayList<>();
            for (Map.Entry<String, Map<String, Integer>> entry : grafoConexoes.entrySet()) {
                String saborBase = entry.getKey();
                for (Map.Entry<String, Integer> conexao : entry.getValue().entrySet()) {
                    String saborConectado = conexao.getKey();
                    String conexaoId = saborBase.compareTo(saborConectado) > 0 ? saborConectado + "-" + saborBase : saborBase + "-" + saborConectado;
                    if (!conexoesImpressas.contains(conexaoId)) {
                         sb.append(String.format("- '%s' e '%s' foram pedidos juntos %d vez(es).\n", saborBase, saborConectado, conexao.getValue()));
                         conexoesImpressas.add(conexaoId);
                    }
                }
            }
        }
        sb.append("\n--------------------------\n");

        return sb.toString();
    }
}

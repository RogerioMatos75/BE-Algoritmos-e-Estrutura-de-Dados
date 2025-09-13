import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Arrays;

/**
 * Solução para o Exercício 7 da Unidade 02 de Algoritmos e Estrutura de Dados.
 *
 * Descrição: Crie um programa que simule a ordem de chegada e exiba a ordem 
 * de saída de clientes em uma loja, respeitando a fila de atendimento.
*
 * "Além da solução padrão solicitada, criarei também uma versão
 * `Exercicio_7_Dialogo.java` com interface gráfica (JOptionPane) para
 * demonstrar a aplicação do algoritmo em um contexto com interação visual com o
 * usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_7 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial no terminal

        // A fila de clientes, implementada com LinkedList.
        Queue<String> filaClientes = new LinkedList<>();
        
        // Lista de clientes que chegarão à loja para a simulação.
        List<String> clientesChegando = Arrays.asList("Mariana", "Felipe", "Juliana", "Ricardo", "Patrícia");

        // --- 1. Simulação da Chegada dos Clientes ---
        System.out.println("===============================================");
        System.out.println("    LOJA ABERTA - Início da Simulação de Fila");
        System.out.println("===============================================");
        System.out.println();

        System.out.println("--- Chegada dos Clientes ---");
        for (String cliente : clientesChegando) {
            filaClientes.add(cliente); // Adiciona o cliente ao final da fila.
            System.out.println("-> O cliente '" + cliente + "' chegou e entrou na fila.");
        }
        
        System.out.println();
        System.out.println("Fila de espera atual: " + filaClientes);
        System.out.println();


        // --- 2. Simulação do Atendimento (Saída dos Clientes) ---
        System.out.println("--- Atendimento dos Clientes (Ordem de Saída) ---");
        int clienteNumero = 1;
        // O loop continua enquanto houver clientes na fila.
        while (!filaClientes.isEmpty()) {
            // poll() remove e retorna o cliente do início da fila (o que chegou primeiro).
            String clienteAtendido = filaClientes.poll(); 
            System.out.println("<- Atendimento #" + clienteNumero + ": O cliente '" + clienteAtendido + "' foi atendido e saiu.");
            clienteNumero++;
        }

        System.out.println();
        System.out.println("Todos os clientes foram atendidos!");
        System.out.println("Fila de espera atual: " + filaClientes);
        
        System.out.println();
        System.out.println("===============================================");
        System.out.println("      FIM DA SIMULAÇÃO - Loja Fechada");
        System.out.println("===============================================");
        System.out.println();
    }
}

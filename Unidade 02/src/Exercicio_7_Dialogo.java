import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Arrays;

public class Exercicio_7_Dialogo {

    public static void main(String[] args) {
        // A fila de clientes e a lista de clientes que vão chegar.
        Queue<String> filaClientes = new LinkedList<>();
        List<String> clientesChegando = Arrays.asList("Mariana", "Felipe", "Juliana", "Ricardo", "Patrícia");

        // --- 1. Início e Simulação da Chegada ---
        JOptionPane.showMessageDialog(null, 
            "Iniciando a Simulação de Fila na Loja...\n\nClique em 'OK' para ver as chegadas.", 
            "LOJA ABERTA", 
            JOptionPane.INFORMATION_MESSAGE);

        // Constrói uma única string com todas as chegadas.
        StringBuilder logChegadas = new StringBuilder("---\n-- Chegada dos Clientes ---\n\n");
        for (String cliente : clientesChegando) {
            filaClientes.add(cliente);
            logChegadas.append("-> O cliente '").append(cliente).append("' chegou e entrou na fila.\n");
        }
        
        // Mostra o log de chegadas em um único diálogo.
        JOptionPane.showMessageDialog(null, logChegadas.toString(), "Log de Chegadas", JOptionPane.PLAIN_MESSAGE);

        // Mostra o estado da fila após as chegadas.
        JOptionPane.showMessageDialog(null, 
            "Fila de espera atual após as chegadas:\n\n" + filaClientes.toString(), 
            "Estado da Fila", 
            JOptionPane.PLAIN_MESSAGE);


        // --- 2. Simulação do Atendimento (Saída) ---
        JOptionPane.showMessageDialog(null, 
            "Agora, vamos iniciar os atendimentos.\n\nClique em 'OK' para continuar.", 
            "Início dos Atendimentos", 
            JOptionPane.INFORMATION_MESSAGE);

        // Constrói uma única string com todos os atendimentos.
        StringBuilder logAtendimentos = new StringBuilder("---\n-- Atendimento dos Clientes (Ordem de Saída) ---\n\n");
        int clienteNumero = 1;
        while (!filaClientes.isEmpty()) {
            String clienteAtendido = filaClientes.poll(); 
            logAtendimentos.append("<- Atendimento #").append(clienteNumero).append(": O cliente '").append(clienteAtendido).append("' foi atendido e saiu.\n");
            clienteNumero++;
        }

        // Mostra o log de atendimentos em um único diálogo.
        JOptionPane.showMessageDialog(null, logAtendimentos.toString(), "Log de Atendimentos", JOptionPane.PLAIN_MESSAGE);

        // --- 3. Fim da Simulação ---
        JOptionPane.showMessageDialog(null, 
            "Todos os clientes foram atendidos!\n\nFila de espera final: " + filaClientes.toString(), 
            "FIM DA SIMULAÇÃO", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}

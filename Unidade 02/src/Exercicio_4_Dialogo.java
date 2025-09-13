import javax.swing.JOptionPane;
import java.util.ArrayDeque;
import java.util.Deque;

public class Exercicio_4_Dialogo {

    public static void main(String[] args) {
        // Loop infinito para permitir que o usuário teste várias expressões.
        while (true) {
            // 1. Pede ao usuário para inserir uma expressão.
            String expressao = JOptionPane.showInputDialog(
                null,
                "Digite uma expressão matemática (ou cancele para sair):",
                "Verificador de Parênteses",
                JOptionPane.QUESTION_MESSAGE
            );

            // 2. Se o usuário cancelar ou fechar a caixa, o programa termina.
            if (expressao == null) {
                break;
            }

            // 3. Chama o método de verificação.
            boolean balanceada = verificarParenteses(expressao);

            // 4. Monta a mensagem de resultado.
            String resultado;
            if (balanceada) {
                resultado = "A expressão está com os parênteses balanceados.";
            } else {
                resultado = "A expressão NÃO está com os parênteses balanceados.";
            }

            // 5. Exibe o resultado para o usuário.
            JOptionPane.showMessageDialog(
                null,
                "Expressão: \"" + expressao + "\"\n\n" + resultado,
                "Resultado da Verificação",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Verifica se os parênteses em uma expressão estão balanceados usando uma Pilha (Deque).
     * (Lógica copiada de Exercicio_4.java)
     * @param expressao A string da expressão a ser verificada.
     * @return true se os parênteses estiverem balanceados, false caso contrário.
     */
    public static boolean verificarParenteses(String expressao) {
        Deque<Character> pilha = new ArrayDeque<>();

        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(') {
                pilha.push(caractere);
            } else if (caractere == ')') {
                if (pilha.isEmpty()) {
                    return false;
                }
                pilha.pop();
            }
        }
        return pilha.isEmpty();
    }
}

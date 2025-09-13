import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Exercicio_5_Dialogo {

    public static void main(String[] args) {
        
        int repetir;
        do {
            // 1. Pede ao usuário para inserir números separados por vírgula.
            String input = JOptionPane.showInputDialog(
                null,
                "Digite números inteiros separados por vírgula (ex: 1,2,3,4,5):",
                "Inversor de Lista com Pilha",
                JOptionPane.QUESTION_MESSAGE
            );

            // 2. Se o usuário cancelar ou fechar a caixa, o programa termina.
            if (input == null) {
                break;
            }

            // 3. Converte a string de entrada em um ArrayList de Inteiros.
            ArrayList<Integer> numeros = new ArrayList<>();
            try {
                // Remove espaços, divide a string pela vírgula e converte para Inteiros
                numeros.addAll(
                    Arrays.stream(input.replace(" ", "").split(","))
                          .map(Integer::parseInt)
                          .collect(Collectors.toCollection(ArrayList::new))
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Entrada inválida. Por favor, insira apenas números inteiros separados por vírgula.",
                    "Erro de Formato",
                    JOptionPane.ERROR_MESSAGE);
                repetir = JOptionPane.YES_OPTION; // Força a repetição do loop
                continue; // Pula para a próxima iteração
            }
            
            // Guarda a lista original para exibição
            String original = numeros.toString();

            // 4. Inverte a lista
            inverterArrayListComPilha(numeros);
            String invertida = numeros.toString();

            // 5. Monta e exibe a mensagem de resultado.
            String mensagem = "Lista Original:  " + original + "  " +
                              "Lista Invertida: " + invertida;

            JOptionPane.showMessageDialog(
                null,
                mensagem,
                "Resultado da Inversão",
                JOptionPane.INFORMATION_MESSAGE
            );

            // 6. Pergunta se o usuário quer repetir a operação.
            repetir = JOptionPane.showConfirmDialog(
                null,
                "Deseja inverter outra lista?",
                "Continuar?",
                JOptionPane.YES_NO_OPTION
            );

        } while (repetir == JOptionPane.YES_OPTION);
    }

    /**
     * Inverte a ordem dos elementos de um ArrayList de inteiros usando uma Pilha.
     * (Lógica copiada de Exercicio_5.java)
     * @param lista O ArrayList de inteiros a ser invertido.
     */
    public static void inverterArrayListComPilha(ArrayList<Integer> lista) {
        Stack<Integer> pilha = new Stack<>();
        for (Integer numero : lista) {
            pilha.push(numero);
        }
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, pilha.pop());
        }
    }
}
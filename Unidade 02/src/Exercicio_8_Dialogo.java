import javax.swing.JOptionPane;
import java.util.Map;

public class Exercicio_8_Dialogo {

    public static void main(String[] args) {
        // 1. Solicita ao usuário que insira um texto.
        // Um texto padrão é fornecido para facilitar o teste.
        Object input = JOptionPane.showInputDialog(
            null,
            "Digite o texto ou artigo que deseja analisar:",
            "Analisador de Frequência de Palavras",
            JOptionPane.QUESTION_MESSAGE,
            null,
            null,
            "A persistência é o caminho do êxito. O estudo constante e a prática dedicada são o segredo do sucesso. Estudo e prática levam à perfeição."
        );

        // Verifica se o usuário cancelou
        if (input == null) {
            JOptionPane.showMessageDialog(null, 
                "Análise cancelada pelo usuário.", 
                "Cancelado", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String artigo = input.toString();

        // 2. Verifica se o texto está vazio.
        if (artigo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Nenhum texto foi inserido. O programa será encerrado.", 
                "Análise Cancelada", 
                JOptionPane.WARNING_MESSAGE);
            return; // Encerra o programa
        }

        // 3. Reutiliza o método de contagem do Exercicio_8.
        Map<String, Integer> frequenciaPalavras = Exercicio_8.contarFrequencia(artigo);

        // 4. Prepara a string de resultados para exibição.
        StringBuilder resultado = new StringBuilder("---\n--- Frequência de Cada Palavra ---\n\n");
        if (frequenciaPalavras.isEmpty()) {
            resultado.append("Nenhuma palavra foi encontrada no texto para análise.");
        } else {
            // Itera sobre o mapa e constrói a string de resultado.
            frequenciaPalavras.forEach((palavra, contagem) -> {
                resultado.append("'" ).append(palavra).append("': ").append(contagem).append(" vez(es)\n");
            });
        }

        // 5. Exibe o resultado final em uma caixa de diálogo.
        JOptionPane.showMessageDialog(null, 
            resultado.toString(), 
            "Resultado da Análise", 
            JOptionPane.PLAIN_MESSAGE);

        // Mensagem de finalização
        JOptionPane.showMessageDialog(null, 
            "Análise concluída com sucesso!", 
            "Fim da Análise", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
import java.util.HashMap;
import java.util.Map;

/**
 * Solução para o Exercício 8 da Unidade 02 de Algoritmos e Estrutura de Dados.
 *
 * Descrição: Escreva um programa que conte a frequência de cada palavra em uma
 * frase, utilizando um HashMap, para identificar as palavras mais comuns.
 *
 * "Além da solução padrão solicitada, criarei também uma versão
 * `Exercicio_8_Dialogo.java` com interface gráfica (JOptionPane) para
 * demonstrar a aplicação do algoritmo em um contexto com interação visual com o
 * usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_8 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial no terminal

        // 1. Artigo de exemplo para análise.
        String artigo = "A persistência é o caminho do êxito. O estudo constante e a prática dedicada são o segredo do sucesso. Estudo e prática levam à perfeição.";

        // 2. Exibimos o artigo original.
        System.out.println("========================================================");
        System.out.println("        Analisador de Frequência de Palavras");
        System.out.println("========================================================");
        System.out.println();
        System.out.println("--- Artigo Original ---");
        System.out.println(artigo);
        System.out.println();

        // 3. Chamamos o método para contar a frequência das palavras.
        Map<String, Integer> frequenciaPalavras = contarFrequencia(artigo);

        // 4. Exibimos o resultado da contagem.
        System.out.println("--- Frequência de Cada Palavra ---");
        // A interface Map.forEach simplifica a iteração sobre as entradas do mapa.
        frequenciaPalavras.forEach((palavra, contagem) -> {
            System.out.println("'" + palavra + "': " + contagem + " vez(es)");
        });
        
        System.out.println();
        System.out.println("========================================================");
        System.out.println("                 Análise Concluída");
        System.out.println("========================================================");
        System.out.println();
    }

    /**
     * Conta a frequência de cada palavra em um texto.
     *
     * @param texto O texto a ser analisado.
     * @return Um Map onde a chave é a palavra e o valor é sua frequência.
     */
    public static Map<String, Integer> contarFrequencia(String texto) {
        // O HashMap armazenará a palavra (chave) e sua contagem (valor).
        Map<String, Integer> mapaFrequencia = new HashMap<>();

        // 1. Normalização do texto:
        //    - Converte tudo para minúsculas para não diferenciar "A" de "a".
        //    - Remove pontuações como pontos e vírgulas para simplificar a análise.
        String textoNormalizado = texto.toLowerCase().replaceAll("[.,]", "");
        
        // 2. Divisão do texto em palavras:
        //    - O método split("\s+") divide a string por um ou mais espaços em branco.
        String[] palavras = textoNormalizado.split("\s+");

        // 3. Contagem das palavras:
        //    - Itera sobre cada palavra no array de palavras.
        for (String palavra : palavras) {
            // Ignora strings vazias que podem surgir de múltiplos espaços.
            if (!palavra.isEmpty()) {
                // Usa o método getOrDefault para simplificar a lógica:
                // - Se a palavra já existe no mapa, pega o valor atual.
                // - Se não existe, usa o valor padrão (0).
                // Em seguida, adiciona 1 ao valor e atualiza o mapa.
                mapaFrequencia.put(palavra, mapaFrequencia.getOrDefault(palavra, 0) + 1);
            }
        }

        return mapaFrequencia;
    }
}

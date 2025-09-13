import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Solução para o Exercício 4 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Utilize uma pilha para desenvolver um programa que verifique se os 
 * parênteses em uma expressão matemática estão corretamente balanceados.
 * 
 * "Além da solução padrão solicitada, criei também uma versão `Exercicio_4_Dialogo.java` com interface
 * gráfica (JOptionPane) para demonstrar a aplicação do algoritmo em um contexto com interação visual
 * com o usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_4 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial

        // Lista de expressões para teste
        String[] expressoes = {
            "((a+b) * (c-d))",      // Válida
            "(a+b) * c)",           // Inválida - fecha parêntese a mais
            "((a+b) * c",           // Inválida - falta fechar parêntese
            "())a+b(()",            // Inválida - ordem errada e falta fechar
            "a + b",                // Válida - sem parênteses
            "(()())"                // Válida
        };

        System.out.println("===========================================");
        System.out.println("   Verificador de Parênteses Balanceados");
        System.out.println("===========================================");
        
        // Itera sobre as expressões e verifica cada uma
        for (String exp : expressoes) {
            boolean balanceada = verificarParenteses(exp);
            System.out.printf("Expressão \"%s\" -> Balanceada? %s\n", exp, balanceada);
        }
        
        System.out.println("===========================================");
        System.out.println();
    }

    /**
     * Verifica se os parênteses em uma expressão estão balanceados usando uma Pilha (Deque).
     * 
     * @param expressao A string da expressão a ser verificada.
     * @return true se os parênteses estiverem balanceados, false caso contrário.
     */
    public static boolean verificarParenteses(String expressao) {
        // Usamos Deque como a implementação da nossa pilha, conforme boas práticas.
        Deque<Character> pilha = new ArrayDeque<>();

        // Percorremos cada caractere da expressão.
        for (char caractere : expressao.toCharArray()) {
            // Se for um parêntese de abertura, empilhamos.
            if (caractere == '(') {
                pilha.push(caractere);
            } 
            // Se for um parêntese de fechamento...
            else if (caractere == ')') {
                // ...verificamos se a pilha está vazia. 
                // Se estiver, significa que há um ')' a mais, então a expressão é inválida.
                if (pilha.isEmpty()) {
                    return false;
                }
                // Se a pilha não estiver vazia, desempilhamos um '('.
                pilha.pop();
            }
        }

        // Ao final, a expressão está balanceada se a pilha estiver vazia.
        // Se sobrar algo na pilha, significa que há um '(' a mais.
        return pilha.isEmpty();
    }
}

import java.util.ArrayList;
import java.util.Stack;

/**
 * Solução para o Exercício 5 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Crie um programa que inverta a ordem dos elementos em um ArrayList 
 * de inteiros utilizando uma pilha como estrutura auxiliar.
 * 
 * "Além da solução padrão solicitada, criei também uma versão `Exercicio_5_Dialogo.java` com interface
 * gráfica (JOptionPane) para demonstrar a aplicação do algoritmo em um contexto com interação visual
 * com o usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_5 {

    public static void main(String[] args) {
        System.out.println(); // Espaçamento inicial

        // Cria e preenche a lista de números.
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);

        System.out.println("===========================================");
        System.out.println("      Inversor de ArrayList com Pilha");
        System.out.println("===========================================");
        
        System.out.println("Lista Original: " + numeros);
        System.out.println();

        // Inverte a lista usando o método auxiliar
        inverterArrayListComPilha(numeros);

        System.out.println("Lista Invertida: " + numeros);
        
        System.out.println("===========================================");
        System.out.println();
    }

    /**
     * Inverte a ordem dos elementos de um ArrayList de inteiros usando uma Pilha.
     * 
     * @param lista O ArrayList de inteiros a ser invertido.
     */
    public static void inverterArrayListComPilha(ArrayList<Integer> lista) {
        // 1. Cria uma pilha para servir como estrutura de dados auxiliar.
        Stack<Integer> pilha = new Stack<>();

        // 2. Empurra todos os elementos da lista para a pilha.
        // O primeiro elemento da lista (índice 0) ficará no fundo da pilha,
        // e o último elemento ficará no topo.
        for (Integer numero : lista) {
            pilha.push(numero);
        }

        // 3. Substitui os elementos na lista original pelos elementos da pilha.
        // O método pop() remove e retorna o elemento do topo da pilha (LIFO).
        // Assim, o último elemento a entrar será o primeiro a sair, invertendo a ordem.
        for (int i = 0; i < lista.size(); i++) {
            lista.set(i, pilha.pop());
        }
    }
}

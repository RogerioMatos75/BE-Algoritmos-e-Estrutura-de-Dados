import java.util.ArrayList;
import java.util.List;
/**
 * Solução para o Exercício 1 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * Descrição: Escreva um programa que inverta os elementos de uma lista de
 * tarefas (ArrayList) sem utilizar métodos prontos da linguagem, dando ao
 * usuário a opção de ver a lista do fim para o começo.
 * "Além da solução padrão solicitada, criei também uma versão `Exercicio_1_Dialogo.java` com interface
 * gráfica (JOptionPane) para demonstrar a aplicação do algoritmo em um contexto com interação visual
 * com o usuário, explorando outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_1 {

    public static void main(String[] args) {
        // Força a quebra de linha inicial no terminal
        System.out.println();

        // 1. Criamos uma lista de tarefas original.
        List<String> tarefas = new ArrayList<>();
        tarefas.add("Estudar Algoritmos");
        tarefas.add("Fazer o exercício 1");
        tarefas.add("Preparar o jantar");
        tarefas.add("Ler um livro");
        tarefas.add("Assistir a uma aula");

        // 2. Exibimos a lista original com a nova formatação.
        System.out.println("===========================================");
        System.out.println("      App de Tarefas - Lista Original");
        System.out.println("===========================================");
        for (String tarefa : tarefas) {
            System.out.println("- " + tarefa);
        }
        System.out.println("===========================================");
        System.out.println(); // Adiciona uma linha em branco para espaçamento

        // 3. Chamamos o método para inverter a lista.
        List<String> tarefasInvertidas = inverterLista(tarefas);

        // 4. Exibimos a nova lista, agora invertida e formatada.
        System.out.println("===========================================");
        System.out.println("     App de Tarefas - Lista Invertida");
        System.out.println("===========================================");
        for (String tarefa : tarefasInvertidas) {
            System.out.println("- " + tarefa);
        }
        System.out.println("===========================================");
        System.out.println(); // Adiciona uma linha em branco para espaçamento
    }

    /**
     * Inverte uma lista de Strings criando uma nova lista.
     * 
     * @param listaOriginal A lista a ser invertida.
     * @return Uma nova lista contendo os elementos da lista original em ordem
     *         inversa.
     */
    public static List<String> inverterLista(List<String> listaOriginal) {
        // Criamos uma nova lista vazia que armazenará o resultado.
        List<String> listaInvertida = new ArrayList<>();

        // Iteramos sobre a lista original, começando do último elemento (índice
        // tamanho - 1)
        // até o primeiro elemento (índice 0).
        for (int i = listaOriginal.size() - 1; i >= 0; i--) {
            // Para cada elemento, o adicionamos à nova lista.
            // Como estamos percorrendo a original de trás para frente,
            // os elementos são inseridos na nova lista em ordem inversa.
            listaInvertida.add(listaOriginal.get(i));
        }

        // Retornamos a nova lista que contém a ordem invertida.
        return listaInvertida;
    }
}
import java.util.ArrayList;
import java.util.List;

/**
 * Solução para o Exercício 2 da Unidade 02 de Algoritmos e Estrutura de Dados.
 * 
 * Descrição: Crie um método que remova contatos duplicados de uma lista de
 * usuários (ArrayList), deixando apenas uma entrada por pessoa e otimizando o
 * banco de dados.
 * 
 * "Além da solução padrão solicitada, criei também uma versão `Exercicio_2_Dialogo.java`com interface 
 * gráfica (JOptionPane) para demonstrar a aplicação do algoritmo em um contexto com interação visual   
 * com o usuário, explorando   * outros recursos da plataforma Java."
 *
 * Autor: Rogério
 */
public class Exercicio_2 {

    public static void main(String[] args) {
        // Força a quebra de linha inicial no terminal
        System.out.println();

        // 1. Criamos uma lista de contatos com várias duplicatas.
        List<String> contatos = new ArrayList<>();
        contatos.add("Ana");
        contatos.add("Bruno");
        contatos.add("Ana");
        contatos.add("Carlos");
        contatos.add("Daniela");
        contatos.add("Bruno");
        contatos.add("Ana");

        // 2. Exibimos a lista original com a formatação padrão.
        System.out.println("===========================================");
        System.out.println("    Sistema de Contatos - Lista Original");
        System.out.println("===========================================");
        for (String contato : contatos) {
            System.out.println("- " + contato);
        }
        System.out.println("===========================================");
        System.out.println(); // Adiciona uma linha em branco para espaçamento

        // 3. Chamamos o método para remover os elementos duplicados.
        List<String> contatosSemDuplicatas = removerDuplicatas(contatos);

        // 4. Exibimos a nova lista, sem as duplicatas.
        System.out.println("===========================================");
        System.out.println("  Sistema de Contatos - Lista Corrigida");
        System.out.println("===========================================");
        for (String contato : contatosSemDuplicatas) {
            System.out.println("- " + contato);
        }
        System.out.println("===========================================");
        System.out.println(); // Adiciona uma linha em branco para espaçamento
    }

    /**
     * Remove elementos duplicados de uma lista de Strings usando iteração manual.
     *
     * @param listaOriginal A lista contendo possíveis duplicatas.
     * @return Uma nova lista contendo apenas elementos únicos, na ordem em que
     *         apareceram pela primeira vez.
     */
    public static List<String> removerDuplicatas(List<String> listaOriginal) {
        // Criamos uma nova lista vazia que armazenará o resultado.
        List<String> listaSemDuplicatas = new ArrayList<>();

        // Usamos um "for-each loop" para percorrer cada elemento da lista original.
        for (String elemento : listaOriginal) {
            // Verificamos se a nova lista (listaSemDuplicatas) JÁ CONTÉM o elemento
            // atual.
            if (!listaSemDuplicatas.contains(elemento)) {
                // Se o elemento AINDA NÃO EXISTE na nova lista, nós o adicionamos.
                listaSemDuplicatas.add(elemento);
            }
            // Se o elemento já existe, simplesmente o ignoramos e continuamos para o
            // próximo.
        }

        // Retornamos a nova lista, que agora contém apenas os elementos únicos.
        return listaSemDuplicatas;
    }
}

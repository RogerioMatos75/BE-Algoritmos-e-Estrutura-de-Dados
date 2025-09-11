/**
 * Classe de modelo para representar um Cliente (Pessoa).
 * Usada no Exercício 9.
 * Autor: Rogério
 */
public class Pessoa {
    private String nome;
    private int idade;
    private String endereco;

    // Construtor da classe Pessoa
    public Pessoa(String nome, int idade, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Métodos "getters" para acessar os atributos privados
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    /**
     * Sobrescreve o método toString para fornecer uma representação em texto do objeto.
     * Isso é útil para imprimir os detalhes do cliente de forma legível.
     */
    @Override
    public String toString() {
        return "Cliente: " + nome + " | Idade: " + idade + " | Endereço: " + endereco;
    }
}

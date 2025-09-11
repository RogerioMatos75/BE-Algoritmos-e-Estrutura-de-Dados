package Exercicio10;

public class ItemEstoque {
    private final String nome;
    private int quantidade;

    public ItemEstoque(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + nome + ", Quantidade: " + quantidade;
    }
}

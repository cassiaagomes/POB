package modelo;

public class Produto {
    private int codproduto;
    private double preco;
    private String nome;  // Adicionando o campo nome

    // Construtor com nome, preço e código
    public Produto(int codproduto, String nome, double preco) {
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e setters
    public int getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto = codproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codproduto=" + codproduto +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}


package modelo;

public class Item {
    private int id;
    private Produto produto;
    private double desconto;
    private int quantidade;

    public Item(int id, Produto produto, double desconto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.desconto = desconto;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    //PRECOITEM É O VALOR DO ITEM - DESCONTO
    public double getPrecoitem() {
        return (produto.getPreco() - desconto) * quantidade;
    }
}


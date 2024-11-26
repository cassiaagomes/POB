package modelo;

import java.util.ArrayList;

public class Venda {
    private int id;
    private String cliente; // Adicionando o campo cliente
    private double frete;
    private ArrayList<Item> itens = new ArrayList<>();

    public Venda(int id, String cliente, double frete) {  // Ajustando o construtor
        this.id = id;
        this.cliente = cliente;
        this.frete = frete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    // Calcula o valor total pago (soma dos itens + frete)
    public double getValorpago() {
        double somaItens = 0;
        for (Item item : itens) {
            somaItens += item.getPrecoitem();
        }
        return somaItens + frete;
    }

    public void addItem(Item item) {
        this.itens.add(item);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", frete=" + frete +
                ", itens=" + itens +
                '}';
    }
}

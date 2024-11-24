package modelo;

import java.util.ArrayList;

public class Venda {
    private int id;
    private double frete;
    private ArrayList<Item> itens = new ArrayList<>();

    public Venda(int id, double frete) {
        this.id = id;
        this.frete = frete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //O VALOR PAGO É A SOMA DOS ITENS + FRETE
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
}

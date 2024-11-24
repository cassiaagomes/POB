package modelo;

public class Produto {
	private int codproduto;
	private double preco;
	
	public Produto (double preco, int codproduto) {
		this.codproduto = codproduto;
		this.preco = preco;
	}

	public int getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(int codproduto) {
		this.codproduto = codproduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
}

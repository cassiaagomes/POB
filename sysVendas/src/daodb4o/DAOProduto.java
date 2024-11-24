package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Produto;

public class DAOProduto extends DAO<Produto>{

	public void create (Produto obj) {
		int novocodprod = super.gerarId(Produto.class);
		obj.setCodproduto(novocodprod);
		manager.store(obj);
	}
	
	public Produto readByCodProduto (int codProduto) {
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("codproduto").constrain(codProduto);
		List<Produto> resultados = q.execute();
		if (resultados.size()>0) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE PRODUTO
	 * 
	 **********************************************************/

	 public List<Produto> readBypreco(double preco) {
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("preco").constrain(preco);
		return q.execute();
	 }

	 public List<Produto> readAll() {
		Query q = manager.query();
		q.constrain(Produto.class);
		List<Produto> resultados = q.execute();
		if (resultados.isEmpty()) {
			return null;
		} else {
			return resultados;
		}
	 }


}

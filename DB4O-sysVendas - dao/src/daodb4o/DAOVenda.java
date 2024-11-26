package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Venda;

public class DAOVenda extends DAO<Venda> {

    public void create(Venda obj) {
        int novoId = super.gerarId(Venda.class);
        obj.setId(novoId);
        manager.store(obj);
    }

    public List<Venda> readAll() {
        Query q = manager.query();
        q.constrain(Venda.class);
        return q.execute();
    }

    public List<Venda> readVendasWithFreeShipping() {
        Query q = manager.query();
        q.constrain(Venda.class);
        q.descend("frete").constrain(0.0); // Frete igual a zero
        return q.execute();
    }

    public List<Venda> readVendasWithMoreThanNItems(int n) {
        Query q = manager.query();
        q.constrain(Venda.class);
        q.descend("itens").constrain(n).greater(); // Número de itens maior que N
        return q.execute();
    }

    public List<Venda> readItemsWithDiscountInVenda(int vendaId) {
        Query q = manager.query();
        q.constrain(Venda.class);
        q.descend("id").constrain(vendaId); // Filtra a venda pelo ID
        q.descend("itens.desconto").constrain(0).greater(); // Apenas itens com desconto > 0
        return q.execute();
    }
}



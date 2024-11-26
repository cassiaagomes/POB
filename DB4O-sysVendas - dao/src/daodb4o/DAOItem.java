package daodb4o;

import java.util.List;
import com.db4o.query.Query;
import modelo.Item;

public class DAOItem extends DAO<Item> {

    public void create(Item obj) {
        int novoId = super.gerarId(Item.class);
        obj.setId(novoId);
        manager.store(obj);
    }


    public List<Item> readAll() {
        Query q = manager.query();
        q.constrain(Item.class);
        return q.execute();
    }


    public List<Item> readItemsWithDiscountByVenda(int vendaId) {
        Query q = manager.query();
        q.constrain(Item.class);
        q.descend("desconto").constrain(0).greater(); 
        q.descend("produto.venda.id").constrain(vendaId); 
        return q.execute();
    }

    
    public List<Item> readItemsByVendaWithMoreThanNItems(int n) {
        Query q = manager.query();
        q.constrain(Item.class);
        q.descend("venda.itens").constrain(n).greater(); 
        return q.execute();
    }
}


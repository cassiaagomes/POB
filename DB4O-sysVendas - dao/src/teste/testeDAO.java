package teste;

import daodb4o.DAO;
//import daodb4o.Util;
import modelo.Produto;
import modelo.Item;
import modelo.Venda;

import java.util.List;

public class testeDAO {
    
    public static void main(String[] args) {
        try {
            DAO.open();

            System.out.println("---- Testando CRUD para Produto ----");

            Produto produto1 = new Produto(1, "Produto A", 100.0);
            Produto produto2 = new Produto(2, "Produto B", 200.0);
            
            DAO<Produto> daoProduto = new DAO<>() {};
            

            daoProduto.create(produto1);
            daoProduto.create(produto2);
            System.out.println("Produtos criados.");
            
            // Ler todos os produtos
            List<Produto> produtos = daoProduto.readAll();
            System.out.println("Lista de produtos:");
            for (Produto p : produtos) {
                System.out.println(p);
            }

            // Atualizar produto
            produto1.setPreco(120.0);
            daoProduto.update(produto1);
            System.out.println("Produto atualizado: " + produto1);

            // Ler novamente após a atualização
            produtos = daoProduto.readAll();
            System.out.println("Lista de produtos após atualização:");
            for (Produto p : produtos) {
                System.out.println(p);
            }

            // Deletar um produto
            daoProduto.delete(produto2);
            System.out.println("Produto deletado: " + produto2);

            // Ler novamente após a exclusão
            produtos = daoProduto.readAll();
            System.out.println("Lista de produtos após exclusão:");
            for (Produto p : produtos) {
                System.out.println(p);
            }

            // Testar CRUD para Venda e Item
            System.out.println("\n---- Testando CRUD para Venda e Item ----");

            // Criar venda com itens
            Venda venda1 = new Venda(1, "Cliente A", 15.0);  // Agora inclui o frete
            Item item1 = new Item(1, produto1, 10.0, 3); // 3 unidades de produto1 com 10% de desconto
            venda1.addItem(item1);

            DAO<Venda> daoVenda = new DAO<>() {};
            
            // Criar venda
            daoVenda.create(venda1);
            System.out.println("Venda criada: " + venda1);

            // Ler todas as vendas
            List<Venda> vendas = daoVenda.readAll();
            System.out.println("Lista de vendas:");
            for (Venda v : vendas) {
                System.out.println(v);
            }

            // Atualizar venda (adicionar um item)
            Produto produto3 = new Produto(3, "Produto C", 300.0);
            Item item2 = new Item(2, produto3, 5.0, 2); // 2 unidades de produto3 com 5% de desconto
            venda1.addItem(item2);
            daoVenda.update(venda1);
            System.out.println("Venda atualizada: " + venda1);

            // Ler novamente após atualização
            vendas = daoVenda.readAll();
            System.out.println("Lista de vendas após atualização:");
            for (Venda v : vendas) {
                System.out.println(v);
            }

            // Fechar conexão com o banco
            DAO.close();

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


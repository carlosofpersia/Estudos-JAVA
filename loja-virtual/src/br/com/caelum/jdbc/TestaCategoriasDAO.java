package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.CategoriasDAO;
import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Categorias;
import br.com.caelum.jdbc.modelo.Produtos;

public class TestaCategoriasDAO {

	public static void lista(CategoriasDAO categoriasDAO, ProdutosDAO produtosDAO) throws SQLException {

		List<Categorias> categorias = categoriasDAO.lista();
		for (Categorias categoria : categorias) {
			System.out.println("---------------" + categoria.getNome() + "---------------");

			for (Produtos produto : categoriasDAO.listaN1NaoNormalizada(categoria)) {
				System.out.println(categoria.getNome() + " - " + produto.getNome());
			}
		}
	}

	public static void listaComProdutos(CategoriasDAO categoriasDAO, ProdutosDAO produtosDAO) throws SQLException {

		List<Categorias> categorias = categoriasDAO.listaComProdutos();
		for (Categorias categoria : categorias) {
			System.out.println("---------------" + categoria.getNome() + "---------------");

			for (Produtos produto : categoria.getProdutos()) {
				System.out.println(categoria.getNome() + " - " + produto.getNome());

			}
		}
	}

	public static void main(String[] args) throws SQLException {

		try (Connection conn = new ConnectionPool().getConnection()) {

			CategoriasDAO categoriasDAO = new CategoriasDAO(conn);
			ProdutosDAO produtosDAO = new ProdutosDAO(conn);

			// lista(categoriasDAO, produtosDAO);

			// Relacionamentos, otimizando N+1 e ferramentas ORM
			listaComProdutos(categoriasDAO, produtosDAO);

		}
	}
}

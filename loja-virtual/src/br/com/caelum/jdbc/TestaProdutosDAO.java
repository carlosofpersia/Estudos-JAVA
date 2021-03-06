package br.com.caelum.jdbc;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produtos;

public class TestaProdutosDAO {

	public static void main(String[] args) throws SQLException {

		Produtos mesa = new Produtos("Mesa Azul", "Mesa com 4 p�s");
		try (Connection conn = new ConnectionPool().getConnection()) {

			ProdutosDAO produtosDAO = new ProdutosDAO(conn);
			produtosDAO.salva(mesa);

			List<Produtos> produtos = produtosDAO.lista();
			for (Produtos produto : produtos) {
				System.out.println("Existe o produto:" + produto);
			}
		}
		System.out.println(mesa);

	}
}

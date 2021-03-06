package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public void remove() throws SQLException {

		Connection connection = new ConnectionPool().getConnection();

		Statement statement = connection.createStatement();

		statement.execute("delete from Produto where id > 3");

		// getUpdateCount verifica quantas linhas foram atualizadas
		int count = statement.getUpdateCount();
		System.out.println(count + " linhas removidas");

		statement.close();
		connection.close();

	}

	public static void main(String[] args) throws SQLException {

		TestaRemocao tr = new TestaRemocao();
		tr.remove();
	}

}

/*
 * 
 * metodo execute : statement.execute O m�todo � claro em seu javadoc: se o
 * retorno � um ResultSet por conta de um select, ele retorna true. Caso
 * contr�rio ou caso n�o haja nenhum valor retornado, o m�todo devolve false.
 * 
 * 
 * 
 */
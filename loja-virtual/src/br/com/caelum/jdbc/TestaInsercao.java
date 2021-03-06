package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public void insertDesprotegidoSimples() throws SQLException {

		// uso connection.createStatement
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();

		boolean resultado = statement.execute(
				"insert into Produto (nome, descricao) values ('Sansumg Galaxy', 'Celular Sansumg Galaxy antigo')",
				Statement.RETURN_GENERATED_KEYS);

		System.out.println("O resultado foi: " + resultado);

		// Statement.RETURN_GENERATED_KEYS - retorna o ID Gerado.
		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}

		resultSet.close();
		statement.close();
		connection.close();
	}

	public void insertWithSqlInjectionAndPreparedStatement() throws SQLException {

		Connection connection = new ConnectionPool().getConnection();

		String nome = "Sansumg A80's";// com prepareStetement o jdbc realiza os scape adequados
		String descricao = "Celular Sansumg A80 com camera externa";

		String sql = "insert into Produto (nome, descricao) values (?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nome);
		statement.setString(2, descricao);

		boolean resultado = statement.execute();

		System.out.println("O resultado foi: " + resultado);

		ResultSet resultSet = statement.getGeneratedKeys();
		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}

		resultSet.close();
		statement.close();
		connection.close();
	}

	public void insertWithCommit() throws SQLException {

		// nao preciso fechar a conecction quando uso try
		// connection.close();
		try (Connection connection = new ConnectionPool().getConnection()) {

			connection.setAutoCommit(false);

			String sql = "insert into Produto (nome, descricao) values (?, ?)";
			// mesma coisa com o statement, dentro do try nao preciso fecha-lo
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				// commit ou rollback
				adiciona("Sansumg A10", "Sansumg A10, primeira versao", statement);
				adiciona("Sansumg A20", "Sansumg A20, segunda versao", statement);
				adiciona("Sansumg A30", "Sansumg A30, terceira versao", statement);
				adiciona("Sansumg A50", "Sansumg A50, quinta versao", statement);

				connection.commit();
				System.out.println("Commit efetuado");

				// statement.close(); //o PreparedStatement parametrizado dentro de um try nao
				// precisa fechar, o try fecha

			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();

				System.out.println("Rollback efetuado");
			}

			// nao preciso fechar a conecction quando uso try
			// connection.close();
		}
	}

	public static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {

		if (nome.equals("Sansumg A30")) {
			// throw new IllegalArgumentException("Problema ocorrido arrocha um rollback");

		}
		statement.setString(1, nome);
		statement.setString(2, descricao);

		boolean resultado = statement.execute();

		System.out.println("O resultado foi: " + resultado);

		// Statement.RETURN_GENERATED_KEYS - retorna o ID Gerado.
		// dentro do try nao perciso fechar o resultSet
		try (ResultSet resultSet = statement.getGeneratedKeys()) {
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				System.out.println(id + " gerado");
			}
		}

	}

	public static void main(String[] args) throws SQLException {

		TestaInsercao ti = new TestaInsercao();

		// Aula 1
		// ti.insertDesprotegidoSimples();
		// Aula 2
		// ti.insertWithSqlInjectionAndPreparedStatement();

		// Aula 3
		ti.insertWithCommit();

	}

}

/*
 * 
 * metodo execute : statement.execute O m�todo � claro em seu javadoc: se o
 * retorno � um ResultSet por conta de um select, ele retorna true. Caso
 * contr�rio ou caso n�o haja nenhum valor retornado, o m�todo devolve false.
 *
 * 
 * O principal problema de usar statement est� ligado com o SQL Injection: os
 * usu�rios podem quebrar nossas queries e atacar nosso sistema caso eles
 * escrevam valores espec�ficos em nossos campos e n�o tratemos eles. Outro
 * problema � de performance: um prepared statement permite execu��o de inserts
 * ou updates em batch.
 *
 *
 * 
 * 
 * PreparedStatement statement = connection.prepareStatement(sql,
 * Statement.RETURN_GENERATED_KEYS);
 * 
 * 
 * statement.setString(1, nome); statement.setString(2, descricao);
 * 
 * 
 * boolean resultado = statement.execute();
 * 
 * 
 * 
 * Statement statement = connection.createStatement(); n�o trata, pode vir sql
 * injection
 * 
 * SQL Injection e Prepared Statement
 * 
 * 
 * Primeiro, o JDBC passa a fazer o escaping de caracteres especiais e n�o nos
 * preocupamos com SQL Injection. Por fim, o JDBC d� a chance ao banco de dados
 * de escolher a melhor maneira de executar uma query (definir o plano de a��o),
 * e podemos executar a mesma query com par�metros diferentes bastando chamar o
 * m�todo setter e o execute novamente.
 * 
 * 
 * para escapar usamos o prepareStetement para proteger de sql injections
 * 
 * 
 * 
 * 
 * connection.setAutoCommit(false); connection.commit(); connection.rollback();
 * 
 * 
 * 
 * Mas temos tamb�m que fechar o statement que abrimos, onde fechamos ele?
 * Dentro do try? Mas e se acontecer uma exception, n�o fecharemos: precisamos
 * do close dentro de um bloco finally. E precisamos conferir que ela foi aberto
 * com sucesso. S�o muitos detalhes pequenos que precisamos cuidar para fechar
 * tudo o que abrimos em blocos do tipo try/catch/finally! Para evitar ter que
 * se preocupar com o caso de fechar tais recursos, o Java 7 introduziu a
 * constru��o try(recurso). Se abrirmos uma conex�o, ou qualquer coisa
 * "fech�vel", ela ser� automaticamente fechada quando o bloco try terminar,
 * seja atrav�s de um sucesso ou de uma exception/error. Portanto podemos abrir
 * a conex�o com:
 * 
 * 
 * 
 * Qual o padr�o do JDBC (ou seja do Driver) para lidar com transa��es e o banco
 * de dados? Por padr�o o JDBC est� no modo de "auto-commit".
 * 
 *
 * 
 * 
 */

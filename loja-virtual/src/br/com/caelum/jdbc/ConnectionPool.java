package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {

	private DataSource dataSource;

	ConnectionPool() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println("Conex�o aberta com pool!");
		return connection;
	}

	public Connection getConnectionSemPool() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		System.out.println("Conex�o aberta sem pool!");
		return connection;
	}

}

/**
 * 
 * 
 * O connection POOL e importante para utilizar as conecxoes entre diversos
 * clientes
 * 
 * 
 * Repare que utilizamos a interface DataSource pois ela s� disponibiliza os
 * getters, n�o os setters. N�o desejamos alterar os setters ap�s a constru��o
 * de nosso pool, portanto usamos a interface. Tiraremos tamb�m a caracter�stica
 * static de nosso m�todo: � importante criar um Database (e consequentemente o
 * pool) antes de invocar o m�todo.
 * 
 * 
 * Alguns servidores na cloud pedem para n�o usarmos connection pool pois eles
 * mesmos j� trazem seu connection pool ou lidam com tais situa��es. Os
 * servidores Java EE tamb�m j� fornecem um connection pool de maneira
 * declarativa (configurando em algum arquivo externo a aplica��o) e basta
 * recebermos o DataSource dentro de nossa aplica��o.
 * 
 * 
 * Em um cen�rio onde diversos clientes podem acessar uma mesma aplica��o
 * simultaneamente: A pr�tica do pool de conex�es consiste em deixar um n�mero
 * fixo ou din�mico de conex�es abertas e recicl�-las utilizando em novas
 * requisi��es.
 * 
 *
 * 
 * 
 * Em um pool simples com 9 conex�es, o que acontece quando o 10� usu�rio se
 * conecta e todas est�o ocupadas? O que acontece quando, posteriormente, o
 * terceiro usu�rio termina sua tarefa?
 * 
 * Que varia��es voc� sugeriria fazer na implementa��o de um pool de conex�es
 * para evitar que um usu�rio espere muito tempo? E o que fazer para evitar que
 * em hor�rios de pico o servidor n�o fique com poucas conex�es para um n�mero
 * grande de usu�rios?
 * 
 * 
 * 
 * \ Opini�o do instrutor
 * 
 * 
 * No cen�rio descrito, o 10� usu�rio esperar�. Quando o terceiro usu�rio
 * terminar sua tarefa, o d�cimo usu�rio passar� a usar a conex�o liberada.
 * 
 * Uma sugest�o seria utilizar um n�mero vari�vel de conex�es: delimitamos um
 * n�mero m�nimo e m�ximo e deixamos que o servidor se adapte com o n�mero de
 * conex�es de acordo com a necessidade atual. Por exemplo podemos configurar 10
 * e 100. A medida que novas conex�es v�o sendo necess�rias, o servidor cria,
 * at� chegar a 100. Quando elas n�o s�o necess�rias, ele deixa diminuir o
 * n�mero de conex�es ativas at� um m�nimo de 10.
 * 
 * 
 */

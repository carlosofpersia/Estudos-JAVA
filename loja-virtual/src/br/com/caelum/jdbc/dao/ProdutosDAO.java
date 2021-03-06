package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Produtos;

/*
 * DataAccessObject - DAO
 */
public class ProdutosDAO {

	private final Connection conn;

	public ProdutosDAO(Connection conn) {

		this.conn = conn;
	}

	public void salva(Produtos produto) throws SQLException {

		String sql = "insert into Produto (nome, descricao) values(?, ?)";

		try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());

			statement.execute();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}

		}

	}

	public List<Produtos> lista() throws SQLException {
		List<Produtos> produtos = new ArrayList<>();
		String sql = "select * from Produto";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.execute();
			this.transformaResultadoEmProdutos(stmt, produtos);
		}
		return produtos;
	}

	private void transformaResultadoEmProdutos(PreparedStatement stmt, List<Produtos> produtos) throws SQLException {

		try (ResultSet resultSet = stmt.getResultSet()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String descricao = resultSet.getString("descricao");

				Produtos produto = new Produtos(nome, descricao);
				produto.setId(id);

				produtos.add(produto);
			}
		}
	}

}

/*
 * O DAO � um padr�o de design que utilizamos para isolar o c�digo SQL (ou
 * qualquer outro c�digo de acesso � um reposit�rio de dados). Ao adot�-lo,
 * sabemos que existe um �nico grupo de classes que trabalha com um sistema
 * externo de dados, e podemos nos preocupar somente com essas classes quando
 * trabalharmos nessa �rea.
 * 
 * 
 * Quais as vantagens de se usar classes com o padr�o DAO? A vantagem est�
 * ligada com a capacidade de isolar todo o c�digo que acessa seu reposit�rio de
 * dados em um �nico lugar. Qualquer desenvolvedor sabe que quando for
 * necess�rio trabalhar com tal camada, existe um �nico lugar para olhar: seus
 * DAOs.
 * 
 * 
 * 
 * Os DAOs que criamos recebem a conex�o no construtor. Imagine que ao inv�s
 * disso pegamos uma nova conex�o automaticamente em um construtor sem
 * argumentos como no c�digo a seguir:
 * 
 * public class ProdutosDAO { private final Connection con; ProdutosDAO() { con
 * = Database.getConnection(); } // resto do DAO aqui }
 * 
 * 
 * O que acontece caso uma tarefa tenha que acessar dois dados, como o
 * ProdutosDAO e CategoriasDAO? Qual a desvantagem desta abordagem? Opini�o do
 * instrutor
 * 
 * Opini�o do instrutor
 * 
 * Um problema grave ocorre quando tentarmos utilizar transa��es. Como cada um
 * dos DAOs possui uma conex�o distinta, eles n�o estar�o envolvidos na mesma
 * transa��o e ficamos incapazes de utilizar tal recurso.
 * 
 * Outro problema � que abrimos um n�mero grande de conex�es para a execu��o de
 * cada tarefa: se ela precisa de 2 daos, ser�o 2 conex�es.
 * 
 * 
 * 
 * 
 */
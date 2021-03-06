package br.com.caelum.financas.lazy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteDoisTodasAsMovimentacoesDasContas {

	public void testeUm() {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		String jpql = "select c from Conta c";

		Query query = em.createQuery(jpql);

		List<Conta> todasAsContas = query.getResultList();

		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentacoes: ");

			// ao chamar esses registros, sera carregado novas selects de busca pois esta
			// com lazy.
			System.out.println(conta.getMovimentacoes());
		}

	}

	public void testeDois() {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// join fetch -> Eager
		// uso do distinct e do efeito left join
		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";

		Query query = em.createQuery(jpql);

		List<Conta> todasAsContas = query.getResultList();

		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentacoes: ");
			System.out.println(conta.getMovimentacoes().size());
		}

	}

	public static void main(String[] args) {

		TesteDoisTodasAsMovimentacoesDasContas teste = new TesteDoisTodasAsMovimentacoesDasContas();
		teste.testeDois();
	}

}

/*
 * 
 * 
 * 
 * @OneToMany por paadr�o sao Lazy Lazy, com "carregamento pregui�oso" ocorre o
 * problema do N+1
 * 
 * 
 * 
 * Por padr�o o join fetch far� um inner join que trar� somente as contas que
 * possuem movimenta��o. Portanto, precisamos fazer um left join fetch em vez de
 * join fetch.
 * 
 * 
 * 
 * 
 * 
 * 
 * Com uso da clausula join, conseguimos trazer todos os resultados em uma �nica
 * query. usa join fica Eager
 * 
 * 
 * Eager Loading
 *
 *
 * N + 1 � um problema de gerenciamento das queries e n�o depende de usarmos
 * carregamento LAZY ou EAGER. Com uso da clausula join, conseguimos trazer
 * todos os resultados em uma �nica query. Abaixo um c�digo de exemplo onde
 * ocorre N+1 com JDBC:
 * 
 * 
 * *
 * 
 */
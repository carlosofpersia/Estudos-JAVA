package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

	// Data Access Object (DAOs)
	// refatoracao da classe TesteCincoFuncoesJPQL e TesteSeisTypedQuery

	private EntityManager em;

	public MovimentacaoDao(EntityManager em) {
		// tem que passar o entity manager no construtor para uso transacional
		this.em = em;
	}

	public List<Movimentacao> getMovimentacoesPorConta(TipoMovimentacao saida, Conta conta) {

		String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and m.tipoMovimentacao = :pTipo"
				+ " order by m.valor desc";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);

		List<Movimentacao> resultados = query.getResultList();

		return resultados;

	}

	public BigDecimal getSomaValoresMovimentacao(TipoMovimentacao saida, Conta conta) {

		/**************
		 * SOMA sum dos valores das movimentacoes
		 *******************************************/

		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipoMovimentacao = :pTipo" + " order by m.valor desc";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);

		BigDecimal soma = (BigDecimal) query.getSingleResult();

		return soma;
	}

	public Double getMediaValoresMovimentacao(TipoMovimentacao saida, Conta conta) {

		/**************
		 * Media AVG dos valores das movimentacoes
		 *******************************************/

		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta"
				+ " and m.tipoMovimentacao = :pTipo" + " order by m.valor desc";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", saida);

		Double media = (Double) query.getSingleResult();
		return media;
	}

	public BigDecimal getMaiorValorMovimentacao(Conta conta) {

		/**************
		 * Maximo MAX dos valores das movimentacoes
		 *******************************************/

		String jpql = "select max(m.valor) from Movimentacao m where m.conta = :pConta";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);

		BigDecimal maiorValor = (BigDecimal) query.getSingleResult();
		return maiorValor;
	}

	public Long getQuantidadeTotalMovimentacao(Conta conta) {

		/**************
		 * Quantidade total das movimentacoes da conta
		 *******************************************/

		String jpql = "select count(m) from Movimentacao m where m.conta = :pConta";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);

		Long quantidade = (Long) query.getSingleResult();

		return quantidade;

	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {

		/***********
		 * Medias agrupadas por dias usando @NamedQueries
		 *******************************************/

		// String jpql = "select avg(m.valor) from Movimentacao m where m.conta =
		// :pConta and m.tipoMovimentacao = :pTipo " + " group by day(m.data),
		// month(m.data), year(m.data)";
		// TypedQuery e obrigatorio passar o tipo do retorno na createQuery dai tenho
		// certeza do retorno
		// TypedQuery<Double> queryMedias = em.createQuery(jpql, Double.class);
		// queryMedias.setParameter("pConta", conta);
		// queryMedias.setParameter("pTipo", saida);
		// List<Double> medias = (List<Double>) queryMedias.getResultList();
		// return medias;

		// ou usar @NamedQuery adicionado na Entity Movientacao.java
		TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);

		typedQuery.setParameter("pConta", conta);
		typedQuery.setParameter("pTipo", saida);

		// Ao trabalhar com a fun��o avg() precisamos usar Double ao inv�s de
		// BigDecimal.
		List<Double> medias = (List<Double>) typedQuery.getResultList();
		return medias;

	}

}

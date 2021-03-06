package br.com.caelum.financas.jpql;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteCincoFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 2);

		MovimentacaoDao mDao = new MovimentacaoDao(em);

		/******************
		 * Lista de Moviemntacao por Conta
		 *********************************/
		List<Movimentacao> resultados = mDao.getMovimentacoesPorConta(TipoMovimentacao.SAIDA, conta);
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Valor: " + movimentacao.getValor());

			System.out.println("----------------------------------------------------");
		}

		/****************** Soma Valores *********************************/
		BigDecimal soma = mDao.getSomaValoresMovimentacao(TipoMovimentacao.SAIDA, conta);
		System.out.println("A soma �: " + soma);

		/****************** Meida Valores *********************************/
		Double media = mDao.getMediaValoresMovimentacao(TipoMovimentacao.SAIDA, conta);
		System.out.println("A media �: " + media);

		/****************** Maior Valor *********************************/
		BigDecimal maiorValor = mDao.getMaiorValorMovimentacao(conta);
		System.out.println("A maior valor de movimentacao �: " + maiorValor);

		/**************** Quantidade de Moviemntacoes *******************/
		Long quantidade = mDao.getQuantidadeTotalMovimentacao(conta);
		System.out.println("A quantidade de movimentacoes �: " + quantidade);

		// em.getTransaction().commit();
		em.close();

	}
}

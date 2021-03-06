package br.com.caelum.financas.jpql;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteSeisTypedQuery {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 2);

		MovimentacaoDao mDao = new MovimentacaoDao(em);

		List<Movimentacao> resultados = mDao.getMovimentacoesPorConta(TipoMovimentacao.SAIDA, conta);
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Valor: " + movimentacao.getValor());

			System.out.println("----------------------------------------------------");
		}

		/************** usando TypedQuery no DAO *************************************************/
		List<Double> medias = mDao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);

		// System.out.println("A m�dia do dia 13 �: " + medias.get(0));
		// System.out.println("A m�dia do dia 14 �: " + medias.get(1));

		for (Double media : medias) {
			System.out.println("A m�dia �: " + media);
		}

		// em.getTransaction().commit();
		em.close();

	}

}

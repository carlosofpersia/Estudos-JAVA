package br.com.caelum.financas.lazy;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteUmMovimentacaoConta {

	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Movimentacao movimentacao = em.find(Movimentacao.class, 3);
		Conta conta = movimentacao.getConta();

		System.out.println(conta.getTitular());
		
		System.out.println("QTDE movimentacoes da conta: " + conta.getMovimentacoes().size());

		
		
	}

}

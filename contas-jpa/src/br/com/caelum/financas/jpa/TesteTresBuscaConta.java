package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTresBuscaConta {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);
		System.out.println(conta);

		conta.setTitular("Carlos Eduardo");
		conta.setAgencia("0804");
		// conta.setTitular("Jo�o");
		// conta.setAgencia("0XXX");

		System.out.println(conta.getTitular());

		em.getTransaction().commit();
		
		
		
		//Resolvendo problema o estado Detached
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("Leonardo");
		//o pulo do gato � n�o usar persiste nesse caso �usar merge
		em2.merge(conta);
		
		em2.getTransaction().commit();
		em2.close();

		
		
		
	}
}

/*
 * 
 * Conta conta = em.find(Conta.class, 1);//id da conta, deve aceitar o mesmo
 * tipo
 * 
 * 
 * Sobre o estado Managed podemos afirmar que: A caracter�stica do estado
 * Managed � a sincroniza��o autom�tica com o banco.
 *
 * 
 * 
 * Executando-se a classe, al�m de buscar e imprimir, o Hibernate realizou um
 * update, que verificamos se est� correto no terminal, digitando select * from
 * Conta;. Como ser� que isto ocorre? A JPA conseguiu sincronizar os dados da
 * Conta com os do registro do banco de dados.
 * 
 * Isto acontece porque o m�todo find() nos devolve uma inst�ncia de Conta
 * considerado como estado Managed (gerenciado), estado da entidade da JPA cujos
 * dados s�o automaticamente sincronizados com o banco de dados.
 * 
 * Caso quis�ssemos alterar a ag�ncia, por exemplo, por meio de
 * conta.setAgencia("456");, repare que apenas um update ser� feito, pois ser�
 * verificado que o nome continua sendo "Jo�o", alterando-se apenas a ag�ncia.
 * 
 * Se repetirmos a mesma execu��o, o Hibernate realiza apenas a busca, sem
 * nenhum update, pois nenhuma altera��o foi feita, ou seja, a conta em mem�ria
 * � igual � conta no banco de dados.
 * 
 * Com o JPA, o objetivo � sempre trazer os objetos para o estado Managed, j�
 * que assim eles ser�o gerenciados e automaticamente sincronizados com o banco.
 * 
 *
 * 
 * 
 */
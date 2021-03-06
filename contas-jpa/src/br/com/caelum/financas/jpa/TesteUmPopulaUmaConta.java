package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteUmPopulaUmaConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setTitular("Antonio Donizildo");
		conta.setBanco("Caixa");
		conta.setAgencia("0000");
		conta.setNumero("54321");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		em.persist(conta);

		conta.setBanco("Bradesco");

		em.getTransaction().commit();

		em.close();
		

		
		
	}
}

/*
 * 
 * 
 * Estado Transient, Estado Managed, Estado Detached e estado Removed
 * 
 * Depois chamamos o m�todo persist() para que a conta fosse inserida ao banco
 * de dados. Sem isto, ela n�o seria salva e sumiria completamente caso a
 * aplica��o terminasse. Esse estado � chamado Transiente(ou Transient) e a
 * tarefa do m�todo persist() � justamente alterar esse estado para
 * Gerenciado(Managed).
 * 
 * 
 * 
 * 
 * Ao executar esse c�digo, percebemos na view console do Eclipse que foi gerado
 * automaticamente um insert e depois um update para a conta. Novamente o JPA
 * cuidou da entidade e sincronizou todas as suas altera��es. � importante
 * frisar que o estado Managed da entidade dura enquanto o EntityManager estiver
 * aberto.
 * 
 * 
 * 
 * estado Detached conta.setTitular("Leonardo"); em.persist(conta); quando o
 * entityManager estiver fechado ocasiona o erro de estado Detached, que ocorre
 * quando o objeto � retornado do banco mas o entityManager ja foi fechado para
 * resolver esse problema usa-se o em2.merge(conta);
 * 
 * 
 * 
 * Por �ltimo, h� o estado Removed, que significa que n�o h� mais representa��o
 * ou registro no banco, mas a entidade continua em mem�ria.
 * 
 * 
 * 
 * 
 * 
 */
package br.com.caelum.financas.jpql;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dto.ContaComNumeroEAgencia;
import br.com.caelum.financas.util.JPAUtil;

public class TesteTresComDTO {

	public static void main(String[] args) {

		System.out.println("**********************************");

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		List<ContaComNumeroEAgencia> contas = em.createQuery(
				"select new br.com.caelum.financas.dto.ContaComNumeroEAgencia(c.numero, c.agencia) from Conta c",
				ContaComNumeroEAgencia.class).getResultList();

		for (ContaComNumeroEAgencia con : contas) {
			System.out.println("Conta.Agencia: " + con.getAgencia());
			System.out.println("Conta.Numero: " + con.getNumeroConta());
		}

		//em.getTransaction().commit();

		em.close();

	}
}

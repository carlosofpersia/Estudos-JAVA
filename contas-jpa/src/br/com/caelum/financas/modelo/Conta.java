package br.com.caelum.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titular;
	private String banco;
	private String agencia;
	private String numero;

	@OneToMany(mappedBy = "conta", fetch = FetchType.EAGER)
	private List<Movimentacao> movimentacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", titular=" + titular + ", banco=" + banco + ", agencia=" + agencia + ", numero="
				+ numero + "]";
	}

}

/*
 * 
 * ---------------------------------------------------
 * Efeito Espelho:
 * Com mappedBy="conta" conseguimos manter o @OneToMany apenas como um espelho
 * (bidirecional) evitando que ele crie duas vezes o relacionamento no
 * banco(chave estrangeira e tabela de relacionamento). Movimentacao
 * 
 * Conta.class
 * @OneToMany(mappedBy = "conta") private List<Movimentacao> movimentacoes;
 * 
 * Movimento.class
 * @ManyToOne private Conta conta;
 * ---------------------------------------------------
 * 
 * 
 * 
 * @Entity, @Id e @GeneratedValue
 * 
 * 
 * nomenclatura do postgres, ele usa sequeces para autoincremento de chave
 * primaria
 * 
 * @SequenceGenerator(name = "SEQ_CONTAS", sequenceName = "SEQ_CONTAS",
 * initialValue = 1) public class Conta ..
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CONTAS")
 * private Integer id; ...
 * 
 * 
 * msqyl -u root -p skater
 * 
 * create database financas
 * 
 * show tables;
 * 
 * 
 * 
 */

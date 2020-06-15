package br.com.alura.business;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.exception.BusinessException;
import br.com.alura.interceptor.Logger;

/*
 * 
 * 
 * 
 * Aula 4 sobre logger, criar a interface interceptor Logger e adiciona-la aqui na classe  atrav�s de uma anota��o @Logger
 
 *
 *
 *

 * 
 * EJB com Java EE 8: API Rest com WildFly 15
 * Aula 4 - Nesta aula, aprendemos:
Como tratar exce��es em uma aplica��o que usa EJB
Como criar um interceptador EJB
Como criar a sua pr�pria anota��o
Como mapear uma exce��o para a view (JSON)
Como escrever mensagens de log usando java.util.logging.Logger
 *
 *
 *
 *
 * @Resource CDI para fazer o lookup da sess�o de e-mail do servidor de aplica��o - Esta anota��o faz o lookup de recursos disponibilizados pelo servidor de aplica��o.
 *
 * 
 *
 *
 * Aula 07
A integrar o EJB com JMS
Que JMS � o padr�o Java EE para mensageria
Que uma fila JMS serve como reposit�rio JMS
A injetar uma Queue e usar o JMSContext
A enviar mensagens JMS usando Producer
Como receber mensagens JMS com Message Driven Bean

* Gerenciamento de Transacao 2 formas:

	* CMT: container managed transaction;
		CMT � a marca��o impl�cita, ou declarativa da transa��o.
			A especifica��o JTA (Java Transaction API) oferece uma anota��o para tal.
		@TransactionAttribute;
		@TransactionManagement(TransactionManagementType.CONTAINER)

	* BMT: Bean Managed Transaction
		BMT � a marca��o expl�cita, ou program�tica da transa��o.
			No Bean Managed Transaction usamos o objeto UserTransaction para chamar begin, rollback e commit.
		begin(); rollback(); commit();
		@TransactionManagement(TransactionManagementType.BEAN)
		

O uso de transa��es � essencial para qualquer sistema que deseja garantir a durabilidade, integridade e consist�ncia dos dados. 
Por esse motivo, o EJB integra uma especifica��o para trabalhar com transa��es de alto n�vel, 
o JTA (Java Transaction API). 
O JTA oferece duas formas de demarca��o de transa��es, Bean-Managed (BMT), e Container-Managed (CMT).

		CMT (Container-Managed) � a marca��o impl�cita, ou declarativa da transa��o.
			A especifica��o JTA (Java Transaction API) oferece uma anota��o para tal.

		BMT (Bean-Managed) � a marca��o expl�cita, ou program�tica da transa��o.
			No Bean Managed Transaction usamos o objeto UserTransaction para chamar begin, rollback e commit.
 *
 *
 *Quando o m�todo inserir for chamado, uma nova transa��o deve ser criada para este processo. Como podemos configurar a chamada deste m�todo para iniciar sempre uma nova transa��o?
	Anotando o m�todo com @TransactionAttribute(TransactionAttributeType.REQUIRED_NEW)
	Esta anota��o, com este par�metro, configura o EJB para abrir uma nova transa��o toda vez que o m�todo � chamado.


Coloquei manualmente, agora o checked vai dar rollback!
@ApplicationException(rollback=true)
public class BusinessException extends Exception



 *
 *
 */

@Stateless
@Logger
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmailBusiness {

	@Inject
	AgendamentoEmailDAO agendamentoEmailDAO;

	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session sessaoEmail;

	private static String EMAIL_FROM = "mail.smtp.host";
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";

	public List<AgendamentoEmail> listarAgendamentosEmail() {

		return agendamentoEmailDAO.listarAgendamentoEmail();
	}

	/*
	 * @Valid = Bean Validation, usa o arquivo ValidationMessages.properties para
	 * mensagens
	 * 
	 * @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) - nao pega transacao ejb, da erro devido a exception, mas salva! nao gerou rollback.
	 * @TransactionAttribute(TransactionAttributeType.REQUIRED) - se nao tiver transacao aberta, ele abre! e da rollback.
	 * 
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarAgendamentosEmail(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {

		if (!agendamentoEmailDAO.listarAgendamentosEmailPorEmail(agendamentoEmail.getEmail()).isEmpty()) {
			// aula 4 ejb - criando uma excessao personalizada e carregando via interceptors
			throw new BusinessException("E-mail j� agendado.");
		}

		agendamentoEmail.setEnviado(false);
		agendamentoEmailDAO.salvarAgendamentoEmail(agendamentoEmail);

		//throw new RuntimeException(); com TransactionAttributeType.NOT_SUPPORTED deu exception, mas salvou.
		//throw new RuntimeException(); com TransactionAttributeType.REQUIRED deu excpetion e gerou um rollback.
		//throw new BusinessException(); 
	}

	public List<AgendamentoEmail> listarAgendamentosEmailNaoEnviados() {

		return agendamentoEmailDAO.listarAgendamentosEmailNaoEnviados();
	}
	
	public void marcarEnviadas(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setEnviado(true);
		agendamentoEmailDAO.atualizarAgendamentoEmail(agendamentoEmail);
	}
	

	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		try {

			
			System.out.println(sessaoEmail.getProperties());
			
			MimeMessage mensagem = new MimeMessage(sessaoEmail);
			mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
			mensagem.setRecipients(Message.RecipientType.TO, agendamentoEmail.getEmail());
			mensagem.setSubject(agendamentoEmail.getAssunto());

			mensagem.setText(Optional.ofNullable(agendamentoEmail.getMensagem()).orElse(""));

			Transport.send(mensagem, sessaoEmail.getProperty(EMAIL_USER), sessaoEmail.getProperty(EMAIL_PASSWORD));

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * public List<String> listarAgendamentosEmail() { List<String> emails = new
	 * ArrayList<>(); emails.add("carloseduardophp@gmail.com");
	 * emails.add("carlosofpersia@hotmail.com");
	 * emails.add("batataringosk8@yahoo.com.br"); return emails; }
	 */
}

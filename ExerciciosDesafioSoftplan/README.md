
-  Simplicidade e Clareza: Valorizamos um c�digo de boa qualidade, que seja simples, f�cil de ler, entender e alterar, ou seja, simplicidade, clareza e organiza��o do c�digo � essencial.

- Orienta��o a objetos: O design orientado a objetos tamb�m � uma caracter�stica importante que verificaremos. Buscamos aplicar em nosso dia a dia bons princ�pios como: DRY, YAGNI e SOLID. E padr�es como: DDD e Clean Architecture. Ent�o se ligue nisso, pois ser� um diferencial!

- Automa��o de Testes: Cobertura de testes automatizados � imprescind�vel para garantirmos a qualidade e produtividade na manuten��o e evolu��o do software. Por isso, tamb�m avaliaremos os testes.

- Compartilhar decis�es tomadas: Inclua um arquivo chamado README.md com as instru��es, compartilhando as decis�es e escolhas tomadas para melhor entendimento do seu racioc�nio.

N�o esperamos e n�o vamos analisar as seguintes quest�es de c�digo: persist�ncia em banco de dados ou arquivo, front-end ou itera��o com console. Por outro lado, separa��o de classes e pacotes � algo esperado e que ser� considerado na implementa��o.


--------------------------------------------------------


Sexta: Inicio do projeto

Comecei 23:26 e parei 03:30 da Sexta
Fiz algumas brincadeiras domingo de 19h as 22h.

Criei um projeto no Maven, com um Skeleton Simples;
Configurei o JUNIT;

Para ligar o projeto basta ter o Maven e dar uma olhada, as Classes Centrais s�o br.com.softplan.GeradorObservacaoNumeros, br.com.softplan.GeradorObservacaoValores 

Chamei nossa classe de br.com.softplan.business.GeradorObservacao para separar as camadas para ficar mais f�cil o entendimento do que ela representa.

Apesar que ela parece uma Utils, mas vamos ver at� o final.

Come�ando a refatora��o primeira coisa que fiz foi tirar o condicional para verificar se existe os n�meros da Listagem. 
Isso se encaixa nas "DRY, YAGNI e SOLID. E padr�es como: DDD e Clean Architecture"

Ap�s terminar a refatora��o percebi que al�m do que a classe j� fazia ela precisava fazer a funcionalidade de Valores funcionar.

Como eu lembrei depois da parte dos valores, eu refatorei novamente a classe para poder renderizar os valores no resultado.

Essa foi a minha primeira decis�o, eu precisava refatorar para retornar os valores, sem ter que mudar a forma de uso, ent�o eu decidi fazer as duas formas funcionarem. criei assim uma inteligencia com sobrecarga de metodos isso tudo em br.com.softplan.business.GeradorObservacao

agora se eu passar os parametros List ele retorna s�mente os n�meros, se eu passar um Map ele me retornar a funcionalidade de valores, a classe ficou inteligente e funcional, mas agora ela ficou muito cheia de responsabilidades, ent�o separei a classe que usa o List(Numeros) para a que usa Map (Valores).

Agora come�ou a ficar melhor, mais limpo, pois cada Classe ficou menor e com responsabilidade bem definida, nesse tempo tudo ok, mas eu queria usar essa funcionalidade de forma mais inteligente, ent�o utilizei os poderes do padr�o Strategy que serve para criar o objeto que eu espero e brinquei um pouco com o polimorfismo.

Talvez eu tenha brincado um pouco demais, mas ficou muito legal a implementa��o, criei tudo com testes unit�rios e criei tamb�m uma classe resource para testar a aplica��o direto.

Criei tamb�m algumas classes para organizar melhor a estrutura proposta, como uma classe para colocar as Constantes e uma Util para metodos est�ticos.

A solu��o uma vez decidida a programa��o foi r�pida, poderia ter usado mais recursos, como padr�es criadores, Exceptions, mas n�o vi necessidade para esse Exemplo1.










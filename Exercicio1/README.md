

Sexta: Inicio do projeto

Comecei 23:26 e parei 03:30

Criei um projeto no Maven, com um Skeleton Simples;
Configurei o JUNIT;



Chamei nossa classe de GeradorObservacaoBusiness para separar as camadas para ficar mais f�cil o entendimento do que ela representa.
Apesar que ela parece uma Utils, mas vamos ver at� o final.



Criei uma classe para testar a funcionalidade via pelo main (CLI ou Application)
Criei uma Classe de Teste.


Copiei a Classe que me passaram para refatorar.

Come�ando a fatora��o primeira coisa que fiz foi tirar o condicional para verificar se existe os n�meros da Listagem. 
Isso se encaixa nas "DRY, YAGNI e SOLID. E padr�es como: DDD e Clean Architecture"

Ajustei o codigo para o geraObservacao funcionar em uma linha, isso tudo testando com testes unit�rios:

Feito isso fui para o pr�ximo passo trabalhar no m�todo retornaCodigos, este estava com muitas respons�bilidades

decidi que o geraObservacao deveria finalizar o chamado e n�o o retornar c�digo, retirei o texto e criei tamb�m um m�etodo isPlural para auxiliar
a decis�o do texto se plural ou singular.


Feito isso resolvi dar uma lida novamente nos requisitos e vi que o cliente propos altera��es, da� tive que repensar minha solu��o sem alterar o 
comportamento j� existente no sistema, e agora.


Fiz uma sobrecarga nos m�todos principais geraObservacao e retornaCodigos, agora o sistema ficou dinamico e ainda aproveitei o que j� havia desenvolvido.

Decidi colocar os textos em uma classe privada dentro do nosso projeto pois n�o achei necess�rio criar um resource nesse momento.



package br.com.resources;

/**
 * 
 * @author carloss
 * 
 * 
 */

public class Aula1 {

	public static void main(String[] args) {

		
		//Arrays:
		
		
		
		
		//forma literal;
		int[] refs = {1,2,3,4,5};

		
		/*
		 * 
		 * 
		
		//C�digo omitido
		List<Conta> lista = new ArrayList<>();
		lista.add(cc1);
		lista.add(cc2);
		lista.add(cc3);
		lista.add(cc4);

		lista.sort( (c1, c2) -> Integer, compare(c1.getNumero(), c2.getNumero()) );

		Comparator<Conta> comp = (Conta c1, Conta c2) -> {

	        String nomeC1 = c1.getTitular().getNome();
	        String.nomeC2 = c2.getTitular().getNome();
	        return nomeC1.compareTo(nomeC2);
		};

		lista.sort( comp );

		lista.forEach( (conta) -> System.out.println(conta + ", " + conta.getTitular().getNome());

		//C�digo omitido

		 * 
		 * 
		 */

		System.out.println("ok");
	}
}

class Conta {
	
}




/*
 * 
Ol�! Bem-vindos ao curso de Java parte 6: Conhecendo o Java.util!

Meu nome � Nico Steppat e os acompanharei nas pr�ximas aulas. Se voc� gostaria de entender o seguinte trecho de c�digo:

Codigo da classe ...

Ent�o voc� veio ao lugar certo!

Trabalharemos com estruturas de dados, e entenderemos o seu funcionamento. Veremos como trabalhar com os m�todos das listas, como ordenar, e trabalhar com classes an�nimas, classes wrapper, incluindo o m�todo equals(), que havia falado anteriormente.

Temos v�rias funcionalidades importantes para o dia-a-dia do desenvolvedor, trabalharemos bastante neste pacote, e espero encontrar-los nos pr�ximos v�deos!

Ol�!

Neste curso, nosso objetivo � entendermos os pacotes java.util e java.io, e continuaremos a utilizar o java.lang.

Com rela��o ao java.lang, entenderemos melhor a nossa classe Object, pois temos os m�todos hashCode() e equals(), isso ser� esclarecido ao falarmos sobre java.util.

Antes de entrarmos no java.util em si, retornaremos ao c�digo que escrevemos para aprendermos um novo conceito.

Temos o projeto bytebank-herdado-conta, que finalizamos anteriormente, e conseguimos entend�-lo completamente, com uma exce��o, vamos observar o c�digo:

package br.com.bytebank.banco.test;

import br.com.bytebank.banco.modelo.Cliente;

public class Teste {

        public static void main(String[] args) {

//C�digo omitido
Na declara��o do m�todo main, n�o sabemos exatamente qual � a fun��o dos colchetes []. Antes de come�armos com o java.util, primeiro desvendaremos este mist�rio.

Renomearemos a classe Teste, clicando com o bot�o direto do mouse sobre ela, e selecionando "Refactor > Rename", a chamaremos de TesteObject. Em seguida, criaremos uma nova classe de teste, chamada Teste, com o seguinte conte�do:

package br.com.bytebank.banco.test;

public class Teste {

        public static void main(String[] args) {

        }
}
Por que existem os colchetes []?

Imaginemos que queremos armazenar um conjunto de dados, por exemplo, as idades de cinco pessoas, para isso, utilizamos as vari�veis:

package br.com.bytebank.banco.test;

public class Teste {

        public static void main(String[] args) {

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
Para cinco idades, isto pode at� funcionar, mas quando trabalhamos com um grande n�mero de dados, por exemplo, cem idades, j� se torna invi�vel. Precisamos de formas mais enxutas de armazenamento de dados, e para isso existem as estruturas de dados.

Conheceremos agora nossa primeira estrutura de dados, que se chama array:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
Raramente os arrays ser�o utilizados da forma como faremos agora, ou seja, manualmente.

Arrays s�o colchetes ([]) associados a algum tipo de dados, no caso, queremos apresentar idade que, como vimos, s�o do tipo int. Assim, o array ser� do tipo int. Em seguida, precisaremos de um nome para a vari�vel, que ser� idades:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades;

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
Os colchetes ([]) tamb�m poderiam estar posicionados ap�s o nome da vari�vel, da seguinte forma int idades[], apesar de ser mais comum sua utiliza��o da forma como colocamos no c�digo acima.

Um array tamb�m � um objeto, assim sendo, precisamos cri�-lo, pois temos uma refer�ncia que ainda n�o foi inicializada:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[];

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
O compilador aponta um erro de compila��o. Isso porque, ao criar o array, precisamos informar o seu tamanho. Quantas idades queremos guardar? 5, portando, o tamanho do nosso array � 5:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5];

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
Assim que inserimos o tamanho do array o c�digo volta a compilar.

Internamente, temos um processo que come�a com uma pilha de execu��o, representada pelo m�todo main. Nesta pilha, ou seja, no m�todo, foi criada uma vari�vel idades.

Esta vari�vel � um array, que como vimos, � um objeto. Os objetos ficam armazenados na mem�ria HEAP. Assim, idades � uma refer�ncia que aponta para um objeto na mem�ria, capaz de guardar cinco idades.

Como podemos acessar uma das posi��es? Utilizamos a vari�vel como refer�ncia e precisamos informar a posi��o que queremos acessar dentro de colchetes ([]). Por exemplo, se quisermos acessar a primeira posi��o, ela � representada pelo n�mero 0:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5];

            idades[0]

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
Nos ser� devolvido o valor que estiver armazenado na referida posi��o. Por enquanto, este valor � 0 j� que, por padr�o, ao criarmos um array ele � inicializado com 0 em todas as posi��es:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0]

            int idade1 = 29;
            int idade2 = 39;
            int idade3 = 19;
            int idade4 = 69;
            int idade5 = 59;

        }
}
O primeiro valor padr�o do tipo int dispon�vel � 0.

Acessando o array na posi��o 0, ele nos retorna o valor desta posi��o, que no nosso caso � a idade1. Podemos apagar os dados que hav�amos preenchido anteriormente:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            int idade1 = idades[0];

            System.out.println(idade1);

        }
}
Ao executarmos, temos o seguinte resultado no console:

0
Ele nos imprimiu o valor presente na posi��o 0, que por coincid�ncia, tamb�m � 0. Mas n�o queremos que seja 0, de acordo com nossos dados, ele deve ser 29. Para isso, inicializaremos o nosso array.

Utilizamos primeiro a refer�ncia do array, idades, seguida pelos colchetes ([]), que devem ser preenchidos com a posi��o que desejamos utilizar, no caso 0, e ao fim, informamos o valor que desejamos armazenar, no caso, 29:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0] = 29;

            int idade1 = idades[0];

            System.out.println(idade1);

        }
}
Isso significa que estamos armazenando no primeiro espa�o de mem�ria que criamos, como falamos acima. O 29 est� no �ndice zero, ou seja, na primeira posi��o.1

Agora, ao executarmos, temos o seguinte resultado no console:

29
Com isso, podemos inicializar as demais posi��es do array:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0] = 29;
            idades[1] = 39;
            idades[2] = 49;
            idades[3] = 59;
            idades[4] = 69;

            int idade1 = idades[0];

            System.out.println(idade1);

        }
}
Tentaremos acessar, por exemplo, a posi��o 4:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0] = 29;
            idades[1] = 39;
            idades[2] = 49;
            idades[3] = 59;
            idades[4] = 69;

            int idade1 = idades[4];

            System.out.println(idade1);

        }
}
Executaremos o programa, e temos o seguinte resultado no console:

69
Se o array tivesse, por exemplo, 50 posi��es, seria poss�vel acessarmos a de n�mero 49:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[50]; //inicializa o array com os valores padroes

            idades[0] = 29;
            idades[1] = 39;
            idades[2] = 49;
            idades[3] = 59;
            idades[4] = 69;

            int idade1 = idades[49];

            System.out.println(idade1);

        }
}
Ao executarmos o programa, temos o seguinte resultado no console:

0
Isso porque n�o inicializamos essa posi��o, logo, nos � fornecido o valor padr�o.

E o que acontece se tentarmos acessar uma posi��o que n�o existe? voltaremos a definir o array com 5 posi��es, e tentaremos novamente acessar a de n�mero 49:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0] = 29;
            idades[1] = 39;
            idades[2] = 49;
            idades[3] = 59;
            idades[4] = 69;

            int idade1 = idades[49];

            System.out.println(idade1);

        }
}
Executaremos o programa, e temos o seguinte resultado no console:

Exception in thread "main" java.long.ArrayIndexOutOfBoundsException: 49
        at br.com.bytebank.banco.test.Teste.main(Teste.java:16)
Um erro ocorre. Este tipo de erro, inclusive, � bastante comum. � uma exce��o unchecked, n�o somos obrigados a fazer nenhum tratamento.

Os arrays nos permitem ainda que perguntemos o seu tamanho. Criaremos um System.out.println(), utilizando a refer�ncia idades, e chamando o atributo length - notamos que n�o � um m�todo pois n�o � acompanhado de par�nteses:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            idades[0] = 29;
            idades[1] = 39;
            idades[2] = 49;
            idades[3] = 59;
            idades[4] = 69;

            int idade1 = idades[4];

            System.out.println(idade1);

            System.out.println(idades.length);

        }
}
Com a posi��o de volta para 4, na impress�o, executaremos o programa e temos o seguinte resultado no console:

69
5
Ou seja, temos em primeiro lugar o valor armazenado na quarta posi��o, 69, e, em seguida, o tamanho do nosso array, que possui um total de 5 posi��es.

Veremos agora como podemos inicializar um array dentro de um la�o. Primeiro, apagaremos todo o c�digo referente a inicializa��o que acabamos de criar.

Definiremos a primeira posi��o como 0, portanto, i = 0. O limite do la�o ser� o n�mero de posi��es, assim, utilizaremos o atributo que acabamos de aprender idades.length. Por fim, incrementaremos com o i++:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            for(int i = 0; i < idades.length; i++) {

            }

        }
}
O pr�ximo passo � a inicializa��o do array, dentro do la�o.

Como i representa as posi��es, � esta vari�vel que utilizaremos na inicializa��o, e que receber� i * i:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            for(int i = 0; i < idades.length; i++) {
                idades[i] = i * i;

            }

        }
}
Em seguida, teremos o mesmo la�o, mas dentro desta segunda representa��o faremos a impress�o dos valores:

package br.com.bytebank.banco.test;

public class Teste {

        //Array []
        public static void main(String[] args) {

            int[]idades = new int[5]; //inicializa o array com os valores padroes

            for(int i = 0; i < idades.length; i++) {
                idades[i] = i * i;
            }

            for(int i = 0; i < idades.length; i++) {
                System.out.println(idades[i]);
            }

        }
}
Executaremos, e temos o seguinte resultado no console:

0
1
4
9
16
Funcionou! Assim, conseguimos utilizar o array, tamb�m, com um la�o.

Ainda n�o vimos o porqu� da exist�ncia do String[] na assinatura do m�todo main, mas chegaremos l�. Primeiro, precisamos entender o que s�o arrays de refer�ncias.

At� a pr�xima!

------------------------------------------

Ao acessar uma posi��o inv�lida recebemos a exce��o ArrayIndexOutOfBoundException



1. Um array � sempre zero-based (o primeiro elemento se encontra na posi��o 0).
� correto, a primeira posi��o do array � sempre indicada pelo 0.

2. Arrays tamb�m s�o objetos.

3. Um array � sempre inicializado com os valores padr�es.
� verdade, pois cada posi��o do array � inicializada com o valor padr�o. Qual � esse valor padr�o? O tipo do array define. Por exemplo, no array do tipo int o valor padr�o � 0, no double o valor padr�o � 0.0.

4. � uma estrutura de dados.
� correto. Array � uma estrutura de dados.

5. Um array realmente possui uma sintaxe estranha pois usa esses colchetes ([]). Ent�o, se tiver com d�vida, relaxe e teste cada linha dentro de um m�todo main. N�o h� problema em errar, pois estamos aprendendo...
Sabendo disso, qual � a forma correta de criar um array do tipo double
double[] precos = new double[5];
Correto, definimos o tamanho na hora de criar o array.
Al�m da sintaxe apresentada na alternativa, tem uma alternativa (menos utilizada):
double precos[] = new double[5];
Ambas as formas s�o corretas.



* Valor padrao de uma referencia de array e null




----------------------------------------------------------------------------------------------------------------------------


Nesta aula, daremos continuidade � constru��o do array que inicializamos na aula anterior.

Como arrays s�o objetos, para criarmos um novo, utilizamos a palavra new:

public class Teste {

        public static void main(String[] args) {
            int[] idades = new int[5];

        }
}
Precisamos definir qual tipo de dados s�o armazenados, no caso, utilizamos o int. Indicamos que se tratam de arrays por meio do uso de colchetes ([]), os utilizamos tanto ao declarar o tipo, quanto ao definir o tamanho do array. Todo array deve ter um tamanho fixo, pr�-definido.

No nosso caso, definimos o tamanho como 5, isso significa que, na mem�ria onde os objetos s�o armazenados � criado um espa�o suficiente para que sejam guardados cinco n�meros inteiros.

Automaticamente, o array � inicializado com o valor padr�o do tipo definido, como aqui utilizamos o int, o valor padr�o inicial � 0.

Em seguida, vimos como podemos acessar um array. No caso, fizemos um la�o e criamos um mecanismo que nos permite acessar cada posi��o:

public class Teste {

        public static void main(String[] args) {
            int[] idades = new int[5];

        for(int i = 0; i < idades.length; i++) {
            idades[i] = i * i;
        }
    }
}
Utilizamos a refer�ncia idades, e os colchetes ([]), para indicarmos qual posi��o pretendemos acessar. Importante lembrar que para os arrays, as posi��es iniciam em 0, ou seja, a primeira posi��o � representada pelo n�mero 0.

Mas n�o esclarecemos anteriormente, o real significado de String[] na assinatura do m�todo main.

Temos que ter em mente que String � um tipo, uma classe, n�o um primitivo. Ou seja, o que fazemos ao declarar:

//C�digo omitido

public static void main(String[] args) {

//C�digo omitido
� declarar um array de refer�ncia.

Renomearemos a classe Teste, para TesteArrayDePrimitivos, e em seguida criaremos uma nova classe, um novo teste, chamado TestArrayReferencias, com um m�todo main:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

        }

}
Por que um array de refer�ncias? para estarmos preparados caso surja a necessidade de armazenamos diversas contas. E se tivermos 10 contas? onde guardar�amos as 10 refer�ncias? Uma possibilidade seria guard�-las dentro de um array.

Primeiro, vamos trabalhar com a classe ContaCorrente, onde iremos armazenar 10 contas correntes, declararemos ent�o o tipo:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                ContaCorrente

        }

}
Utilizaremos a mesma sintaxe do exemplo anterior, por isso, podemos desde j� traz�-la e mant�-la em coment�rios sobre o tipo ContaCorrente:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente

        }

}
Para indicarmos que se trata de um array, incluiremos os colchetes ([]), ap�s ContaCorrente:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[]

        }

}
Chamaremos a vari�vel de contas. Utilizaremos o new para indicar que estamos criando um novo objeto, repetindo o tipo, e os colchetes ([]), al�m do n�mero total de contas que pretendemos armazenar, no caso, 5:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];

        }

}
Criamos um objeto que pode guardar cinco refer�ncias de contas correntes. Quantas contas foram de fato criadas? nenhuma. Temos por enquanto somente o compartimento capaz de armazen�-las.

Dentro deste array n�o h� primitivos, mas podem viver refer�ncias, estas por sua vez, ser�o inicializadas com os valores padr�es.

Como criamos uma ContaCorrente, qual � o seu valor padr�o? No caso, � null. Por isso, n�o podemos dizer que foi criada alguma conta, pois o array n�o aponta para nenhum objeto.

Em seguida, criaremos uma ContaCorrente cc1, com seus respectivos dados de ag�ncia e n�mero:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];

                ContaCorrente cc1 = new ContaCorrente(22, 11);

        }

}
A ideia � que, agora, criamos o nosso primeiro objeto. Temos uma refer�ncia cc1 que aponta para ele. Em seguida, nosso objetivo ser� armazen�-lo na primeira posi��o em nosso array.

Como acessamos a primeira posi��o do array? primeiro, utilizamos o nome, em seguida fazemos a refer�ncia � posi��o entre colchetes ([]), para ent�o atribuirmos o valor, cc1:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];

                ContaCorrente cc1 = new ContaCorrente(22, 11);

                contas[0] = cc1;

        }

}
Internamente, � criada uma c�pia do valor cc1, que � armazenada na primeira posi��o e aponta para o objeto.

Criaremos em seguida mais um objeto, cc2:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);

        }

}
Temos mais uma refer�ncia em nosso c�digo, apontando para este novo objeto. O pr�ximo passo � armazenamos uma c�pia desta c�pia dentro do nosso array.

Para acessarmos a segunda posi��o, utilizamos o n�mero 1, e atribu�mos o valor cc2:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

        }

}
Testaremos nosso c�digo, tentaremos acessar o n�mero da segunda conta a partir do nosso array. Criaremos um System.out.println(), com o m�todo getNumero():

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                System.out.println(cc2.getNumero());

        }

}
Mas n�o queremos acessar o objeto diretamente, queremos acessar a c�pia que armazenamos. Como podemos fazer isso? Temos de fazer a refer�ncia ao array, utilizando a palavra contas, e incluir a posi��o que desejamos acessar, no caso a segunda posi��o, representada pelo n�mero 1:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[1].getNumero());
        }

}
Executaremos e temos o seguinte resultado no console:

22
Se tentarmos acessar a posi��o 0:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[0].getNumero());
        }

}
Temos o seguinte resultado:

11
Se tentarmos acessar a terceira posi��o:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[2].getNumero());
        }

}
Temos o seguinte resultado:

Exception in thread "main" java.long.NullPointerException
        at br.com.bytebank.banco.test.TestArrayReferencias.main(TestArrayReferencias.java:20)
Pois ela ainda n�o foi inicializada e, por padr�o, tem o valor null.

Retornaremos para a impress�o da segunda posi��o:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[1].getNumero());
        }

}
Estamos acessando o contas[1] e, em contrapartida, nos ser� devolvido um valor, que � uma refer�ncia. Mas onde ela � armazenada? em uma vari�vel, que por sua vez, tem que ter um tipo.

No nosso caso, o tipo da vari�vel � ContaCorrente. Assim, nosso retorno � uma refer�ncia do tipo ContaCorrente:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[1].getNumero());

                ContaCorrente ref = contas[1];
        }

}
Aqui, chamamos nossa refer�ncia de ref, ela tem o mesmo valor de cc2, ou seja, aponta para o objeto ContaCorrente.

Assim, podemos utilizar o ref.getNumero() para imprimirmos o n�mero da conta. Executaremos:

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[1].getNumero());

                ContaCorrente ref = contas[1];

                System.out.println(ref.getNumero());
        }

}
E obtivemos o seguinte resultado:

22
22
Indicando que nosso c�digo funcionou.

Este �ltimo System.out.println() equivale a System.out.println(cc2.getNumero()):

package br.com.bytebank.banco.test;

public class TestArrayReferencias {

        public static void main(String[] args) {

                //int[] idades = new int[5];
                ContaCorrente[] contas = new ContaCorrente[5];
                ContaCorrente cc1 = new ContaCorrente(22, 11);
                contas[0] = cc1;

                ContaCorrente cc2 = new ContaCorrente(22, 22);
                contas[1] = cc2;

                //System.out.println(cc2.getNumero());

                System.out.println(contas[1].getNumero());

                ContaCorrente ref = contas[1];
                System.out.println(cc2.getNumero());
                System.out.println(ref.getNumero());
        }

}
Se executarmos, temos o seguinte resultado:

22
22
22
Portanto, quantas contas criamos afinal? Duas. Quantos objetos criamos? Tr�s. J� refer�ncias, temos 9, destas, apenas 6 foram inicializadas.

Temos assim um array de refer�ncias.

Adiante, falaremos sobre array de refer�ncias polim�rfico. At� l�!



Voc� est� se preparando para a certifica��o Java e passou pelo c�digo seguinte:

ContaPoupanca[] contas = new ContaPoupanca[10];
ContaPoupanca cp1 = new ContaPoupanca(11,22);
ContaPoupanca cp2 = new ContaPoupanca(33,44);

contas[0] = cp1;
contas[1] = cp1;
contas[4] = cp2;
contas[5] = cp2;

ContaPoupanca ref1 = contas[1];
ContaPoupanca ref2 = contas[4];
Quantas refer�ncias apontam para a ContaPoupanca com a agencia 33 e n�mero 44?


Bingo! Vamos contar?

1) ContaPoupanca cp2 = new ContaPoupanca(33, 44);
2) contas[4] = cp2;
3) contas[5] = cp2;
4) ContaPoupanca ref2 = contas[4];




Continuando os estudos para certifica��o, voc� encontrou mais um trecho de c�digo:

ContaPoupanca[] contas = new ContaPoupanca[10];
ContaPoupanca cp1 = new ContaPoupanca(11,22);
ContaPoupanca cp2 = new ContaPoupanca(33,44);

contas[0] = cp1;
contas[4] = cp2;

System.out.println(contas[1].getNumero());
Executando esse trecho dentro do m�todo main do nosso projeto, qual � o resultado?

Obs: Se tiver com d�vidas teste o c�digo, sem problemas!

Selecione uma alternativa


* NullPointerException


Correto, pois estamos acessando o segundo elemento do array (a posi��o 1) e essa posi��o ainda est� null:

System.out.println(contas[1].getNumero());
Consequentemente recebemos uma NullPointerException.



* M�os na massa: Trabalhando com arrays

Chegou a hora de voc� p�r em pr�tica o que foi visto na aula. Para isso, execute os passos listados abaixo.

Arrays
1) No projeto bytebank-herdado-conta, no pacote br.com.bytebank.banco.test, renomeie a classe Teste para TesteObject. Ap�s isso, tamb�m no mesmo pacote, crie uma nova classe TesteArrayDePrimitivos, j� com o m�todo main:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

    }

}
2) Para guardar idades, crie um array de inteiros, com cinco posi��es:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

    }

}
3) Em seguida, inicialize cada posi��o do array com uma idade:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

        idades[0] = 29;
        idades[1] = 39;
        idades[2] = 49;
        idades[3] = 59;
        idades[4] = 69;

    }

}
4) Ao acessar alguma posi��o do array, ele retorna o valor guardado nessa posi��o. Ent�o, guarde o valor da quarta posi��o do array em uma vari�vel e imprima-a:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

        idades[0] = 29;
        idades[1] = 39;
        idades[2] = 49;
        idades[3] = 59;
        idades[4] = 69;

        int idade4 = idades[3];

        System.out.println(idade4);

    }

}
5) Imprima o tamanho do array, acessando o seu atributo length:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

        idades[0] = 29;
        idades[1] = 39;
        idades[2] = 49;
        idades[3] = 59;
        idades[4] = 69;

        int idade4 = idades[3];

        System.out.println(idade4);
        System.out.println(idades.length);

    }

}
6) Inicialize o array dentro de um la�o, mas antes apague todo o c�digo, deixando somente a inicializa��o do array:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

    }

}
7) Agora, inicialize o array dentro de um la�o, por exemplo:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

        for (int i = 0; i < idades.length; i++) {
            idades[i] = i * i;
        }

    }

}
8) Em seguida, fa�a um outro la�o e imprima cada elemento do array:

public class TesteArrayDePrimitivos {

    public static void main(String[] args) {

        int[] idades = new int[5];

        for (int i = 0; i < idades.length; i++) {
            idades[i] = i * i;
        }

        for (int i = 0; i < idades.length; i++) {
            System.out.println(idades[i]);
        }

    }

}
Arrays de refer�ncia
9) No projeto bytebank-herdado-conta, no pacote br.com.bytebank.banco.test, crie a classe TesteArrayReferencias, j� com o m�todo main:

public class TesteArrayReferencias {

    public static void main(String[] args) {

    }

}
10) Para guardar contas, crie um array de ContaCorrente, com cinco posi��es:

public class TesteArrayReferencias {

    public static void main(String[] args) {

        ContaCorrente[] contas = new ContaCorrente[5];

    }

}
11) Instancie duas contas e guarde-as nas duas primeiras posi��es do array, por exemplo:

public class TesteArrayReferencias {

    public static void main(String[] args) {

        ContaCorrente[] contas = new ContaCorrente[5];

        ContaCorrente cc1 = new ContaCorrente(22, 11);
        ContaCorrente cc2 = new ContaCorrente(22, 22);

        contas[0] = cc1;
        contas[1] = cc2;

    }

}
12) Atrav�s do array, acesse a conta da primeira posi��o e imprima seu n�mero:

public class TesteArrayReferencias {

    public static void main(String[] args) {

        ContaCorrente[] contas = new ContaCorrente[5];

        ContaCorrente cc1 = new ContaCorrente(22, 11);
        ContaCorrente cc2 = new ContaCorrente(22, 22);

        contas[0] = cc1;
        contas[1] = cc2;

        System.out.println(contas[0].getNumero());

    }

}




* Forma literal
PR�XIMA ATIVIDADE

At� agora vimos a forma "classica" de criar um objeto array usando a palavra chave new, por exemplo:

int[] numeros = new int[6];
numeros[0] = 1;
numeros[1] = 2;
numeros[2] = 3;
numeros[3] = 4;
numeros[4] = 5;
No entanto tamb�m h� uma forma literal. Literal, nesse contexto, significa usar valores diretamente, menos burocr�tico, mais direito. Veja a diferen�a:

int[] refs = {1,2,3,4,5};
Usamos as chaves {} para indicar que se trata de um array e os valores j� ficam declarados dentro das chaves.


O que aprendemos?
PR�XIMA ATIVIDADE

Nessa aula sobre Arrays aprendemos:

Um array � uma estrutura de dados e serve para guardar elementos (valores primitivos ou refer�ncias)
Arrays usam colchetes ([]) sintaticamente
Arrays t�m um tamanho fixo!
Um array tamb�m � um objeto!
Arrays s�o zero-based (o primeiro elemento se encontra na posi��o 0)
Um array � sempre inicializado com os valores padr�es.
Ao acessar uma posi��o inv�lida recebemos a exce��o ArrayIndexOutOfBoundException
Arrays possuem um atributo length para saber o tamanho
A forma literal de criar uma Array, com o uso de chaves {}.
No pr�ximo cap�tulo falaremos mais um pouco sobre arrays (do tipo Object) e veremos como funciona esse par�metro do m�todo main.



 * 
 *
 */


# Sistema de Eleições

Este projeto, desenvolvido para a disciplina de Engenharia de Software II, apresenta um sistema de eleições robusto e versátil. Ele suporta uma variedade de tipos de eleições, incluindo presidenciais, municipais, estaduais e universitárias, com a capacidade de realizar eleições em dois turnos.

O sistema é pré-carregado com candidatos para cada tipo de eleição e funciona como uma urna eletrônica básica, permitindo votações para um candidato à presidência e dois candidatos a deputados federais através de uma interface de linha de comando intuitiva.

A segurança é uma prioridade neste sistema, com a inicialização da urna e o término da votação protegidos por senha. Apenas indivíduos credenciados pelo Tribunal Superior Eleitoral (TSE) têm acesso a essas senhas, garantindo a integridade do processo eleitoral.

Cada eleitor tem permissão para votar uma única vez, usando seu título de eleitor. Ao se apresentar para votar, o eleitor fornece o número do título, que a urna eletrônica usa para exibir o nome do eleitor. Após a confirmação dos dados, a votação é iniciada.

Os eleitores têm a opção de votar em branco ou nulo, além de poderem se abster da votação. Cada eleitor deve votar em três candidatos: um presidente e dois deputados.

Após o término da eleição, o sistema pode fornecer estatísticas detalhadas, incluindo o número de votos de cada candidato e as respectivas percentagens em relação ao total de votos válidos. O sistema também anuncia os candidatos eleitos e derrotados, proporcionando transparência e clareza ao processo eleitoral.

## Integrantes do grupo:

  - João Vitor Ferreira - 2021039654
  - Luiza de Melo Gomes - 2021040075
  - Lucas Roberto Santos Avelar - 2021039743
  
## Escolha das funções a serem refatoradas

Após rodar a ferramenta em cima do lizard, foi feita a escolha das 3 funções mais complexas do ponto de vista da análise ciclomática. Essas funções são:

- `ReadAndPrint::certifiedProfessionalMenu@153-206@./src/main/java/com/election/view/ReadAndPrint.java` com CCN de 14.
- `Main::main@6-57@./src/main/java/Main.java` com CCN de 9.
- `ElectionController::initializeElection@25-60@./src/main/java/com/election/controller/ElectionController.java` com CCN de 8.

## Dependências

Para executar o sistema, você precisará ter instalado:

- [Java Developer Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Make

## Comandos Make

Na raiz do repositório, você pode usar os seguintes comandos:

- `make` ou `make all`: Compila o sistema
- `make run`: Executa o sistema
- `make clean`: Limpa os arquivos `.class` gerados na compilação

## Como Rodar

Na raiz do repositório, use o comando `make all` para compilar o sistema e `make run` para executá-lo.

## Utilização

O sistema já vem inicializado com alguns candidatos para cada tipo de eleição, que podem ser visualizados nos arquivos .txt referentes a cada eleição. O sistema também já vem com todos os eleitores possíveis; para utilizá-los, basta verificar o arquivo voterLoad.txt.

No menu inicial, siga os passos abaixo:

1. Selecione o tipo de eleição que você deseja.
2. Selecione o turno em que você deseja realizar a eleição.
3. Selecione a opção 2 para logar como um profissional certificado e aperte 1 para fazer login (usuário: Cert, senha: 54321). Isso permitirá iniciar/finalizar a eleição e mostrar o resultado ao final da eleição.
4. Após iniciar a eleição, aperte 1 no menu principal para logar como eleitor e insira o número do título (títulos disponíveis: `123456789012`, `268888719264`, `638991919941`, `965575671024`).
5. Digite o número do candidato em que você deseja votar e confirme.
6. Faça o login como um profissional certificado e aperte 1 para finalizar a eleição.
7. Realize o login como profissional novamente e aperte 1 para mostrar os resultados.
8. Aperte 0 para sair da aplicação.

# Projeto da disciplina Reutilização de Software
- Inicialmente projeto apenas com eleição presidencial em que houve a adição da possibilidade de segundo turno assim como novos tipos de eleições, sendo estendido para suportar municipais, estaduais e universitárias.

## Padrões de projeto:
- Factory Method
- Strategy

## Padrão de arquitetura:
- Model-View-Control

## Dependencias:

- [Java Developer Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Make

## Comandos make:

- `make` ou `make all`: Compilar
- `make run`: Executar
- `make clean`: Limpa os arquivos `.class` gerados na compilação

## Como rodar:

- Na root do repositório use o comando `make all` para compilar e make run para executar o programa

## Utilização:

- O sistema já vem inicializado com alguns candidatos para cada tipo de eleição, sendo possivel visualizar nos arquivos .txt refente a cada eleição
- O sistema já vem com todos os eleitores possíveis para utilizá-los basta checar o arquivo voterLoad.txt

No menu inicial:

- **Passo 1:** Selecione o tipo de eleição que você deseja
- **Passo 2:** Selecione o turno em que você deseja realizar a eleição
- **Passo 3:** Selecione a opção 2 para logar como um profissional certificado e aperte 1 para fazer login:
  - Usuário: cert , Senha: 54321 -> Inicialização/finalização da eleição (liberar pra poder votar) e mostrar o resultado ao final da eleição.
- **Passo 4:** Após iniciar a eleição, aperte 1 no menu principal para logar como eleitor e insira o número do título
  - Títulos disponíveis: `123456789012`, `268888719264`, `638991919941`. `965575671024`
- **Passo 5:** Digite o número do candidato em que você deseja votar e confirme
- **Passo 6:** Faça o login como um profissional certificado e aperte 1 para finalizar a eleição
- **Passo 7:** Realize o login como profissional novamente e aperte 1 para mostrar os resultados
- **Passo 8:** Aperte 0 para sair da aplicação
 

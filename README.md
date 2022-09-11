# Api de Votação para Associados

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Essa API é uma solução backend para gerenciar essas sessões de votação.

------------
## Quick Start

### Dependências:

Serão necessárias algumas dependências para rodar o projeto.
- [Docker](https://www.docker.com/ "Docker") e [Docker Compose](https://docs.docker.com/compose/install/ "Docker Compose")
- Java [OpenJDK:17](https://adoptium.net/ "OpenJDK:17")

### Como rodar o projeto:

Com esse repositório clonado, em sua ultima versão, siga os passos abaixo.

#### Compilar a aplicação
Dentro do diretório `/sessoesdevoto`

Execute o comando `./mvnw clean package`

Aguarde, esse processo pode demorar alguns minutos.

#### Executar o Docker Compose

No diretório raiz, existe um arquivo `docker-compose.yml`

Para executá-lo, basta rodar o comando `docker-compose up`

Certifique-se de que não existam aplicações rodando nas portas **8080** e **27017**

#### Verificando se está tudo rodando

Para verificar se a api está rodando, acesse o endereço `http://localhost:8080/api/v1/actuator/health`

O retorno deve ser `{"status":"UP"}`

Caso o retorno seja `{"status":"DOWN"}` verifique se a aplicação do banco de dados está rodando corretamente

#### Acessando a documentação

Para acessar a documentação da api e começar a utilizá-la.

Acesse o endereço `http://localhost:8080/api/v1/swagger-ui.html`


------------

## Sobre o projeto

### Arquitetura
Esse projeto foi desenvolvido utilizando os conceitos da arquitura **[Hexagonal](https://engsoftmoderna.info/artigos/arquitetura-hexagonal.html "Hexagonal")**. Permitindo a separação entre as camadas de **aplicação**, **dominio** e **infraestrutura**. Facilitando, assim, as substituições das tecnologias utilizadas.

### Tecnologias e Frameworks
Foram utilizadas várias tecnologias para o desenvolvimento da API, mas seu principal Framework foi o [Spring](https://spring.io/ "Spring").
Abaixo, segue uma lista das tecnologias:

- **Java 17**
- **Maven**
- **Spring Boot**
- **Spring Web MvC**
- **Spring Actuator**
- **Spring Data MongoDB**
- **MongoBD**
- **Docker e Docker Compose**
- **SpringDoc (Swagger, OpenAPI)**

------------

## To do list

- :white_check_mark: Registrar os votos de associados em pautas com o controle da sessão

- :white_check_mark: Persistir os dados

- :white_check_mark: Visualizar o resultado da votação

- :white_check_mark: Documentar o projeto

- :white_check_mark: Documentar a API

- Documentar o código
	- :white_check_mark: Camada de domínio
	- Camada de aplicação
	- Camada de infraestrutura

- :white_check_mark: Aplicar uma arquitetura que deixe a aplicação desacoplada

- Gerar Logs da aplicação

- Criar testes automatizados

- Permitir e facilitar o deploy da aplicação

- Integração com sistemas externos (verificar se o associado pode votar)

- Emissão de evento em mensageria e filas (ao término de uma sessão de votação)

- Executar testes de performance

- :white_check_mark: Possibilitar o versionamento da API

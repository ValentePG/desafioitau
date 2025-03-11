# 📌 Desafio Junior Itaú

- Descrição completa do projeto [aqui](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior?tab=readme-ov-file)

# 📦 Pré-requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados na sua máquina:

- [Docker](https://www.docker.com/get-started) (versão recomendada: `28.0.1`)
  
- (Opcional) [Java 21](https://www.oracle.com/java/technologies/downloads/#java21) e [Maven 3.9.9](https://maven.apache.org/download.cgi) para rodar sem Docker
  
# 🚀 Passo a passo para rodar o projeto

1️⃣ Clonar o repositório

    git clone https://github.com/ValentePG/desafioitau.git
    cd desafioitau
    
2️⃣ Instalar dependências

- Linux/MacOs

      ./mvnw clean install
    
- Windows
    
      mvnw.cmd clean install
  
💡 Caso tenha Maven instalado globalmente, você pode rodar:

     mvn clean install
     
3️⃣ Rodar a aplicação

Você pode rodar o projeto de duas formas:

- Método 1: Rodando sem Docker

- Executar o projeto:
  - Linux/MacOs
  
        ./mvnw spring-boot:run
    
  - Windows
  
        mvnw.cmd spring-boot:run

- Método 2: Rodando com Docker

    Caso tenha o Docker instalado **(Certifique-se de estar autenticado no docker engine)**, você vai precisar buildar a imagem para seu docker daemon com:

      mvn compile jib:dockerBuild

    Logo após, rodar o comando:

      docker run -d -p 8080:8080 --name desafioitau desafiovagaitau:0.0.1-SNAPSHOT  

4️⃣ Acessando a API

Após iniciar o projeto, os endpoints da API estarão disponíveis nos seguintes links:

- API:

      GET http://localhost:8080/estatistica
      POST http://localhost:8080/transacao
      DELETE http://localhost:8080/transacao

- Swagger:

      http://localhost:8080/swagger-ui.html

- HealthCheck
      
      http://localhost:8080/actuator/health

5️⃣ Rodar os testes (opcional)

- **Para rodar os testes unitários**

      ./mvnw test

- **Para rodar os testes de integração**

      ./mvnw verify -P integration-test

- **Para rodar ambos**
  - Linux/MacOS

        ./mvnw test && ./mvnw verify -P integration-test

  - Windows

        ./mvnw test; ./mvnw verify -P integration-test

# 🛠️ Tecnologias Utilizadas

- Java 21

- Spring Boot 3.4.3

- Docker 28.0.1

# 📄 Licença
Este projeto está sob a licença MIT - veja o arquivo LICENSE para mais detalhes.



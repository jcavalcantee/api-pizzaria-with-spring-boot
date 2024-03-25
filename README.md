# RESTful API com SpringBoot 3 - Gerenciamento de Pizzaria

Este projeto se trata de uma API RESTful para gerenciar uma pizzaria. A API permite realizar operações básicas como criação, leitura, atualização e exclusão de produtos,
clientes e pedidos. Além disso, essa aplicação consome uma API externa (ViaCEP API) para buscar dados relacionados a endereços.

### Tecnologias Utilizadas:

- **Java**
- **SpringBoot**
- **JPA / Hibernate:**
- **SQL / MySQL**
- **Maven**
- **Swagger**
- **Flyway**
- **HATEOAS**

## Pré-requisitos
Antes de começar, certifique-se de que você tenha as seguintes ferramentas instaladas:
- **JDK 11 ou superior**
- **Maven**
- **MySQL**

## Configuração do Banco de Dados
Criar um banco de dados com o nome de `pizzaria` e adicione as credenciais ao `/resources/application.properties`
```bash
spring.datasource.dbcp2.driver-class-name: com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/pizzaria?createDatabaseIfNotExist=true
spring.datasource.username=pizzaria
spring.datasource.password=101729

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
## Executando
Você pode executar a aplicação de diferentes maneiras:

**Opção 1: Linha de Comando**
```bash 
mvn spring-boot run
```

**Opção 2: IDE**
- Importe o projeto em sua IDE de preferência que suporte Spring Boot e Maven
- Procure a classe principal PizzariaApplication.java e execute como uma aplicação Java.

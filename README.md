# 🥷 CRUD Naruto II - API Spring Boot

![Java](https://img.shields.io/badge/Java-21-informational?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.10-brightgreen?style=flat&logo=spring)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=flat)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange?style=flat)
![GitHub](https://img.shields.io/badge/GitHub-Version_Controle-black?style=flat&logo=github)
---

## 📌 Descrição do Projeto
CRUD completo inspirado no universo **Naruto**, desenvolvido com **Java 21** e **Spring Boot 3.4.10**, utilizando **JPA/Hibernate** para persistência.  
Permite gerenciar personagens, jutsus, e suas relações de forma estruturada, com validações e tratamento de exceções personalizadas.

O projeto suporta dois perfis de banco de dados:
- **H2** (em memória, para testes rápidos e desenvolvimento local)
- **MySQL** (para persistência real)

---

## ⚡ Tecnologias

- Java 21
- Spring Boot 3.4.10
- Spring Data JPA
- H2 Database (in-memory)
- MySQL 8
- Maven
- MapStruct
- Swagger/OpenAPI
- JUnit 5

---

## 🏗 Estrutura do Projeto
```
src/main/java/io/github/Erissonteixeira/api_crudnarutoII
├── controller
│   ├── PersonagemController.java
│   └── JutsuController.java
├── dto
│   ├── PersonagemRequestDTO.java
│   ├── PersonagemResponseDTO.java
│   ├── JutsuRequestDTO.java
│   └── JutsuResponseDTO.java
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── InvalidActionException.java
│   └── ResourceNotFoundException.java
├── mapper
│   ├── PersonagemMapper.java
│   └── JutsuMapper.java
├── model
│   ├── Personagem.java
│   ├── Jutsu.java
│   ├── Ninja.java
│   ├── NinjaDeNinjutsu.java
│   └── NinjaDeTaijutsu.java
├── repository
│   ├── PersonagemRepository.java
│   └── JutsuRepository.java
└── service
    ├── PersonagemService.java
    └── JutsuService.java

src/test/java/io/github/Erissonteixeira/api_crudnarutoII
├── controller
│   ├── PersonagemControllerTest.java
│   └── JutsuControllerTest.java
├── service
│   ├── PersonagemServiceTest.java
│   ├── JutsuServiceTest.java
│   ├── PersonagemServiceExceptionTest.java
│   └── JutsuServiceExceptionTest.java

```

---

## 🗄 Bancos de Dados

O projeto possui **dois perfis de banco** configuráveis:

### H2 (In-Memory) - Padrão para desenvolvimento e testes
```yaml
spring:
  profiles: h2
  datasource:
    url: jdbc:h2:mem:naruto_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
```
### MySQL - Para produção/local
```yaml
spring:
  profiles: mysql
  datasource:
    url: jdbc:mysql://localhost:3306/naruto_db?useSSL=false&serverTimezone=UTC
    username: root
    password: senha123
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
```
---
Para rodar com MySQL:
```
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
## 🧪 Testes

### O projeto conta com testes para Controllers, Services e Exceptions:

- PersonagemControllerTest

- JutsuControllerTest

- PersonagemServiceTest

- JutsuServiceTest

- PersonagemServiceExceptionTest

- JutsuServiceExceptionTest

Para rodar todos os testes:
```
mvn test
```
---
## 📌 API

A documentação está disponível via Swagger:
```
http://localhost:8081/swagger-ui.html
```
---
## 💻 Git & Branches

- Branch principal: main

- Branch de refatoração: refactor/api-crudnarutoII

- Para atualizar a main com a refactor:

```
git pull origin main
git merge refactor/api-crudnarutoII
git push origin main
```
---
## 🚀 Executando o Projeto
1. Clonar o repositório:
   ```
   git clone https://github.com/Erissonteixeira/Crud-Naruto-II.git
   ```
2. Entrar na pasta do projeto:
   ```
   cd api-crudnarutoII
   ```
3. Rodar com H2 (padrão):
   ```
   mvn spring-boot:run
   ```
4. boot:run
Ou rodar com MySQL:
```
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
---
## 📂 Endpoints Principais
- personagens - CRUD de personagens

- jutsus - CRUD de jutsus

  Todos os endpoints estão documentados via Swagger.
  ---
  ## 🔧 Observações
  Perfil padrão: H2

- Para usar MySQL, configurar o application-mysql.yml corretamente

- Testes unitários e de serviço já incluídos

- Exceptions personalizadas:

- ResourceNotFoundException

- InvalidActionException

- GlobalExceptionHandler padroniza respostas
---








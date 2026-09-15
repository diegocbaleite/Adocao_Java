# 🐾 Sistema de Adoção de Animais

<p align="center">
  <strong>API REST para gerenciamento de processos de adoção responsável de animais</strong>
</p>

<p align="center">
  Java 17 • Spring Boot • PostgreSQL • Spring Data JPA • MinIO • Swagger/OpenAPI
</p>

---

## 📌 Sobre o projeto

O **Sistema de Adoção de Animais** é uma API REST desenvolvida em **Java com Spring Boot** para apoiar o gerenciamento de processos relacionados à adoção responsável de animais.

A aplicação permite administrar informações de:

* Usuários;
* Animais;
* Abrigos;
* Solicitações de adoção.

O projeto foi estruturado utilizando princípios de **Programação Orientada a Objetos**, arquitetura em camadas, separação de responsabilidades, validação de dados e persistência utilizando **Spring Data JPA / Hibernate**.

Além disso, a aplicação possui integração com **MinIO** para armazenamento de imagens e utiliza **Swagger/OpenAPI** para documentação dos endpoints.

---

## 🎯 Objetivos do projeto

O projeto possui como principais objetivos:

* Desenvolver uma API REST utilizando Java e Spring Boot;
* Aplicar conceitos de Programação Orientada a Objetos;
* Implementar arquitetura em camadas;
* Persistir dados utilizando PostgreSQL;
* Utilizar Spring Data JPA e Hibernate;
* Aplicar DTOs para transferência de dados;
* Implementar validações de entrada;
* Centralizar regras de negócio na camada Service;
* Documentar a API utilizando Swagger/OpenAPI;
* Implementar armazenamento de imagens utilizando MinIO;
* Aplicar boas práticas de desenvolvimento backend;
* Evoluir progressivamente o projeto para uma arquitetura mais próxima de ambientes corporativos.

---

# 🚀 Funcionalidades

## 👤 Usuários

O módulo de usuários é responsável pelo gerenciamento das pessoas cadastradas no sistema.

Principais operações:

* Cadastro de usuários;
* Consulta de usuários;
* Atualização de dados;
* Gerenciamento de informações pessoais;
* Gerenciamento de informações de contato.

---

## 🐶 Animais

O módulo de animais permite controlar os animais cadastrados e disponíveis no sistema.

Entre as informações gerenciadas estão:

* Nome;
* Espécie;
* Raça;
* Idade;
* Descrição;
* Status;
* Abrigo responsável;
* Imagem do animal.

Principais operações:

* Cadastro de animais;
* Consulta de animais;
* Atualização dos dados;
* Controle do status do animal;
* Associação com abrigo;
* Armazenamento de imagens.

---

## ❤️ Adoções

O módulo de adoções é responsável por controlar o relacionamento entre usuários e animais.

Principais responsabilidades:

* Registro de solicitações de adoção;
* Associação entre usuário e animal;
* Controle das informações da adoção;
* Consulta dos registros existentes.

---

## 🏠 Abrigos

O módulo de abrigos permite gerenciar as instituições responsáveis pelo acolhimento dos animais.

Principais operações:

* Cadastro de abrigos;
* Consulta de abrigos;
* Atualização das informações;
* Associação dos animais aos respectivos abrigos.

---

# 🧱 Arquitetura

O sistema utiliza uma arquitetura organizada em camadas.

O objetivo dessa organização é separar responsabilidades e facilitar manutenção, evolução e testes da aplicação.

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

A aplicação também utiliza:

```text
DTO
 │
 ▼
Mapper
 │
 ▼
Model
```

---

## 📂 Estrutura do projeto

```text
src
├── main
│   ├── java
│   │   └── br
│   │       └── com
│   │           └── adocao
│   │               └── system
│   │                   ├── controller
│   │                   ├── docs
│   │                   ├── dto
│   │                   ├── enums
│   │                   ├── exception
│   │                   ├── mapper
│   │                   ├── model
│   │                   ├── repository
│   │                   └── service
│   │
│   └── resources
│       └── application.properties
│
└── test
    └── java
```

---

## 📚 Responsabilidades das camadas

| Camada         | Responsabilidade                                         |
| -------------- | -------------------------------------------------------- |
| **Controller** | Receber requisições HTTP e disponibilizar endpoints REST |
| **Service**    | Implementar regras de negócio                            |
| **Repository** | Realizar persistência e consulta dos dados               |
| **Model**      | Representar as entidades do domínio                      |
| **DTO**        | Transferir dados entre cliente e aplicação               |
| **Mapper**     | Converter entidades em DTOs e DTOs em entidades          |
| **Exception**  | Centralizar tratamento de erros                          |
| **Docs**       | Organizar a documentação dos endpoints da API            |

---

# 🛠️ Tecnologias utilizadas

## Backend

* **Java 17**
* **Spring Boot 3**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **Bean Validation**
* **Lombok**

---

## Banco de dados

* **PostgreSQL** — banco de dados principal;
* **H2** — utilizado como apoio para testes.

---

## Armazenamento de arquivos

* **MinIO**

O MinIO é utilizado para armazenamento de arquivos e imagens e possui compatibilidade com a API do Amazon S3.

---

## Documentação da API

* **Swagger**
* **OpenAPI**
* **Springdoc OpenAPI**

Após iniciar a aplicação, a documentação poderá ser acessada pelo Swagger UI.

Exemplo:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Gerenciamento do projeto

* **Maven**
* **Git**
* **GitHub**

---

## Ambiente de desenvolvimento

* **IntelliJ IDEA**

---

# 🗄️ Persistência de dados

A aplicação utiliza **PostgreSQL** como banco de dados relacional.

O acesso aos dados é realizado através do **Spring Data JPA**, utilizando o Hibernate como implementação ORM.

Fluxo simplificado:

```text
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
Spring Data JPA
    │
    ▼
Hibernate
    │
    ▼
PostgreSQL
```

O modelo de domínio possui relacionamentos envolvendo:

```text
Usuário
   │
   │
   ▼
Adoção
   ▲
   │
   │
Animal
   │
   ▼
Abrigo
```

---

# 🐘 PostgreSQL

O projeto utiliza PostgreSQL como banco de dados principal.

Exemplo de criação do banco:

```sql
CREATE DATABASE adocao_db;
```

Exemplo de configuração local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/adocao_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

> ⚠️ Não utilize senhas reais diretamente em repositórios públicos. Para ambientes de produção, as credenciais devem ser armazenadas utilizando variáveis de ambiente ou mecanismos apropriados de gerenciamento de segredos.

---

# 🖼️ Armazenamento de imagens

O projeto utiliza **MinIO** para armazenamento das imagens dos animais.

Fluxo simplificado:

```text
Cliente
   │
   │ Upload
   ▼
Spring Boot
   │
   ▼
MinIO Service
   │
   ▼
MinIO
   │
   ▼
Bucket
```

Exemplo de configuração:

```properties
minio.url=http://localhost:9000
minio.access-key=SUA_ACCESS_KEY
minio.secret-key=SUA_SECRET_KEY
minio.bucket=adocao
```

---

# 📖 Swagger / OpenAPI

A documentação da API é disponibilizada utilizando **Springdoc OpenAPI**.

O objetivo é permitir:

* Visualização dos endpoints;
* Visualização dos parâmetros;
* Documentação das respostas;
* Testes das requisições diretamente pelo navegador;
* Melhor entendimento da API por outros desenvolvedores.

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# ⚙️ Como executar o projeto

## Pré-requisitos

Antes de executar a aplicação, certifique-se de possuir:

* Java 17 ou superior;
* PostgreSQL;
* Maven ou Maven Wrapper;
* Git;
* MinIO, caso queira utilizar o armazenamento de imagens.

---

## 1. Clone o repositório

```bash
git clone https://github.com/diegocbaleite/Adocao_Java.git
```

Entre no projeto:

```bash
cd Adocao_Java
```

---

## 2. Configure o PostgreSQL

Crie o banco:

```sql
CREATE DATABASE adocao_db;
```

Depois configure o arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/adocao_db
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver
```

---

## 3. Configure o MinIO

Exemplo:

```properties
minio.url=http://localhost:9000
minio.access-key=SUA_ACCESS_KEY
minio.secret-key=SUA_SECRET_KEY
minio.bucket=adocao
```

---

## 4. Execute a aplicação

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

---

# 🔎 API

A aplicação possui recursos REST para gerenciamento de:

```text
Usuários
Animais
Adoções
Abrigos
```

Para consultar os endpoints disponíveis e seus respectivos métodos HTTP, utilize a documentação Swagger da aplicação.

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🌿 Estratégia de branches

O projeto utiliza uma estratégia simples de versionamento.

```text
main
  ▲
  │
developer
  ▲
  │
feature/*
```

### `main`

Contém a versão estável do sistema.

### `developer`

Branch utilizada para integração e desenvolvimento contínuo.

### `feature/*`

Branches temporárias utilizadas para desenvolvimento de novas funcionalidades.

Exemplos:

```text
feature/swagger-abrigos
feature/autenticacao
feature/testes-unitarios
```

Após a conclusão e validação, a funcionalidade poderá ser integrada à branch `developer`.

---

# 📚 Conceitos aplicados

Durante o desenvolvimento estão sendo aplicados conceitos importantes de desenvolvimento backend:

* Programação Orientada a Objetos;
* Classes e objetos;
* Encapsulamento;
* API REST;
* Arquitetura em camadas;
* Injeção de dependência;
* Spring Boot;
* Spring Data JPA;
* Hibernate;
* PostgreSQL;
* Relacionamentos entre entidades;
* DTOs;
* Mappers;
* Bean Validation;
* Tratamento de exceções;
* Upload de arquivos;
* Documentação com Swagger/OpenAPI;
* Git e GitHub;
* Organização de branches.

---

# 🔐 Segurança — evolução planejada

A camada de autenticação e autorização faz parte da evolução planejada do projeto.

Entre as implementações previstas estão:

* Spring Security;
* Autenticação de usuários;
* Criptografia de senhas;
* JWT;
* Controle de acesso por perfil;
* Proteção dos endpoints.

Fluxo planejado:

```text
Usuário
   │
   ▼
Login
   │
   ▼
Spring Security
   │
   ▼
JWT
   │
   ▼
Endpoint protegido
```

---

# 🐳 Docker — evolução planejada

Também está prevista a utilização de Docker para padronizar o ambiente de desenvolvimento.

Arquitetura planejada:

```text
┌───────────────────────┐
│      Spring Boot      │
│        API REST       │
└───────────┬───────────┘
            │
      ┌─────┴─────┐
      ▼           ▼
 PostgreSQL      MinIO
```

A utilização do Docker Compose permitirá iniciar os serviços necessários de forma padronizada.

---

# 🔮 Roadmap

Funcionalidades e melhorias previstas:

* [x] API REST com Spring Boot
* [x] Persistência com Spring Data JPA
* [x] PostgreSQL
* [x] DTOs
* [x] Mappers
* [x] Bean Validation
* [x] Tratamento de exceções
* [x] Integração com MinIO
* [x] Swagger/OpenAPI
* [ ] Spring Security
* [ ] Autenticação JWT
* [ ] Testes unitários
* [ ] Testes de integração
* [ ] Docker
* [ ] Docker Compose
* [ ] Pipeline CI/CD
* [ ] Dashboard administrativo
* [ ] Estatísticas de adoções
* [ ] Sistema de doações via PIX
* [ ] Integração com WhatsApp API

---

# 👨‍💻 Autor

**Diego Assunção Leite**

**Analista de Sistemas | Desenvolvedor Java**

📍 Cuiabá - MT

📧 [dev.diegocba@gmail.com](mailto:dev.diegocba@gmail.com)

GitHub:
https://github.com/diegocbaleite

---

# ⭐ Projeto

Este projeto está em desenvolvimento contínuo e faz parte da evolução prática de conhecimentos em **Java, Spring Boot, arquitetura de software, APIs REST e desenvolvimento backend**.

Novas funcionalidades serão adicionadas conforme a evolução da aplicação.

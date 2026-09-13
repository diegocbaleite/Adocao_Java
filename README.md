# 🐾 Sistema de Adoção de Animais

<p align="center">
  <strong>API REST para gerenciamento de adoção responsável de animais</strong>
</p>

<p align="center">
  Java • Spring Boot • PostgreSQL  • JWT • MinIO • Docker
</p>

---

## 📌 Sobre o projeto

O **Sistema de Adoção de Animais** é uma aplicação web desenvolvida com **Java e Spring Boot** com o objetivo de facilitar o gerenciamento do processo de adoção responsável.

A aplicação permite o gerenciamento de **usuários, animais, unidades/abrigos e solicitações de adoção**, além de oferecer autenticação segura e armazenamento de imagens utilizando **MinIO**.

O projeto foi desenvolvido seguindo princípios de **API RESTful**, separação de responsabilidades, validação de dados e boas práticas de desenvolvimento backend.

---

## 🎯 Objetivos

* Desenvolver uma API REST utilizando Java e Spring Boot.
* Implementar autenticação e autorização utilizando JWT.
* Persistir dados utilizando PostgreSQL e Spring Data JPA.
* Implementar upload e armazenamento de imagens utilizando MinIO.
* Utilizar Docker para facilitar a configuração e execução do ambiente.
* Aplicar boas práticas de desenvolvimento e organização de código.

---

## 🚀 Funcionalidades

### 👤 Usuários

* Cadastro de usuários
* Autenticação
* Controle de usuários ativos
* Armazenamento seguro de senhas
* Dados pessoais e informações de contato

### 🐶 Animais

* Cadastro de animais
* Consulta de animais disponíveis
* Informações sobre espécie, raça, idade e descrição
* Controle do status de adoção
* Upload de imagens
* Armazenamento das imagens utilizando MinIO

### ❤️ Adoções

* Solicitação de adoção
* Associação entre usuário e animal
* Registro das adoções
* Histórico de adoções

### 🏠 Unidades / Abrigos

* Cadastro de unidades e abrigos
* Associação de animais às unidades
* Gerenciamento das informações dos locais de acolhimento

---

## 🔐 Segurança

A API utiliza **Spring Security** juntamente com **JWT (JSON Web Token)** para autenticação e proteção dos endpoints.

O fluxo de autenticação funciona da seguinte forma:

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
Requisições autenticadas
```

Além disso:

* Senhas são armazenadas de forma criptografada.
* Endpoints protegidos exigem autenticação.
* O token JWT é enviado no header das requisições protegidas.

Exemplo:

```http
Authorization: Bearer <token>
```

---

## 🧱 Arquitetura

O projeto segue uma organização baseada em responsabilidades, buscando facilitar a manutenção e evolução da aplicação.

```text
src
└── main
    └── java
        └── ...
            ├── controller
            ├── service
            ├── repository
            ├── entity
            ├── dto
            ├── security
            ├── exception
            └── config
```

### Principais responsabilidades

| Camada     | Responsabilidade             |
| ---------- | ---------------------------- |
| Controller | Exposição dos endpoints REST |
| Service    | Regras de negócio            |
| Repository | Persistência dos dados       |
| Entity     | Representação das entidades  |
| DTO        | Transferência de dados       |
| Security   | Autenticação e autorização   |
| Exception  | Tratamento de exceções       |
| Config     | Configurações da aplicação   |

---

## 🛠️ Tecnologias utilizadas

### Backend

* **Java 17+**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **Spring Security**
* **JWT**
* **Bean Validation**
* **Lombok**

### Banco de dados

* **PostgreSQL **

### Armazenamento

* **MinIO**

### DevOps / Ambiente

* **Docker**
* **Docker Compose**
* **Maven**

### IDE

* IntelliJ IDEA

---

## 🗄️ Persistência de dados

A aplicação utiliza **MySQL** como banco de dados relacional e **Spring Data JPA / Hibernate** para o mapeamento objeto-relacional.

O modelo contempla entidades relacionadas ao processo de adoção, incluindo:

```text
Usuário
   │
   └──► Adoção ◄─── Animal
                      │
                      └──► Unidade / Abrigo
```

---

## 🖼️ Armazenamento de imagens

As imagens dos animais são armazenadas utilizando **MinIO**, uma solução compatível com a API do Amazon S3.

O fluxo de upload é realizado pela aplicação e os arquivos são armazenados em um bucket configurado no ambiente.

```text
Cliente
   │
   │ Upload
   ▼
Spring Boot
   │
   ▼
MinIO
   │
   ▼
Bucket de imagens
```

---

## 🐳 Docker

O projeto utiliza **Docker Compose** para facilitar a configuração dos serviços necessários para execução da aplicação.

O ambiente pode ser estruturado com os seguintes serviços:

```text
┌─────────────────────┐
│    Spring Boot      │
│       API REST      │
└──────────┬──────────┘
           │
     ┌─────┴─────┐
     ▼           ▼
  MySQL        MinIO
```

Isso permite reproduzir o ambiente de desenvolvimento de forma mais simples e consistente.

---

## ⚙️ Como executar o projeto

### Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java 17 ou superior
* Maven
* Docker
* Docker Compose
* Git

### 1. Clone o repositório

```bash
git clone https://github.com/diegocbaleite/Adocao_Java.git
```

Entre no diretório:

```bash
cd Adocao_Java
```

### 2. Configure as variáveis de ambiente

Configure as credenciais e informações necessárias para conexão com:

* MySQL
* MinIO
* JWT

Exemplo:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=adocao
DB_USERNAME=root
DB_PASSWORD=sua_senha

MINIO_ENDPOINT=http://localhost:9000
MINIO_ACCESS_KEY=sua_access_key
MINIO_SECRET_KEY=sua_secret_key

JWT_SECRET=sua_chave_secreta
```

> **Importante:** não versionar arquivos contendo senhas, tokens ou outras credenciais reais.

### 3. Suba os serviços

```bash
docker compose up -d
```

### 4. Execute a aplicação

Utilizando Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## 🔎 API

A aplicação disponibiliza endpoints REST para gerenciamento dos principais recursos do sistema.

Entre os recursos disponíveis estão:

```text
/api/usuarios
/api/animais
/api/adocoes
/api/unidades
```

A autenticação é realizada através de JWT para os endpoints protegidos.

---

## 📚 Principais conceitos aplicados

Este projeto foi desenvolvido com foco em conceitos importantes do desenvolvimento backend com Java:

* Programação orientada a objetos
* API REST
* Injeção de dependência
* Spring Boot
* Spring Security
* JWT
* JPA / Hibernate
* Relacionamentos entre entidades
* DTOs
* Validação de dados
* Tratamento de exceções
* Upload de arquivos
* Docker
* Banco de dados relacional
* Arquitetura em camadas

---

## 🔮 Próximas melhorias

Algumas funcionalidades planejadas para evolução do projeto:

* [ ] Integração com WhatsApp API
* [ ] Sistema de doações via PIX
* [ ] Dashboard administrativo
* [ ] Estatísticas de adoções e doações
* [ ] Melhorias no gerenciamento de unidades e abrigos
* [ ] Documentação completa da API com Swagger/OpenAPI
* [ ] Implementação de testes automatizados
* [ ] Pipeline de CI/CD

---

## 👨‍💻 Autor

**Diego Assunção Leite**

**Analista de Sistemas | Desenvolvedor Java**

📍 Cuiabá - MT

📧 [dev.diegocba@gmail.com](mailto:dev.diegocba@gmail.com)

🔗 GitHub: https://github.com/diegocbaleite

---

## ⭐ Projeto

Se este projeto foi útil para você ou despertou seu interesse, considere deixar uma ⭐ no repositório.

O projeto está em evolução e novas funcionalidades serão adicionadas conforme o desenvolvimento avançar.

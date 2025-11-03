# 🐶 Sistema de Adoção de Animais

> **Autor:** Diego Assunção Leite  
> **Local:** Cuiabá - MT  
> **Linguagem:** Java com Spring Boot  
> **Banco de Dados:** MySQL  
> **Armazenamento de Imagens:** MinIO  
> **Gerenciamento:** Docker Compose  
> **Autenticação:** JWT (JSON Web Token)

---

## 📖 Descrição do Projeto

O **Sistema de Adoção de Animais** é uma aplicação web desenvolvida para **facilitar o processo de adoção responsável**, permitindo o cadastro de animais, gerenciamento de adoções, doações.

O sistema foi desenvolvido em **Spring Boot** e segue boas práticas de arquitetura **RESTful**, com validação de dados, segurança e integração com o **MinIO** para upload de fotos temporárias.

---

## 🧱 Tecnologias Utilizadas

| Camada | Tecnologia |
|--------|-------------|
| Linguagem | Java 17+ |
| Framework | Spring Boot |
| Banco de Dados | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Armazenamento de Arquivos | MinIO |
| Autenticação | JWT |
| Gerenciamento de Containers | Docker Compose |
| Build Tool | Maven |
| IDE Recomendada | IntelliJ IDEA |
| Outras Bibliotecas | Lombok, Validation, Spring Security |

---

## ⚙️ Funcionalidades Principais

### 👤 Usuário
- Cadastro e autenticação de usuários  
- Campos: nome, email, telefone, endereço, CPF, idade, senha, data de cadastro  
- Senha armazenada de forma criptografada  
- Campo `ativo` indica se o usuário está habilitado no sistema  

### 🐕 Animal
- Cadastro de animais disponíveis para adoção  
- Upload de fotos para o **MinIO**  
- Informações: nome, espécie, raça, idade, descrição e status (disponível/adotado)  

### 💌 Adoção
- Solicitação de adoção por parte dos usuários  
- Registro do vínculo entre o usuário e o animal adotado  
- Histórico de adoções  

### 🏠 Unidade / Abrigo
- Cadastro de abrigos e unidades que acolhem animais  

### 💾 Upload de Fotos (MinIO)
- Envio de fotos de animais com geração 
- Armazenamento seguro em bucket configurado no MinIO  

---

## 🔒 Segurança e Autenticação

- O sistema utiliza **JWT (JSON Web Token)** para autenticação.  
- O login retorna um **token de acesso**, que deve ser enviado em todas as requisições protegidas no header:

💰 Integrações Futuras

Integração com WhatsApp API para comunicação direta com adotantes.

PIX de doações para apoiar abrigos.

Painel Dashboard Administrativo com estatísticas de adoções, doações e unidades.

👨‍💻 Autor

Diego Assunção Leite
Analista de Sistemas | Desenvolvedor Java | 
📍 Cuiabá - MT
📧 E-mail: dev.diegocba@gmail.com
🔗 GitHub: https://github.com/diegocbaleite



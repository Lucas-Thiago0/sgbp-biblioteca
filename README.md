# SGBP — Sistema de Gerenciamento de Biblioteca

> Projeto desenvolvido para a disciplina de Análise e Projeto de Software — UMC (Universidade de Mogi das Cruzes)  
> Etapa 3 — Aplicação Web com Java, Spring Boot e MongoDB Atlas

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar Localmente](#como-executar-localmente)
- [Configuração do MongoDB Atlas](#configuração-do-mongodb-atlas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [Capturas de Tela](#capturas-de-tela)

---

## Sobre o Projeto

O **SGBP** é um sistema web para gerenciamento de bibliotecas universitárias. Permite o controle completo do acervo de livros, com cadastro, edição, exclusão, controle de disponibilidade, reservas e registro de atendimentos (empréstimos e devoluções).

O sistema foi desenvolvido seguindo os padrões da **Engenharia de Software**, com separação em camadas (Controller → Service → Repository → Model), integração com banco de dados em nuvem e interface responsiva.

---

## Tecnologias Utilizadas

| Camada       | Tecnologia                        |
|--------------|-----------------------------------|
| Back-end     | Java 21 + Spring Boot 3.4         |
| Front-end    | Thymeleaf + Tailwind CSS (CDN)    |
| Banco de Dados | MongoDB Atlas (Nuvem)           |
| Build        | Maven                             |
| IDE sugerida | IntelliJ IDEA / VS Code           |

---

## Pré-requisitos

Antes de executar, certifique-se de ter instalado:

- [Java 21 (JDK)](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/download.cgi) (ou use o wrapper `./mvnw` incluído)
- Conta no [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) (gratuita — tier M0)
- Git

---

## Como Executar Localmente

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/sgbp-biblioteca.git
cd sgbp-biblioteca
```

### 2. Configure a conexão com o MongoDB Atlas

Configure sua string de conexão no MongoDB no arquivo application.properties
```

### 3. Execute a aplicação

```bash
# Com Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Ou com Maven instalado globalmente
mvn spring-boot:run
```

### 4. Acesse no navegador

```
http://localhost:8080
```

O sistema redirecionará automaticamente para o **Painel Geral**.

---

## Configuração do MongoDB Atlas

1. Acesse [cloud.mongodb.com](https://cloud.mongodb.com) e faça login
2. Crie um cluster gratuito (M0 — Free Tier)
3. Em **Database Access**, crie um usuário com senha
4. Em **Network Access**, adicione `0.0.0.0/0` para permitir acesso de qualquer IP
5. Clique em **Connect → Connect your application** e copie a URI
6. Cole a URI no `application.properties` conforme o passo 2

---

## Estrutura do Projeto

```
sgbp-biblioteca/
├── src/
│   └── main/
│       ├── java/br/com/umc/sgbp_biblioteca/
│       │   ├── controller/
│       │   │   └── LivroController.java     # Rotas HTTP e lógica de navegação
│       │   ├── model/
│       │   │   └── Livro.java               # Entidade mapeada no MongoDB
│       │   ├── repository/
│       │   │   └── LivroRepository.java     # Interface Spring Data MongoDB
│       │   ├── service/
│       │   │   └── LivroService.java        # Regras de negócio
│       │   └── SgbpBibliotecaApplication.java
│       └── resources/
│           ├── templates/
│           │   ├── dashboard.html           # Painel Geral
│           │   ├── lista-livros.html        # Gestão de Acervo
│           │   ├── cadastro-livro.html      # Novo / Editar Livro
│           │   ├── reservas.html            # Tela de Reservas
│           │   ├── atendimento.html         # Tela de Atendimento
│           │   ├── usuarios.html            # Tela de Usuários
│           │   └── emprestimos.html         # Tela de Empréstimos
│           └── application.properties      # Configurações da aplicação
├── pom.xml
└── README.md
```

---

## Funcionalidades Implementadas

### ✅ Etapa 3 — Concluídas

| Funcionalidade                        | Status |
|---------------------------------------|--------|
| Dashboard com métricas do acervo      | ✅ |
| Listagem de livros (acervo completo)  | ✅ |
| Cadastro de novo livro (todos os campos) | ✅ |
| Edição de livro existente (upsert)    | ✅ |
| Exclusão de livro                     | ✅ |
| Alternar status Disponível/Emprestado | ✅ |
| Campos: título, autor, editora, categoria, ISBN, ano, exemplares, localização | ✅ |
| Integração com MongoDB Atlas (nuvem)  | ✅ |
| Tela de Reservas (UI + modal)         | ✅ |
| Tela de Atendimento (UI + modais)     | ✅ |
| Design consistente Teal #14b8a6       | ✅ |

## Capturas de Tela

> Inclua prints das telas aqui para o Manual do Usuário (ABNT).

| Tela | Descrição |
|------|-----------|
| `dashboard.html` | Painel com totais de livros, disponíveis, emprestados e exemplares |
| `lista-livros.html` | Tabela completa do acervo com ações de editar/excluir/alternar status |
| `cadastro-livro.html` | Formulário de cadastro com todos os campos (incluindo editora, categoria, exemplares) |
| `reservas.html` | Listagem de reservas com modal para nova reserva |
| `atendimento.html` | Painel de atendimento com modais de empréstimo, devolução e ocorrência |

---

## Autor

Desenvolvido por Lucas Thiago
Curso de Engenharia de Software — UMC

---

*Este projeto foi desenvolvido para fins acadêmicos como parte das atividades práticas do M1.*
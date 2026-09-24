# 📚 Bookstore — Sistema Completo de Gerenciamento de Livraria

![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Next.js](https://img.shields.io/badge/Next.js-16-000000?style=for-the-badge&logo=next.js&logoColor=white)
![React](https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-5-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-4-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

Aplicação full-stack moderna para gestão e comercialização de livros, composta por um backend robusto em **Spring Boot** com persistência em **MySQL** (gerenciado por **Flyway** e **MapStruct**) e uma interface reativa em **Next.js 16** com **React 19**, **Tailwind CSS 4** e componentes **Shadcn UI**.

---

## 📌 Visão Geral do Sistema

O projeto é organizado como um monorepo modular contendo:
- 🖥️ **`front_end`**: Interface do usuário moderna construída com Next.js (App Router), Tailwind CSS v4 e validações com Zod.
- ⚙️ **`back_end`**: API RESTful em Java e Spring Boot com arquitetura desacoplada, migrações versionadas via Flyway, mapeamento DTO-Entidade via MapStruct e documentação via OpenAPI/Swagger.
- 🐳 **Infraestrutura**: Configuração em Docker Compose para inicialização instantânea do banco MySQL.
- 📋 **Governança**: Padrão [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/) e versionamento semântico integrado a fluxos de PR e Jira.

---

## 🏗️ Estrutura do Repositório

```text
bookstore/
├── back_end/               # API RESTful Spring Boot
│   ├── src/main/java/      # Controladores, Entidades, Mappers, Services, Repositories
│   ├── build.gradle        # Gerenciamento de dependências e plugins Gradle
│   ├── docker-compose.yml  # Container MySQL 8
│   └── README.md
├── front_end/              # Interface Web Next.js
│   ├── src/                # Páginas, componentes UI, hooks e serviços Axios
│   ├── package.json        # Dependências React 19, Next 16, Tailwind 4
│   └── README.md
├── docs/                   # Documentação adicional e diagramas
├── CHANGELOG.md            # Histórico de alterações e releases
└── README.md               # Este arquivo
```

---

## 🛠️ Tecnologias Utilizadas

### Backend (`/back_end`)
- **Linguagem & Framework:** Java 21+ & Spring Boot 4.x
- **Persistência:** Spring Data JPA & Hibernate
- **Banco de Dados:** MySQL 8
- **Migrações:** Flyway (`flyway-core`, `flyway-mysql`)
- **Mapeamento:** MapStruct 1.6.3
- **Documentação da API:** SpringDoc OpenAPI / Swagger UI
- **Build Tool:** Gradle

### Frontend (`/front_end`)
- **Framework:** Next.js 16 (React 19)
- **Linguagem:** TypeScript 5
- **Estilização:** Tailwind CSS v4 & PostCSS
- **Componentes:** Shadcn UI
- **Consumo HTTP:** Axios
- **Validação de Formulários:** Zod

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- [JDK 21](https://adoptium.net/) ou superior
- [Node.js](https://nodejs.org/) (v20+) e npm
- [Docker & Docker Compose](https://www.docker.com/)

---

### 1. Inicializar o Banco de Dados (MySQL via Docker)
```bash
cd back_end
docker compose up -d
```

### 2. Executar o Back-end
No diretório `back_end`:
```bash
./gradlew bootRun
```
*No Windows:* `gradlew.bat bootRun`

A API estará disponível em `http://localhost:8080`.
Acesse a documentação Swagger em: `http://localhost:8080/swagger-ui/index.html`.

---

### 3. Executar o Front-end
Em outro terminal, acesse o diretório `front_end`:
```bash
cd front_end
npm install
npm run dev
```

Abra `http://localhost:3000` no seu navegador para interagir com o sistema.

---

## 👨‍💻 Autor

Desenvolvido por **Douglas Meneses**.

- 💼 GitHub: [@douglasmeneses](https://github.com/douglasmeneses)
- ✉️ Email: [meneses.doug@gmail.com](mailto:meneses.doug@gmail.com)

# 🧠 FórumHub — Challenge Back End 4 | Alura ONE

Este projeto é uma API REST inspirada no funcionamento real de fóruns como o da Alura. A ideia é criar um espaço onde usuários possam interagir com tópicos e respostas, tudo isso gerenciado via back-end com autenticação e persistência de dados.

## 🧩 Sobre

Esse projeto faz parte do Challenge Back-End T6 do programa ONE da Alura + Oracle. A proposta é simular o que acontece por trás de um fórum real: usuários, tópicos, respostas, autenticação e todas as validações que um sistema desse tipo exige.

## 🚀 Tecnologias e ferramentas usadas

- **Java 21**
- **Spring Boot**
- **Spring Security** — autenticação/autorização
- **JWT** — geração e validação de token
- **SpringDoc OpenAPI** — documentação da API (JSON + Swagger UI)
- **MySQL** — banco de dados relacional
- **Flyway** — controle de migrations

## 🛠️ Funcionalidades principais

- Rotas protegidas com token (JWT)
- Cadastro e autenticação de usuários
- Criação de tópicos vinculados a usuários
- Sistema de respostas para cada tópico
- Data de criação automática
- Filtros e paginação nas listagens
- Swagger UI em: [`/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)


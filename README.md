# RestAPIFurb

API REST para gerenciamento de equipamentos, desenvolvida para a **Prova de Suficiência de Programação Web II** — FURB, 2026/2.

Projeto inicializado com o [Spring Initializr](https://start.spring.io) e construído sobre Spring Boot 4.1.0 / Java 17.

## Tecnologias

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- Spring Security + JWT (io.jsonwebtoken)
- H2 Database (em memória)
- springdoc-openapi (Swagger UI)
- Bean Validation (Jakarta Validation)

## Requisitos do enunciado atendidos

| # | Requisito | Como foi atendido |
|---|---|---|
| 1 | REST + JSON com códigos HTTP corretos | `ResponseEntity` em cada controller + `ApiExceptionHandler` centralizado (400, 401, 404 etc.) |
| 2 | Persistência via ORM, nomenclatura padrão (tabela plural / classe singular) | JPA/Hibernate — `Equipment`→`equipamentos`, `EquipmentType`→`tipos`, `User`→`usuarios` |
| 3 | Rota protegida por token | Spring Security + JWT — POST/PUT/DELETE em `/equipamentos` exigem `Authorization: Bearer` |
| 4 | Documentação Swagger | springdoc-openapi, disponível em `/swagger-ui.html` |
| 5 | Arquitetura em camadas (DAO/Service) | Pacotes separados: `controller`, `service`, `repository`, `entity`, `dto` |
| 6 | Validação dos atributos do modelo | `@NotBlank`/`@NotNull` nas entidades e DTOs |

## Como executar

**Pré-requisitos:** JDK 17+ (Maven é opcional — qualquer IDE com suporte a Maven/Spring Boot também resolve as dependências e roda o projeto sozinha).

Na raiz do projeto (onde está o `pom.xml`):

```bash
mvn spring-boot:run
```

Isso baixa as dependências, compila e sobe a aplicação. A API fica disponível em:

```
http://localhost:8080/RestAPIFurb
```

## Endpoints

URL base: `http://localhost:8080/RestAPIFurb`

| Método | Rota | Autenticação |
|---|---|---|
| GET | `/equipamentos` | pública |
| GET | `/equipamentos/{id}` | pública |
| POST | `/equipamentos` | requer token |
| PUT | `/equipamentos/{id}` | requer token |
| DELETE | `/equipamentos/{id}` | requer token |
| POST | `/auth/login` | pública |

Exemplo de corpo para `POST` / `PUT` em `/equipamentos`:

```json
{
  "nome": "Imp HP",
  "tipo": { "id": 3, "nome": "Impressora" }
}
```

## Autenticação (JWT)

Um usuário já vem pré-cadastrado ao subir a aplicação:

```
login: admin
senha: senha123
```

Login:

```
POST /auth/login
Content-Type: application/json

{ "username": "admin", "password": "senha123" }
```

Resposta:

```json
{ "token": "<jwt>", "type": "Bearer" }
```

Use o token nas rotas protegidas:

```
Authorization: Bearer <token>
```

## Documentação Swagger

```
http://localhost:8080/RestAPIFurb/swagger-ui.html
```

Todas as rotas — inclusive o login — podem ser testadas ali mesmo, sem precisar de Postman.

## Banco de dados (H2)

Banco em memória, recriado a cada execução. Ao subir, já vêm cadastrados 3 equipamentos (Notebook Dell, Projetor Epson, Notebook Lenovo) e o usuário `admin`.

Console para inspecionar os dados:

```
http://localhost:8080/RestAPIFurb/h2-console

JDBC URL: jdbc:h2:mem:resapifurb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
Usuário:  sa
Senha:    (em branco)
```

## Estrutura do projeto

```
src/main/java/br/furb/resapifurb/
├── config/       # segurança, JWT, Swagger
├── controller/   # endpoints REST
├── dto/          # objetos de entrada/saída da API
├── entity/       # entidades JPA
├── exception/    # tratamento de erros
├── repository/   # acesso a dados (Spring Data JPA)
└── service/      # regras de negócio
```

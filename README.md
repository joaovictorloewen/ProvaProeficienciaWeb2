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

Isso baixa as dependências, compila e sobe a aplicação em `http://localhost:8080/RestAPIFurb`.

> Abrir só essa URL base no navegador redireciona automaticamente para o Swagger — não é um endpoint em si, é só o prefixo de todas as rotas abaixo.

### Links úteis (com a aplicação rodando)

| O que é | Link |
|---|---|
| Documentação Swagger (testa tudo por aqui) | http://localhost:8080/RestAPIFurb/swagger-ui.html |
| Lista de equipamentos | http://localhost:8080/RestAPIFurb/equipamentos |
| Console do banco H2 | http://localhost:8080/RestAPIFurb/h2-console |
| Login (gera o token JWT) | `POST` http://localhost:8080/RestAPIFurb/auth/login |

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

> ⚠️ `/auth/login` só aceita **POST**. Colar essa URL direto no navegador faz uma requisição GET e retorna erro (405 - Method Not Allowed) — isso é esperado, não é bug. Pra testar, use o botão "Try it out" no Swagger (ou um POST via curl/Postman/Insomnia).

No Swagger: depois de rodar o login e copiar o `token` da resposta, clique no botão **Authorize** (cadeado no canto superior direito) e cole só o token (sem escrever "Bearer" antes — o Swagger já adiciona isso sozinho). A partir daí os botões "Try it out" de POST/PUT/DELETE em `/equipamentos` passam a funcionar.

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

> ⚠️ O formulário do H2 Console abre com valores de exemplo (JDBC URL `jdbc:h2:~/test`, usuário `admin`) que **não são os do projeto**. Se clicar em Conectar sem trocar, dá erro "Database not found". Sempre sobrescreva os três campos acima antes de conectar.

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

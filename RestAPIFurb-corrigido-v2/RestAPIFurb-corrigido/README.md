# RestAPIFurb

API REST de equipamentos para a prova de suficiência de Programação Web II - FURB - 2026/2.

Baseado em **Spring Boot 4.1.0** / Java 17.

## Funcionalidades

- CRUD de equipamentos com JSON
- Persistência em banco relacional (H2) via JPA/Hibernate
- Autenticação JWT para operações protegidas (POST/PUT/DELETE)
- Documentação Swagger disponível em `/swagger-ui.html`
- Validação de todos os atributos nos modelos
- Estrutura separada em entidades, repositórios, serviços e controladores (padrão em camadas)

## Requisitos

- JDK 17+
- Maven 3.9+ (ou usar o Maven embutido do Eclipse/VSCode)

## Como rodar

### Via terminal (Maven)
```bash
cd RestAPIFurb
mvn spring-boot:run
```

### Via VSCode
1. Abra a pasta do projeto (com a extensão *Extension Pack for Java* e *Spring Boot Extension Pack* instaladas).
2. Abra `ResApifurbApplication.java` e clique em **Run**, ou use o terminal integrado com `mvn spring-boot:run`.

### Via Eclipse
1. `File > Import > Maven > Existing Maven Projects` e selecione a pasta `RestAPIFurb`.
2. Aguarde o Eclipse baixar as dependências.
3. Clique com o botão direito em `ResApifurbApplication.java` > `Run As > Java Application` (ou `Spring Boot App`, se tiver o STS).

Após subir, a aplicação roda em `http://localhost:8080/RestAPIFurb`.

## Swagger

```
http://localhost:8080/RestAPIFurb/swagger-ui.html
```

## Endpoints principais

URL BASE: `http://localhost:8080/RestAPIFurb/`

- `GET /equipamentos`
- `GET /equipamentos/{id}`
- `POST /equipamentos`
- `PUT /equipamentos/{id}`
- `DELETE /equipamentos/{id}`
- `POST /auth/login`

Exemplo de corpo para `POST /equipamentos`:
```json
{
  "nome": "Imp HP",
  "tipo": { "id": 3, "nome": "Impressora" }
}
```

## Autenticação

`POST /auth/login`
```json
{ "username": "admin", "password": "senha123" }
```

Resposta:
```json
{ "token": "<jwt>", "type": "Bearer" }
```

Use o token nas requisições protegidas (POST/PUT/DELETE em `/equipamentos`):
```
Authorization: Bearer <token>
```

## Usuário padrão (criado automaticamente ao subir a aplicação)

- login: `admin`
- senha: `senha123`

## Console H2 (para conferir os dados no banco)

```
http://localhost:8080/RestAPIFurb/h2-console
JDBC URL: jdbc:h2:mem:resapifurb
Usuário: sa
Senha: (em branco)
```

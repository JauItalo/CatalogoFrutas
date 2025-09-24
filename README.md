Resumo da Aplicação

O Catálogo de Akuma no Mi é uma API RESTful desenvolvida em Java 17 com Spring Boot 3.1.4, que permite gerenciar um catálogo de frutas do universo One Piece.
A aplicação implementa autenticação e autorização com JWT (JSON Web Token), garantindo segurança no acesso aos endpoints. Usuários podem se registrar e realizar login para obter um token, que deve ser enviado em cada requisição.

Principais funcionalidades:

Autenticação: Registro e login de usuários, com atribuição de roles (USER e ADMIN).

Gerenciamento de Frutas: CRUD de frutas (criar, listar, atualizar, remover), com suporte a filtro por nome e tipo.

Controle de Usuários da Fruta: Histórico de usuário atual e anterior associado à fruta.

Documentação da API: Swagger UI disponível para explorar e testar os endpoints.

Banco de Dados: Persistência em PostgreSQL, com entidades para frutas, usuarios e roles.

=================================================================

No arquivo application.properties, configure:

spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

=================================================================

Configure a chave JWT:

export JWT_SECRET="minhaChaveSuperSeguraComPeloMenos32CaracteresAqui"

==================================================================

Executar o Projeto

mvn spring-boot:run

====================================================================

Documentação da API

Acesse Swagger UI:

http://localhost:8080/swagger-ui.html
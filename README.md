Projeto demo da incubadora. Spring Security com JWT.

Usando:
* Spring Security
* JWT
* JDK 11
* Springboot 2.2.1
* IntelliJ
* Rest
* Lombok
* Devtools
* Gradle
* H2 Database em Memória

Este projeto é uma poc com Spring Security e Jwt para Web como microserviço. Possui um crud da entidade Usuário
com uma lista de regras de acesso por login.

Ao subir a primeira vez o projeto, um usuário Adminstrador é criado para o primeiro acesso:
```
  Usuario: admin
  Senha: admin
```
JSON para requisistar acesso:
```
{
  "username":"admin",
  "password":"admin"
}
```
Ao validar o acesso é gerando um token de validade de dois dias que deve ser passado no Header de novas requisições:
```
<Autorization> <Bearer (token gerado pelo JWT)>
```

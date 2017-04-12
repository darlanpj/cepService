# cepService

Tecnologia e estratégia

* Spring boot para tornar a aplicação bootável que pode ser instalada e de fácil startup
* Mongodb que facilita a utilização de um banco de dados podendo ser instanciado e testado com um mongoDB embarcado (de.flapdoodle.embed)

------------------------------------------------------------------------------

Q1
*A documentar

------------------------------------------------------------------------------

Q2 
-----ENDPOINTS------

CEP:

[GET] http://127.0.0.1:8080/api/cep/{cep} - Consultar um Endereço a partir de um CEP existente 
(deve conter exatamente 8 dígitos numéricos); CEPs já existentes: 83838383, 98543012, 78543934, 64598884 e 50492398;

ENDEREÇO:

[POST] http://127.0.0.1:8080/api/address - Cadastrar um novo Endereço (zipcode, number, street, neighborhood, city e state são campos obrigatórios)

Request:

Response:

[GET] http://127.0.0.1:8080/api/address/{id} - Consultar um Endereço a partir de um Id único e inteiro

Request:

Response:

[PUT] http://127.0.0.1:8080/api/address - Atualiza um endereço existente

Request:

Response:

[DELETE] http://127.0.0.1:8080/api/address/{id} - Exclui um Endereço a partir de um Id único e inteiro

Request:

Response:

HOW TO USE

Para usar o projeto é necessário ter o Java 8 e Maven instalados, com isso, basta ir até a raiz do projeto onde o pom.xml está localizado e executar o comando mvn clean package spring-boot:run


SWAGGER

Para utilizar a documentação da api com o Swagger basta acessar:
*AINDA NÂO IMPLEMENTADO
------------------------------------------------------------------------------

Questão 3 *AINDA NÂO IMPLEMENTADO

Tecnologias:

* maven para build da aplicação e gerenciamento de dependências
* Java 8 e Junit

------------------------------------------------------------------------------
Q4

Ao relizar uma requisição ao site considerado faz uma requisição à uma URL traduzida pelo servidor DNS que correponderá ao IP do servidor hospodeiro da empresa. Servidor este que verificando os headers de resposta do mesmo é servidor NGINX.

Status: HTTP/1.1 200 OK
Server: nginx
Content-Type: text/html; charset=utf-8
Pragma: no-cache
X-ATG-Version: version=QVRHUGxhdGZvcm0vMTEuMg==
X-Powered-By: Servlet/3.0 JSP/2.2
X-XSS-Protection: 1; mode=block
X-Frame-Options: SAMEORIGIN
Cache-Control: no-cache
Expires: Tue, 11 Apr 2017 03:32:08 GMT
Date: Tue, 11 Apr 2017 03:32:08 GMT
Transfer-Encoding: chunked
Connection: close
Connection: Transfer-Encoding

Ao requisitar informações no servidor com o endereço especificado, conteúdos estáticos nos quais possam ser cacheados, e 
dinâmicos que ser escalados e acessível por meio de load balancing e apis distintas (por domínio, microserviços, soa etc) 
as quais também podem ser cacheadas, são retornados para o cliente e então renderizadas no browser do usuário.

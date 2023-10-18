# Wishlist API

A API Wishlist é um serviço que permite que os clientes gerenciem seus produtos em uma lista de desejos.


## Feature

Uma das funcionalidades mais interessantes em um e-commerce é a Wishlist, ou a lista de desejos. No e-commerce o cliente pode 
realizar a busca de produtos, ou pode acessar a tela de detalhes do produto. Em ambas as telas ele pode selecionar os produtos 
de sua preferência e armazená-los na sua Wishlist. A qualquer momento o cliente pode visualizar sua Wishlist completa, com todos 
os produtos que ele selecionou em uma única tela.


## O que deve ser feito? 

O objetivo é que você desenvolva um serviço HTTP resolvendo a funcionalidade de Wishlist do cliente. Esse serviço deve atender 
os seguintes requisitos:
- Adicionar um produto na Wishlist do cliente;
- Remover um produto da Wishlist do cliente;
- Consultar todos os produtos da Wishlist do cliente;
- Consultar se um determinado produto está na Wishlist do cliente;


## Executando a Aplicação

Para executar a aplicação, você pode utilizar o Maven. Abra o terminal e execute o seguinte comando 'mvn spring-boot:run'.
É necessário que tenha o Java 11 e o Maven instalado em sua máquina. Verifique utilizando o comando 'java -version' e 
o comando 'mvn -version'. Para utilizar o swagger, basta chamar a URL 'http://localhost:8080/swagger-ui.html' em seu browser.


## Configuração do Banco de Dados

A aplicação Wishlist utiliza um banco de dados na nuvem que é hospedado no serviço MongoDB Atlas. Ao iniciar a aplicação, 
ela se conectará automaticamente ao cluster do MongoDB Atlas da Wishlist. As informações de conexão com o banco de dados 
estão no arquivo 'application.properties', que está localizado na pasta resources.


## Endpoints da Wishlist

### Consultar todos os produtos da Wishlist

Endpoint: `GET /wishlist/{token}`
Retorna a lista de produtos na Wishlist de um cliente

### Consultar se um determinado produto está na Wishlist

Endpoint: `GET /wishlist/contains/{token}/{productId}`
Verifica se um produto específico está na Wishlist de um cliente.

### Detalhes do produto na Wishlist

Endpoint: `GET /wishlist/product/{token}/{productId}`
Retorna os detalhes de um produto específico na Wishlist de um cliente.

### Adicionar um produto à Wishlist

Endpoint: `POST /wishlist/add`
Adiciona um produto a Wishlist do cliente. O RequestBody deve conter os detalhes do produto e o RequestParam deve conter o token 
do usuário.

### Remover um produto da Wishlist

Endpoint: `DELETE /wishlist/remove/{token}/{productId}`
Remove um produto da Wishlist de um cliente.

### Limpar Wishlist completa

Endpoint: `DELETE /wishlist/clear/{token}`
Remove todos os produtos da Wishlist de um cliente.


Observação: Para buscar o id do produto, busque nos endpoints onde retornam os produtos disponíveis, explicados abaixo. Para
buscar token do usuário, primeiro é necessário criar um cadastro e realizar o login. Assim que o login for realizado, o token
ficará disponível no response. 


## Endpoints de Produtos

### Consultar todos os produtos

Endpoint: `GET /product/`
Retorna a lista de todos os produtos registrados.

### Consultar produto por ID

Endpoint: `GET /product/id/{id}`
Retorna um produto específico com base no ID.

### Consultar produto por Nome

Endpoint: `GET /product/name/{name}`
Retorna um produto específico com base no nome.


## Endpoints de Usuários

### Cadastro de usuário

Endpoint: `POST /user/signup`
Realiza o cadastro de um novo usuário. O corpo da solicitação deve conter os detalhes do usuário.

### Login de usuário

Endpoint: `POST /user/signin`
Permite que um usuário faça login. O corpo da solicitação deve conter as email e senha do usuário. O corpo de resposta
retorna o token necessário para a Wishlist.


Observação: Esta API Wishlist foi projetada para demonstrar as funcionalidades de uma lista de desejos. Embora a 
arquitetura seja baseada em micro-serviços, como está nas orientações do projeto, optei por criar uma forma em que 
os usuários possam se cadastrar e fazer login para utilizar o serviço de Wishlist.


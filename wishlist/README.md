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

Para executar a aplicação, você pode utilizar o Maven. Abra o terminal e execute o seguinte comando: mvn spring-boot:run

## Configuração do Banco de Dados

Para a aplicação Wishlist, foi utilizado um banco de dados na nuvem. O banco de dados é hospedado no serviço MongoDB Atlas. As
informações do banco de dados estão na pasta "application.properties".


## Endpoints da Wishlist

### Adicionar Produto à Wishlist

Endpoint: `POST /wishlist/add/{clientId}`
Adiciona um produto à Wishlist de um cliente.

### Listar Produtos na Wishlist

Endpoint: `GET /wishlist/{clientId}`
Retorna a lista de produtos na Wishlist de um cliente.

### Remover Produto da Wishlist

Endpoint: `DELETE /wishlist/remove/{clientId}/{productId}`
Remove um produto da Wishlist de um cliente.

### Verificar se um Produto está na Wishlist

Endpoint: `GET /wishlist/contains/{clientId}/{productId}`
Verifica se um produto específico está na Wishlist de um cliente.

### Detalhes do Produto na Wishlist

Endpoint: `GET /wishlist/product/{clientId}/{productId}`
Retorna os detalhes de um produto específico na Wishlist de um cliente.

### Limpar Wishlist

Endpoint: `DELETE /wishlist/clear/{clientId}`
Remove todos os produtos da Wishlist de um cliente.

### Adicionar Produto à Wishlist

Endpoint: `POST /wishlist/add/{clientId}`
Adiciona um produto à Wishlist de um cliente.

### Remover Produto da Wishlist

Endpoint: `DELETE /wishlist/remove/{clientId}/{productId}`
Remove um produto da Wishlist de um cliente.


## Endpoints de Clientes

### Listar Todos os Clientes

Endpoint: `GET /client/`
Retorna a lista de todos os clientes registrados.

### Buscar Cliente por ID

Endpoint: `GET /client/id/{id}`
Retorna um cliente específico com base no ID.

### Buscar Cliente por Nome

Endpoint: `GET /client/name/{name}`
Retorna um cliente específico com base no nome.


## Endpoints de Produtos

### Listar Todos os Produtos

Endpoint: `GET /product/`
Retorna a lista de todos os produtos registrados.

### Buscar Produto por ID

Endpoint: `GET /product/id/{id}`
Retorna um produto específico com base no ID.

### Buscar Produto por Nome

Endpoint: `GET /product/name/{name}`
Retorna um produto específico com base no nome.


Nota: A API da Wishlist foi projetada para demonstrar as funcionalidades de uma lista de desejos. Embora a arquitetura seja 
baseada em micro-serviços, os dados de clientes e produtos foram mantidos fixos no banco de dados da aplicação. Isso foi 
feito com o intuito de concentrar apenas no serviço da Wishlist, como foi dito nas Orientações do projeto.


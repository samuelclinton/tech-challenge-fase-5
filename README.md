O projeto de E-commerce proposto para a pós-graduação [FIAP](https://www.fiap.com.br/) em [Arquitetura e desenvolvimento Java](https://postech.fiap.com.br/curso/arquitetura-desenvolvimento-java) consiste em uma API de gerenciamento de e-commerce de uma empresa fictícia. A aplicação como um todo é composta por quatro microsserviços, um de gerenciamento e autenticação de usuários, um de gerenciamento de estoque e preços, um de gerenciamento de carrinhos de compras dos usuários e um de pagamentos (simulados) que gerencia o checkout dos carrinhos.
Eles se comunicam utilizando o Spring Cloud OpenFeign. Tudo isso roda atrás de um Spring Cloud Gateway para roteamento e é protegido pelo Spring Security que usei para autenticação e autorização.
O projeto roda totalmente dockerizado em containers e pode ser executado utilizando o docker compose para testes e avaliações.

## Índice

1. [Instalação](#instalação)
2. [Documentação](#documentação)
3. [Jornada de desenvolvimento](#jornada)
4. [Autores](#autores)
5. [Licença](#licença)

## Instalação
Estes são os passos para a execução do projeto num ambiente local, utilizando o docker compose.
1. Clone este repositório git na sua máquina local
   ``` 
   git clone https://github.com/samuelclinton/tech-challenge-fase-5.git
   ```
   
2. Caso o Docker não esteja instalado no seu computador, siga os passos da [documentação oficial](https://docs.docker.com/engine/install/).
   
4. Não é necessário fazer a build de cada imagem dos serviços, eles já estão disponíveis no Docker Hub, porém caso queira, cada diretório tem seu Dockerfile que pode ser usado para criar uma imagem com o seguinte comando:
   ``` 
   docker build -t [nome-da-imagem] [caminho-ate-Dockerfile]
   ```
   
   É recomendado seguir o padrão de nome ecommerce-auth, ecommerce-storage, ecommerce-shopping-cart, ecommerce-payment e ecommerce-gateway, pois caso altere, o arquivo `compose.yml` precisará ser alterado também.
   
6. Abra um Powershell (ou equivalente no seu OS) no diretório que o arquivo `compose.yml` estiver localizado e execute o comando:
   ``` 
   docker compose up -d
   ```
   
7. Aguarde alguns segundos para que todos os containers sejam executados e após aguardar pode se verificar se os containers estão rodando com o comando:
   ``` 
   docker container ls -a
   ```
   
8. Com todos os serviços rodando o endereço das APIs relevantes serão `localhost:8888` para a acessar via Gateway onde cada api possui seu prefixo (/auth, /storage, /shopping-cart, /payment), porém as requisições do arquivo postman desse repositório já deve estar configuradas para que todos os testes possam ser feitos.

## Documentação
A documentação no padrão Open Api 3 (Swagger) estará disponível para consulta assim que os containers estiverem sendo executados corretamente no endereço `/docs.html` de cada um dos serviços, então `http://localhost:8888/auth/docs.html` vai expor a documentação do microsserviço de autenticação, e o mesmo acontece se mudar o prefixo do serviço para o desejado com todos os outros serviços.

## Jornada

A jornada de desenvolvimento desse projeto foi muito parecida com o projeto Fiapark (de estacionamentos). Então desenvolvi cada microsserviço de maneira independente e mais pra frente fiz a integração entre eles.
A maior mudança foi utilizar os projetos do Spring Cloud, já que eu apenas havia utilizado RabbitMQ para mensageria, e não conhecia o OpenFeign e nem o Spring Cloud Gateway.

Uma solução interessante foi na parte de segurança da API, onde tive que pesquisar bastante para fazer um meio de autorizar e autenticar os usuário com microsserviços. Eu apenas havia feito isso com aplicações monolíticas e foi um belo desafio.

No final optei por centralizar a autenticação em um serviço e nos demais, apenas usar filtros do Cloud Gateway para repassar os dados do usuário autenticado (de maneira transparente para o consumidor da API, que não sabe desse repasse e nem sabe quantos serviços existem por trás do Gateway) para que cada serviço faça a autorização ou não da requisição baseado nas autoridades do usuário autenticado.

Com os serviços desenvolvidos, eu os Dockerizei individualmente e criei o arquivo compose.yml que iria subir tudo que fosse necessário pra aplicação rodar, para meus proprios testes e futuramente para a correção do Tech Challenge. Porém surgiram erros de inicialização devido a ordem de execução e dependências que eu resolvi com uma ferramenta ótima chamada [docker-compose-wait](https://github.com/ufoscout/docker-compose-wait), que funcionou maravilhosamente bem pra definir a dependência entre os containers e até a múltiplos containers.

Finalmente, com tudo funcionando, disponibilizei as imagens dos serviços em repositórios no [Docker Hub](https://hub.docker.com/u/samuelclinton) pra facilitar a subida do projeto com apenas um comando `compose up`, sem necessidade de buildar cada serviço manualmente. Deixei assim mesmo os `Dockerfile` para referência e pra caso seja necessário gerar uma nova imagem de algum dos serviços novamente.

## Autores

- [Samuel Clinton](https://www.linkedin.com/in/samuelclinton)

## Licença

[Licença MIT](https://opensource.org/license/mit/): permite o uso, a modificação e a distribuição do software sem restrições significativas.

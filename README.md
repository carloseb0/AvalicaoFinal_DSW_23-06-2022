Disciplina: Desenvolvimento de Serviços Web 2022 - 1º PL

<b>Tecnologias Utilizadas:</b><br>
JSF (JavaServer Faces): Para a camada de apresentação web.
JPA (Java Persistence API): Para mapeamento objeto-relacional e operações de banco de dados.
PostgreSQL: Como banco de dados relacional para armazenamento dos dados.
JWT (JSON Web Token): Para autenticação e autorização dos usuários.
Postman: Para testar e validar todos os endpoints da API.

<b>Escopo:</b><br>
Implementar uma API REST contendo uma entidade responsável por gerenciar os atributos de login, incluindo outras duas entidades relacionadas entre si, como exemplo: Carro e TipoCarro, Produto e Marca, etc... Cada entidade deverá conter no mínimo 4 atributos, mesclando os tipos long ou inteiro, float, string e data.

<b>Premissas:</b><br>
O projeto é individual e único;
Os testes deverão ser realizados utilizando uma collection do Postman contendo todos os endpoints testados com sucesso;
Deve ser implementado utilizando (JSF + JPA com DB PostgreSQL), conforme os padrões adotados em sala de aula;

<b>A API deve:</b><br>
Disponibilizar um microserviço para validar as credenciais do usuário (usuário e senha), após a validação com sucesso, a api deverá retornar um token JWT a ser utilizado em todas as demais requisições;
Cada entidade deverá possuir no mínimo os microserviços para: incluir, excluir, editar, listar todos e buscar por parte do nome implementados em REST;

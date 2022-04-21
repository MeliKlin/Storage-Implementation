# QA Bugs

### Exercicio
Um testador manual possui uma série de casos de teste que você deseja acompanhar para
não perder o controle do que já foi testado e do que ainda precisa ser testado. Para isso,
decidiu implementar uma API Rest que lhe permite acompanhar os processos que está
realizando em um banco de dados.

Para isso, você usará a classe TestCase, que deve ter os seguintes atributos:
- long id_case
- String description
- boolean tested
- boolean passed
- int number_of_tries
- date last_update

Por outro lado, a API deve ser capaz de:
1. Criar, recuperar, atualizar e excluir casos de teste.
2. Suporte a métodos de pesquisa personalizados com base em determinados filtros.

Tenha como referência os seguintes pontos finais:

MÉTODO URI AÇÃO
- POST /api/testcases/new Crie um novo caso de teste.
- GET /api/testcases Retorne todos os casos de teste.
- GET /api/testcases/id Retornar um caso de teste por id.
- PUT /api/testcases/id Atualizar um caso de teste por id.
- DELETE /api/testcases/id Excluir um tutorial por id.
- GET /api/testcases?last_update=’dd/mm/yyyy’ Encontre todos os casos de teste que foram atualizados após uma determinada data.
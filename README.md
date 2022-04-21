# QA Bugs

### Exercicio
Um testador manual possui uma série de casos de teste que você deseja acompanhar para
não perder o controle do que já foi testado e do que ainda precisa ser testado. Para isso,
decidiu implementar uma API Rest que lhe permite acompanhar os processos que está
realizando em um banco de dados.

Para isso, você usará a classe TestCase, que deve ter os seguintes atributos:
- String id_case
- String description
- boolean tested
- boolean passed
- int number_of_tries
- date last_update

Por outro lado, a API deve ser capaz de:
1. Criar, recuperar, atualizar e excluir casos de teste.
2. Suporte a métodos de pesquisa personalizados com base em determinados filtros.

Tenha como referência os seguintes pontos finais:

### MÉTODO URI AÇÃO
Crie um novo caso de teste.
- POST /api/testcases/new

Retorne todos os casos de teste.
- GET /api/testcases

Retornar um caso de teste por id.
- GET /api/testcases/{id}

Atualizar um caso de teste por id.
- PUT /api/testcases/{id} 

Excluir um tutorial por id.
- DELETE /api/testcases/{id}

Encontre todos os casos de teste que foram atualizados após uma determinada data.
- GET /api/testcases?last_update=dd/mm/yyyy 
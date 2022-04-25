# Tutoriais

### Exercicio
Um aluno da academia depois de concluir com sucesso todos os cursos que iniciou, decide
criar alguns tutoriais para colocar em prática os conhecimentos aprendidos e revisá-los
caso se esqueça de algumas coisas.
É muito importante para ele que os dados sejam armazenados corretamente e estejam
disponíveis para quando quiser consultá-los novamente. Para fazer isso, você decide
implementar um serviço REST que permite alcançá-lo.

Uma entidade chamada “Tutorial” deve ser criada com os seguintes atributos:
- ID do tutorial.
- Título do tutorial.
- Descrição do tutorial.
- Situação da publicação.

A API deve ser capaz de:
1. Criar, recuperar, atualizar e excluir tutoriais.
2. Dê suporte a métodos de pesquisa personalizados, como pesquisar por status de
   publicação ou por título.

### MÉTODO URI AÇÃO
Crie um novo Tutorial.
- POST /api/tutorials

Retornar todos os tutoriais.
- GET /api/tutorials 

Retornar um tutorial por id.
- GET /api/tutorials/id

Atualizar um tutorial por id.
- PUT /api/tutorials/id

Excluir todos os tutoriais.
- DELETE /api/tutorials

Excluir um tutorial por id.
- DELETE /api/tutorials/id 

GET /api/tutorials/published Buscar todos os tutoriais publicados.
GET /api/tutorials?title=Spring Buscar todos os tutoriais que tenham a palavra “Spring” no título.
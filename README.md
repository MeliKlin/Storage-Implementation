# As Pérolas

### Exercício
Uma joalheria conhecida quer implementar uma API que permita adicionar, deletar,
modificar e ler/consultar cada uma das joias que ela tem em exposição (CRUD). Para isso,
são registrados os seguintes dados para cada joia: número de identificação, material (ouro,
prata, etc.), peso (em kg), quilates.

Para fazer isso, lembre-se de que a API deve ser capaz de:

1. Crie uma nova joia e retorne o código de status correspondente com uma
   mensagem informando seu "número de identificação". (URI: joia/inserir).
2. Devolva a lista de todas as joias registradas. (URI: /joias).
3. Remove uma joia. (URI: joia/excluir?número identificacao={})
4. Atualize os dados de uma joia. Uma vez atualizado, retorne uma mensagem com o
   código de status correspondente e os dados da jóia modificada. (URI:
   joia/atualizar?número identificacao={}).


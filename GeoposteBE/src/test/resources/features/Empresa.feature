#language: pt
Funcionalidade: Empresa

  Cenário de Fundo: Autenticação
    Dado que o usuário está autenticado na API

  Cenário: Cadastrar uma Empresa no Sistema
    Dado que o usuário deseja cadastrar uma empresa com os seguintes dados
      |ordenacao    |122              |
      |nome_fantasia|GEOPOSTE_FANTASIA|
      |endereco     |Rua do Freela, 41|
    Quando a operação de "cadastro" é realizada
    Então os dados da empresa são "criados" com sucesso

  Cenário: Obter Informações de uma Empresa no Sistema
    Dado que o usuário deseja pesquisar a empresa de nome fantasia "GEOPOSTE_FANTASIA"
    Quando a operação de "pesquisa" é realizada
    Então os dados da empresa são "retornados" com sucesso

  Cenário: Atualizar uma Informação de uma Empresa no Sistema
    Dado que o usuário deseja atualizar as seguintes informações da empresa "GEOPOSTE_FANTASIA"
      |ordenacao    |130                      |
      |nome_fantasia|GEOPOSTE_FANTASIA_UPDATED|
      |endereco     |Rua do Freelacer, 41     |
    Quando a operação de "atualização" é realizada
    Então os dados da empresa são "atualizados" com sucesso

  Cenário: Excluir o registro de uma Empresa no Sistema
    Dado que o usuário deseja excluir o registro da empresa "GEOPOSTE_FANTASIA"
    Quando a operação de "exclusão" é realizada
    Então os dados da empresa são "apagados" com sucesso
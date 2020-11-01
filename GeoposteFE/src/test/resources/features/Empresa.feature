#language: pt
Funcionalidade: Empresa

  Cenário de Fundo:
    E clica no menu Empresas

  Cenário: Cadastrar Empresa
    E clica no botão Novo
    Quando todas as informações obrigatórias são inseridas
      |ordenacao    |122              |
      |nome_fantasia|GEOPOSTE_FANTASIA|
    E o botão Salvar é pressionado
    Então um Alert de sucesso é exibido

  @hooks
  Cenário: Consultar Empresa
    Quando que o usuário insere nome fantasia "GEOPOSTE_FANTASIA" no campo de pesquisa
    E clica em pesquisar
    Então os dados da empresa "GEOPOSTE_FANTASIA" são exibidos


package br.com.geopostelight.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

public class EmpresaStep {

    @Steps
    Helper helper;

    @Dado("que o usuário está autenticado na API")
    public void que_o_usuario_esta_autenticado_na_api(){
        helper.autenticacao();
    }

    @Dado("que o usuário deseja pesquisar a empresa de nome fantasia {string}")
    public void configurando_header(String nomeFantasia){
        helper.configurarInformacoesPesquisa(nomeFantasia);
    }

    @Dado("que o usuário deseja cadastrar uma empresa com os seguintes dados")
    public void que_o_usuário_deseja_cadastrar_uma_empresa_com_os_seguintes_dados(DataTable dataTable) throws IOException {
        helper.configurarInformacoesDaEmpresa(dataTable);
    }

    @Quando("a operação de {string} é realizada")
    public void operacao_realizada(String operacao) {
        helper.gerenciarAPI(operacao);
    }

    @Então("os dados da empresa são {string} com sucesso")
    public void a_mensagem_é_retornada(String acao) {
        helper.validacao(acao);
    }

    @Dado("que o usuário deseja atualizar as seguintes informações da empresa {string}")
    public void que_o_usuário_deseja_atualizar_as_seguintes_informações_da_empresa(String nomeFantasia, DataTable dataTable) throws IOException {
        helper.atualizarEmpresa(nomeFantasia, dataTable);
    }

    @Dado("que o usuário deseja excluir o registro da empresa {string}")
    public void que_o_usuário_deseja_excluir_o_registro(String nomeFantasia) {
        helper.getEmpresaId(nomeFantasia);
    }
}

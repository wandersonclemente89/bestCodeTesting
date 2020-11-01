package br.com.geopostelight.empresa.steps;

import br.com.geopostelight.empresa.pages.EmpresaPage;
import br.com.geopostelight.empresa.pages.HomePage;
import br.com.geopostelight.empresa.pages.LoginPage;
import br.com.geopostelight.empresa.pages.NovaEmpresaPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.util.List;

public class EmpresaStep {

    LoginPage loginPage;
    HomePage homePage;
    EmpresaPage empresaPage;
    NovaEmpresaPage novaEmpresaPage;

    @Dado("que o usuário faz login como {string} e {string}")
    public void doLogin(String username, String password){
        loginPage.doLogin(username,password);
    }

    @Dado("que o usuário navega ate a página {string}")
    public void navegaPara(String url){
        loginPage.getDriver().get(url);
    }

    @Dado("clica no menu Empresas")
    public void navegaParaEmpresa(){
        homePage.goToEmpresaPage();
    }

    @Dado("clica no botão Novo")
    public void navegaParaNovaEmpresaPage(){
        empresaPage.adicionarNovaEmpresa();
    }

    @Quando("todas as informações obrigatórias são inseridas")
    public void insereInfos(DataTable dataTable){
        List<List<String>> linhas = dataTable.asLists(String.class);
        novaEmpresaPage.insertOrdenacao(linhas.get(0).get(1));
        novaEmpresaPage.insertNomeFantasia(linhas.get(1).get(1));
    }

    @Quando("o botão Salvar é pressionado")
    public void o_botao_Salvar_e_pressionado(){
        novaEmpresaPage.salvar();
    }

    @Então("um Alert de sucesso é exibido")
    public void message(){
        Assert.assertTrue(empresaPage.isSuccess());
    }

    @Quando("que o usuário insere nome fantasia {string} no campo de pesquisa")
    public void inserirPesquisa(String nomeFantasia){
        empresaPage.inserirTextoPesquisaEmpresa(nomeFantasia);
    }

    @Quando("clica em pesquisar")
    public void clicarEmPesquisar(){
        empresaPage.clicaEmPesquisarEmpresa();
    }

    @Então("os dados da empresa {string} são exibidos")
    public void osDadosSaoExibidos(String nomeFantasia){
        empresaPage.isOnList(nomeFantasia);
    }
}

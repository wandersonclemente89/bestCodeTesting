package br.com.geopostelight.companies.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://adhomol01.geopostelight.com.br/empresas/create")
public class NovaEmpresaPage extends PageObject {

    @FindBy(css = "#empresa_ordenacao")
    private WebElementFacade ordenacaoInputField;

    @FindBy(css = "#empresa_nome_fantasia")
    private WebElementFacade nFantasiaInputField;

    @FindBy(css = "#empresa_razao_social")
    private WebElementFacade rSocialInputField;

    @FindBy(css = "#empresa_endereco")
    private WebElementFacade enderecoInputField;

    @FindBy(css = "button.bg-teal")
    private WebElementFacade adicionarButton;



    public NovaEmpresaPage insertOrdenacao(String ordenacao){
        typeInto(ordenacaoInputField,ordenacao);
        return this;
    }

    public NovaEmpresaPage insertNomeFantasia(String nomeFantasia){
        typeInto(nFantasiaInputField,nomeFantasia);
        return this;
    }

    public NovaEmpresaPage insertRazaoSocial(String razaoSocial){
        typeInto(rSocialInputField,razaoSocial);
        return this;
    }

    public NovaEmpresaPage insertEndereco(String endereco){
        typeInto(enderecoInputField,endereco);
        return this;
    }

    

}

package br.com.geopostelight.empresa.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("https://adhomol01.geopostelight.com.br/empresas/create")
public class NovaEmpresaPage extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(NovaEmpresaPage.class);

    @FindBy(css = "#empresa_ordenacao")
    private WebElementFacade ordenacaoInputField;

    @FindBy(css = "#empresa_nome_fantasia")
    private WebElementFacade nFantasiaInputField;

    @FindBy(css = "#empresa_razao_social")
    private WebElementFacade rSocialInputField;

    @FindBy(css = "#empresa_endereco")
    private WebElementFacade enderecoInputField;

    @FindBy(css = "button.btn-success")
    private WebElementFacade salvarButton;



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

    public EmpresaPage salvar(){
        logger.info("Salvando empresa...");
        clickOn(salvarButton);
        return new EmpresaPage();
    }
    

}

package br.com.geopostelight.empresa.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@DefaultUrl("https://adhomol01.geopostelight.com.br/empresas")
public class EmpresaPage extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaPage.class);

    @FindBy(css = ".card-tools")
    private WebElementFacade newCompanyButton;

    @FindBy(css = ".card-body .bg-navy")
    private WebElementFacade searchCompanyButton;

    @FindBy(css = "#filters_nome_fantasia")
    private WebElementFacade filterTradingName;

    @FindBy(css = ".alert-success")
    private WebElementFacade successAlert;

    @FindBy(css = "tbody tr td:nth-child(2)")
    private List<WebElementFacade> listaNomeFantasia;

    @FindBy(css = "button.btn-danger[title='Remover'] ")
    private List<WebElementFacade> listaNomeFantasiaParaDeletar;

    @FindBy(css = "#modal-delete button.btn-primary")
    private WebElementFacade confirmaDelete;

    public EmpresaPage deletaLista(String nomeFantasia){
        for (int i=0; i < listaNomeFantasiaParaDeletar.size(); i++){
            listaNomeFantasiaParaDeletar.get(i).click();
            confirmaDelete.click();
            isSuccess();
        }
        return this;
    }

    public boolean isOnList(String nomeFantasia){
        for (int i=0; i < listaNomeFantasia.size(); i++){
            if( listaNomeFantasia.get(i).getText().trim().equals(nomeFantasia)){
                return true;
            }
        }
        return false;
    }

    public boolean isSuccess(){
        try {
            waitFor(successAlert);
        }catch (Throwable e){
            return false;
        }
        return true;
    }

    public NovaEmpresaPage adicionarNovaEmpresa(){
        logger.info("Clicando no botão Novo...");
        clickOn(newCompanyButton);
        return new NovaEmpresaPage();
    }

    public EmpresaPage clicaEmPesquisarEmpresa(){
        logger.info("Clicando no botão Pesquisar...");
        clickOn(searchCompanyButton);
        return this;
    }

    public EmpresaPage inserirTextoPesquisaEmpresa(String nomeFantasia){
        logger.info("Clicando no botão Pesquisar...");
        typeInto(filterTradingName,nomeFantasia);
        return this;
    }

}

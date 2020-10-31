package br.com.geopostelight.companies.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://adhomol01.geopostelight.com.br/empresas")
public class EmpresaPage extends PageObject {

    @FindBy(css = ".card-tools")
    WebElementFacade newCompanyButton;

    @FindBy(css = ".card-body .bg-navy")
    WebElementFacade searchCompanyButton;

    @FindBy(css = "#filters_nome_fantasia")
    WebElementFacade filterTradingName;



}

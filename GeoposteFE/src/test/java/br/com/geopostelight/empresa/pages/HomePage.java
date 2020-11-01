package br.com.geopostelight.empresa.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "[href='/empresas']")
    private WebElementFacade linkEmpresas;

    @FindBy(css = "[title='Administrador Geral']")
    private WebElementFacade adminGeralButton;

    @FindBy(css = ".dropdown-menu-right .fa-power-off")
    private WebElementFacade desconectarButton;

    public EmpresaPage goToEmpresaPage(){
        logger.info("Clicando no menu Empresas...");
        clickOn(linkEmpresas);
        return new EmpresaPage();
    }

    public HomePage clickOnAdminIcon(){
        logger.info("Clicando no ícone de administrador...");
        clickOn(adminGeralButton);
        return this;
    }

    public LoginPage desconectar(){
        logger.info("Desconectando...");
        clickOn(desconectarButton);
        return new LoginPage();
    }

}

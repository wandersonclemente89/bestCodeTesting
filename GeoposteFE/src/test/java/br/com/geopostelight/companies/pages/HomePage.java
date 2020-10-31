package br.com.geopostelight.companies.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(css = "[href='/empresas']")
    private WebElementFacade linkEmpresas;

    @FindBy(css = "[title='Administrador Geral']")
    private WebElementFacade adminGeralButton;

    @FindBy(css = ".dropdown-menu-right .fa-power-off")
    private WebElementFacade desconectarButton;

}

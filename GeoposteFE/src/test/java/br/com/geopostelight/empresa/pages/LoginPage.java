package br.com.geopostelight.empresa.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("https://adhomol01.geopostelight.com.br/")
public class LoginPage extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(css = "input[type='text']")
    private WebElementFacade inputUsernameField;

    @FindBy(css = "input[type='password']")
    private WebElementFacade inputPasswordField;

    @FindBy(css = "button[type='button']")
    private WebElementFacade submitButton;

    private LoginPage insertUserName(String username){
        logger.info("Inserting username as: " + username);
        typeInto(inputUsernameField,username);
        return this;
    }

    private LoginPage insertPassword(String password){
        logger.info("Inserting password as: " + password);
        typeInto(inputPasswordField,password);
        return this;
    }

    private HomePage submitLogin(){
        logger.info("Log in to the system!");
        clickOn(submitButton);
        return new HomePage();
    }

    public HomePage doLogin(String username, String password){
        return insertUserName(username).insertPassword(password).submitLogin();
    }

}

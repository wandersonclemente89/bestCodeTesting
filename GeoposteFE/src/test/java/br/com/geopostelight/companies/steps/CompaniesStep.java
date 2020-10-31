package br.com.geopostelight.companies.steps;

import br.com.geopostelight.companies.pages.LoginPage;
import io.cucumber.java.en.Given;

public class CompaniesStep {

    LoginPage loginPage;

    @Given("^I do a login as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void doLogin(String username, String password){
        loginPage.getDriver().get("https://adhomol01.geopostelight.com.br/");
        loginPage.doLogin(username,password);
    }

}

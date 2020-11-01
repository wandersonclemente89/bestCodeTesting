package br.com.geopostelight.empresa.hooks;

import br.com.geopostelight.empresa.pages.EmpresaPage;
import br.com.geopostelight.empresa.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static br.com.geopostelight.empresa.utils.ConstantStrings.*;

public class Hooks {

    LoginPage loginPage;
    EmpresaPage empresaPage;

    @Before
    public void setUp(){
        loginPage.getDriver().manage().window().maximize();
        loginPage.getDriver().get(BASE_URL);
        loginPage.doLogin(USER_NAME,PASSWORD);
    }

    @After("@hooks")
    public void tearDown(){
        empresaPage.deletaLista(EMPRESA_DEFAULT);
    }
}

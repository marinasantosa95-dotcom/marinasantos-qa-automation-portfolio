package com.portfolio.tests;

import com.portfolio.base.BaseTest;
import com.portfolio.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Validar el inicio de sesión con credenciales correctas")
    public void testLoginExitoso() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        String mensajeEsperado = "You logged into a secure area!";
        Assert.assertTrue(loginPage.getAlertMessage().contains(mensajeEsperado), 
                "El mensaje de éxito no se encuentra en la pantalla.");
    }
}

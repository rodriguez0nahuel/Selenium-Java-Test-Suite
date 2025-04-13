package com.seleniumjava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
    private By loginField = By.xpath("//*[@id=\"username\"]");
    private By firstNameField = By.xpath("//*[@id=\"firstName\"]");
    private By lastNameField = By.xpath("//*[@id=\"lastName\"]");
    private By passwordField = By.xpath("//*[@id=\"password\"]");
    private By confirmPasswordField = By.xpath("//*[@id=\"confirmPassword\"]");
    private By registerButton = By.xpath("/html/body/my-app/div/main/my-register/div/div/form/button");
    private By successMessage = By.xpath("/html/body/my-app/div/main/my-register/div/div/form/div[6]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Ingresa los datos en el formulario de registro y valida el mensaje de exito.
    // Devuelve falso si no esta ese mensaje.
    public boolean register(String login, String firstName, String lastName, String password, String confirmPassword) {
        waitForElement(loginField);
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        driver.findElement(registerButton).click();

        try {
            waitForElement(successMessage);
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

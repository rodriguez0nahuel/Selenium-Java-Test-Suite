package com.seleniumjava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By registerLink = By.xpath("/html/body/my-app/header/nav/div/my-login/div/form/a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Ingresa al sitio de registro desde el homepage.
    public void goToRegistrationPage() {
        waitForElement(registerLink);
        driver.findElement(registerLink).click();
    }
}

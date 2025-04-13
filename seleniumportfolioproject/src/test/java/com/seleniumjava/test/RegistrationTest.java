package com.seleniumjava.test;

import com.seleniumjava.pages.HomePage;
import com.seleniumjava.pages.RegistrationPage;
import com.seleniumjava.utils.ExcelUtils;
import com.seleniumjava.utils.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;

    // Instancia driver,home y registration. Abre el navegador e ingresa al sitio
    // web a testear. Establece nombre del reporte.
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://buggy.justtestit.org");
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        Reporter.startReport("Registration-Test-Report");
    }

    // Localiza el excel y toma los datos de prueba.
    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return ExcelUtils.getData(
                "C:\\Users\\Usuario\\Documents\\Selenium Java Portfolio Project\\seleniumportfolioproject\\src\\main\\java\\com\\seleniumjava\\data test\\newUsersData.xlsx",
                "userData");
    }

    // Ingresa al sitio de registro y completa el formulario con los datos del
    // excel.
    @Test(dataProvider = "registrationData")
    public void testRegistration(String login, String firstName, String lastName, String password,
            String confirmPassword) {
        homePage.goToRegistrationPage();
        boolean result = registrationPage.register(login, firstName, lastName, password, confirmPassword);
        Reporter.logTest("Registration Test - " + login, result);
        assertTrue(result, "Registration failed for user: " + login);
    }

    // Cierra el navegador y termina el reporte.
    @AfterClass
    public void tearDown() {
        Reporter.endReport();
        driver.quit();
    }
}
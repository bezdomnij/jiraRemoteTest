package com.codecool.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class AlternateLogin extends BasePage {
    public AlternateLogin() throws MalformedURLException {
        PageFactory.initElements(driver, this);
//        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
    }


//    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id = "login-form-username")
    private WebElement username;

    @FindBy(id = "login-form-password")
    private WebElement password;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    public void loginSuccessfulAlternateLoginPage() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.manage().window().maximize();
        username.sendKeys(System.getProperty("jiraUsername"));
        password.sendKeys(System.getProperty("jiraPassword"));
        loginButton.click();
    }
}

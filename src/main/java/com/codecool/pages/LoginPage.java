package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class LoginPage extends BasePage{


    public LoginPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(id = "login-form-username")
    private WebElement username;

    @FindBy(id = "login-form-password")
    private WebElement password;

    //    @FindBy(id = "login-form-submit")
    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//div/p[contains(text(),'Sorry, your username and password are incorrect - please try again.')]")
    private WebElement loginError;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your userid is required to answer a CAPTCHA')]")
    private WebElement captcha;


    public void loginSuccessful() {
        driver.get(LOGINPAGE);
        driver.manage().window().maximize();
        try {
            waitForVisibility(username);
            username.sendKeys(System.getProperty("jiraUsername"));
            password.sendKeys(System.getProperty("jiraPassword"));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("I'm in already");
        }

    }

    public WebElement loginFailed(String reason) throws InterruptedException {
        driver.get(LOGINPAGE);
        driver.manage().window().maximize();
        if (reason.equals("wrongUsername")) {
            username.sendKeys("wrongUsername");
            password.sendKeys(System.getProperty("jiraPassword"));
        } else {
            Thread.sleep(3000);
            username.sendKeys(System.getProperty("jiraUsername"));
            password.sendKeys("wrongPassword");
        }
        loginButton.click();
        waitForVisibility(loginError);
        return loginError;
    }

    public WebElement loginWrongPassword3Times() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            username.sendKeys(System.getProperty("jiraUsername"));
            password.sendKeys("wrongPassword");
            loginButton.click();
        }
        waitForVisibility(captcha);
        return captcha;
    }
}

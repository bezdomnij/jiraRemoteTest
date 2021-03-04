package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver = WebDriverSingleton.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driver, 5);

    protected final String LOGIN_PAGE = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
    protected final String VIEW_PROFILE_PAGE ="https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa";
    protected final String BROWSE_PAGE = "https://jira.codecool.codecanvas.hu/browse/";

    protected void waitForVisibility(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void maximizeWindow(){
        driver.manage().window().maximize();
    }

    protected void happyLogin(WebElement username, WebElement password, WebElement loginButton) {
        username.sendKeys(System.getProperty("jiraUsername"));
        password.sendKeys(System.getProperty("jiraPassword"));
        loginButton.click();
    }

}

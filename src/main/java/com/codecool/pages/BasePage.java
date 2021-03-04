package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver = WebDriverSingleton.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driver, 5);

    protected final String LOGINPAGE = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
    protected final String VIEWPROFILEPAGE ="https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa";
    protected final String BROWSEPAGE = "https://jira.codecool.codecanvas.hu/browse/";

    protected void waitForVisibility(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

}

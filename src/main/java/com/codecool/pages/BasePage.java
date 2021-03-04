package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver = WebDriverSingleton.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driver, 5);

}

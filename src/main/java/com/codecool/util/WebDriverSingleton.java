package com.codecool.util;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    public static MutableCapabilities setUp() throws MalformedURLException {
        MutableCapabilities capabilities;
        if (System.getenv("STAGE_NAME").equals("run with chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        return capabilities;
    }

    public static WebDriver instance = null;

    private WebDriverSingleton() throws MalformedURLException {
    }

    public static WebDriver getInstance() throws MalformedURLException {
        if (instance == null) {
            String nodeUrl = "https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub";
            WebDriver instance = new RemoteWebDriver(new URL(nodeUrl), setUp());
        }
        return instance;
    }
}

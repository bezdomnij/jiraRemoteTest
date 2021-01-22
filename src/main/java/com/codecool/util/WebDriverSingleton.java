package com.codecool.util;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    public static MutableCapabilities setUp() {
        MutableCapabilities capabilities;
        if (System.getenv("STAGE_NAME").equals("run with chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        return capabilities;
    }

    public static WebDriver instance = null;

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance(){
        if (instance == null) {
            String selPw = System.getenv("SEL_PW");
            String beginning = "https://selenium:";
            String end = "@seleniumhub.codecool.codecanvas.hu/wd/hub";
            String nodeUrl = beginning + selPw + end;
            try {
                instance = new RemoteWebDriver(new URL(nodeUrl), setUp());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}

package com.codecool;

import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.net.MalformedURLException;

public class BaseTest {
    protected static LoginPage loginPage;
    protected static DashBoardPage dashBoardPage;
    protected CreateIssuePage createIssuePage = new CreateIssuePage();
    static {
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
    }

    public BaseTest() throws MalformedURLException {
    }

    @BeforeAll
    public static void login(){
        loginPage.loginSuccessful();
    }

    @AfterAll
    static void endGame() {
        dashBoardPage.logout2();
        dashBoardPage.quit();
    }
}

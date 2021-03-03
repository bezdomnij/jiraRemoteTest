package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import javax.sql.DataSource;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static final LoginPage loginPage;

    static {
        loginPage = new LoginPage();
    }

    private static DashBoardPage dashBoardPage;

    static {
        dashBoardPage = new DashBoardPage();
    }

    private AlternateLogin alternateLogin = new AlternateLogin();

    public LoginTest() throws MalformedURLException {
    }

    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testLoginSuccessful(String userId) throws MalformedURLException {
        loginPage.loginSuccessful();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }

//    @Test
//    public void testLoginFailedWithIncorrectPassword() throws InterruptedException {
//        WebElement loginError = loginPage.loginFailed("incorrectPassword");
//        assertNotNull(loginError);
//    }
//
//    @Test
//    public void testLoginFailedWithIncorrectUserName() throws InterruptedException {
//        WebElement loginError = loginPage.loginFailed("incorrectUsername");
//        assertNotNull(loginError);
//    }

    /*@Test
    public void loginWrongPassword3Times() throws InterruptedException {
        WebElement captcha = loginPage.loginWrongPassword3Times();
        assertNotNull(captcha);
    }*/

    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testAlternateLoginSuccessful(String userId) throws MalformedURLException {
        alternateLogin.loginSuccessfulAlternateLoginPage();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }

    @AfterAll
    public static void cleanUp() {
        dashBoardPage.logout();
        dashBoardPage.quit();
    }
}


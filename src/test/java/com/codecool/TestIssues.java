package com.codecool;

import com.codecool.pages.DashBoardPage;
import com.codecool.pages.IssuesPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIssues {
    private static final LoginPage loginPage;

    static {
        loginPage = new LoginPage();
    }

    private static DashBoardPage dashBoardPage;

    static {
        try {
            dashBoardPage = new DashBoardPage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //    private final CreateIssuePage createIssuePage = new CreateIssuePage();
    private final IssuesPage issuesPage = new IssuesPage();

    public TestIssues() throws MalformedURLException {
    }

    @BeforeAll
    public static void login() {
        loginPage.loginSuccessful();
    }

    @ParameterizedTest
    @CsvSource({"TOUCAN, Task"})
    public void testCreateIssue(String project, String issueType) throws InterruptedException {
        dashBoardPage.getCreateIssueButton().click();

        String result = issuesPage.createIssue(project, issueType, "randomString");
        boolean resultActual = issuesPage.compare(result, project);
        dashBoardPage.deleteIssueByIssueId(result);
        assertTrue(resultActual);
    }

    @ParameterizedTest
    @MethodSource("createListOfIssueType")
    public void testIssueTypeOfProject(String project, String issueType) {
        dashBoardPage.getCreateIssueButton().click();
        String issueId = issuesPage.createIssue(project, issueType, "randomString");
        String actualIssueType = dashBoardPage.getIssueTypeByIssueId(issueId);
        dashBoardPage.deleteIssueByIssueId(issueId);
        assertEquals(issueType, actualIssueType);
    }

    private static List<Arguments> createListOfIssueType() {
        List<String> issueTypes = Arrays.asList("Story", "Task", "Improvement");
        List<String> projects = Arrays.asList("TOUCAN");
        List<Arguments> argumentsList = new ArrayList<>();
        for (String project : projects) {
            for (String type : issueTypes) {
                argumentsList.add(Arguments.of(project, type));
            }
        }
        return argumentsList;
    }

    @AfterAll
    static void endGame() {
        dashBoardPage.logout();
        dashBoardPage.quit();
    }
}

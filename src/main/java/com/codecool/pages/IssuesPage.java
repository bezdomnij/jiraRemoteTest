package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class IssuesPage {
    WebDriver driver = WebDriverSingleton.getInstance();
    WebDriverWait wait = new WebDriverWait(driver, 3);

    @FindBy(xpath = "//a[@id=\"create_link\"]")
    private WebElement createButton;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement typeInputField;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(xpath = "//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")
    private WebElement popUpMessageClose;

    public IssuesPage() throws MalformedURLException {
        PageFactory.initElements(driver, this);
    }

    public String createIssue(String project, String issueType, String text) {

        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.click(); // clear field
        projectInputField.sendKeys(project + Keys.ENTER);

        try {
            wait.until(ExpectedConditions.invisibilityOf(typeInputField));
        } catch (Exception e) {
            System.out.println("TypeInputField not invisible");
        }
        wait.until(ExpectedConditions.elementToBeClickable(typeInputField));
        typeInputField.click();
        typeInputField.sendKeys(issueType + Keys.TAB);

        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        summaryField.click();
        summaryField.sendKeys(text + Keys.ENTER);

        try {
            wait.until(ExpectedConditions.stalenessOf(successMessage));
        } catch (Exception e) {
            System.out.println("success message not stale");
        }
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        String id = getCreatedIssueId(successMessage.getText());
        System.out.println(successMessage.getText());
        System.out.println(id);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")));
        popUpMessageClose.click();
        return id;
    }

    public boolean compare(String result, String project) {
        String[] resultArray = result.split("-");
        return resultArray[0].equals(project);
    }

    private String getCreatedIssueId(String text) {
        System.out.println(text);
        String[] words = text.split(" ");
        return words[1];
    }
}

package com.codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class DashBoardPage extends BasePage {
//    WebDriver driver = WebDriverSingleton.getInstance();
//    WebDriverWait wait = new WebDriverWait(driver, 5);

    public DashBoardPage()  {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 4), this);
    }

    @FindBy(xpath = "//img[starts-with(@alt, 'User profile')]")
    private WebElement userIcon;

    @FindBy(id = "log_out")
    private WebElement logout;

    @FindBy(id = "view_profile")
    private WebElement userProfile;

    @FindBy(xpath = "//h1[contains(text(),'Logout')]")
    private WebElement logoutConfirmation;

    @FindBy(id = "browse_link")
    private WebElement browseLink;

    @FindBy(id = "project_view_all_link")
    private WebElement viewAllLink;

    @FindBy(id = "project-filter-text")
    private WebElement projectFilterText;

    @FindBy(id = "create_link")
    private WebElement createIssue;

    @FindBy(id = "find_link")
    private WebElement issuesButton;

    @FindBy(id = "filter_lnk_reported_lnk")
    private WebElement reportedByMe;

    @FindBy(id = "searcher-query")
    private WebElement searchQuery;

    @FindBy(xpath = "//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")
    private WebElement popUpClose;

    public WebElement getCreateIssueButton() {
        return createIssue;
    }

    public boolean checkLogout() {
        waitForVisibility(userIcon);
        userIcon.click();
        return logout.isDisplayed();
    }

    public String checkUserName() throws MalformedURLException {
        waitForVisibility(userIcon);
        userIcon.click();
        ViewProfilePage viewProfilePage = new ViewProfilePage();
        return viewProfilePage.getUserNameTitle();
    }

    // creating simple logout page
    public void logout2(){
        try {
            waitForVisibility(userIcon);
            userIcon.click();
            logout.click();
        } catch (Exception ee) {
            System.out.println("not logged in?");
        }
    }


    public WebElement logout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(userIcon));
            userIcon.click();
            logout.click();
        } catch (Exception ee) {
            System.out.println("not logged in?");
        }

        try {
            waitForVisibility(logoutConfirmation);
        } catch (TimeoutException e) {
            return null;
        }

        return logoutConfirmation;
    }

    public void quit() {
        driver.quit();
    }

    public String browseProject(String projectName) {
        String xpathTerm = String.format("//a[starts-with(@title,'%s')]", projectName);
        browseLink.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        viewAllLink.click();
        projectFilterText.sendKeys(projectName);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.xpath(xpathTerm)).click();
        String checkIconXpath = String.format("//img[starts-with(@alt, '%s')]", projectName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkIconXpath)));
        WebElement icon = driver.findElement(By.xpath(checkIconXpath));
        return icon.getAttribute("alt");
    }

    public String searchProject(String projectName, String urlEnds) {
        String locator = String.format("//a[contains(text(),'%s Project')]", projectName);
        String url = String.format("https://jira.codecool.codecanvas.hu/projects/%s", urlEnds);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));

        String text = driver.findElement(By.xpath(locator)).getText();
        System.out.println(text);
        return text;
    }

    public void deleteIssue(String projectName) throws InterruptedException {
        Thread.sleep(2000);
        String locator = String.format("//a[starts-with(@data-issue-key,'%s')]", projectName);
        driver.findElement(By.xpath(locator)).click();
        driver.findElement(By.xpath("//a[@id='opsbar-operations_more']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Delete')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();
    }

    public void searchForIssueCreatedByMe(String keyWord) {
        issuesButton.click();
        reportedByMe.click();
        searchQuery.click();
        searchQuery.sendKeys(keyWord);
        searchQuery.sendKeys(Keys.ENTER);
    }

    public String getIssueTypeByIssueId(String issueId) {
        String url = String.format(BROWSE_PAGE +"%s", issueId);
        driver.get(url);
        return driver.findElement(By.id("type-val")).getText();
    }

    public void deleteIssueByIssueId(String issueId) {
        String url = String.format(BROWSE_PAGE +"%s", issueId);
        driver.get(url);
        driver.findElement(By.id("opsbar-operations_more")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Delete')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")));
        driver.findElement(By.xpath("//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")).click();
    }
}

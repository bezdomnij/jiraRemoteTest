package com.codecool.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewProfilePage extends BasePage {

    public ViewProfilePage() {
        PageFactory.initElements(driver, this);
        driver.navigate().to(VIEWPROFILEPAGE);
    }

    @FindBy(id = "up-user-title-name")
    private WebElement userNameTitle;

    public String getUserNameTitle() {
        return userNameTitle.getText();
    }

    public void setUserNameTitle(WebElement userNameTitle) {
        this.userNameTitle = userNameTitle;
    }
}

package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 6/21/16.
 */
public class HomePage {
    WebDriver driver;


    public static final By RIGHT_NAV_MENU = By.cssSelector(".nav--main__level__list--right > li");
    public static final By LEFT_NAV_MENU = By.cssSelector(".nav--main__level__list--left > li");
    public static final By EMAIL_INPUT = By.id("email_address");
    public static final By ACCOUNT_TYPE_SELECTOR = By.className("pix-selectable");
    public static final By JOIN_BUTTON =  By.id("reg-form-submit");

    public HomePage(WebDriver driver) { this.driver = driver;
    }

    public LoginPage openLoginPage() {
        List<WebElement> headerLinks = driver.findElements(RIGHT_NAV_MENU);
        headerLinks.get(1).click();
        return new LoginPage(driver);

    }

    public MakersPage openMakersPage() {
        List<WebElement> headerLinks = driver.findElements(LEFT_NAV_MENU);
        headerLinks.get(1).click();
        return new MakersPage(driver);

    }


    public RegistrationPage openRegistrationStep1(String email, int accountIndex) throws InterruptedException {

        WebElement emailInput = driver.findElement(EMAIL_INPUT);
        WebElement accountType = driver.findElement(ACCOUNT_TYPE_SELECTOR);

        emailInput.sendKeys(email);
        accountType.click();

        WebElement accountTypeClick = driver.findElement(By.className("pix-selectable-list"));

        List<WebElement> accountTypeList=accountTypeClick.findElements(By.className("pix-selectable-item"));
        accountTypeList.get(accountIndex).click();

        WebElement button = driver.findElement(JOIN_BUTTON);
        button.submit();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(10000);
        return new RegistrationPage(driver);
    }
}

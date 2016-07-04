package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by syasenovich on 6/21/16.
 */
public class HomePage {
    WebDriver driver;

   // public static By NAV_MENU = By.className("nav--main__level__list__item");


    public static By RIGHT_NAV_MENU = By.cssSelector(".nav--main__level__list--right > li");

    public HomePage(WebDriver driver) { this.driver = driver;
    }

    public LoginPage openLoginPage() {
        List<WebElement> headerLinks = driver.findElements(RIGHT_NAV_MENU);
        headerLinks.get(1).click();
        return new LoginPage(driver);

    }
}

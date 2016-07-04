package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by syasenovich on 6/21/16.
 */
public class HomePageLoggedUser {
    WebDriver driver;
    public static By MAKERS_MENU = By.xpath("//a[text()='Makers']");

    public HomePageLoggedUser(WebDriver driver) { this.driver =driver;}

    public MakersPageLoggedUser openMakersPageLoggedUser() {


        WebElement makersMenu = driver.findElement(MAKERS_MENU);
        makersMenu.click();
        return new MakersPageLoggedUser(driver);
    }
}

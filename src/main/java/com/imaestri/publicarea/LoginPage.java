package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by syasenovich on 6/21/16.
 */
public class LoginPage {
    WebDriver driver;

    public static By USERNAME_INPUT = By.id("email");
    public static By PASSWORD_INPUT = By.id("pass");
    public static By LOGIN_BUTTON = By.id("send2");


    public LoginPage(WebDriver driver) { this.driver = driver;
    }

    public HomePageLoggedUser login(String username, String password) {

        WebElement loginName = driver.findElement(USERNAME_INPUT);
        WebElement pass = driver.findElement(PASSWORD_INPUT);
        WebElement button = driver.findElement(LOGIN_BUTTON);

        loginName.sendKeys(username);
        pass.sendKeys(password);

        button.submit();
        return new HomePageLoggedUser(driver) ;
    }
}

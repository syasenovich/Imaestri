package com.imaestri.publicarea;

import junit.framework.Assert;
//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


import java.io.IOException;

/**
 * Created by syasenovich on 6/21/16.
 */
public class MakersIndexPageLoggedUserTest {

    private static WebDriver driver;

    private static GetPropertyValues prop;


public static void readProperties() throws IOException {
    prop = new GetPropertyValues();
    prop.getPropValues();

}

    @org.testng.annotations.BeforeClass
    public static void startDriver() throws IOException {
        readProperties();

        //сделан хромдрайвер потому фаерфокс обновился до последней версии

        System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get(prop.URL);
        //driver.get(prop.Prod_ENVIROMENT);

        driver.manage().window().maximize();



    }

    @org.testng.annotations.Test

    public void testMakersPagesLoggedUser() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.openLoginPage();
        HomePageLoggedUser homePageLoggedUser = loginPage.login(prop.USERNAME, prop.PASSWORD);
        MakersPageLoggedUser makersPageLoggedUser = homePageLoggedUser.openMakersPageLoggedUser();
        Assert.assertTrue("Makers Index page was not opened", makersPageLoggedUser.checkOpening());
        Assert.assertEquals("some page were not opened: " + makersPageLoggedUser.openAllMakersPages(),"", makersPageLoggedUser.openAllMakersPages());


    }

//    public static void initFirefox() throws IOException {
//        FirefoxProfile ffProfile = new FirefoxProfile();
//        JavaScriptError.addExtension(ffProfile);
//        driver = new FirefoxDriver(ffProfile);
//
//
//        // String URL = Prod_ENVIROMENT;
//        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENV;
//
//        driver.get(URL);
//        WebElement username = driver.findElement(By.id("email"));
//        WebElement pass = driver.findElement(By.id("pass"));
//        WebElement button = driver.findElement(By.id("send2"));
//
//        //username.sendKeys();
//        //pass.sendKeys();
//
//        username.sendKeys(");
//        pass.sendKeys();
//
//        button.submit();
//
//    }

    @org.testng.annotations.AfterClass
    public static void tearDown(){
        driver.quit();
    }

}

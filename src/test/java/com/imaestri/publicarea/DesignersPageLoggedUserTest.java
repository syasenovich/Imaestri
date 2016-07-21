package com.imaestri.publicarea;

//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Created by syasenovich on 2/21/15.
 */
public class DesignersPageLoggedUserTest {

    private static WebDriver driver;

    private static GetPropertyValues prop;

//    public static void initFirefox() throws IOException {
//        FirefoxProfile ffProfile = new FirefoxProfile();
//      //  JavaScriptError.addExtension(ffProfile);
//        driver = new FirefoxDriver(ffProfile);
//
//    }
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

    public void testDesignersPagesLoggedUser() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.openLoginPage();
        HomePageLoggedUser homePageLoggedUser = loginPage.login(prop.USERNAME, prop.PASSWORD);
        DesignersPageLoggedUser designersPageLoggedUser = homePageLoggedUser.openDesignersPageLoggedUser();
        Assert.assertTrue("Designers Index page was not opened", designersPageLoggedUser.checkOpening());
        Assert.assertEquals("Some designers page were not opened: " + designersPageLoggedUser.openAllDesignersPages(), "", designersPageLoggedUser.openAllDesignersPages());


    }



    @org.testng.annotations.AfterClass
    public static void closeFirefox(){
        driver.quit();
    }


}

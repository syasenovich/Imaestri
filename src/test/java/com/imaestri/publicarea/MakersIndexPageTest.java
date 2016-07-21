package com.imaestri.publicarea;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 6/20/16.
 */
public class MakersIndexPageTest {
    private static WebDriver driver;
    private static GetPropertyValues prop;

//    @BeforeClass
//    public static void initFirefox() throws IOException {
//
//        FirefoxProfile ffProfile = new FirefoxProfile();
//        JavaScriptError.addExtension(ffProfile);
//        driver = new FirefoxDriver(ffProfile);
//
//    }

public static void readProperties() throws IOException {
    prop = new GetPropertyValues();
    prop.getPropValues();

}
    @org.testng.annotations.BeforeClass

public static void initDriver() throws IOException{
    readProperties();

    System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
    driver = new ChromeDriver();
        driver.get(prop.URL);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();



}


    @org.testng.annotations.Test
    public void testBrandsOpens() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        MakersPage makersPage = homePage.openMakersPage();


        Assert.assertTrue("Makers Index page was not opened", makersPage.checkOpening());

        Assert.assertEquals("some page were not opened: " + makersPage.openAllMakersPages(),"", makersPage.openAllMakersPages());


    }

    @org.testng.annotations.AfterClass
    public static void tearDown(){
        driver.quit();
    }


}

package com.imaestri.publicarea.registration;

import com.imaestri.publicarea.GetPropertyValues;
import com.imaestri.publicarea.HomePage;
import com.imaestri.publicarea.MakersPage;
import com.imaestri.publicarea.RegistrationPage;
import junit.framework.*;
import org.junit.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 5/15/15.
 */
public class RegistrationConsumerTest {
    private static WebDriver driver;
    private static GetPropertyValues prop;


    public static void readProperties() throws IOException {
        prop = new GetPropertyValues();
        prop.getPropValues();

    }

    @org.testng.annotations.BeforeClass
    public static void initChrome() throws IOException {
        readProperties();

        //сделан хромдрайвер потому фаерфокс обновился до последней версии

        System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get(prop.URL);
        //driver.get(prop.Prod_ENVIROMENT);

        driver.manage().window().maximize();



    }

//    public static void initFirefox() {
//        driver = new FirefoxDriver();

//        driver.get(URL);
//
//
//
//    }

    @org.testng.annotations.Test
    public void testConsumerRegistration() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage = homePage.openRegistrationStep1(prop.CONSUMER_EMAIL, 2);

        Assert.assertTrue("Registration Step1 page was not opened", registrationPage.checkOpeningStep1('C'));
        Assert.assertTrue("Registration Step2 page was not successfull", registrationPage.checkConsumerRegistration());

    }

   @org.testng.annotations.AfterClass
    public static void tearDown(){
        driver.quit();
    }
}

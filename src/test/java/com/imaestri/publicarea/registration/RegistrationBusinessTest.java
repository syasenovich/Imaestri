package com.imaestri.publicarea.registration;

import com.imaestri.publicarea.GetPropertyValues;
import com.imaestri.publicarea.HomePage;
import com.imaestri.publicarea.RegistrationPage;
import com.thoughtworks.selenium.SeleneseTestCase;
import junit.framework.TestCase;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 5/15/15.
 */
public class RegistrationBusinessTest{

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
       // driver.get(prop.Prod_ENVIROMENT);

        driver.manage().window().maximize();



    }
//
//    public static void initFirefox() {
//        driver = new FirefoxDriver();
//
//        driver.get(URL);
//
//
//    }
@org.testng.annotations.Test
    public void testBusinessRegistration() throws InterruptedException {
    HomePage homePage = new HomePage(driver);
    RegistrationPage registrationPage = homePage.openRegistrationStep1(prop.BUSINESS_EMAIL, 3);

    Assert.assertTrue("Business Registration Step1 page was not opened", registrationPage.checkOpeningStep1('B'));
    Assert.assertTrue("Business Registration Step2 was not successfull", registrationPage.checkBusinessRegistration());

}
    @org.testng.annotations.AfterClass
    public static void tearDown(){
        driver.quit();
    }

}

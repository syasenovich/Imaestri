package com.imaestri.publicarea;

import junit.framework.Assert;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
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
public class MakersIndexPageLoggedUser {

    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/";
    public static String USERNAME = "sveta.yasenovich@gmail.com";
    public static String PASSWORD = "2013Popova";


    @BeforeClass
    public static void startDriver() {

        //сделан хромдрайвер потому фаерфокс обновился до последней версии

        System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get(Prod_ENVIROMENT);

        driver.manage().window().maximize();



    }

    @Test

    public void testMakersPagesLoggedUser() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.openLoginPage();
        HomePageLoggedUser homePageLoggedUser = loginPage.login(USERNAME, PASSWORD);
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
//        //username.sendKeys("mt+trade@eristocrat.net");
//        //pass.sendKeys("]WPYx48QFi;HTMv");
//
//        username.sendKeys("sveta.yasenovich@gmail.com");
//        pass.sendKeys("2013Popova");
//
//        button.submit();
//
//    }



}

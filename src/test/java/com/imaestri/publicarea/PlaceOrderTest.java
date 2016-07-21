package com.imaestri.publicarea;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

/**
 * Created by syasenovich on 7/14/16.
 */
public class PlaceOrderTest {
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

    public void testPlaceOrder() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.openLoginPage();
        HomePageLoggedUser homePageLoggedUser = loginPage.login(prop.USERNAME, prop.PASSWORD);
        SimpleProductPage simpleProductPage = homePageLoggedUser.openSimpleSinProductPage();
        Assert.assertTrue("Simple Product Detail page was not opened", simpleProductPage.checkOpening("Sin"));
        Assert.assertTrue("Simple Product was not added to cart", simpleProductPage.checkAddingToCart("SIN"));

        GrouppedProductPage grouppedProductPage=homePageLoggedUser.openGrouppedProductPage();

       // Assert.assertEquals("some page were not opened: " + makersPageLoggedUser.openAllMakersPages(),"", makersPageLoggedUser.openAllMakersPages());


    }
    @org.testng.annotations.AfterClass
    public static void tearDown(){
        driver.quit();
    }
}

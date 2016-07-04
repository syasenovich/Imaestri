package com.imaestri.publicarea;

import junit.framework.Assert;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 6/20/16.
 */
public class MakersIndexPage {
    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/brands-preview/?limit=60";

//    @BeforeClass
//    public static void initFirefox() throws IOException {
//
//        FirefoxProfile ffProfile = new FirefoxProfile();
//        JavaScriptError.addExtension(ffProfile);
//        driver = new FirefoxDriver(ffProfile);
//
//    }
@BeforeClass

public static void startDriver() {

    //сделан хромдрайвер потому фаерфокс обновился до последней версии

    System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
    driver = new ChromeDriver();
    driver.get(Prod_ENVIROMENT);

    driver.manage().window().maximize();


}

//    @Before
//    public void openBrands()  {
//        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT+"brands-preview?limit=all";
//        // String URL = Prod_ENVIROMENT;
//
//        driver.get(URL);
//
//
//    }

    @Test
    public void testBrandsOpens() throws InterruptedException {
        MakersPage makersPage = new MakersPage(driver);
        Assert.assertTrue("Makers Index page was not opened", makersPage.checkOpening());

        Assert.assertEquals("some page were not opened: " + makersPage.openAllMakersPages(),"", makersPage.openAllMakersPages());


    }


}

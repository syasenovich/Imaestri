package com.imaestri.publicarea;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by syasenovich on 1/18/15.
 */
public class BrandsTest {
    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/brands-preview/?limit=60";


    @BeforeClass
    public static void initFirefox() throws IOException {
       /* final String firebugPath = "/Users/syasenovich/Downloads/firebug-2.0.11-fx.xpi";
        FirefoxProfile ffProfile = new FirefoxProfile();
        ffProfile.addExtension(new File(firebugPath));
        ffProfile.setPreference("extensions.firebug.currentVersion", "2.0.11"); //(here you can include the version you currently have)
        ffProfile.setPreference("extensions.firebug.showStackTrace", true);
        ffProfile.setPreference("extensions.firebug.delayLoad", false);
        ffProfile.setPreference("extensions.firebug.showFirstRunPage", false);
        ffProfile.setPreference("extensions.firebug.allPagesActivation", "on");
        ffProfile.setPreference("extensions.firebug.console.enableSites", true);
        ffProfile.setPreference("extensions.firebug.defaultPanelName", "console");
        driver = new FirefoxDriver(ffProfile);*/
       // driver = new FirefoxDriver();
        FirefoxProfile ffProfile = new FirefoxProfile();
        JavaScriptError.addExtension(ffProfile);
        driver = new FirefoxDriver(ffProfile);

    }
    @Before
    public void openBrands()  {
        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT+"brands-preview?limit=all";
       // String URL = Prod_ENVIROMENT;

        driver.get(URL);


    }
    @Test
    public void testBrandsOpens() throws InterruptedException {
        WebElement brandsTitle = driver.findElement(By.className("toolbar-title"));
        //String pageTitle = driver.getTitle();
        //Assert.assertEquals("Current page title", "Reference Web App - QA Automation", pageTitle);
        System.out.println(brandsTitle.getText());

        List<JavaScriptError> jsErrorsMain = JavaScriptError.readErrors(driver);
      //  System.out.println("Total No Of JavaScript Errors : " + jsErrorsMain.size());
        Assert.assertTrue("JS errors occured: " + jsErrorsMain, jsErrorsMain.isEmpty());

        if ( !brandsTitle.getText().contains("BRANDS")) {
            Assert.fail("Brands page was not opened");
        }
        else {
            WebElement brandsContainer = driver.findElement(By.className("companies"));

            //List<WebElement> link=brandsContainer.findElements(By.className("images"));
            List<WebElement> link=brandsContainer.findElements(By.xpath("//div[@class='images']/a"));
           // WebElement forgotPasswordTitle = driver.findElement(By.xpath("//div[@id='forgot-password']/h1"));

           // System.out.println("brands link number: " +link.size());
            //System.out.println("brands link 0: " + link.get(0).getAttribute("href"));
            String FailureMessage = "";

           for (int index=0; index<link.size(); index++ ) {
          //  for (int index=0; index<3; index++ ) {
                String url = link.get(index).getAttribute("href");
               // String expectedBrand = link.get(index).getText();

                WebDriver linkDriver = new FirefoxDriver();

                try {
                    linkDriver.get(url);
                    final List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
                    Assert.assertTrue("JS errors occured: " + jsErrors, jsErrors.isEmpty());
                     try {
                         WebElement brandContainer = linkDriver.findElement(By.className("company-title"));
                         //if ( !expectedBrand.equalsIgnoreCase(brandContainer.getText()) )
                         if (brandContainer.getText().isEmpty())
                             FailureMessage = FailureMessage + link.get(index).getAttribute("href") + ", ";
                     }
                     catch (Exception e) {
                        // Assert.fail("Brand detail page was not opened" + url +"; ");

                         FailureMessage = FailureMessage + "Brand detail page was not opened: " + url +"; ";
                     }

                } finally {
                    linkDriver.quit();
                }



//            Thread.sleep(200);
                //driver.navigate().back();
            }
            if ( !FailureMessage.isEmpty())
             Assert.assertEquals("some page were not opened: " + FailureMessage,"", FailureMessage);

        }

    }


   @AfterClass
    public static void closeFirefox(){
        driver.quit();
    }
}

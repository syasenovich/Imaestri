package com.imaestri.publicarea;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by syasenovich on 3/11/15.
 */
public class BrandsLoggedUser {
    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.fourteen.imaestri.com/customer/account/login/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/customer/account/login/";
    public static final String TEST_ENV = "www.stg1.imaestri.com/customer/account/login/";

    @BeforeClass
    public static void initFirefox() throws IOException {
        FirefoxProfile ffProfile = new FirefoxProfile();
        JavaScriptError.addExtension(ffProfile);
        driver = new FirefoxDriver(ffProfile);


       // String URL = Prod_ENVIROMENT;
         String URL = "https://imaestri:9cCQD%404M@" + TEST_ENV;

        driver.get(URL);
        WebElement username = driver.findElement(By.id("email"));
        WebElement pass = driver.findElement(By.id("pass"));
        WebElement button = driver.findElement(By.id("send2"));

        //username.sendKeys("mt+trade@eristocrat.net");
        //pass.sendKeys("]WPYx48QFi;HTMv");

        username.sendKeys("sveta.yasenovich@gmail.com");
        pass.sendKeys("2013Popova");

        button.submit();

    }


    @Test
    public void testBrandsLinks() throws InterruptedException {

        WebElement brandsMenu = driver.findElement(By.xpath("//span[text()='Brands']"));
        brandsMenu.click();
        List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
        Assert.assertTrue("JS errors occured: " + jsErrors, jsErrors.isEmpty());
        System.out.println("——————START displaying JS errors——————");

        for (int i = 0; i < jsErrors.size(); i++) {
            System.out.println(jsErrors.get(i).getErrorMessage());
            System.out.println("Error in Line: "+ jsErrors.get(i).getLineNumber());
            System.out.println(jsErrors.get(i).getSourceName());
            System.out.println("\n");
        }
        System.out.println("——————STOP displaying JS errors———————");

        WebElement brandsTitle = driver.findElement(By.className("toolbar-title"));
        if ( !brandsTitle.getText().contains("BRANDS") )
            Assert.assertTrue("Brands page was not opened.", brandsTitle.getText().contains("BRANDS"));
        else {


            //WebElement brandsMenu = driver.findElement(By.xpath("//span[text()='Brands']"));
            WebElement pageLimiter = driver.findElement(By.className("limiter"));
            WebElement pageLim = pageLimiter.findElement(By.className("pix-selectable-trigger"));

            pageLim.click();
            WebElement pageList = pageLimiter.findElement(By.className("pix-selectable-list"));
            List<WebElement> perPage = pageList.findElements(By.className("pix-selectable-item"));
            perPage.get(perPage.size()-1).click();
            WebElement brandsContainer = driver.findElement(By.className("companies"));
            // System.out.println(brandsContainer.getText());

            List<WebElement> link = brandsContainer.findElements(By.className("reader-medium"));
            System.out.println(link.size());
            String FailureMessage = "";
            Integer linkSize = link.size();

            for (int index = 0; index < linkSize ; index++) {
                brandsContainer = driver.findElement(By.className("companies"));
                // System.out.println(brandsContainer.getText());

               link = brandsContainer.findElements(By.className("reader-medium"));
                String url = link.get(index).getAttribute("href");
                String expectedBrand = link.get(index).getText();

                System.out.println(expectedBrand);

                try {

                    Actions newTab = new Actions(driver);
                    newTab.keyDown(Keys.SHIFT).click(link.get(index)).keyUp(Keys.SHIFT).build().perform();
                    Thread.sleep(5000);

//handle windows change
                    String base = driver.getWindowHandle();
                    Set<String> set = driver.getWindowHandles();

                    set.remove(base);
                    assert set.size() == 1;
                    driver.switchTo().window((String) set.toArray()[0]);

                    final List<JavaScriptError> jsErrors2 = JavaScriptError.readErrors(driver);
                    Assert.assertTrue("JS errors occured: " + jsErrors2, jsErrors2.isEmpty());
                    WebElement brandContainer = driver.findElement(By.className("company-title"));
                    if (!expectedBrand.contains(brandContainer.getText()))
                        FailureMessage = FailureMessage + link.get(index).getText() + ", ";
//close the window and sitch back to the base tab
                    driver.close();
                    driver.switchTo().window(base);

                } finally {

                   // driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "w");
                }


//            Thread.sleep(200);
                //driver.navigate().back();
            }
            Assert.assertEquals("some page were not opened: " + FailureMessage, "", FailureMessage);
        }

    }



    @AfterClass
    public static void closeFirefox(){
        driver.quit();
    }
}

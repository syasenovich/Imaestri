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

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by syasenovich on 2/21/15.
 */
public class DesignersTest {

    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.fourteen.imaestri.com/customer/account/login/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/customer/account/login/";
    public static final String TEST_ENV = "www.stg1.imaestri.com/customer/account/login/";

    @BeforeClass
    public static void initFirefox() throws IOException {
        FirefoxProfile ffProfile = new FirefoxProfile();
        JavaScriptError.addExtension(ffProfile);
        driver = new FirefoxDriver(ffProfile);

    }
    @Before
    public void openDesignersPage(){
        //String URL = Prod_ENVIROMENT;
        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENV;
        driver.get(URL);
        WebElement username = driver.findElement(By.id("email"));
        WebElement pass = driver.findElement(By.id("pass"));
        WebElement button = driver.findElement(By.id("send2"));

        username.sendKeys("mt+trade@eristocrat.net");
        pass.sendKeys("]WPYx48QFi;HTMv");

        //username.sendKeys("sveta.yasenovich@gmail.com");
        //pass.sendKeys("2013Popova");

        button.submit();

    }
    @Test
    public void testDesignersOpens() {

        String FailureMessage = "";
        WebElement designersMenu = driver.findElement(By.xpath("//span[text()='Designers']"));
        designersMenu.click();
        WebElement designersTitle = driver.findElement(By.className("toolbar-title"));
        final List<JavaScriptError> jsErrorsMain2 = JavaScriptError.readErrors(driver);
        Assert.assertTrue("JS errors occured: " + jsErrorsMain2, jsErrorsMain2.isEmpty());

        if (!designersTitle.getText().equalsIgnoreCase("Designers"))
            Assert.assertTrue("Designers page was not opened", designersTitle.getText().equalsIgnoreCase("Designers"));
        else {
            WebElement pageLimiter = driver.findElement(By.className("limiter"));
            WebElement pageLim = pageLimiter.findElement(By.className("pix-selectable-trigger"));

            pageLim.click();
            WebElement pageList = pageLimiter.findElement(By.className("pix-selectable-list"));
            List<WebElement> perPage = pageList.findElements(By.className("pix-selectable-item"));
            perPage.get(perPage.size() - 1).click();

            final List<JavaScriptError> jsErrorsMain = JavaScriptError.readErrors(driver);
            Assert.assertTrue("JS errors occured: " + jsErrorsMain, jsErrorsMain.isEmpty());

            WebElement designersContainer = driver.findElement(By.className("designers"));
            // System.out.println("designersContainer " + designersContainer.);
            List<WebElement> designerLinks = designersContainer.findElements(By.xpath("//div[@class='image']/a"));
            List<WebElement> designerNames = designersContainer.findElements(By.className("reader-medium"));
            System.out.println("size1 " + designerLinks.size());
            System.out.println("designerNames size1 " + designerNames.size());


          for (int index = 0; index < designerLinks.size() ; index++) {


                String expectedDesignerName = designerNames.get(index).getText();
                String url = designerLinks.get(index).getAttribute("href");


                System.out.println(expectedDesignerName);

                try {

                    Actions newTab = new Actions(driver);
                    newTab.keyDown(Keys.SHIFT).click(designerLinks.get(index)).keyUp(Keys.SHIFT).build().perform();
                    Thread.sleep(5000);

//handle windows change
                    String base = driver.getWindowHandle();
                    Set<String> set = driver.getWindowHandles();

                    set.remove(base);
                    assert set.size() == 1;
                    driver.switchTo().window((String) set.toArray()[0]);

                    final List<JavaScriptError> jsErrors2 = JavaScriptError.readErrors(driver);
                    Assert.assertTrue("JS errors occured: " + jsErrors2, jsErrors2.isEmpty());

                   // WebElement designerContainer = driver.findElement(By.className("toolbar-title"));
                    WebElement designerContainer = driver.findElement(By.xpath("//div[@class='left']/h2"));

                    System.out.println("designerContainer " + designerContainer.getText());

                    if (! designerContainer.getText().contains(expectedDesignerName))
                  //  if (!expectedDesignerName.contains(designerContainer.getText()))
                        FailureMessage = FailureMessage + expectedDesignerName + ", ";
//close the window and sitch back to the base tab
                    driver.close();
                    driver.switchTo().window(base);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                   // driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "w");
                }




        }


        }
    }


    /*@AfterClass
    public static void closeFirefox(){
        driver.quit();
    }
*/

}

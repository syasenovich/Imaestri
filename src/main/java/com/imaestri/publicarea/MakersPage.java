package com.imaestri.publicarea;

//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 6/20/16.
 */
public class MakersPage extends ImaestriPages {
    WebDriver driver;
    public static By MAKERS_TITLE = By.className("toolbar-title");
    public static By MAKERS_CONTAINER = By.className("companies");
   // public static By MAKERS_URLS = By.xpath("//div[@class='images']/a");
   public static By MAKERS_URLS = By.cssSelector(".images >a");




    public MakersPage(WebDriver driver) { this.driver = driver;
    }

    public boolean checkOpening() {
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.visibilityOf(driver.findElement(MAKERS_TITLE)));
        WebElement brandsTitle = driver.findElement(MAKERS_TITLE);
        if ( !brandsTitle.getAttribute("textContent").contains("Makers")) {
            System.out.println("Page has content:  "+ brandsTitle.getAttribute("textContent"));
            return false;
            //Assert.fail("Brands page was not opened");
        }
        else return true;

    }

    public String openAllMakersPages() throws InterruptedException {
        openAllItemsOnPage(driver);

        WebElement brandsContainer = driver.findElement(MAKERS_CONTAINER);
        List<WebElement> link=driver.findElements(MAKERS_URLS);

        //List<WebElement> link=brandsContainer.findElements(MAKERS_URLS);


        // System.out.println("brands link number: " +link.size());
        //System.out.println("brands link 0: " + link.get(0).getAttribute("href"));
        String FailureMessage = "";

        for (int index=0; index<link.size(); index++ ) {

            String url = link.get(index).getAttribute("href");
           // System.out.println("index= " + index+ " "+link.get(index).getAttribute("href"));

            System.setProperty("webdriver.chrome.driver", "/Users/syasenovich/Documents/chromedriver");
            WebDriver linkDriver = new ChromeDriver();

            try {
                linkDriver.get(url);
//                final List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
//                Assert.assertTrue("JS errors occured: " + jsErrors, jsErrors.isEmpty());
                try {
                    WebElement brandContainer = linkDriver.findElement(By.className("company-title"));
                    //if ( !expectedBrand.equalsIgnoreCase(brandContainer.getText()) )
                    if (brandContainer.getText().isEmpty())
                        FailureMessage = FailureMessage + link.get(index).getAttribute("href") + ", ";
                } catch (Exception e) {
                    // Assert.fail("Brand detail page was not opened" + url +"; ");

                    FailureMessage = FailureMessage + "Brand detail page was not opened: " + url + "; ";
                }

            } finally {
                linkDriver.quit();
            }

        }
        return FailureMessage;
//
        }


}

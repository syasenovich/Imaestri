package com.imaestri.publicarea;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;

/**
 * Created by syasenovich on 6/21/16.
 */
public class MakersPageLoggedUser {
    WebDriver driver;

    public static By MAKERS_TITLE = By.className("toolbar-title");
    public static By MAKERS_CONTAINER = By.className("companies");
    public static By MAKERS_URLS = By.xpath("//div[@class='images']/a");

    public MakersPageLoggedUser(WebDriver driver) { this.driver =driver;
    }


    public boolean checkOpening() {
        WebElement brandsTitle = driver.findElement(MAKERS_TITLE);
        if ( !brandsTitle.getAttribute("textContent").contains("Makers")) {
            System.out.println("Page has content:  "+ brandsTitle.getAttribute("textContent"));
            return false;
            //Assert.fail("Brands page was not opened");
        }
        else return true;
    }

    public String openAllMakersPages() throws InterruptedException {
       // WebElement brandsMenu = driver.findElement(By.xpath("//span[text()='Brands']"));
       // brandsMenu.click();
        List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);
        Assert.assertTrue("JS errors occured: " + jsErrors, jsErrors.isEmpty());
        System.out.println("——————START displaying JS errors——————");

        for (int i = 0; i < jsErrors.size(); i++) {
            System.out.println(jsErrors.get(i).getErrorMessage());
            System.out.println("Error in Line: " + jsErrors.get(i).getLineNumber());
            System.out.println(jsErrors.get(i).getSourceName());
            System.out.println("\n");
        }
        System.out.println("——————STOP displaying JS errors———————");

//        WebElement brandsTitle = driver.findElement(MAKERS_TITLE);
//        if (!brandsTitle.getText().contains("BRANDS"))
//            Assert.assertTrue("Brands page was not opened.", brandsTitle.getText().contains("BRANDS"));
//        else {


            //WebElement brandsMenu = driver.findElement(By.xpath("//span[text()='Brands']"));
            WebElement pageLimiter = driver.findElement(By.className("limiter"));
            WebElement pageLim = pageLimiter.findElement(By.className("pix-selectable-trigger"));

            pageLim.click();
            WebElement pageList = pageLimiter.findElement(By.className("pix-selectable-list"));
            List<WebElement> perPage = pageList.findElements(By.className("pix-selectable-item"));
            perPage.get(perPage.size() - 1).click();
            WebElement brandsContainer = driver.findElement(MAKERS_CONTAINER);
            // System.out.println(brandsContainer.getText());

            List<WebElement> link = brandsContainer.findElements(By.className("reader-medium"));
            System.out.println(link.size());
            String FailureMessage = "";
            Integer linkSize = link.size();

            for (int index = 0; index < linkSize; index++) {
                brandsContainer = driver.findElement(MAKERS_CONTAINER);
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
        return FailureMessage;
        }


}

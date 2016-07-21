package com.imaestri.publicarea;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;

/**
 * Created by syasenovich on 7/6/16.
 */
public class DesignersPageLoggedUser extends ImaestriPages {
    WebDriver driver;
    public static final By DESIGNERS_TITLE = By.className("toolbar-title");
    public static final By DESIGNERS_CONTAINER = By.className("designers");
    public static final By DESIGNERS_URL = By.xpath("//div[@class='image']/a");
    public static final By DESIGNERS_NAME = By.className("reader-medium");
    public static final By DESIGNER_NAME = By.className("company-title");

    public DesignersPageLoggedUser(WebDriver driver) {
        this.driver = driver;
    }


    public boolean checkOpening() {

        WebElement designersTitle = driver.findElement(DESIGNERS_TITLE);
        if (!designersTitle.getAttribute("textContent").contains("Designers")) {
            System.out.println("Page has content:  " + designersTitle.getAttribute("textContent"));
            return false;

        } else return true;
    }

    public String openAllDesignersPages() throws InterruptedException {
        String FailureMessage = "";

        //  final List<JavaScriptError> jsErrorsMain2 = JavaScriptError.readErrors(driver);
        //   Assert.assertTrue("JS errors occured: " + jsErrorsMain2, jsErrorsMain2.isEmpty());
        openAllItemsOnPage(driver);
//
//
////            final List<JavaScriptError> jsErrorsMain = JavaScriptError.readErrors(driver);
////            Assert.assertTrue("JS errors occured: " + jsErrorsMain, jsErrorsMain.isEmpty());
//
        WebElement designersContainer = driver.findElement(DESIGNERS_CONTAINER);
        // System.out.println("designersContainer " + designersContainer.);
        List<WebElement> designerLinks = designersContainer.findElements(DESIGNERS_URL);
        List<WebElement> designerNames = designersContainer.findElements(DESIGNERS_NAME);
//            System.out.println("size1 " + designerLinks.size());
//            System.out.println("designerNames size1 " + designerNames.size());
//
//
        for (int index = 0; index < designerLinks.size(); index++) {


            String expectedDesignerName = designerNames.get(index).getText();
            String url = designerLinks.get(index).getAttribute("href");


            System.out.println(expectedDesignerName);

            try {
                openNewTab(driver, designerLinks.get(index));

//                Actions newTab = new Actions(driver);
//                newTab.keyDown(Keys.SHIFT).click(designerLinks.get(index)).keyUp(Keys.SHIFT).build().perform();
//                Thread.sleep(3000);

//handle windows change
                String base = driver.getWindowHandle();
                Set<String> set = driver.getWindowHandles();

                set.remove(base);
                assert set.size() == 1;
                driver.switchTo().window((String) set.toArray()[0]);

//                    final List<JavaScriptError> jsErrors2 = JavaScriptError.readErrors(driver);
//                    Assert.assertTrue("JS errors occured: " + jsErrors2, jsErrors2.isEmpty());

                // WebElement designerContainer = driver.findElement(By.className("toolbar-title"));
                //WebElement designerContainer = driver.findElement(By.xpath("//div[@class='left']/h2"));
                WebElement designerContainer = driver.findElement(DESIGNER_NAME);

             //   System.out.println("designerContainer " + designerContainer.getText());

                if (!designerContainer.getText().contains(expectedDesignerName))
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
//
//
//
//

        }

        return FailureMessage;
    }
}


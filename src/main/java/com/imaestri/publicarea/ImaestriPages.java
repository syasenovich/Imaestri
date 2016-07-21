package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by syasenovich on 7/6/16.
 */
public class ImaestriPages {


    public void openAllItemsOnPage(WebDriver driver) throws InterruptedException {
        WebElement pageLimiter = driver.findElement(By.className("limiter"));
        WebElement pageLim = pageLimiter.findElement(By.className("pix-selectable-trigger"));

        pageLim.click();

        WebElement pageList = pageLimiter.findElement(By.className("pix-selectable-list"));
        List<WebElement> perPage = pageList.findElements(By.className("pix-selectable-item"));
        perPage.get(perPage.size() - 1).click();
        Thread.sleep(10000);


    }

    public void openNewTab(WebDriver driver, WebElement link) throws InterruptedException {
        Actions newTab = new Actions(driver);
        newTab.keyDown(Keys.SHIFT).click(link).keyUp(Keys.SHIFT).build().perform();
        Thread.sleep(3000);


    }
}

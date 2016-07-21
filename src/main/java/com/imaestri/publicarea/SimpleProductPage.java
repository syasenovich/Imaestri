package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by syasenovich on 7/14/16.
 */
public class SimpleProductPage {
    WebDriver driver;
    public static final By PRODUCT_TITLE = By.cssSelector(".product-name >h1");
    public static final By ADD_CART_BUTTON = By.className("btn-cart");
    public static final By CONFIRMATION_POP_UP_TEXT = By.cssSelector(".block-title>strong");
    public static final By CANCEL_BUTTON =  By.className("btn-cancel");

    public SimpleProductPage(WebDriver driver) { this.driver =driver;
    }


    public boolean checkOpening(String productName) {
        WebElement productTitle = driver.findElement(PRODUCT_TITLE);
        if (!productTitle.getAttribute("textContent").contains(productName)) {
            System.out.println("Product page has content:  " + productTitle.getAttribute("textContent"));
            return false;

        } else return true;
    }

    public boolean checkAddingToCart(String productName) throws InterruptedException {
        String expectedConfirmationMessage = "The item "+productName+" has been added to your cart";
        WebElement addToCart = driver.findElement(ADD_CART_BUTTON);
        addToCart.click();
        Thread.sleep(1000);

        if(!driver.findElement(CONFIRMATION_POP_UP_TEXT).getText().equalsIgnoreCase(expectedConfirmationMessage)) {
            driver.findElement(CANCEL_BUTTON).click();
            return false;

        } else {
            driver.findElement(CANCEL_BUTTON).click();
            return true;
        }

    }
}

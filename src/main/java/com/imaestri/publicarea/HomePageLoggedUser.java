package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by syasenovich on 6/21/16.
 */
public class HomePageLoggedUser {
    WebDriver driver;

    public static final By RESIDENTIAL_MENU = By.xpath("//a[text()='Residential']");
    public static final By SEARCH_ICON = By.className("search-link");
    public static final By MAKERS_MENU = By.xpath("//a[text()='Makers']");
    public static final By DESIGNERS_MENU = By.xpath("//a[text()='Designers']");
    public static final By SEARCH_INPUT_FIELD = By.id("search");
    public static final By SEARCH_AUTOCOMPLETE_PREVIEW = By.className("asearch-preview");
    public static final By SUGGEST_LINKS =  By.cssSelector(".block-products >ul >li");

    public HomePageLoggedUser(WebDriver driver) { this.driver =driver;}

    public MakersPageLoggedUser openMakersPageLoggedUser() {

        WebElement makersMenu = driver.findElement(MAKERS_MENU);
        makersMenu.click();
        return new MakersPageLoggedUser(driver);
    }

    public DesignersPageLoggedUser openDesignersPageLoggedUser() {
        WebElement makersMenu = driver.findElement(DESIGNERS_MENU);
        makersMenu.click();
        return new DesignersPageLoggedUser(driver);
    }

    public SimpleProductPage openSimpleSinProductPage() throws InterruptedException {
        WebElement searchIcon = driver.findElement(SEARCH_ICON);
        searchIcon.click();
        WebElement searchInput = driver.findElement(SEARCH_INPUT_FIELD);
        searchInput.sendKeys("Sin");
        Thread.sleep(1000);
        WebElement searchAutoComplete = driver. findElement(SEARCH_AUTOCOMPLETE_PREVIEW);
        List<WebElement> searchAutosuggestionLinks =  searchAutoComplete.findElements(SUGGEST_LINKS);
        searchAutosuggestionLinks.get(0).click();
        Thread.sleep(1000);
        return new SimpleProductPage(driver);


    }
}

package com.imaestri.publicarea.forgotPassword;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by syasenovich on 5/18/15.
 */
public class ForgotPasswordTest {

       private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
    //public static final String Prod_ENVIROMENT = "https://www.imaestri.com/brands-preview/?limit=60";


    @BeforeClass
    public static void initFirefox() {
        driver = new FirefoxDriver();

        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT+"customer/account/login/";
        // String URL = Prod_ENVIROMENT;
        driver.get(URL);



    }

    @Test
    public void testInvalidEmail(){
        try {
            WebElement forgotPasswordLink = driver.findElement(By.className("forgot-password"));
            forgotPasswordLink.click();
            try {
                WebElement forgotPasswordTitle = driver.findElement(By.xpath("//div[@id='forgot-password']/h1"));


                if (!forgotPasswordTitle.getText().equalsIgnoreCase("Forgot Password?"))
                    Assert.fail("Forgot password page was not opened.");

                else {
                    WebElement emailInput = driver.findElement(By.id("email_address"));
                    emailInput.sendKeys("invalidemail@test.com");

                    WebElement submitBtn = driver.findElement(By.className("btn"));
                    submitBtn.submit();

                    try{
                        WebElement errorMsg = driver.findElement(By.className("error-msg"));
                        Assert.assertTrue("Failed login Error message is invalid", errorMsg.getText().equalsIgnoreCase("An account associated with invalidemail@test.com does not exist in our system. Please click on JOIN NOW to Request an Account."));
                    }
                    catch (Exception e) {
                        Assert.fail("Failed login Error message was not displayed");

                    }

                }

            }
            catch (Exception e) {
                Assert.fail("Forgot password page was not opened.");

            }


        }
        catch (Exception e) {
            Assert.fail("No Forgot password link");

        }

    }
    @AfterClass
    public static void closeFirefox(){
        driver.quit();
    }

}

package com.imaestri.publicarea.forgotPassword;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 5/18/15.
 */
public class ForgotPasswordAfterStep1 {

//    private static WebDriver driver;
//    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
//    //public static final String Prod_ENVIROMENT = "https://www.imaestri.com/brands-preview/?limit=60";
//
//
//    @BeforeClass
//    public static void initFirefox() {
//        driver = new FirefoxDriver();
//
//        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT + "request-account/#register";
//        // String URL = Prod_ENVIROMENT;
//        driver.get(URL);
//
//
//    }
//
//    @Test
//    public void testForgotPasswordAfterStep1() {
//        ////Registration
//        WebElement emailInput = driver.findElement(By.id("email_address"));
//        WebElement accountType = driver.findElement(By.className("pix-selectable"));
//
//        emailInput.sendKeys("svetlana.yasenovich+5@gmail.com");
//        accountType.click();
//
//        WebElement accountTypeClick = driver.findElement(By.className("pix-selectable-list"));
//
//        List<WebElement> accountTypeList = accountTypeClick.findElements(By.className("pix-selectable-item"));
//        accountTypeList.get(2).click();
//
//        WebElement button = driver.findElement(By.id("reg-form-submit"));
//        button.submit();
//////-----
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        try {
//            WebElement step2Success = driver.findElement(By.className("top_section_signup"));
//            if (!step2Success.getText().equalsIgnoreCase("COMPLETE YOUR ACCOUNT REGISTRATION"))
//                Assert.assertTrue("Step2 registration page was not opened.", step2Success.getText().equalsIgnoreCase("COMPLETE YOUR ACCOUNT REGISTRATION"));
//
//            else {
//                driver.get("https://www.stg1.imaestri.com/customer/account/login/");
//                try {
//                    WebElement forgotPasswordLink = driver.findElement(By.className("forgot-password"));
//                    forgotPasswordLink.click();
//                    try {
//                        WebElement forgotPasswordTitle = driver.findElement(By.xpath("//div[@id='forgot-password']/h1"));
//
//
//                        if (!forgotPasswordTitle.getText().equalsIgnoreCase("Forgot Password?"))
//                            Assert.fail("Forgot password page was not opened.");
//
//                        else {
//                            WebElement emailInputNew = driver.findElement(By.id("email_address"));
//                            emailInputNew.sendKeys("svetlana.yasenovich+5@gmail.com");
//
//                            WebElement submitBtn = driver.findElement(By.className("btn"));
//                            submitBtn.submit();
//
//                            try{
//                                WebElement successMsg = driver.findElement(By.className("success-msg"));
//                                Assert.assertTrue("Success message is invalid", successMsg.getText().equalsIgnoreCase("If there is an account associated with sveta.yasenovich@gmail.com you will receive an email with a link to reset your password."));
//                            }
//                            catch (Exception e) {
//                                Assert.fail("Success message was not displayed");
//
//                            }
//
//                        }
//
//                    }
//                    catch (Exception e) {
//                        Assert.fail("Forgot password page was not opened.");
//
//                    }
//
//
//                }
//                catch (Exception e) {
//                    Assert.fail("No Forgot password link");
//
//                }
//            }
//
//        } catch (Exception e) {
//            Assert.fail("Registration Step 1 was not successfull");
//
//        }
//    }
}

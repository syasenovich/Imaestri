package com.imaestri.publicarea.registration;

import com.thoughtworks.selenium.SeleneseTestCase;
import junit.framework.TestCase;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 5/15/15.
 */
public class RegistrationBusinessTest{

    private static WebDriver driver;
    public static final String TEST_ENVIRONMENT = "www.stg1.imaestri.com/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/request-account/#register";
    public static final String PreProd_ENVIROMENT = "www.perf-prod.imaestri.com/request-account/#register";


    @BeforeClass

    public static void initFirefox() {
        driver = new FirefoxDriver();

        String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT+"request-account/#register";
      //  String URL = PreProd_ENVIROMENT;
       // String URL = "https://imaestri:9cCQD%404M@" + PreProd_ENVIROMENT;
        driver.get(URL);


    }

    @Test
    public void testRequestAccountFormOpens(){
        try {
            WebElement requestTitle = driver.findElement(By.className("serif"));
            //String pageTitle = driver.getTitle();
            //Assert.assertEquals("Current page title", "Reference Web App - QA Automation", pageTitle);
            System.out.println(requestTitle.getText());
            Assert.assertTrue("Request An Account form was not opened", requestTitle.getText().equalsIgnoreCase("Request An Account"));
            System.out.println("Test 1");
        }
        catch (Exception e) {
            Assert.fail("Request An Account form was not opened");

        }
        // Assert.assertEquals("not valid","adasdasd", brandsTitle.getText() );
    }
    @Test
    public void testBusinessRegistration() {
        System.out.println("Step 1");
        WebElement emailInput = driver.findElement(By.id("email_address"));
        WebElement accountType = driver.findElement(By.className("pix-selectable"));

        emailInput.sendKeys("svetlana.yasenovich+2@gmail.com");
        accountType.click();

        WebElement accountTypeClick = driver.findElement(By.className("pix-selectable-list"));

        List<WebElement> accountTypeList=accountTypeClick.findElements(By.className("pix-selectable-item"));
        accountTypeList.get(3).click();

        WebElement button = driver.findElement(By.id("reg-form-submit"));
        button.submit();
////-----
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        try {
            WebElement step2Success = driver.findElement(By.className("top_section_signup"));
            if (!step2Success.getText().equalsIgnoreCase("COMPLETE YOUR BUSINESS ACCOUNT REGISTRATION"))
                Assert.fail("Step2 BUSINESS account registration page was not opened.");

            else {
                System.out.println("Step 2");
                try {
                    WebElement firstNameInput = driver.findElement(By.id("first_name"));
                    WebElement lastNameInput = driver.findElement(By.id("last_name"));
                    WebElement phoneInput = driver.findElement(By.id("customer_telephone"));
                    WebElement passwordInput = driver.findElement(By.id("password"));
                    WebElement verifyPasswordInput = driver.findElement(By.id("verify_password"));
                    WebElement companyNameInput = driver.findElement(By.id("company"));
                    WebElement streetInput = driver.findElement(By.id("street_1"));
                    WebElement cityInput = driver.findElement(By.id("city"));
                    WebElement zipInput = driver.findElement(By.id("zip"));
                    WebElement telephoneInput = driver.findElement(By.id("telephone"));

                    WebElement submitLogInButton = driver.findElement(By.className("button_signup"));

                    firstNameInput.sendKeys("SvetaBusiness");
                    lastNameInput.sendKeys("Yasenovich");
                    phoneInput.sendKeys("2012039194");
                    passwordInput.sendKeys("1234567");
                    verifyPasswordInput.sendKeys("1234567");
                    companyNameInput.sendKeys("Qa Test");
                    streetInput.sendKeys("21 2nd street");
                    cityInput.sendKeys("Jersey City");
                    zipInput.sendKeys("07302");
                    telephoneInput.sendKeys("2012039198");

                    Select dropStateList = new Select(driver.findElement(By.id("region_id")));
                    dropStateList.selectByVisibleText("New Jersey");


                    submitLogInButton.submit();
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    WebElement successSignup = driver.findElement(By.className("success-signup"));

                    Assert.assertTrue("Registration Step 2 was not success", successSignup.getText().contains("THANK YOU FOR JOINING"));
                }
                catch (Exception e) {
                    Assert.fail("Registration Step 2 was not successfull");

                }

            }
        }


        catch (Exception e) {
            Assert.fail("Registration Step 1 was not successfull");

        }




    }

  // @AfterClass
  //  public static void closeFirefox(){
       // driver.quit();
    //}
}

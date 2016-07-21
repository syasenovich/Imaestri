package com.imaestri.publicarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by syasenovich on 7/6/16.
 */
public class RegistrationPage {
    WebDriver driver;
    public static final By REGISTRATION_PAGE_TEXT = By.className("content-column");
    public static final By FIRST_NAME_INPUT = By.id("first_name");
    public static final By LAST_NAME_INPUT = By.id("last_name");
    public static final By PHONE_INPUT = By.id("customer_telephone");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By VERIFY_PASSWORD_INPUT = By.id("verify_password");
    public static final By BUTTON_SUBMIT = By.className("button_signup");
    public static final By COMPANY_TYPE_SELECTOR = By.id("company_type");
    public static final By COMPANY_INPUT = By.id("company");
    public static final By STREET_INPUT = By.id("street_1");
    public static final By CITY_INPUT = By.id("city");
    public static final By ZIP_INPUT = By.id("zip");
    public static final By TELEPHONE_INPUT = By.id("telephone");
    public static final By STATE_INPUT = By.id("region_id");

    public static final By SUCCESS_MSG = By.cssSelector(".block-content >h1");

    WebElement firstNameInput;
    WebElement lastNameInput;
    WebElement phoneInput;
    WebElement passwordInput;
    WebElement verifyPasswordInput;
    WebElement submitLogInButton;
    WebElement successSignup;
    WebElement companyNameInput;
    WebElement streetInput;
    WebElement cityInput;
    WebElement zipInput;
    WebElement telephoneInput;

    WebElement registrationPageTitle;

    private boolean caseCheck=false;


    public RegistrationPage(WebDriver driver) {this.driver =driver;
    }

    public boolean checkOpeningStep1(char caseAccount) {
        registrationPageTitle = driver.findElement(REGISTRATION_PAGE_TEXT);

        switch(caseAccount) {


            case 'C':

                if (!registrationPageTitle.getText().contains("COMPLETE YOUR ACCOUNT REGISTRATION"))
                    caseCheck =false ;
                else caseCheck = true;
                break;

            case 'B':

                if (!registrationPageTitle.getText().contains("COMPLETE YOUR BUSINESS ACCOUNT REGISTRATION"))
                    caseCheck =false ;
                else caseCheck = true;
                break;

            case 'D':

                if (!registrationPageTitle.getText().contains("COMPLETE YOUR DESIGN TRADE ACCOUNT REGISTRATION"))
                    caseCheck =false ;
                else caseCheck = true;
                break;

        }
        if (!caseCheck)
            return false;
        else return true;
    }

    public boolean checkConsumerRegistration() {
        System.out.println("Step 2");
        try {
            firstNameInput = driver.findElement(FIRST_NAME_INPUT);
            lastNameInput = driver.findElement(LAST_NAME_INPUT);
            phoneInput = driver.findElement(PHONE_INPUT);
            passwordInput = driver.findElement(PASSWORD_INPUT);
            verifyPasswordInput = driver.findElement(VERIFY_PASSWORD_INPUT);
            submitLogInButton = driver.findElement(BUTTON_SUBMIT);

            firstNameInput.sendKeys("SvetaConsumer");
            lastNameInput.sendKeys("Yasenovich");
            phoneInput.sendKeys("2012039194");
            passwordInput.sendKeys("1234567");
            verifyPasswordInput.sendKeys("1234567");

            submitLogInButton.submit();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            successSignup = driver.findElement(SUCCESS_MSG);

            if (successSignup.getText().contains("THANK YOU FOR JOINING"))
                return true;
            else return false;
        }
        finally {
            driver.quit();
        }
            }


    public boolean checkBusinessRegistration() {
        try {
            enterRegistrationFields();

            submitLogInButton = driver.findElement(BUTTON_SUBMIT);
            submitLogInButton.submit();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            successSignup = driver.findElement(SUCCESS_MSG);

            if (successSignup.getText().contains("THANK YOU FOR JOINING"))
                return true;
            else return false;
        }
        finally {
            driver.quit();
        }
    }

    public boolean checkDesignTradeRegistration() {
        try {
            enterRegistrationFields();
            Select dropCompanyType = new Select(driver.findElement(COMPANY_TYPE_SELECTOR));
            dropCompanyType.selectByVisibleText("Other");

            submitLogInButton = driver.findElement(BUTTON_SUBMIT);

            submitLogInButton.submit();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            successSignup = driver.findElement(SUCCESS_MSG);

            if (successSignup.getText().contains("THANK YOU FOR JOINING"))
                return true;
            else return false;
        }
        finally {
            driver.quit();
        }
    }

    public void enterRegistrationFields() {
        firstNameInput = driver.findElement(FIRST_NAME_INPUT);
        lastNameInput = driver.findElement(LAST_NAME_INPUT);
        phoneInput = driver.findElement(PHONE_INPUT);
        passwordInput = driver.findElement(PASSWORD_INPUT);
        verifyPasswordInput = driver.findElement(VERIFY_PASSWORD_INPUT);

        companyNameInput = driver.findElement(COMPANY_INPUT);
        streetInput = driver.findElement(STREET_INPUT);
        cityInput = driver.findElement(CITY_INPUT);
        zipInput = driver.findElement(ZIP_INPUT);
        telephoneInput = driver.findElement(TELEPHONE_INPUT);


        firstNameInput.sendKeys("SvetaDesignT");
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

    }
}

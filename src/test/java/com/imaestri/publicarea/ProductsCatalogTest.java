package com.imaestri.publicarea;


        import org.junit.*;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.firefox.FirefoxProfile;
        import org.openqa.selenium.*;
        import org.openqa.selenium.firefox.internal.ProfilesIni;
        import org.openqa.selenium.interactions.Action;
        import org.openqa.selenium.interactions.Actions;

        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

/**
 * Created by syasenovich on 1/19/15.
 */
public class ProductsCatalogTest {
    private static WebDriver driver;
   // public static final String TEST_ENVIRONMENT = "www.fourteen.imaestri.com/";
    public static final String Prod_ENVIROMENT = "https://www.imaestri.com/customer/account/login/";

    @BeforeClass
    public static void initFirefox() {
        driver = new FirefoxDriver();

    }
    @Before
    public void openLogin() throws InterruptedException {
        //String URL = "https://imaestri:9cCQD%404M@" + TEST_ENVIRONMENT+"brands-preview?limit=all";
        String URL = Prod_ENVIROMENT;
        driver.get(URL);
        WebElement username = driver.findElement(By.id("email"));
        WebElement pass = driver.findElement(By.id("pass"));
        WebElement button = driver.findElement(By.id("send2"));
      //  username.sendKeys("mt+trade@eristocrat.net");
        username.sendKeys("sveta.yasenovich@gmail.com");
        pass.sendKeys("2013Popova");
       // pass.sendKeys("9cCQD@4M");
        button.submit();

     //   Thread.sleep(200);

    }
    @Test
    public void testProductsSeatingOpens() throws InterruptedException {
        String FailureMessage = "";

        WebElement productsMenu = driver.findElement(By.className("level-top"));
        productsMenu.click();

        WebElement seatingMenu = driver.findElement(By.xpath(".//*[@id='nav']/li[1]/ul/li[1]/a/span"));
        seatingMenu.click();

        WebElement seatingProducts = driver.findElement(By.className("category-products"));
        //List<WebElement> products = seatingProducts.findElements(By.xpath("//a[contains(@class,'product-image')]"));

        List<WebElement> productDetails = seatingProducts.findElements(By.xpath("//h2[@class='product-name']/a"));

        System.out.println(productDetails.size());

       // /@title
      //  /@href

        //handle windows change
        String mainWindowHandle = driver.getWindowHandle();

        for (WebElement element : productDetails) {
            driver.switchTo().window(mainWindowHandle);

            try {

                String url = element.getAttribute("href");
                String expectedProductTitle = element.getAttribute("title");
                System.out.println("expectedProductTitle: " + expectedProductTitle);
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "t");
                driver.get(url);

                //try {

                String productTitle = driver.findElement(By.xpath("//div[@class='product-name']/h1")).getText();


                if (!productTitle.equalsIgnoreCase(expectedProductTitle))
                    FailureMessage = FailureMessage + expectedProductTitle + ", ";

                //System.out.println("productTitle: " + productTitle);
                //   } finally {
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "w");
                // linkDriver.quit();
                //  }
            } catch (Exception e) {
                System.out.println("Failed processing ");
                e.printStackTrace();


            }


        }
        System.out.println("FM: " +FailureMessage);
        Assert.assertEquals("Product details page was not opened for: " + FailureMessage,"", FailureMessage);


       /* Set<String> uniqueLinks =  new HashSet<>();

        products.forEach((product) ->
                        uniqueLinks.add(product.getAttribute("href"))
        );*/


    }
    //@Test
   public void testProductsOpens(){

       //Thread.sleep(200);



        }

    @AfterClass
    public static void closeFirefox(){
      //driver.quit();
    }


}

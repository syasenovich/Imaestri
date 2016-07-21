package com.imaestri.publicarea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by syasenovich on 7/5/16.
 */
public class GetPropertyValues {
    String result = "";
    InputStream inputStream;
    public static String USERNAME;
    public static String PASSWORD;
//    public static String TEST_ENVIRONMENT;
//    public static String Prod_ENVIROMENT;
    public static String URL;
    public static String CONSUMER_EMAIL;
    public static String BUSINESS_EMAIL;
    public static String DESIGN_TRADE_EMAIL;


    public void getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String host = java.lang.System.getProperties().getProperty("host");
            if (host == null )
                host= "prod";

            String propFileName = host + ".properties";

//            input = new FileInputStream("src/main/resources/" + host + ".properties");

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

           // Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
             USERNAME = prop.getProperty("username");
             PASSWORD = prop.getProperty("password");
//            TEST_ENVIRONMENT = prop.getProperty("TEST_ENVIRONMENT");
//            Prod_ENVIROMENT = prop.getProperty("Prod_ENVIROMENT");
            URL = prop.getProperty("url");
            CONSUMER_EMAIL= prop.getProperty("consumer_email");
            BUSINESS_EMAIL= prop.getProperty("business_email");
            DESIGN_TRADE_EMAIL= prop.getProperty("design_trade_email");

            //System.out.println("USERNAME " + USERNAME + "pwd "+PASSWORD);

            //result = "Company List = " + company1 + ", " + company2 + ", " + company3;
           // System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
       // return result;
    }
}

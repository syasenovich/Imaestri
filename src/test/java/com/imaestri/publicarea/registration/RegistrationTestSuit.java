package com.imaestri.publicarea.registration;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = {RegistrationBusinessTest.class, RegistrationConsumerTest.class, RegistrationDesignTradeTest.class})
/**
 * Created by syasenovich on 5/15/15.
 */
public class RegistrationTestSuit extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        //suite.addTestSuite(RegistrationConsumerTest.class);
       // suite.addTestSuite(RegistrationBusinessTest.class);
        //suite.addTestSuite(RegistrationDesignTradeTest.class);
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

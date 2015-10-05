package SpecialStack;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Look at the differences in error messages
     */
    public void testApp()
    {
        assertTrue( false );
    }

    public void testAppHamcrest()
    {
        assertThat(false, is(true));      // is(equalTo(value))
    }


}

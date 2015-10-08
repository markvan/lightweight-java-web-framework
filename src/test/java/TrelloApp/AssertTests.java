package TrelloApp;

// adapted from https://github.com/junit-team/junit/wiki/Assertions
// deprecations removed

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import java.util.Arrays;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;


public class AssertTests {

    @Test
    public void testAssertArrayEquals() {
        int[] expected = new int[]{1,2,3};
        int[] actual = new int []{1,2};

        assertArrayEquals("Arrays differ", expected, actual);
    }

    @Test
    public void newTestAssertArrayEquals() {

        int[] computedArray = new int[]{1, 2}; // set as a result of some computation
        int[] expectedArray = new int[]{1, 2, 3}; // what we want

        assertThat("Arrays should be the same", computedArray, is(expectedArray) );
    }


    @Test
    public void testAssertEquals() {
        org.junit.Assert.assertEquals("Strings are not equal", "text", "text");
    }

    @Test
    public void testAssertFalse() {
        org.junit.Assert.assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNotNull() {
        org.junit.Assert.assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNotSame() {
        org.junit.Assert.assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        org.junit.Assert.assertNull("should be null", null);
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        org.junit.Assert.assertSame("should be same", aNumber, aNumber);
    }

    // Core Hamcrest Matchers with assertThat
    @Test
    public void testAssertThatHamcrestCoreMatchers() {

        assertThat("String does not start with 'good' or does not contain 'news'", //improve output
                "good newz", allOf(startsWith("good"), containsString("news")));

        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        assertThat(new Object(), not(sameInstance(new Object())));
    }

    @Test
    public void testAssertTrue() {
        org.junit.Assert.assertTrue("failure - should be true", true);
    }
}
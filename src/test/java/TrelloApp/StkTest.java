package TrelloApp;

import Stk.Stk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StkTest {

    private Stk s;

    @Before
    public void setUp()  {
        s = new Stk();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testInitialState() {
        assertThat(s.count(), is(0));
    }

    @Test
    public void testPush() {
        s.push(100);
        assertThat(s.count(), is(1));
    }
    @Test
    public void testPushAndPop() {
        s.push(-99);
        assertThat(s.count(), is(1));
        assertThat(s.pop(), is(-99));
        assertThat(s.count(), is(0));
    }

    @Test
    public void testRepeatPushAndPop() {
        s.push(100);
        s.push(200);
        assertThat(s.count(), is(2));
        s.push(300);
        assertThat(s.count(), is(3));
        assertThat(s.pop(), is(300));
        assertThat(s.count(), is(2));
        assertThat(s.pop(), is(200));
        assertThat(s.count(), is(1));
        assertThat(s.pop(), is(100));
    }

    @Test
    public void testManyPushAndPop() {
        int numberOfItems=11;
        for (int i=1;i<=numberOfItems;i++)
        {
            s.push(i);
        }
        for (int i=numberOfItems;i>=1;i--)
        {
            assertThat(s.pop(), is(i));
        }
    }
}





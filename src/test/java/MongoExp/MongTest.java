package MongoExp;

import TrelloApp.Stk2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MongTest {

    private Stk2 s;

    @Before
    public void setUp() throws Exception {
        s = new Stk2();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitialState() {
        assertThat(s.count(), is(0));
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
        int numberOfItems=2000000;
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
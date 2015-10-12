package MongoExp;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MongTest {

    private Mong m;

    @Before
    public void setUp() throws Exception {
        m = new Mong();
        m.initialize();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitialState() {
        assertThat(m.all("people").size(), is(4));
    }

    @Test
    public void testCounter() {
        MongoCursor<Document> cursor = m.cursor("people");
        int i=0;
        while (cursor.hasNext()) {
            i += 1;
            // System.out.println(cursor.next().toJson());
        }
        assertThat(i, is(4));
    }

    @Test
    public void testFind() {

    }




}
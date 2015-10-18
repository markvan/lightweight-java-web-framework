package MongoExp;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;

import org.bson.BSON;
import org.bson.Document;
import org.bson.types.ObjectId;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PeopleTest {

    private People people;

    @Before
    public void setUp() throws Exception {
        people = new People();
        people.initialize();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAll() {
        assertThat(people.all().size(), is(4));
    }

    @Test
    public void testCursor() {
        MongoCursor<Document> cursor = people.cursor();
        int i=0;
        while ( cursor.hasNext() ) { cursor.next(); i += 1; }
        assertThat(i, is(4));
    }

    @Test
    public void testFind() {
        assertThat( people.find( Filters.or(eq("first name", "d"), eq("first name", "a"))).size(), is(2) );
    }

    @Test
    public void testCreate() {
        assertThat(people.all().size(), is(4));
        people.create("x", "y", "z");
        assertThat(people.all().size(), is(5));

    }

    @Test
    public void testDelete() {
        assertThat( people.find(Filters.eq("first name", "d")).size(), is(1) );
        people.delete( eq("first name", "d") );
        assertThat( people.find(Filters.eq("first name", "d")).size(), is(0) );
        assertThat( people.find(Filters.eq("first name", "a")).size(), is(1) );

        Document doc = people.findOne(Filters.eq("first name", "a"));
        people.delete( (ObjectId)doc.get("_id") );
        assertThat(people.find(Filters.eq("first name", "d")).size(), is(0));
        assertThat(people.all().size(), is(2));
    }

}
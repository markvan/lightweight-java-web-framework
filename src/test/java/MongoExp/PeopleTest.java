package MongoExp;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PeopleTest {

    private People people;

    @Before
    public void setUp() throws Exception {
        people = new People();
        people.populate();
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
        assertThat( people.find( Filters.or(eq("first_name", "Frank"), eq("first_name", "Dwight"))).size(), is(2) );
    }

    @Test
    public void testFindOne() {
        assertThat( people.findOne( Filters.eq("first_name", "Frank") ).get("first_name"), is("Frank") );
    }

    @Test
    public void testCreate() {
        assertThat(people.all().size(), is(4));
        people.create("x", "y", "z");
        assertThat(people.all().size(), is(5));

    }

    @Test
    public void testDelete() {
        assertThat( people.find(Filters.eq("first_name", "Frank")).size(), is(1) );
        people.delete( eq("first_name", "Frank") );
        assertThat( people.find(Filters.eq("first_name", "Frank")).size(), is(0) );
        assertThat( people.find(Filters.eq("first_name", "Dwight")).size(), is(1) );

        Document doc = people.findOne(Filters.eq("first_name", "Dwight"));
        people.delete( (ObjectId)doc.get("_id") );
        assertThat(people.find(Filters.eq("first_name", "Dwight")).size(), is(0));
        assertThat(people.all().size(), is(2));
    }

}
package MongoExp;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;



import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class People {

    private MongoDatabase database;

    public People() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase(DBConstants.DATABASE);
    }

    public MongoCursor<Document> cursor() {
        return database.getCollection( DBConstants.PEOPLE ).find().iterator();
    }

    public ArrayList<Document> all() {
        ArrayList<Document> arr = new ArrayList<>();
        MongoCursor<Document> collectionCursor = cursor();
        while (collectionCursor.hasNext()) { arr.add(collectionCursor.next()) }
        return arr;
    }

    public ArrayList<Document> find(Bson filter) {
        ArrayList<Document> arr = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection(DBConstants.PEOPLE);
        FindIterable<Document> iterator = collection.find(filter);

        // http://stackoverflow.com/questions/30424894/java-syntax-with-mongodb
        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        iterator.forEach((Block<Document>)  document -> { arr.add(document); } );
        return arr;
    }

    public People create(String firstName, String secondName, String profession) {
        Document doc = new Document();
        doc.append("first name", firstName);
        doc.append("second name", secondName);
        doc.append("profession", profession);
        database.getCollection(DBConstants.PEOPLE).insertOne(doc);
        return this;
    }

    public Boolean delete(Bson filter) {
        find(filter);
        // delete whatever is found
        return false;
    }


        // only of use in testing
    public void initialize() {
        database.getCollection(DBConstants.PEOPLE).drop();
        MongoCollection<Document> collection = database.getCollection(DBConstants.PEOPLE);
        create("a", "b", "c").create("d", "e", "f").create("g", "h", "i").create("j", "k", "l");
        assert collection.count()==4 : "something wrong in People.initialize, number of documents is not 4";
    }

    public static void main(String[] args) {
        People people = new People();
        people.initialize();

        ArrayList<Document> docs = people.find(Filters.or(eq("first name", "d"), eq("first name", "a")));
        for (Document d : docs) {
            System.out.println(d.toJson());
        }
        System.out.println("==============================");


        for (Document d : people.all()) {
            System.out.println(d.toJson());
        }

    }
}


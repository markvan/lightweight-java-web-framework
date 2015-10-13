package MongoExp;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;


import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class Mong {

    private MongoDatabase database;
    private MongoClient mongoClient;

    public Mong() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("demoDatabase");
    }

    public MongoCursor<Document> cursor(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find().iterator();
    }

    public ArrayList<Document> all(String collectionName) {
        ArrayList<Document> arr = new ArrayList<>();
        MongoCursor<Document> collectionCursor = cursor(collectionName);

        try {
            while (collectionCursor.hasNext()) {
                arr.add(collectionCursor.next());
            }
        } finally {
            collectionCursor.close();
        }
        return arr;
    }

    public ArrayList<Document> find(Bson filter) {
        ArrayList<Document> arr = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("people");

        FindIterable<Document> iterator = collection.find(filter);

        // http://stackoverflow.com/questions/30424894/java-syntax-with-mongodb
        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        iterator.forEach((Block<Document>) document -> {
            arr.add(document);
        });
        return arr;
    }

    public void initialize() {
        database.getCollection("people").drop();
        MongoCollection<Document> collection = database.getCollection("people");
        addPerson(collection, "a", "b", "c").addPerson(collection, "d", "e", "f");
        addPerson(collection, "g", "h", "i").addPerson(collection, "j", "k", "l");
        assert collection.count()==4 : "something wrong in Mong.initialize";
    }

    public void x() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("demoDatabase");
        MongoCollection<Document> collection = database.getCollection("people");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
    }

    private Mong addPerson(MongoCollection<Document> collection, String firstName, String secondName, String profession) {
        Document doc = new Document();
        doc.append("first name", firstName);
        doc.append("second name", secondName);
        doc.append("profession", profession);
        collection.insertOne(doc);
        return this;
    }

    public static void main(String[] args) {
        Mong m = new Mong();
        m.find(Filters.or(eq("first name", "d"), eq("first name", "a")));

    }
}
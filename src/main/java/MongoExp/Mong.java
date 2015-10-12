package MongoExp;

import com.mongodb.*;
import com.mongodb.client.*;

import org.bson.Document;

import java.util.*;

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
        ArrayList<Document> arr = new ArrayList<Document>();
        MongoCursor<Document> cursor = this.cursor(collectionName);

        try {
            while (cursor.hasNext()) {
                arr.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return arr;
    }

    public void initialize() {
        database.getCollection("people").drop();
        MongoCollection<Document> collection = database.getCollection("people");
        addPerson(collection, "a", "b", "c").addPerson(collection, "d", "e", "f");
        addPerson(collection, "g", "h", "i").addPerson(collection, "j", "k", "l");
        assert collection.count()==4 : "something wrong in Mong.initialize";
    }

    private Mong addPerson(MongoCollection<Document> collection, String firstName, String secondName, String profession) {
        Document doc = new Document();
        doc.append("first name", firstName);
        doc.append("second name", secondName);
        doc.append("profession", profession);
        collection.insertOne(doc);
        return this;
    }
}
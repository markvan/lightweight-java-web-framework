package MongoExp;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;


import org.bson.Document;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;
import spark.Request;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class People {

    private MongoDatabase database;
    MongoCollection<Document> collection;

    // private AppLogs logger = new AppLogs();

    public People() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase(DBConstants.DATABASE);
        collection = database.getCollection(DBConstants.PEOPLE);
    }

    public MongoCursor<Document> cursor() {
        return database.getCollection( DBConstants.PEOPLE ).find().iterator();
    }

    public ArrayList<Document> all() {
        ArrayList<Document> arr = new ArrayList<>();
        MongoCursor<Document> collectionCursor = cursor();
        while (collectionCursor.hasNext()) { arr.add(collectionCursor.next()); }
        return arr;
    }

    public ArrayList<Document> find(Bson filter) {
        ArrayList<Document> arr = new ArrayList<>();
        FindIterable<Document> iterator = collection.find(filter);
        // http://stackoverflow.com/questions/30424894/java-syntax-with-mongodb
        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        iterator.forEach((Block<Document>)  document ->  arr.add(document) );
        return arr;
    }

    public Document findOne(Bson filter) {
        return collection.find(filter).limit(1).first();
    }

    public Document update(Document updatedDoc) {
        /* db.inventory.update(
              {{ item: "MNO2" },
        {
            $set: {
                category: "apparel",
                        details: { model: "14Q3", manufacturer: "XYZ Company" }
            },
            $currentDate: { lastModified: true }
        }}
        )*/
        return updatedDoc;
    }

    public Document findOne(ObjectId id) {
        return collection.find( eq("_id", id) ).limit(1).first();
    }

    public Document create(String firstName, String secondName, String profession) {
        Document doc = new Document();
        doc.append("first name", firstName);
        doc.append("second name", secondName);
        doc.append("profession", profession);
        //database.getCollection(DBConstants.PEOPLE).insertOne(doc);
        collection.insertOne(doc);
        return doc;
    }

    public Document create(Request request) {
        //TODO use arg in creation
        return create("Frankie", "Zappa", "musician");
    }

    public Document delete(ObjectId id) {
        return collection.findOneAndDelete(eq("_id", id));
    }

    public Document delete(Bson filter) {
        return collection.findOneAndDelete( filter );
    }


    // only of use in testing
    public void initialize() {
        collection.deleteMany(new Document());
        create("a", "b", "c"); create("d", "e", "f"); create("g", "h", "i"); create("j", "k", "l");
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


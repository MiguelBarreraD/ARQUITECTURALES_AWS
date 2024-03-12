package org.example;


import java.time.LocalDateTime;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * The MongoService class provides methods to interact with a MongoDB database
 * for storing and retrieving log records.
 */
public class MongoService {

    private static final String URL_DATABASE = "mongodb://logsdb:27017/";
    private static final String DATABASE_NAME = "logsdb";
    private MongoClient mongoClient;
    private MongoCollection<Document> logsCollection;

    /**
     * Establishes the connection with the MongoDB database.
     * Creates a MongoClient instance and retrieves the "logs" collection from the "logsdb" database.
     */
    public void connect() {
        this.mongoClient = MongoClients.create(new ConnectionString(URL_DATABASE));
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        this.logsCollection = database.getCollection("logs");
    }

    /**
     * Closes the connection with the MongoDB database.
     */
    public void disconnect() {
        this.mongoClient.close();
    }

    /**
     * Inserts a new log record into the database.
     * Establishes the connection, creates a new document with the log message, and inserts it into the collection.
     * Finally, closes the connection.
     *
     * @param log The log message to be inserted.
     */
    public void insertlog(String log) {
        connect();
        Document newLog = new Document("log", log).append("date", LocalDateTime.now());
        logsCollection.insertOne(newLog);
        disconnect();
    }

    /**
     * Retrieves the latest 10 log records from the database.
     * Establishes the connection, retrieves the latest 10 documents sorted by their _id (insertion date)
     * in descending order, and adds them to a list of Strings.
     * Finally, closes the connection and returns the list of logs.
     *
     * @return A list of Strings containing the latest 10 log records.
     * @throws IllegalStateException if the connection to the database has not been established.
     */
    public List<String> getLatestLogs() {
        connect();
        if (logsCollection == null) {
            throw new IllegalStateException("Connection to MongoDB not established");
        }
        List<String> messages = new ArrayList<>();
        logsCollection.find().sort(Sorts.descending("_id")).limit(10).forEach(log -> messages.add(log.toJson()));
        disconnect();
        return messages;
    }
}
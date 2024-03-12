package org.example;
import static spark.Spark.*;

/**
 * The LogService class provides a RESTful API for inserting and retrieving log messages
 * using the MongoService class to interact with a MongoDB database.
 */
public class LogService {

    // Instance of the MongoService class
    private static MongoService mongoService;

    /**
     * The main entry point of the application.
     * Sets up the Spark server and defines the routes.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, res) -> {
            return addAndGetLog(req.queryParams("message"));
        });
    }

    /**
     * Handles the insertion of a new log message and retrieves the latest logs.
     *
     * @param log The log message to be inserted
     * @return A string representation of the latest log messages
     */
    private static String addAndGetLog(String log) {
        if (mongoService == null) {
            mongoService = new MongoService();
        }
        mongoService.insertlog(log);
        return mongoService.getLatestLogs().toString();
    }

    /**
     * Retrieves the port number to be used by the Spark server.
     * If the PORT environment variable is set, it uses that value; otherwise, it defaults to 6000.
     *
     * @return The port number for the Spark server
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }
}
package org.example;
import static spark.Spark.*;

public class LogService {
    public static void main( String[] args ){
        staticFiles.location("/public");
        port(5000);
        get("/logService", (req, res) -> RRInvoke.invoke());
    }
}

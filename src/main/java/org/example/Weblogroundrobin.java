package org.example;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class Weblogroundrobin {
    public static void main( String[] args ){
        port(4567);
        get("/log", (req,res) -> "{\"msg\":\"primer mensaje, 24-02-2024 16:45:45\"}");

    }
}

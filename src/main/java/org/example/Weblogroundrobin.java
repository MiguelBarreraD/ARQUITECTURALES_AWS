package org.example;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class Weblogroundrobin {
    public static void main( String[] args ){
        staticFiles.location("/public");
        port(4567);
        get("/log", (req,res) -> RRInvoke.invoke());

    }
}

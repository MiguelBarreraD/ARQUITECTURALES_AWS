package org.example;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class Weblogroundrobin {
    public static void main( String[] args ){
        staticFiles.location("/public");
        port(getPort());
        //get("/log/:message", (req,res) -> RRInvoke.invoke(req.params(":message")));

        get("/log/:message", (req, res) -> {
            //return req.params(":message");
            return RRInvoke.invoke(req.params(":message"));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}


package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class RRInvoke {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL_1 = "http://localhost:35001/logservice?message=";
    private static final String URL_2 = "http://localhost:35002/logservice?message=";
    private static final String URL_3 = "http://localhost:35003/logservice?message=";
    private static int counter = 1;

    public synchronized static String invoke(String message) throws IOException {
        String url;

        switch (counter) {
            case 1:
                url = URL_1;
                System.out.println("LA URL UTILIZADA FUE LA PRIMERA");
                break;
            case 2:
                url = URL_2;
                System.out.println("LA URL UTILIZADA FUE LA SEGUNDA");
                break;
            case 3:
                url = URL_3;
                System.out.println("LA URL UTILIZADA FUE LA TERCERA");
                break;
            default:
                url = URL_1;
        }
        if (counter == 3) {
            counter = 1;
        } else {
            counter++;
        }
        
        // Encode the message
        String messageUTF = URLEncoder.encode(message, StandardCharsets.UTF_8.toString()).replace("+", "%20");

        // Create the URL object
        URL obj = new URL(url + messageUTF);
        System.out.println(obj);

        // Open the connection
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // Perform the connection
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        // Process the response
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");

        return response.toString();
    }
}

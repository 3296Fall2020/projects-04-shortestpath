import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
// import org.json.simple.JSONObject;

public class Search {

    int port;
    String location;
    HttpURLConnection connection;
    // TODO, other init for future requests

    Search(int port) throws IOException {
        this.port = port;

    }

    private void init(){
        // TODO
    }


    public boolean geocoding(Map<String, String> location) throws IOException {
        // our nodejs server, make get request ".../api/search/geocoding?city="CITY"&state="STATE"

        // TEMP, for testing
        URL url = new URL("http://localhost:8000/api/search/geocoding"); // our server
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");

        // TODO
        // ADD PARAMS ?city="CITY"&state="STATE"
        //connection.setDoOutput(true);
        //DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        //String params = paramsBuilder(location);

        //out.writeBytes(params);
        //out.flush();
        //out.close();

        int status = connection.getResponseCode();
        System.out.println(status);

        // READ response (json)
        // TODO
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();


        System.out.println("STRING form: " + content);
        //JSONObject temp = new JSONObject(content);

        return true; // temp
    }

    private String paramsBuilder(Map<String, String> location) throws UnsupportedEncodingException {
        // ?city="CITY"&state="STATE"

        StringBuilder temp = new StringBuilder();

        // for city, state
        temp.append("?");
        temp.append(URLEncoder.encode("city", "UTF-8"));
        temp.append("=");
        temp.append(URLEncoder.encode(location.get("city"), "UTF-8"));
        temp.append("&");
        temp.append(URLEncoder.encode("state", "UTF-8"));
        temp.append("=");
        temp.append(URLEncoder.encode(location.get("state"), "UTF-8"));

        return temp.toString();
    }

    private void bodyBuilder(Map<String, String> location){
        // TODO, will send city, state in req. body or params (maybe both) this method setup request body
    }

}

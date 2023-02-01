package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DeleteUser {

    public static void main(String[] args) throws IOException {
        String uri = "https://jsonplaceholder.typicode.com/users";
        int userID = 5;

        delete(uri, userID);
    }

    public static void delete(String uri, int userID) throws IOException {


        URL url = new URL(uri+"/"+userID);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();

        System.out.println(responseCode);
    }



}

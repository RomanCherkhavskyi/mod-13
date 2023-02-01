package task1;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class CreateNewUser {

    public static void main(String[] args) throws IOException, InterruptedException {

        String filePath = "src/newUserData.json";
        String uri  = "https://jsonplaceholder.typicode.com/users";

        createUser(uri,filePath);       //return 201 - create

    }
        public static void createUser(String uri, String filePath) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(filePath)))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

    }

}

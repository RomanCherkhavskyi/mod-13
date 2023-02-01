package task1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class UpdateUserInfo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = "src/newUserData.json";
        String uri = "https://jsonplaceholder.typicode.com/users";
        int userID = 5;

        update(uri, userID, filePath);

    }

    public static void update(String uri, int userID, String filePath) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri+"/"+userID))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get(filePath)))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }



}

package task1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GetUserInfoByID {

    public static void main(String[] args) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users";
        int userID = 7;
        get(uri,userID);
    }

    public static void get(String uri, int userID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri+"/"+ userID))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}

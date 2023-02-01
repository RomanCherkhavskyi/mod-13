package task1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetUserInfoByUserName {

    public static void main(String[] args) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users";
        String userName = "Antonette";
        get(uri,userName);
    }

    public static void get(String uri, String userName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri+"?username="+ userName))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}

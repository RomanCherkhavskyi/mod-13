package task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class OpenTasksFinder {

    public static void main(String[] args) throws Exception {
        int xUser = 1;
        String url = "https://jsonplaceholder.typicode.com/users/"+xUser+"/todos";
//        System.out.println(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        List<OpenTask> openTasks = objectMapper.readValue(response.body(),new TypeReference<>(){});

        List<OpenTask> completedOpenTasks = openTasks.stream()
                .filter(openTask -> !openTask.isCompleted())
                .toList();

        System.out.println(completedOpenTasks);

    }

}

package task2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


public class CommentReader {

    public static void main(String[] args) throws IOException, InterruptedException {
        int userID = 1;
        String postsURL = "https://jsonplaceholder.typicode.com/users/"+userID+"/posts";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(postsURL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
//        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();

        List <Post> posts = objectMapper.readValue(response.body(), new TypeReference<>() {});

        Optional<Integer> max = posts.stream()
                .map(post -> post.getId())
                .max(Integer::compareTo);

//        System.out.println(max.toString());

        String URL = "https://jsonplaceholder.typicode.com/posts/".concat(max.get().toString()).concat("/comments");

//        System.out.println(URL);


        HttpRequest secondRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> secondResponse =
                client.send(secondRequest, HttpResponse.BodyHandlers.ofString());


        List<Comment> comments = objectMapper.readValue(secondResponse.body(), new TypeReference<>() {});

        List<String> strings = comments.stream()
                .map(Comment -> Comment.getBody())
                .toList();

        System.out.println(strings);

        objectMapper.writeValue(new File("src/user-"+userID+"-post-"+max.get()
                +"-comments.json"),strings);

    }

}

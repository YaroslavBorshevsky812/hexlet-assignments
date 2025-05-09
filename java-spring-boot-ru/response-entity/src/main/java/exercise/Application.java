package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
    @Setter
    private static List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> result = posts.stream().limit(limit).toList();
        return ResponseEntity.ok()
                             .header("X-Total-Count", String.valueOf(posts.size()))
                             .body(result);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        return posts.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .map(post -> ResponseEntity.ok(post))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        return posts.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .map(post -> {
                        post.setTitle(data.getTitle());
                        post.setBody(data.getBody());
                        return ResponseEntity.ok(post);
                    })
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> destroy(@PathVariable String id) {
        boolean removed = posts.removeIf(p -> p.getId().equals(id));
        return removed
               ? ResponseEntity.ok("Post deleted successfully") // 200 OK
               : ResponseEntity.notFound().build(); // 404 Not Found
    }
    // END
}

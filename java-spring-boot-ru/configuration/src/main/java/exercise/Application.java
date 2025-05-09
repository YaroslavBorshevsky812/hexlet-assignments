package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import exercise.component.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;

@SpringBootApplication
@RestController
public class Application {
    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties userInfo;


    // BEGIN
    @GetMapping("/admins")
    public List<String> getAdmins() {
        List<String> adminEmails = userInfo.getAdmins();

        return users.stream()
                    .filter(user -> adminEmails.contains(user.getEmail()))
                    .sorted(Comparator.comparing(User::getName))
                    .map(User::getName)
                    .collect(Collectors.toList());
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });



        // BEGIN

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParam("id");
            User user =
                USERS.stream()
                         .filter(c -> id.equals(String.valueOf(c.getId())))
                         .findFirst()
                         .orElseThrow(
                             () -> new NotFoundResponse("User not found")
                         );

            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/users", ctx -> {
            List<User> users = USERS;
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

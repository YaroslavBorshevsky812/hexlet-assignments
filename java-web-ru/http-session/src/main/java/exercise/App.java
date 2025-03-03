package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN

        List<Map<String, String>> users = Data.getUsers();

        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
            var startIndex = (page - 1) * per;
            int endIndex = startIndex + per;

            if (startIndex >= users.size() || startIndex < 0) {
                ctx.status(404).result("Page not found");
                return;
            }

            endIndex = Math.min(endIndex, users.size());

            List<Map<String, String>> paginatedUsers = users.subList(startIndex, endIndex);

            ctx.json(paginatedUsers);
        });

        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

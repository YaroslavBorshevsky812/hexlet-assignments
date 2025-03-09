package exercise;

import io.javalin.Javalin;
import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), SessionsController::showMain);
        app.get(NamedRoutes.buildSessionPath(), SessionsController::showLogin);
        app.post(NamedRoutes.loginPath(), SessionsController::login);
        app.post(NamedRoutes.logoutPath(), SessionsController::logout);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}

package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

import java.util.Optional;

public class SessionsController {

    // BEGIN
    public static void showMain(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("user"));
        ctx.render("index.jte", model("page", page));
    }

    public static void showLogin(Context ctx) {
        var page = new LoginPage("Login", "");
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");

        Optional<User> user = UsersRepository.findByName(name);

        if (user.isPresent() && encrypt(password).equals(user.get().getPassword())) {
            ctx.sessionAttribute("user", user.get().getName());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            ctx.sessionAttribute("flash", "Wrong username or password");
            var page = new LoginPage("", "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }
    public static void logout(Context ctx) {
        ctx.sessionAttribute("user", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}

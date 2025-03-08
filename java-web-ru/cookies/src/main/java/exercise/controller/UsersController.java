package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

import java.util.Collections;

public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) {
        // Получаем id пользователя из URL
        var id = ctx.pathParamAsClass("id", Long.class).get();

        // Ищем пользователя в репозитории
        var user = UserRepository.find(id)
                                 .orElseThrow(() -> new NotFoundResponse("User not found"));

        // Получаем токен из куки
        var authToken = ctx.cookie("auth_token");

        // Проверяем, совпадает ли токен из куки с токеном пользователя
        if (authToken == null || !authToken.equals(user.getToken())) {
            // Если токены не совпадают, перенаправляем на страницу регистрации
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }

        // Если токены совпадают, отображаем данные пользователя
        var page = new UserPage(user);
        ctx.render("users/show.jte", Collections.singletonMap("page", page));
    }

    public static void createUser(Context ctx) {
        // Получаем данные из формы
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        // Генерируем токен
        var token = Security.generateToken();

        // Создаем пользователя
        var user = new User(firstName, lastName, email, password, token);

        // Сохраняем пользователя в репозитории
        UserRepository.save(user);

        // Устанавливаем токен в куки
        ctx.cookie("auth_token", token);

        // Перенаправляем на страницу пользователя
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }
    // END
}

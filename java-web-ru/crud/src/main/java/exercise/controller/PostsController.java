package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Collections;
import java.util.List;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                                        .orElseThrow(() -> new NotFoundResponse("Page not found"));



        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    public static void listPosts(Context ctx) {
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var pageSize = 5;
        var posts = PostRepository.findAll(pageNumber, pageSize);

        var totalPosts = PostRepository.getEntities().size();
        var totalPages = (int) Math.ceil((double) totalPosts / pageSize);
        var page = new PostsPage(posts, pageNumber, totalPages);

        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }
    // END
}

<!-- BEGIN -->
@import exercise.util.NamedRoutes
@import exercise.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        <header>
            <h1>My Blog</h1>
            <nav>
                <a href="${NamedRoutes.postsPath()}">All Posts</a>
                <a href="${NamedRoutes.buildPostPath()}">New Post</a>
            </nav>
        </header>
        <main>
            ${content}
        </main>
    </body>
</html>
<!-- END -->

@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes;
@param PostsPage page

@template.layout.page(
    content = @`
        <h1>Posts</h1>

        <ul>
            @for (var post : page.getPosts())
                <li><a href="${NamedRoutes.postPath(post.getId())}?page=${page.getCurrentPage()}">${post.getName()}</a></li>
            @endfor
        </ul>

        @if (page.getCurrentPage() > 1)
            <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() - 1}">Previous</a>
        @endif

        @if (page.getCurrentPage() < page.getTotalPages())
            <a href="${NamedRoutes.postsPath()}?page=${page.getCurrentPage() + 1}">Next</a>
        @endif

        @import exercise.dto.articles.ArticlesPage
        @param ArticlesPage page
    `
)
@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes

@param PostsPage page

@template.layout.page(
    content = @`
        <h2>All Posts</h2>
        @if(page != null && page.getFlash() != null)
                <p>${page.getFlash()}</p>
        @endif
        @if(page.getPosts().isEmpty())
            <p>No posts found.</p>
        @else
            <ul>
                @for(Post post : page.getPosts())
                    <li>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </li>
                @endfor
            </ul>
        @endif
    `
)
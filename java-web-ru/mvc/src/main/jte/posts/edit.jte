@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(
    content = @`
        <main>
            <div class="mx-auto p-4 py-md-5">
                <h1>Редактирование поста</h1>
            </div>

            <form action="${NamedRoutes.updatePostPath(page.getPost().getId())}" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Название</label>
                    <input type="text" class="form-control ${page.getErrors() != null ? "is-invalid" : ""}"
                           id="name" name="name" value="${page.getTitle()}">
                    @if (page.getErrors() != null)
                        <ul>
                           @for(var validator : page.getErrors().values())
                               @for(var error : validator)
                                   <li>${error.getMessage()}</li>
                               @endfor
                           @endfor
                       </ul>
                    @endif
                </div>

                <div class="mb-3">
                    <label for="body" class="form-label">Содержание</label>
                    <textarea class="form-control ${page.getErrors() != null ? "is-invalid" : ""}"
                              id="body" name="body">${page.getContent()}</textarea>
                    @if (page.getErrors() != null)
                        <ul>
                           @for(var validator : page.getErrors().values())
                               @for(var error : validator)
                                   <li>${error.getMessage()}</li>
                               @endfor
                           @endfor
                       </ul>
                    @endif
                </div>

                <button type="submit" class="btn btn-primary">Обновить пост</button>
            </form>
        </main>
    `
)
@import exercise.util.NamedRoutes

@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                  @if (page.getName() != null)
                      <h1>Welcome, ${page.getName().toString()}!</h1>
                      <form action="${NamedRoutes.logoutPath()}" method="post">
                          <button type="submit" class="btn btn-danger">Выйти</button>
                      </form>
                  @else
                      <a href="${NamedRoutes.buildSessionPath()}" class="btn btn-primary">Войти</a>
                  @endif
            </main>
        </div>
    `
)
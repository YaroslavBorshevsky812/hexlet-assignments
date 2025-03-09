@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage;
@param LoginPage page

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                <h1>${page.getName()}</h1>
                @if (page.getError() != "")
                    <div class="alert alert-danger">${page.getError()}</div>
                @endif
                <form action="${NamedRoutes.loginPath()}" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Username</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </main>
        </div>
    `
)
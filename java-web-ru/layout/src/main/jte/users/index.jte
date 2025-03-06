@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    header = @`
        <div>
            <h2><a href="/users">К списку</a></h2>
            <h2><a href="/">Главная</a></h2>
        </div>
    `,
    content = @`
        @for(var user : page.getUsers())
            <div>
                <a href="/users/${user.getId()}">${user.getFirstName()}</a>
                <p>${user.getLastName()}</p>
            </div>
        @endfor
    `,
    footer = @`
        <p>Thanks for visiting, come again soon!</p>
    `
)
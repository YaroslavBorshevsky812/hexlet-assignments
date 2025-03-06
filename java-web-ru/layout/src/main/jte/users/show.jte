@import exercise.dto.users.UserPage;
@param UserPage page

@template.layout.page(
    header = @`
        <div>
            <h2><a href="/users">К списку</a></h2>
            <h2><a href="/">Главная</a></h2>
        </div>
    `,
    content = @`
        <div>${page.getUser().getFirstName()}</div>
        <div>${page.getUser().getLastName()}</div>
        <div>${page.getUser().getEmail()}</div>
    `,
    footer = @`
            <p>Thanks for visiting, come again soon!</p>
    `
)
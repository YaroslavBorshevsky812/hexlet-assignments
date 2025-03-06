@import gg.jte.Content
@param Content content
@param Content header = null
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        @if (header != null)
                <div class="footer">
                    ${header}
                </div>
            @endif
        ${content}
        @if (footer != null)
                <div class="footer">
                    ${footer}
                </div>
            @endif
    </body>
</html>
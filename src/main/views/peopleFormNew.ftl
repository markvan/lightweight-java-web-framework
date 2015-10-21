<html>
    <head>
        <link rel="stylesheet" href="/style.css"/>
    </head>
    <body>
        <h2>Person details</h2>

        <form action="/people" method="post">
            <div>
                <label for="first_name">First name&nbsp;</label>
                <input type="text" id="first_name" />
            </div>
            <div>
                <label for="second_name">Second name&nbsp;</label>
                <input type="text" id="second_name" />
            </div>
            <div>
                <label for="profession">Profession&nbsp;</label>
                <input type="text" id="profession" />
            </div>
            <div class="button">
                <button type="submit">Create</button>
            </div>
        </form>

        <a href="/people" class=""button>Cancel</a>
    </body>
</html>



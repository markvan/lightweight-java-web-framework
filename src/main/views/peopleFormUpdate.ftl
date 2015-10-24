<html>
    <head>
        <link rel="stylesheet" href="/style.css"/>
    </head>
    <body>
        <h2>Person details</h2>

        <form action="/people/${person._id}" method="put">
            <div>
                <label for="first_name">First name&nbsp;</label>
                <input type="text" id="first_name" name="first_name" value="${person.first_name}" />
            </div>
            <div>
                <label for="second_name">Second name&nbsp;</label>
                <input type="text" id="second_name" name="second_name"  value="${person.second_name}" />
            </div>
            <div>
                <label for="profession">Profession&nbsp;</label>
                <input type="text" id="profession" name="profession"  value="${person.profession}" />
            </div>
            <div class="button">
                <button type="submit">Save</button>
            </div>
        </form>

        <a href="/people" class="button">Cancel</a>
    </body>
</html>




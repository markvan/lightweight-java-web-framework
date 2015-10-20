<html>
<head>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <h2>People we know about</h2>
    <#list people as person>
        <ul>
            <li>
                <a href="/people/${person._id}">${person.first_name} ${person.second_name}</a> &nbsp; &nbsp;
                <a href="/people/${person._id}/delete">X</a>
            </li>
        </ul>
        <#else>
            No people to list
    </#list>
</body>
</html>
<html>
<head>
    <link rel="stylesheet" href="/style.css"/>
</head>
<body>
    <h2>People we know about</h2>
    <#list people as person>
        <ul>
            <li>${person.first_name}</li>
        </ul>
        <#else>
            No people to list
    </#list>
</body>
</html>
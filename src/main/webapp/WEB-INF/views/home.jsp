<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" errorPage="error.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Jokes</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <div class="container">
        <br/>
        <h3>Your Chuck Norris Joke:</h3>
        <p>
            ${showResult}
        </p>

        <form><input type="button" value="Next Joke" onClick="window.location.reload()"></form>

        <hr/>
        Powered by <a href="http://www.icndb.com/">The Internet Chuck Norris Database</a>
    </div>
</body>
</html>

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
        <p class="lead font-italic">
            <c:out value="${not empty showResult ? showResult : 'Chuck Norris is currently busy saving the world. Please try again.'}" />
        </p>


        <form>
            <button type="button" class="btn btn-primary" onClick="window.location.reload()">
                Next Joke
            </button>
        </form>


        <hr/>
        Powered by <a href="https://api.chucknorris.io/">chucknorris.io</a>
    </div>
</body>
</html>

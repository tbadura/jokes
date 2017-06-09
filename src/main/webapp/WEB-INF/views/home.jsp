<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<h3>Your Chuck Norris Joke:</h3>
<p>
    ${showResult}
</p>

<form><input type="button" value="Next Joke" onClick="window.location.reload()"></form>

<hr/>
Powered by <a href="http://www.icndb.com/">The Internet Chuck Norris Database</a>

</body>
</html>

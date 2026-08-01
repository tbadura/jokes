<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%-- JSTL taglibs --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    Logger logger = LoggerFactory.getLogger("error.jsp");

    String errorMessage = "";
    if (exception != null) {
        errorMessage = exception.getMessage();
        if (exception.getCause() != null) {
            errorMessage = exception.getCause().getMessage();
        }
        logger.error("Exception encountered in application: " + errorMessage, exception);
    }

    pageContext.setAttribute("errorMessage", errorMessage);
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Error Page</title>
</head>
<body>

<p>An error has occurred in the application: <c:out value="${errorMessage}"/>.</p>

</body>
</html>
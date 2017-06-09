<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.util.Arrays" %>

<%-- JSTL taglibs --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Logger logger = LoggerFactory.getLogger(this.getClass());

    String errorMessage = "";
    if (exception != null) {
        errorMessage = exception.getMessage();
        if (exception.getCause() != null) {
          //  errorMessage = exception.toString();
            errorMessage = exception.getCause().getMessage();
          //  errorMessage = Arrays.toString(exception.getStackTrace());
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


    <p>An error has occurred in the application: ${errorMessage}.
    </p>


</body>
</html>
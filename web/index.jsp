<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title></title>
  </head>
  <body>
    <%--<jsp:forward page="jsp/welcome.jsp" />--%>
    <p>Index</p>
    <form action="controller/register" method="post" >
        <button type="submit">
            <fmt:message key="login.button.txt" bundle="${welcome}" />
            Test
        </button>
    </form>
  </body>
</html>

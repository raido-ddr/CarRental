<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.welcome" var="welcome" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${welcome}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link rel="icon" href="<c:url value="/favicon.ico" />"  type="image/x-icon"  />
</head>
<body>
<div>

   <%-- <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <fmt:message key="brand" bundle="${welcome}" />
            </a>
        </div>
        <ul class="dropdown">
            <li>
                <a href="/controller/changeLocale?locale=ru-RU">
                    <fmt:message key="en.button.txt" bundle="${welcome}" />
                </a>
            </li>
            <li>
                <a href="/controller/changeLocale?locale=en-GB">
                    <fmt:message key="ru.button.txt" bundle="${welcome}" />
                </a>
            </li>
        </ul>
    </div>--%>

    <form action="/controller/authorize" method="get" >
        <button type="submit">
            <fmt:message key="login.button.txt" bundle="${welcome}" />
        </button>
    </form>

    <form action="/controller/register" method="get" >
            <button type="submit">
                <fmt:message key="register.button.txt" bundle="${welcome}" />
            </button>
    </form>





    <form action="/controller/changeLocale" method="get" >
        <input type="hidden" name="locale" value="en-GB" />
        <button type="submit">
            <fmt:message key="en.button.txt" bundle="${welcome}" />
        </button>
    </form>


    <form action="/controller/changeLocale" method="get" >
        <input type="hidden" name="locale" value="ru-RU" />
        <button type="submit">
            <fmt:message key="ru.button.txt" bundle="${welcome}" />
        </button>
    </form>

<%--    <form action="/controller/changeLocale?locale=ru-RU" method="get" >
        <button type="submit">
            <fmt:message key="ru.button.txt" bundle="${welcome}" />
        </button>
    </form>--%>

</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.welcome" var="welcome" />
<fmt:setBundle basename="l10n.navbar" var="navbar" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${welcome}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
    <link rel="icon" href="<c:url value="/favicon.ico" />"  type="image/x-icon"  />
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<div>


    <div class="navbar navbar-inverse navbar-fixed-top"  role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <fmt:message key="brand" bundle="${navbar}" />
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="language" bundle="${navbar}" />
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/controller/changeLocale?locale=ru-RU">
                                <fmt:message key="ru.button.txt" bundle="${navbar}" />
                            </a>
                        </li>
                        <li>
                            <a href="/controller/changeLocale?locale=en-GB">
                                <fmt:message key="en.button.txt" bundle="${navbar}" />
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <form class="navbar-form navbar-left" action="/controller/authorize" method="get" >
                        <button class="btn btn-success" type="submit">
                            <fmt:message key="login.button.txt" bundle="${navbar}" />
                        </button>
                    </form>
                </li>
                <li>
                    <form class="navbar-form navbar-left" action="/controller/register" method="get" >
                        <button class="btn btn-info" type="submit">
                            <fmt:message key="register.button.txt" bundle="${navbar}" />
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </div>

    <div class="jumbotron" id="welcome_jumbotron">
        <div class="container">



        </div>
    </div>



<%--    <form action="/controller/changeLocale?locale=ru-RU" method="get" >
        <button type="submit">
            <fmt:message key="ru.button.txt" bundle="${welcome}" />
        </button>
    </form>--%>

</div>
</body>
</html>

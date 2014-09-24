<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setBundle basename="l10n.navbar" var="navbar"/>

<html>

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
                <a href="/jsp/adminMain.jsp" role="button">
                    <fmt:message key="home" bundle="${navbar}" />
                </a>
            </li>
            <li>
                <form class="navbar-form navbar-left" action="/controller/logout" method="get" >
                    <button class="btn btn-warning" type="submit">
                        <fmt:message key="logout.button.txt" bundle="${navbar}" />
                    </button>
                </form>
            </li>
        </ul>
    </div>
</div>

</html>

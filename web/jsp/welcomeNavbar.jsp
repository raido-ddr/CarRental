<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="l10n.common_captions" var="navbar"/>

<html>

    <div class="navbar navbar-inverse navbar-fixed-top"  role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/jsp/welcome.jsp">
                <fmt:message key="nv.brand" bundle="${navbar}" />
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="nv.language" bundle="${navbar}" />
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/controller/changeLocale?locale=ru-RU">
                                <fmt:message key="nv.ru.button.txt" bundle="${navbar}" />
                            </a>
                        </li>
                        <li>
                            <a href="/controller/changeLocale?locale=en-GB">
                                <fmt:message key="nv.en.button.txt" bundle="${navbar}" />
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <form class="navbar-form navbar-left" action="/controller/authorize" method="get" >
                        <button class="btn btn-success" type="submit">
                            <fmt:message key="nv.login.button.txt" bundle="${navbar}" />
                        </button>
                    </form>
                </li>
                <li>
                    <form class="navbar-form navbar-left" action="/controller/register" method="get" >
                        <button class="btn btn-info" type="submit">
                            <fmt:message key="nv.register.button.txt" bundle="${navbar}" />
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </div>

</html>

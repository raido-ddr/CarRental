<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language
    ? param.language
    : not empty language
        ? language
        : pageContext.request.locale}" scope="session" />

<fmt:requestEncoding value="UTF-8" />

<fmt:setBundle basename="l10n.welcome" var="welcome" />
<fmt:setLocale value="${language}" />

<html>
<head>
    <title><fmt:message key="title" bundle="${welcome}" /></title>
</head>
<body>
<div>
    <%--<h3><fmt:message key="title" bundle="${welcome}" /> </h3>--%>

    <form action="controller/login" method="post" >
        <button type="submit">
            <fmt:message key="login.button.txt" bundle="${welcome}" />
        </button>
    </form>

    <p><button type="submit" formaction="controller/register" name="registerButton">
        <fmt:message key="register.button.txt" bundle="${welcome}" />
    </button> </p>
    <p><button type="submit" formaction="controller/changeLocale/en" name="ruLocaleButton" value="RU">
        <fmt:message key="ru.button.txt" bundle="${welcome}" />
    </button> </p>

    <p><button type="submit" formaction="controller/changeLocale/ru" name="enLocaleButton" value="EN">
        <fmt:message key="en.button.txt" bundle="${welcome}" />
    </button> </p>
</div>
</body>
</html>

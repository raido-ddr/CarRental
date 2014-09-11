<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.welcome" var="welcome" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${welcome}" /></title>
</head>
<body>
<c:out value="${sessionScope.locale}" />
<div>
    <%--<h3><fmt:message key="title" bundle="${welcome}" /> </h3>--%>

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

    <form action="/controller/changeLocale?locale=en-GB" method="post" >
        <button type="submit">
            <fmt:message key="en.button.txt" bundle="${welcome}" />
        </button>
    </form>

    <form action="/controller/changeLocale?locale=ru-RU" method="post" >
        <button type="submit">
            <fmt:message key="ru.button.txt" bundle="${welcome}" />
        </button>
    </form>

</div>
</body>
</html>

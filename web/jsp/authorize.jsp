<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />

<fmt:setBundle basename="l10n.authorize" var="authorize" />
<fmt:setLocale value="${sessionScope.locale}" />

<html>
<head>
    <title><fmt:message key="title" bundle="${authorize}" /></title>
</head>
<body>

    <div><c:out value="${authorizationError}" /> </div>

    <form name="loginForm" action="/controller/authorize" method="post" >
        <label for="login">
            <fmt:message key="enter.login" bundle="${authorize}" />
        </label>
        <input id="login" type="text" name="login">
        <br/>
        <div><c:out value="${loginError}" /></div>
        <br/>

        <label for="password">
            <fmt:message key="enter.password" bundle="${authorize}" />
        </label>
        <input id="password" type="text" name="password">
        <br/>
        <div><c:out value="${loginError}" /></div>
        <br/>

        <button type="submit" formmethod="post">
            <fmt:message key="login.button.txt" bundle="${authorize}"/>
        </button>
    </form>

    <br/>
    <button type="submit" formaction="/controller/back">
        <fmt:message key="back.button.txt" bundle="${authorize}" />
    </button>


</body>
</html>

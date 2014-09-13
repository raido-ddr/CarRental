<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="l10n.authorize" var="authorize" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${authorize}" /></title>
</head>
<body>

    <c:out value="${locale}" />
    <div><fmt:message key="title" bundle="${authorize}" /></div>
    <div><c:out value="${authorizationError}" /> </div>

    <form name="loginForm" action="/controller/authorize" method="post" >

        <input id="login" type="text" name="login"
               placeholder="<fmt:message key="enter.login" bundle="${authorize}" />" />
        <br/>
        <div><c:out value="${loginError}" /></div>
        <br/>

        <input id="password" type="password" name="password"
               placeholder="<fmt:message key="enter.password" bundle="${authorize}" />" />
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

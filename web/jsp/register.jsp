<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.register" var="register" />
<fmt:setBundle basename="input_errors" var="errors" />
<fmt:setBundle basename="html_regex" var="regex" />



<html>
<head>
    <title><fmt:message key="page.title" bundle="${register}" /></title>
</head>
<body>
    <div><title><fmt:message key="title" bundle="${register}" /></title></div>

    <form name="registerForm" action="/controller/register" method="post">
        <label for="fName">
            <fmt:message key="enter.fname" bundle="${register}" />
        </label>
        <input id="fName" type="text" name="firstName"
               pattern="<fmt:message key="first.name.regex" bundle="${regex}" />"
               title="<fmt:message key="first.name.rule" bundle="${errors}" />"
               value="${param.firstName}"/>
        <br />
        <div><c:out value="${fnameRule}" /></div>
        <br />

        <label for="lName">
            <fmt:message key="enter.lname" bundle="${register}" />
        </label>
        <input id="lName" type="text" name="lastName"
               pattern="<fmt:message key="last.name.regex" bundle="${regex}" />"
               title="<fmt:message key="last.name.rule" bundle="${errors}" />"
               value="${param.lastName} "/>
        <br />
        <div><c:out value="${lnameRule}" /></div>
        <br />

        <label for="login">
            <fmt:message key="enter.login" bundle="${register}" />
        </label>
        <input id="login" type="text" name="login"
               pattern="<fmt:message key="login.regex" bundle="${regex}" />"
               title="<fmt:message key="login.rule" bundle="${errors}" />"
               value="${param.login} "/>
        <br />
        <div><c:out value="${loginRule}" /></div>
        <br />

        <label for="password">
            <fmt:message key="enter.password" bundle="${register}" />
        </label>
        <input id="password" type="text" name="password"
               pattern="<fmt:message key="password.regex" bundle="${regex}" />"
               title="<fmt:message key="password.rule" bundle="${errors}" />"
               value="${param.password} "/>
        <br />
        <div><c:out value="${passwordRule}" /></div>
        <br />

        <label for="email">
            <fmt:message key="enter.email" bundle="${register}" />
        </label>
        <input id="email" type="text" name="email"
               pattern="<fmt:message key="email.regex" bundle="${regex}" />"
               title="<fmt:message key="email.rule" bundle="${errors}" />"
               value="${param.email}" />
        <br />
        <div><c:out value="${emailRule}" /></div>
        <br />

        <label for="dob">
            <fmt:message key="enter.dob" bundle="${register}" />
        </label>
        <input id="dob" type="date" name="dob" value="${param.dob}" />
        <br />
        <div><c:out value="${dobRule}" /></div>
        <br />

        <label for="passport">
            <fmt:message key="enter.passport" bundle="${register}" />
        </label>
        <input id="passport" type="text" name="passport"
               pattern="<fmt:message key="passport.regex" bundle="${regex}" />"
               title="<fmt:message key="passport.rule" bundle="${errors}" />"
               value="${param.passport}" />
        <br />
        <div><c:out value="${passportRule}" /></div>
        <br />

        <label for="license.expiry">
            <fmt:message key="enter.license.expiry" bundle="${register}" />
        </label>
        <input id="license.expiry" type="date" name="licenseExpiry"
                value="${param.licenseExpiry}"/>
        <br />
        <div><c:out value="${licenseExpiryRule}" /></div>
        <br />

        <button type="submit" formmethod="post">
            <fmt:message key="register.button.txt" bundle="${register}"/>
        </button>
    </form>

</body>
</html>

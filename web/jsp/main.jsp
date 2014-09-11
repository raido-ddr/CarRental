<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="l10n.main" var="main" />
<fmt:setBundle basename="input_errors" var="errors" />



<html>
<head>
    <title></title>
</head>
<body>
    <p>Main</p>
    <div><c:out value="${currentUser.login}" /></div>
    <div><c:out value="${sessionScope.locale}" /></div>

</body>
</html>

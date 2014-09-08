<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />

<fmt:setBundle basename="l10n.main" var="main" />
<fmt:setBundle basename="input_errors" var="errors" />

<fmt:setLocale value="${sessionScope.locale}" />

<html>
<head>
    <title></title>
</head>
<body>
<p>Error</p>
    <div><c:out value="${pageContext.errorData.statusCode}" />: </div>
    <div><c:out value="${pageContext.exception.message}" /></div>
</body>
</html>

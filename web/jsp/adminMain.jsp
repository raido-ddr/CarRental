<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_main" var="chooseCar"/>

<html>
<head>
    <title><fmt:message key="page.title" bundle="${chooseCar}" /></title>
</head>
<body>
<div>
    <div><c:out value="${successMessage}" /></div>

    <form action="/controller/addCar" method="get" >
        <button type="submit">
            <fmt:message key="add.button.txt" bundle="${chooseCar}" />
        </button>
    </form>

    <form action="/controller/viewAllCars" method="get" >
        <button type="submit">
            <fmt:message key="view.button.txt" bundle="${chooseCar}" />
        </button>
    </form>

</div>
</body>
</html>

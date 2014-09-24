<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.place_order" var="placeOrder"/>

<html>
<head>
    <title><fmt:message key="page.title" bundle="${placeOrder}" /></title>
</head>
<body>

    <div>
        <div><fmt:message key="page.title" bundle="${placeOrder}" /></div>
    </div>

    <div>
        <div>
            <strong><fmt:message key="caption.car" bundle="${placeOrder}" /></strong>
            <div><c:out value="${param.make} ${param.model}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.start.date" bundle="${placeOrder}" /></strong>
            <div><c:out value="${param.startDate}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.return.date" bundle="${placeOrder}" /></strong>
            <div><c:out value="${param.returnDate}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.value" bundle="${placeOrder}" /></strong>
            <div><c:out value="${orderValue}" /></div>
        </div>
    </div>

    <form name="submitOrderForm" action="/controller/placeOrder" method="post">
        <input type="hidden" name="carId" value="${param.carId}">
        <input type="hidden" name="startDate" value="${param.startDate}">
        <input type="hidden" name="returnDate" value="${param.returnDate}">
        <input type="hidden" name="orderValue" value="${orderValue}">
        <input type="hidden" name="status" value="new" />
        <button type="submit">
            <fmt:message key="submit.button.txt" bundle="${placeOrder}"/>
        </button>
    </form>



</body>
</html>

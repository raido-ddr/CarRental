<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_view_cars" var="main"/>

<html>
<head>
    <title>
        <fmt:message key="page.title" bundle="${main}" />
    </title>
</head>
<body>

<div>
    <fmt:message key="page.title" bundle="${main}" />
</div>

<c:forEach items="${cars}" var="car">
    <div>
        <div>
            <strong><fmt:message key="caption.make" bundle="${main}" /></strong>
            <c:out value="${car.make}" />
        </div>
        <div>
            <strong><fmt:message key="caption.model" bundle="${main}" /></strong>
            <c:out value="${car.model}" />
        </div>
        <div>
            <strong><fmt:message key="caption.mileage" bundle="${main}" /></strong>
            <c:out value="${car.mileage}" />
        </div>
        <div>
            <strong><fmt:message key="caption.power" bundle="${main}" /></strong>
            <c:out value="${car.power}" />
        </div>
        <div>
            <strong><fmt:message key="caption.fuel.type" bundle="${main}" /></strong>
            <c:out value="${car.fuelType.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.transmission" bundle="${main}" /></strong>
            <c:out value="${car.transmissionType.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.body.style" bundle="${main}" /></strong>
            <c:out value="${car.bodyStyle.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.seat.count" bundle="${main}" /></strong>
            <c:out value="${car.seatCount}" />
        </div>
        <div>
            <strong><fmt:message key="caption.daily.cost" bundle="${main}" /></strong>
            <c:out value="${car.dailyCost}" />
        </div>
        <div>
            <strong><fmt:message key="caption.status" bundle="${main}" /></strong>
            <c:out value="${car.status.value}" />
        </div>
        <div>
            <form name="chooseCarForm" action="/controller/placeOrder" method="get" >
                <input type="hidden" name="carId" value="${car.id}" />
                <input type="hidden" name="make" value="${car.make}" />
                <input type="hidden" name="model" value="${car.model}" />
                <input type="hidden" name="dailyCost" value="${car.dailyCost}" />
                <input type="hidden" name="startDate" value="${startDate}" />
                <input type="hidden" name="returnDate" value="${returnDate}" />

                <button type="submit">
                    <fmt:message key="order.button.txt" bundle="${main}" />
                </button>
            </form>
        </div>
    </div>
    <br />
</c:forEach>


</body>
</html>

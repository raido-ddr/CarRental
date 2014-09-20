<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_view_cars" var="chooseCar"/>

<html>
<head>
    <title>
        <fmt:message key="page.title" bundle="${chooseCar}" />
    </title>
</head>
<body>

<div>
    <fmt:message key="page.title" bundle="${chooseCar}" />
</div>

<c:forEach items="${cars}" var="car">
    <div>
        <div>
            <strong><fmt:message key="caption.make" bundle="${chooseCar}" /></strong>
            <c:out value="${car.make}" />
        </div>
        <div>
            <strong><fmt:message key="caption.model" bundle="${chooseCar}" /></strong>
            <c:out value="${car.model}" />
        </div>
        <div>
            <strong><fmt:message key="caption.mileage" bundle="${chooseCar}" /></strong>
            <c:out value="${car.mileage}" />
        </div>
        <div>
            <strong><fmt:message key="caption.power" bundle="${chooseCar}" /></strong>
            <c:out value="${car.power}" />
        </div>
        <div>
            <strong><fmt:message key="caption.fuel.type" bundle="${chooseCar}" /></strong>
            <c:out value="${car.fuelType.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.transmission" bundle="${chooseCar}" /></strong>
            <c:out value="${car.transmissionType.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.body.style" bundle="${chooseCar}" /></strong>
            <c:out value="${car.bodyStyle.value}" />
        </div>
        <div>
            <strong><fmt:message key="caption.seat.count" bundle="${chooseCar}" /></strong>
            <c:out value="${car.seatCount}" />
        </div>
        <div>
            <strong><fmt:message key="caption.daily.cost" bundle="${chooseCar}" /></strong>
            <c:out value="${car.dailyCost}" />
        </div>
        <div>
            <strong><fmt:message key="caption.status" bundle="${chooseCar}" /></strong>
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
                    <fmt:message key="order.button.txt" bundle="${chooseCar}" />
                </button>
            </form>
        </div>
    </div>
    <br />
</c:forEach>


</body>
</html>

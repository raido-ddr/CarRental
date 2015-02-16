<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_captions" var="viewCars"/>

<html>
<head>
    <title>
        <fmt:message key="vc.page.title" bundle="${viewCars}" />
        <link href="<c:url value="/css/bootstrap.min.css" />"
              rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
              rel="stylesheet" type="text/css" />
        <link href="<c:url value="/css/bootstrap-theme.min.css" />"
              rel="stylesheet" type="text/css" />
        <link href="/css/custom_style.css"
              rel="stylesheet" type="text/css" />
        <script src="/js/jquery-1.9.1.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
    </title>
</head>

<body>
<%@include file="userNavbar.jsp"%>

<div>
    <fmt:message key="vc.page.title" bundle="${viewCars}" />
</div>

<c:forEach items="${cars}" var="car">
    <div>
        <div>
            <strong><fmt:message key="vc.caption.make" bundle="${viewCars}" /></strong>
            <c:out value="${car.make}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.model" bundle="${viewCars}" /></strong>
            <c:out value="${car.model}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.mileage" bundle="${viewCars}" /></strong>
            <c:out value="${car.mileage}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.power" bundle="${viewCars}" /></strong>
            <c:out value="${car.power}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.fuel.type" bundle="${viewCars}" /></strong>
            <c:out value="${car.fuelType.value}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.transmission" bundle="${viewCars}" /></strong>
            <c:out value="${car.transmissionType.value}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.body.style" bundle="${viewCars}" /></strong>
            <c:out value="${car.bodyStyle.value}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.seat.count" bundle="${viewCars}" /></strong>
            <c:out value="${car.seatCount}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.daily.cost" bundle="${viewCars}" /></strong>
            <c:out value="${car.dailyCost}" />
        </div>
        <div>
            <strong><fmt:message key="vc.caption.status" bundle="${viewCars}" /></strong>
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
                    <fmt:message key="vc.order.button.txt" bundle="${viewCars}" />
                </button>
            </form>
        </div>
    </div>
    <br />
</c:forEach>


</body>
</html>

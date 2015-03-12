<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cstl" uri="customtaglib" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_captions" var="viewCars"/>

<html>
<head>

    <title><fmt:message key="vc.page.title" bundle="${viewCars}" /></title>
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

</head>

<body>
<%@include file="userNavbar.jsp"%>

<div>
    <fmt:message key="vc.page.title" bundle="${viewCars}" />
</div>

<div class="container col-md-4 col-md-offset-4 " >
    <div class="panel col-md-4">
        <div class="panel-body">
            <c:forEach items="${cars}" var="car">

                <div class="car_description">
                    <cstl:car-description subject="${car}" />

                    <div>
                        <form name="chooseCarForm" action="/controller/placeOrder" method="get" >
                            <input type="hidden" name="carId" value="${car.id}" />
                            <input type="hidden" name="make" value="${car.make}" />
                            <input type="hidden" name="model" value="${car.model}" />
                            <input type="hidden" name="dailyCost" value="${car.dailyCost}" />
                            <input type="hidden" name="startDate" value="${startDate}" />
                            <input type="hidden" name="returnDate" value="${returnDate}" />
                            <button type="submit" role="button" class="btn btn-info">
                                <fmt:message key="vc.order.button.txt" bundle="${viewCars}" />
                            </button>
                        </form>
                    </div>
                </div>
                <br />
            </c:forEach>
        </div>
    </div>
</div>


</body>
</html>

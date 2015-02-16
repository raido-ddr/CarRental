<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_captions" var="main" />

<html>
<head>
    <title>
        <fmt:message key="vc.page.title" bundle="${main}" />
    </title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>

<body>

<%@include file="adminNavbar.jsp"%>

<div class="container col-md-4 col-md-offset-4 " >
    <div class="panel col-md-4 form-background">
        <div class="panel-body">
            <c:forEach items="${cars}" var="car">
                <table class="table-bordered">
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.make" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.make}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.model" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.model}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.mileage" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.mileage}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.power" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.power}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.fuel.type" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.fuelType.value}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.transmission" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.transmissionType.value}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.body.style" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.bodyStyle.value}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.seat.count" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.seatCount}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.daily.cost" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.dailyCost}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <dl class="dl-horizontal">
                                <dt>
                                    <fmt:message key="vc.caption.status" bundle="${main}" />
                                </dt>
                                <dd>
                                    <c:out value="${car.status.value}" />
                                </dd>
                            </dl>
                        </td>
                    </tr>
                </table>
                <div>
                    <form name="editActionForm" action="/controller/editCar" method="get" >
                        <input type="hidden" name="carId" value="${car.id}" />
                        <button type="submit">
                            <fmt:message key="vc.edit.button.txt" bundle="${main}" />
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>

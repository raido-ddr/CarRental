<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.edit_car" var="editCar" />
<fmt:setBundle basename="input_errors" var="errors" />
<fmt:setBundle basename="html_regex" var="regex" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${editCar}" /></title>
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

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-4">
            <div class="panel panel-default" id="auth-panel-default">
                <div class="panel-heading">
                    <fmt:message key="page.title" bundle="${editCar}" />
                </div>

                <div class="panel-body">

                    <form role="form" class="form-horizontal" name="addCarForm"
                          action="/controller/editCar" method="post" >

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="make">
                                <fmt:message key="enter.make" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="make" type="text" name="make"
                                   pattern="<fmt:message key="make.regex" bundle="${regex}" />"
                                   title="<fmt:message key="make.rule" bundle="${errors}" />"
                                   value="${car.make}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty makeRule}" >
                                <div class="alert alert-warning"><c:out value="${makeRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="model">
                                <fmt:message key="enter.model" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="model" type="text" name="model"
                                   pattern="<fmt:message key="model.regex" bundle="${regex}" />"
                                   title="<fmt:message key="model.rule" bundle="${errors}" />"
                                   value="${car.model}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty modelRule}" >
                                <div class="alert alert-warning"><c:out value="${modelRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="mileage">
                                <fmt:message key="enter.mileage" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="mileage" type="text" name="mileage"
                                   pattern="<fmt:message key="mileage.regex" bundle="${regex}" />"
                                   title="<fmt:message key="mileage.rule" bundle="${errors}" />"
                                   value="${car.mileage}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty mileageRule}" >
                                <div class="alert alert-warning"><c:out value="${mileageRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="power">
                                <fmt:message key="enter.power" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="power" type="text" name="power"
                                   pattern="<fmt:message key="power.regex" bundle="${regex}" />"
                                   title="<fmt:message key="power.rule" bundle="${errors}" />"
                                   value="${car.power}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty powerRule}" >
                                <div class="alert alert-warning"><c:out value="${powerRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="fuelType">
                                <fmt:message key="enter.fuel.type" bundle="${editCar}" />
                            </label>
                            <select class="col-sm-7" id="fuelType" name="fuelType">
                                <c:forEach var="type" items="${fuelTypes}">
                                    <option value="<c:out value='${type}' />"
                                            <c:if test="${car.fuelType.value == type})"> selected </c:if>  >
                                        <c:out value="${type}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="transmissionType">
                                <fmt:message key="enter.transmission" bundle="${editCar}" />
                            </label>
                            <select class="col-sm-7" id="transmissionType" name="transmissionType">
                                <c:forEach var="type" items="${transmissionTypes}">
                                    <option value="<c:out value='${type}' />"
                                            <c:if test="${car.transmissionType.value == type})"> selected </c:if>  >
                                        <c:out value="${type}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="bodyStyle">
                                <fmt:message key="enter.body.style" bundle="${editCar}" />
                            </label>
                            <select class="col-sm-7" id="bodyStyle" name="bodyStyle">
                                <c:forEach var="style" items="${bodyStyles}">
                                    <option value="<c:out value='${style}' />"
                                            <c:if test="${car.bodyStyle.value == style})"> selected </c:if>  >
                                        <c:out value="${style}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="seatCount">
                                <fmt:message key="enter.seat.count" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="seatCount" type="text" name="seatCount"
                                   pattern="<fmt:message key="seat.count.regex" bundle="${regex}" />"
                                   title="<fmt:message key="seat.count.rule" bundle="${errors}" />"
                                   value="${car.seatCount}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty seatCountRule}" >
                                <div class="alert alert-warning"><c:out value="${seatCountRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="dailyCost">
                                <fmt:message key="enter.daily.cost" bundle="${editCar}" />
                            </label>
                            <input class="col-sm-7" id="dailyCost" type="text" name="dailyCost"
                                   pattern="<fmt:message key="daily.cost.regex" bundle="${regex}" />"
                                   title="<fmt:message key="daily.cost.rule" bundle="${errors}" />"
                                   value="${car.dailyCost}"/>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty dailyCostRule}" >
                                <div class="alert alert-warning"><c:out value="${dailyCostRule}" /></div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="status">
                                <fmt:message key="enter.status" bundle="${editCar}" />
                            </label>
                            <select class="col-sm-7" id="status" name="status">
                                <c:forEach var="status" items="${statusOptions}">
                                    <option value="<c:out value='${status}' />"
                                            <c:if test="${car.status.value == status})"> selected </c:if>  >
                                        <c:out value="${status}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <input type="hidden" name="carId" value="${car.id}" />

                        <div class="form-group last">
                            <div class="col-sm-offset-2 col-sm-9">
                                <button type="submit" class="btn btn-info btn-sm">
                                    <fmt:message key="confirm.button.txt" bundle="${editCar}"/>
                                </button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>

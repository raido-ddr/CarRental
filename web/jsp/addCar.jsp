<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.add_car" var="addCar" />
<fmt:setBundle basename="input_errors" var="errors" />
<fmt:setBundle basename="html_regex" var="regex" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${addCar}" /></title>
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

<body id="add-car">
    <%@include file="adminNavbar.jsp"%>

    <div class="container col-md-4 col-md-offset-4 " >
        <div class="panel col-md-4 form-background">
            <div class="panel-body">
                <form role="form" class="form-horizontal" name="addCarForm"
                      action="/controller/addCar" method="post" >

                    <div class="form-group">
                        <label class="control-label" for="make">
                            <fmt:message key="enter.make" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="make" type="text" name="make"
                               pattern="<fmt:message key="make.regex" bundle="${regex}" />"
                               title="<fmt:message key="make.rule" bundle="${errors}" />"
                               value="${param.make} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${makeRule}" /></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="model">
                            <fmt:message key="enter.model" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="model" type="text" name="model"
                               pattern="<fmt:message key="model.regex" bundle="${regex}" />"
                               title="<fmt:message key="model.rule" bundle="${errors}" />"
                               value="${param.model} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${modelRule}" /></div>
                    </div>


                    <div class="form-group">
                        <label class="control-label" for="mileage">
                            <fmt:message key="enter.mileage" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="mileage" type="text" name="mileage"
                               pattern="<fmt:message key="mileage.regex" bundle="${regex}" />"
                               title="<fmt:message key="mileage.rule" bundle="${errors}" />"
                               value="${param.mileage} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${mileageRule}" /></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="power">
                            <fmt:message key="enter.power" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="power" type="text" name="power"
                               pattern="<fmt:message key="power.regex" bundle="${regex}" />"
                               title="<fmt:message key="power.rule" bundle="${errors}" />"
                               value="${param.power} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${powerRule}" /></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="fuelType">
                            <fmt:message key="enter.fuel.type" bundle="${addCar}" />
                        </label>
                        <select class="form-control" id="fuelType" name="fuelType">
                            <c:forEach var="type" items="${fuelTypes}">
                                <option value="<c:out value='${type}' />"
                                        <c:if test="${param.fuelType == type})"> selected </c:if>  >
                                    <c:out value="${type}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label class="control-label" for="transmissionType">
                            <fmt:message key="enter.transmission" bundle="${addCar}" />
                        </label>
                        <select class="form-control" id="transmissionType" name="transmissionType">
                            <c:forEach var="type" items="${transmissionTypes}">
                                <option value="<c:out value='${type}' />"
                                        <c:if test="${param.transmissionType == type})"> selected </c:if>  >
                                    <c:out value="${type}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label class="control-label" for="bodyStyle">
                            <fmt:message key="enter.body.style" bundle="${addCar}" />
                        </label>
                        <select class="form-control" id="bodyStyle" name="bodyStyle">
                            <c:forEach var="style" items="${bodyStyles}">
                                <option value="<c:out value='${style}' />"
                                        <c:if test="${param.bodyStyle == style})"> selected </c:if>  >
                                    <c:out value="${style}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="seatCount">
                            <fmt:message key="enter.seat.count" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="seatCount" type="text" name="seatCount"
                               pattern="<fmt:message key="seat.count.regex" bundle="${regex}" />"
                               title="<fmt:message key="seat.count.rule" bundle="${errors}" />"
                               value="${param.seatCount} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${seatCountRule}" /></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="dailyCost">
                            <fmt:message key="enter.daily.cost" bundle="${addCar}" />
                        </label>
                        <input class="form-control" id="dailyCost" type="text" name="dailyCost"
                               pattern="<fmt:message key="daily.cost.regex" bundle="${regex}" />"
                               title="<fmt:message key="daily.cost.rule" bundle="${errors}" />"
                               value="${param.dailyCost} "/>
                        <br />
                        <div class="alert-warning"><c:out value="${dailyCostRule}" /></div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="status">
                            <fmt:message key="enter.status" bundle="${addCar}" />
                        </label>
                        <select class="form-control" id="status" name="status">
                            <c:forEach var="status" items="${statusOptions}">
                                <option value="<c:out value='${status}' />"
                                        <c:if test="${param.status == status})"> selected </c:if>  >
                                    <c:out value="${status}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button role="button" class="btn btn-info" type="submit" formmethod="post">
                        <fmt:message key="add.button.txt" bundle="${addCar}"/>
                    </button>
                </form>
            </div>
        </div>

    </div>


</body>
</html>

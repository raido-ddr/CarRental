<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.choose_rental_period" var="rentalPeriod" />
<fmt:setBundle basename="input_errors" var="errors" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${rentalPeriod}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default" id="auth-panel-default">
                    <div class="panel-heading"><fmt:message key="choose.period" bundle="${rentalPeriod}" /></div>

                    <div class="panel-body">

                        <form class="form-horizontal" role="form" name="rentalPeriodForm"
                              action="/controller/chooseRentalPeriod" method="post">

                            <div class="form-group">
                                <label for="startDate" class="col-sm-4 control-label">
                                    <fmt:message key="enter.start.date" bundle="${rentalPeriod}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="startDate" type="date" name="startDate" value="${param.startDate}" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="returnDate" class="col-sm-4 control-label">
                                    <fmt:message key="enter.return.date" bundle="${rentalPeriod}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="returnDate" type="date" name="returnDate" value="${param.returnDate}" />
                                </div>
                            </div>

                            <div class="form-group last">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <fmt:message key="proceed.button.txt" bundle="${rentalPeriod}"/>
                                    </button>
                                    <button type="reset" class="btn btn-default btn-sm">
                                        <fmt:message key="reset.button.txt" bundle="${rentalPeriod}"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div><c:out value="${rentalPeriodRule}" /></div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_captions" var="rentalPeriod" />
<fmt:setBundle basename="l10n.common_caption" var="errors" />

<html>
<head>
    <title><fmt:message key="rpc.page.title" bundle="${rentalPeriod}" /></title>
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
    <%@include file="userNavbar.jsp"%>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default" id="auth-panel-default">
                    <div class="panel-heading"><fmt:message key="rpc.choose.period" bundle="${rentalPeriod}" /></div>

                    <div class="panel-body">

                        <form class="form-horizontal" role="form" name="rentalPeriodForm"
                              action="/controller/chooseRentalPeriod" method="post">

                            <div class="form-group">
                                <label for="startDate" class="col-sm-4 control-label">
                                    <fmt:message key="rpc.enter.start.date" bundle="${rentalPeriod}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="startDate" type="date" name="startDate" value="${param.startDate}" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="returnDate" class="col-sm-4 control-label">
                                    <fmt:message key="rpc.enter.return.date" bundle="${rentalPeriod}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="returnDate" type="date" name="returnDate" value="${param.returnDate}" />
                                </div>
                            </div>

                            <c:if test="${not empty rentalPeriodRule}" >
                                <div class="alert alert-danger"><c:out value="${rentalPeriodRule}" /></div>
                            </c:if>

                            <div class="form-group last">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <fmt:message key="rpc.proceed.button.txt" bundle="${rentalPeriod}"/>
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

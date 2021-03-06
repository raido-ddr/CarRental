<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_captions" var="damagedOrders"/>

<html>
<head>
    <title><fmt:message key="po.page.title" bundle="${damagedOrders}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
    <script src="<c:url value="/js/jquery-1.9.1.min.js" />" ></script>
    <script src="<c:url value="/js/bootstrap.min.js" />" ></script>
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default" id="auth-panel-default">
                    <div class="panel-heading">
                        <fmt:message key="po.page.title" bundle="${damagedOrders}" />
                    </div>

                    <div class="panel-body">

                        <form class="form-horizontal" role="form" action="<c:url value="/controller/placeOrder" />"
                              method="post">

                            <div class="form-group">
                                <label for="car" class="col-sm-4 control-label">
                                    <fmt:message key="po.caption.start.date" bundle="${damagedOrders}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="car" type="text"
                                           value="<c:out value="${param.make} ${param.model}" />" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="startDate" class="col-sm-4 control-label">
                                    <fmt:message key="po.caption.start.date" bundle="${damagedOrders}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="startDate" type="date" name="startDate"
                                           value="<c:out value="${param.startDate}" />" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="returnDate" class="col-sm-4 control-label">
                                    <fmt:message key="po.caption.return.date" bundle="${damagedOrders}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="returnDate" type="date" name="returnDate"
                                           value="<c:out value="${param.returnDate}" />" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="orderValue" class="col-sm-4 control-label">
                                    <fmt:message key="po.caption.value" bundle="${damagedOrders}" />
                                </label>
                                <div class="col-sm-8">
                                    <input id="orderValue" type="text" name="orderValue"
                                           value="<c:out value="${orderValue}" />" />
                                </div>
                            </div>

                            <input type="hidden" name="carId" value="${param.carId}">
                            <input type="hidden" name="status" value="new" />

                            <div class="form-group last">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <fmt:message key="po.submit.button.txt" bundle="${damagedOrders}"/>
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

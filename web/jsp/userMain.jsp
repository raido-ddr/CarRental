<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_main" var="main" />

<html>
<head>
    <title><fmt:message key="page.title" bundle="${main}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<%@include file="userNavbar.jsp"%>

<div>

    <div class="container col-lg-8">
        <div class="row">
            <div class="alert-success text-center"><c:out value="${successMessage}" /></div>
        </div>

        <div class="row">
            <div class="list-group col-lg-4">
                <div class="list-group-item active text-center">
                    <h5><fmt:message key="manage.orders" bundle="${main}" /></h5>
                </div>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="new">
                    <button class="btn btn-info btn-sm btn-block" type="submit">
                        <fmt:message key="new.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="confirmed">
                    <button class="btn btn-success btn-sm btn-block" type="submit">
                        <fmt:message key="confirmed.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="rejected">
                    <button class="btn btn-danger btn-sm btn-block" type="submit">
                        <fmt:message key="rejected.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="active">
                    <button class="btn btn-info btn-sm btn-block" type="submit">
                        <fmt:message key="active.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="damaged">
                    <button class="btn btn-warning btn-sm btn-block" type="submit">
                        <fmt:message key="damaged.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
                <form class="list-group-item" action="/controller/viewOrders" method="post" >
                    <input type="hidden" name="status" value="archived">
                    <button class="btn btn-default btn-sm btn-block" type="submit">
                        <fmt:message key="archived.orders.button.txt" bundle="${main}" />
                    </button>
                </form>
            </div>

            <div class="row">
                <div class="list-group col-lg-4 col-lg-offset-4">
                    <form class="list-group-item" action="/controller/chooseRentalPeriod" method="get" >
                        <button class="btn btn-success btn-lg btn-block" type="submit">
                            <fmt:message key="rent.button.txt" bundle="${main}" />
                        </button>
                    </form>
                </div>
            </div>


        </div>
    </div>

</div>
</body>
</html>

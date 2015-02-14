<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_active_orders" var="activeOrders"/>


<html>
<head>
    <title><fmt:message key="page.title" bundle="${activeOrders}" /></title>
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

<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-5">
                <h3><fmt:message key="page.title" bundle="${activeOrders}" /></h3>
            </div>
        </div>
    </div>

    <div class="panel-body">
        <div class="alert alert-warning">
            <c:out value="${emptyCategoryMessage}" />
        </div>

        <table class="table">

            <c:forEach items="${summaries}" var="summary">
            <tr><td>
                <div class="row">
                    <div class="col-lg-5 col-lg-offset-4">
                        <%@include file="orderSummary.jsp"%>
                    </div>
                </div>
    </div>
    </td></tr>
    </c:forEach>

    </table>
</div>

</div>

</body>
</html>

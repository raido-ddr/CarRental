<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_damaged_orders" var="damagedOrders"/>


<html>
<head>
    <title><fmt:message key="page.title" bundle="${damagedOrders}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
</head>
<body>

<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-5">
                <h3><fmt:message key="page.title" bundle="${damagedOrders}" /></h3>
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

                            <div class="form-group">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="description" class="text-warning">
                                            <fmt:message key="damage.description" bundle="${damagedOrders}"/>
                                        </label>
                                        <input class="form-control" id="description"
                                                value="${summary.damageDescription}" disabled>
                                        <label for="amount" class="text-warning">
                                            <fmt:message key="penalty.amount" bundle="${damagedOrders}"/>
                                        </label>
                                        <p id="amount" class="form-control">
                                            <c:out value="${summary.penaltyAmount}" />
                                        </p>
                                    </div>
                                </form>

                                <form role="form" name="archiveOrderForm"
                                      action="/controller/changeOrderStatus" method="post">
                                    <input type="hidden" name="orderId" value="${summary.orderId}">
                                    <input type="hidden" name="status" value="archived" />
                                    <button class="btn btn-success" type="submit">
                                        <fmt:message key="archive.button.txt" bundle="${damagedOrders}"/>
                                    </button>
                                </form>
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





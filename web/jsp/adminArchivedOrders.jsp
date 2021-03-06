<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_captions" var="archivedOrders"/>


<html>
<head>
    <title><fmt:message key="aro.page.title" bundle="${archivedOrders}" /></title>
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
<%@include file="adminNavbar.jsp"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-5">
                <h3><fmt:message key="aro.page.title" bundle="${archivedOrders}" /></h3>
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

                            <c:if test="${not empty summary.damageDescription}">
                                <div class="form-group">
                                    <form role="form">
                                        <div class="form-group">
                                            <label for="description" class="text-warning">
                                                <fmt:message key="aro.damage.description"
                                                             bundle="${archivedOrders}"/>
                                            </label>
                                            <input class="form-control" id="description"
                                                   value="${summary.damageDescription}" disabled>
                                            <label for="amount" class="text-warning">
                                                <fmt:message key="aro.penalty.amount"
                                                             bundle="${archivedOrders}"/>
                                            </label>
                                            <p id="amount" class="form-control">
                                                <c:out value="${summary.penaltyAmount}" />
                                            </p>
                                        </div>
                                    </form>
                                </div>
                            </c:if>

                            <c:if test="${not empty summary.rejectionReason}">
                                <div class="form-group">
                                    <form role="form">
                                        <div class="form-group">
                                            <label for="reason" class="text-danger">
                                                <fmt:message key="aro.rejection.reason" bundle="${archivedOrders}"/>
                                            </label>
                                            <input class="form-control" id="reason"
                                                   value="${summary.rejectionReason}" disabled>
                                        </div>
                                    </form>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <form role="form" name="confirmOrderForm"
                                      action="<c:url value="/controller/changeOrderStatus" />" method="post">
                                    <input type="hidden" name="orderId" value="${summary.orderId}">
                                    <input type="hidden" name="status" value="deleted" />
                                    <button class="btn btn-danger" type="submit">
                                        <fmt:message key="aro.delete.button.txt" bundle="${archivedOrders}"/>
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





<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_captions" var="activeOrders"/>

<html>
<head>
    <title><fmt:message key="aco.page.title" bundle="${activeOrders}" /></title>
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
                <h3><fmt:message key="aco.page.title" bundle="${activeOrders}" /></h3>
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
                            <div class="form-group">
                                <form  role="form" name="archiveOrderForm"
                                      action="<c:url value="/controller/changeOrderStatus" />"
                                      method="post">
                                    <input type="hidden" name="orderId" value="${summary.orderId}">
                                    <input type="hidden" name="status" value="archived" />
                                    <button class="btn btn-success" type="submit">
                                        <fmt:message key="aco.archive.button.txt"
                                                     bundle="${activeOrders}"/>
                                    </button>
                                </form>
                            </div>

                            <form role="form" name="reportDamageForm"
                                  action="<c:url value="/controller/changeOrderStatus" />"
                                  method="post">
                                <div>
                                    <input type="hidden" name="orderId" value="${summary.orderId}">
                                    <input type="hidden" name="status" value="damaged" />
                                    <div class="form-group">
                                        <textarea class="form-control custom-control" rows="3" cols="35"
                                                  style="resize:none" name="damageDescription"
                                                  placeholder="<fmt:message key="aco.damage.description"
                                                  bundle="${activeOrders}" />" ></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" type="number"  name="penaltyAmount"
                                                placeholder="<fmt:message key="aco.penalty.amount"
                                                bundle="${activeOrders}" />">
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-warning" type="submit">
                                            <fmt:message key="aco.damage.button.txt"
                                                         bundle="${activeOrders}"/>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>

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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_new_orders" var="confirmedOrders"/>

<html>
<head>
    <title><fmt:message key="page.title" bundle="${confirmedOrders}" /></title>
</head>
<body>

    <div>
        <div><fmt:message key="page.title" bundle="${confirmedOrders}" /></div>
    </div>

    <div>
        <c:out value="${emptyCategoryMessage}" />
    </div>

    <div>
        <c:forEach items="${summaries}" var="summary">

            <%@include file="orderSummary.jsp"%>

            <div>
                <form name="confirmOrderForm" action="/controller/changeOrderStatus" method="post">
                    <input type="hidden" name="orderId" value="${summary.orderId}">
                    <input type="hidden" name="status" value="confirmed" />
                    <button type="submit">
                        <fmt:message key="confirm.button.txt" bundle="${confirmedOrders}"/>
                    </button>
                </form>

                <form name="submitOrderForm" action="/controller/changeOrderStatus" method="post">
                    <input type="hidden" name="orderId" value="${summary.orderId}">
                    <input type="hidden" name="status" value="rejected" />
                    <button type="submit">
                        <fmt:message key="reject.button.txt" bundle="${confirmedOrders}"/>
                    </button>
                    <div>
                        <textarea name="rejectionReason"></textarea>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>


</body>
</html>

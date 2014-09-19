<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.place_order" var="placeOrder" />
<fmt:setBundle basename="input_errors" var="errors" />
<fmt:setBundle basename="html_regex" var="regex" />
<html>
<head>
    <title><fmt:message key="page.title" bundle="${placeOrder}" /></title>
</head>
<body>

    <form name="placeOrderForm"
          action="/controller/placeOrder?carId=${param.carId}&dailyCost=${param.dailyCost}"
          method="post">

        <label for="startDate">
            <fmt:message key="enter.start.date" bundle="${placeOrder}" />
        </label>
        <input id="startDate" type="date" name="startDate" value="${param.startDate}" />
        <br />
        <div><c:out value="${startDateRule}" /></div>
        <br />

        <label for="returnDate">
            <fmt:message key="enter.return.date" bundle="${placeOrder}" />
        </label>
        <input id="returnDate" type="date" name="returnDate" value="${param.returnDate}" />
        <br />
        <div><c:out value="${returnDateRule}" /></div>
        <br />

        <button type="submit" formmethod="post">
            <fmt:message key="order.button.txt" bundle="${placeOrder}"/>
        </button>
    </form>

</body>
</html>

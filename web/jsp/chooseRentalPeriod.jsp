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
</head>
<body>

    <form name="rentalPeriodForm"
          action="/controller/chooseRentalPeriod"
          method="post">

        <label for="startDate">
            <fmt:message key="enter.start.date" bundle="${rentalPeriod}" />
        </label>
        <input id="startDate" type="date" name="startDate" value="${param.startDate}" />
        <br />
        <div><c:out value="${startDateRule}" /></div>
        <br />

        <label for="returnDate">
            <fmt:message key="enter.return.date" bundle="${rentalPeriod}" />
        </label>
        <input id="returnDate" type="date" name="returnDate" value="${param.returnDate}" />
        <br />
        <div><c:out value="${returnDateRule}" /></div>
        <br />

        <button type="submit" formmethod="post">
            <fmt:message key="proceed.button.txt" bundle="${rentalPeriod}"/>
        </button>
    </form>

    <div>${resultMessage}</div>

</body>
</html>

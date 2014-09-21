<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.admin_main" var="main"/>

<html>
<head>
    <title><fmt:message key="page.title" bundle="${main}" /></title>
</head>
<body>
<div>
    <div><c:out value="${successMessage}" /></div>

    <form action="/controller/addCar" method="get" >
        <button type="submit">
            <fmt:message key="add.button.txt" bundle="${main}" />
        </button>
    </form>

    <form action="/controller/viewAllCars" method="get" >
        <button type="submit">
            <fmt:message key="view.button.txt" bundle="${main}" />
        </button>
    </form>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="new">
            <button type="submit">
                <fmt:message key="new.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="confirmed">
            <button type="submit">
                <fmt:message key="confirmed.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="rejected">
            <button type="submit">
                <fmt:message key="rejected.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="active">
            <button type="submit">
                <fmt:message key="active.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="damaged">
            <button type="submit">
                <fmt:message key="damaged.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>

    <div>
        <form action="/controller/viewOrders" method="post" >
            <input type="hidden" name="status" value="archived">
            <button type="submit">
                <fmt:message key="archived.orders.button.txt" bundle="${main}" />
            </button>
        </form>
    </div>





</div>
</body>
</html>

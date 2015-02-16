<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.user_captions" var="viewCars" />

<html>
<head>
    <title><fmt:message key="mp.page.title" bundle="${viewCars}" /></title>
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

<body id="user_main">

<%@include file="userNavbar.jsp"%>

<div>

    <div class="container col-lg-8">
        <c:if test="${not empty successMessage}" >
            <div class="row">
                <div class="alert-success text-center"><c:out value="${successMessage}" /></div>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>

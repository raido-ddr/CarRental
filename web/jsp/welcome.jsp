<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.common_captions" var="welcome" />

<html>
<head>
    <title><fmt:message key="wl.page.title" bundle="${welcome}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
    <link rel="icon" href="<c:url value="/favicon.ico" />"  type="image/x-icon"  />
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>

<body>

    <div>

        <%@include file="welcomeNavbar.jsp"%>

        <div class="jumbotron" id="welcome_jumbotron">
            <div class="container">
            </div>
        </div>

    </div>

</body>
</html>

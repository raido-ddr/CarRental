<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="l10n.common_captions" var="captions"/>

<html>
<head>
    <title><fmt:message key="wl.page.title" bundle="${captions}" /></title>
    <link href="<c:url value="/css/bootstrap.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-responsive.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-theme.min.css" />"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/custom_style.css" />"
          rel="stylesheet" type="text/css" />
    <link rel="icon" href="<c:url value="/favicon.ico" />"  type="image/x-icon"  />
    <script src="<c:url value="/js/jquery-1.9.1.min.js" />" ></script>
    <script src="<c:url value="/js/bootstrap.min.js" />" ></script>
</head>
<body>

    <div class="jumbotron" id="error_jumbotron">
        <div class="container">
            <div class="col-md-4">
                <h2 id="error_box"><fmt:message key="er.error" bundle="${captions}" /></h2>
                <div>
                    <a href="/jsp/welcome.jsp" class="btn btn-info" role="button">
                        <fmt:message key="er.main" bundle="${captions}" />
                    </a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

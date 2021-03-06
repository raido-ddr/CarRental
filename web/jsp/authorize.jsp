<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.common_captions" var="authorize" />


<html>
<head>
    <title><fmt:message key="au.page.title" bundle="${authorize}" /></title>
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

<body >
<%@include file="welcomeNavbar.jsp"%>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default" id="auth-panel-default">
                    <div class="panel-heading"><fmt:message key="au.title" bundle="${authorize}" /></div>

                    <div class="panel-body">

                        <c:if test="${not empty authorizationError}" >
                            <div class="alert alert-danger"><c:out value="${authorizationError}" /></div>
                        </c:if>

                        <form class="form-horizontal" role="form" action="<c:url value="controller/authorize" />"
                                method="post">

                            <div class="form-group">
                                <div class="col-sm-9">
                                    <input type="text" name="login" class="form-control" id="login"
                                           placeholder="<fmt:message key="au.enter.login" bundle="${authorize}" />">
                                </div>
                            </div>
                            <c:if test="${not empty loginError}" >
                                <div class="alert alert-danger"><c:out value="${loginError}" /></div>
                            </c:if>

                            <div class="form-group">

                                <div class="col-sm-9">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="<fmt:message key="au.enter.password" bundle="${authorize}" />" >
                                </div>
                            </div>
                            <c:if test="${not empty passwordError}" >
                                <div class="alert alert-danger"><c:out value="${passwordError}" /></div>
                            </c:if>

                            <div class="form-group last">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <fmt:message key="au.login.button.txt" bundle="${authorize}"/>
                                    </button>
                                    <button type="reset" class="btn btn-default btn-sm">
                                        <fmt:message key="au.reset.button.txt" bundle="${authorize}"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.locale}" />

<fmt:setBundle basename="l10n.common_captions" var="register" />

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html>
<head>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <title><fmt:message key="rg.page.title" bundle="${register}" /></title>
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
<%@include file="welcomeNavbar.jsp"%>

    <div><title><fmt:message key="rg.title" bundle="${register}" /></title></div>

    <div><c:out value="${duplicateLoginError}" /></div>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-4">
            <div class="panel panel-default" id="auth-panel-default">
                <div class="panel-heading">
                    <fmt:message key="rg.title" bundle="${register}" />
                </div>

                <div class="panel-body">

                <form class="form-horizontal" role="form" name="registerForm"
                      action="<c:url value="/controller/register" />" method="post">

                    <div class="form-group">
                        <label for="fName" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.fname" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="fName" type="text" name="firstName"
                               title="<fmt:message key="first.name.rule" bundle="${register}" />"
                               value="${param.firstName}"/>
                    </div>
                    <c:if test="${not empty fnameRule}" >
                        <div class="alert alert-danger"><c:out value="${fnameRule}" /></div>
                    </c:if>


                    <div class="form-group">
                        <label for="lName" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.lname" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="lName" type="text" name="lastName"
                               title="<fmt:message key="last.name.rule" bundle="${register}" />"
                               value="${param.lastName} "/>
                    </div>
                    <c:if test="${not empty lnameRule}" >
                        <div class="alert alert-danger"><c:out value="${lnameRule}" /></div>
                    </c:if>

                    <div class="form-group">
                        <label for="login" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.login" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="login" type="text" name="login"
                               title="<fmt:message key="login.rule" bundle="${register}" />"
                               value="${param.login} "/>
                    </div>
                    <c:if test="${not empty loginRule}" >
                        <div class="alert alert-danger"><c:out value="${loginRule}" /></div>
                    </c:if>


                    <div class="form-group">
                        <label for="password" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.password" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="password" type="password" name="password"
                               title="<fmt:message key="password.rule" bundle="${register}" />"
                               value="${param.password}"/>
                    </div>
                    <c:if test="${not empty passwordRule}" >
                        <div class="alert alert-danger"><c:out value="${passwordRule}" /></div>
                    </c:if>

                    <div class="form-group">
                        <label for="email" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.email" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="email" type="text" name="email"
                               title="<fmt:message key="email.rule" bundle="${register}" />"
                               value="${param.email}" />
                    </div>
                    <c:if test="${not empty emailRule}" >
                        <div class="alert alert-danger"><c:out value="${emailRule}" /></div>
                    </c:if>

                    <div class="form-group">
                        <label for="dob" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.dob" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="dob" type="date" name="dob" value="${param.dob}" />
                    </div>
                    <c:if test="${not empty dobRule}" >
                        <div class="alert alert-danger"><c:out value="${dobRule}" /></div>
                    </c:if>

                    <div class="form-group">
                        <label for="passport" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.passport" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="passport" type="text" name="passport"
                               title="<fmt:message key="passport.rule" bundle="${register}" />"
                               value="${param.passport}" />
                    </div>
                    <c:if test="${not empty passportRule}" >
                        <div class="alert alert-danger"><c:out value="${passportRule}" /></div>
                    </c:if>

                    <div class="form-group">
                        <label for="license.expiry" class="control-label col-sm-4">
                            <fmt:message key="rg.enter.license.expiry" bundle="${register}" />
                        </label>
                        <input class="col-sm-6" id="license.expiry" type="date" name="licenseExpiry"
                                value="${param.licenseExpiry}"/>
                    </div>
                    <c:if test="${not empty licenseExpiryRule}" >
                        <div class="alert alert-danger"><c:out value="${licenseExpiryRule}" /></div>
                    </c:if>

                    <div class="form-group last">
                        <div class="col-sm-offset-2 col-sm-9">
                            <button type="submit" class="btn btn-info btn-sm">
                                <fmt:message key="rg.register.button.txt" bundle="${register}"/>
                            </button>
                            <button type="reset" class="btn btn-default btn-sm">
                                <fmt:message key="rg.reset.button.txt" bundle="${register}"/>
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

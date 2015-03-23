<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setBundle basename="l10n.common_captions" var="navbar"/>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html>

<head>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
</head>

<div class="navbar navbar-inverse navbar-fixed-top"  role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="/jsp/userMain.jsp">
            <fmt:message key="nv.brand" bundle="${navbar}" />
        </a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="nv.language" bundle="${navbar}" />
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="<c:url value="/controller/changeLocale?locale=ru-RU" />">
                            <fmt:message key="nv.ru.button.txt" bundle="${navbar}" />
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/controller/changeLocale?locale=en-GB" />">
                            <fmt:message key="nv.en.button.txt" bundle="${navbar}" />
                        </a>
                    </li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="nv.orders" bundle="${navbar}" />
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu navbar-inverse">
                    <li class="navbar-inverse">
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="new">
                            <button class="btn btn-info btn-sm btn-block" type="submit">
                                <fmt:message key="nv.new.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="confirmed">
                            <button class="btn btn-success btn-sm btn-block" type="submit">
                                <fmt:message key="nv.confirmed.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="rejected">
                            <button class="btn btn-danger btn-sm btn-block" type="submit">
                                <fmt:message key="nv.rejected.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="active">
                            <button class="btn btn-info btn-sm btn-block" type="submit">
                                <fmt:message key="nv.active.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="damaged">
                            <button class="btn btn-warning btn-sm btn-block" type="submit">
                                <fmt:message key="nv.damaged.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="<c:url value="/controller/viewOrders" />"
                              method="post" >
                            <input type="hidden" name="status" value="archived">
                            <button class="btn btn-default btn-sm btn-block" type="submit">
                                <fmt:message key="nv.archived.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                </ul>
            </li>

            <li>
                <form class="navbar-form navbar-left" action="<c:url value="/controller/chooseRentalPeriod" />"
                      method="get" >
                    <button class="btn btn-success" type="submit">
                        <fmt:message key="nv.rent.button.txt" bundle="${navbar}" />
                    </button>
                </form>
            </li>

            <li>
                <form class="navbar-form navbar-left" action="<c:url value="/controller/logout" />" method="get" >
                    <button class="btn btn-warning" type="submit">
                        <fmt:message key="nv.logout.button.txt" bundle="${navbar}" />
                    </button>
                </form>
            </li>
        </ul>
    </div>
</div>

</html>

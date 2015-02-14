<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="l10n.navbar" var="navbar"/>

<html>

<div class="navbar navbar-inverse navbar-fixed-top"  role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="/jsp/userMain.jsp">
            <fmt:message key="brand" bundle="${navbar}" />
        </a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="language" bundle="${navbar}" />
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="/controller/changeLocale?locale=ru-RU">
                            <fmt:message key="ru.button.txt" bundle="${navbar}" />
                        </a>
                    </li>
                    <li>
                        <a href="/controller/changeLocale?locale=en-GB">
                            <fmt:message key="en.button.txt" bundle="${navbar}" />
                        </a>
                    </li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message key="orders" bundle="${navbar}" />
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu navbar-inverse">
                    <li class="navbar-inverse">
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders" method="post" >
                            <input type="hidden" name="status" value="new">
                            <button class="btn btn-info btn-sm btn-block" type="submit">
                                <fmt:message key="new.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders" method="post" >
                            <input type="hidden" name="status" value="confirmed">
                            <button class="btn btn-success btn-sm btn-block" type="submit">
                                <fmt:message key="confirmed.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders"
                              method="post" >
                            <input type="hidden" name="status" value="rejected">
                            <button class="btn btn-danger btn-sm btn-block" type="submit">
                                <fmt:message key="rejected.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders"
                              method="post" >
                            <input type="hidden" name="status" value="active">
                            <button class="btn btn-info btn-sm btn-block" type="submit">
                                <fmt:message key="active.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders"
                              method="post" >
                            <input type="hidden" name="status" value="damaged">
                            <button class="btn btn-warning btn-sm btn-block" type="submit">
                                <fmt:message key="damaged.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                    <li>
                        <form class="list-group-item navbar-inverse" action="/controller/viewOrders"
                              method="post" >
                            <input type="hidden" name="status" value="archived">
                            <button class="btn btn-default btn-sm btn-block" type="submit">
                                <fmt:message key="archived.orders.button.txt" bundle="${navbar}" />
                            </button>
                        </form>
                    </li>
                </ul>
            </li>

            <li>
                <form class="navbar-form navbar-left" action="/controller/chooseRentalPeriod" method="get" >
                    <button class="btn btn-success" type="submit">
                        <fmt:message key="rent.button.txt" bundle="${navbar}" />
                    </button>
                </form>
            </li>

            <li>
                <form class="navbar-form navbar-left" action="/controller/logout" method="get" >
                    <button class="btn btn-warning" type="submit">
                        <fmt:message key="logout.button.txt" bundle="${navbar}" />
                    </button>
                </form>
            </li>
        </ul>
    </div>
</div>

</html>

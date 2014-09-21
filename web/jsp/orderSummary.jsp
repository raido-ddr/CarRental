<fmt:setBundle basename="l10n.order_summary" var="captions" />

<html>
    <table class="table table-bordered">
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.order.id" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.orderId}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.real.name" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userFirstName} ${summary.userLastName}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.email" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userEmail}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.dob" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userDateOfBirth}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.license.expiry" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.licenseExpiryDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.car" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.carMake} ${summary.carModel}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.start.date" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.startDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.return.date" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.returnDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="caption.order.value" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.orderValue}" /></dd>
                </dl>
            </td>
        </tr>
    </table>
</html>
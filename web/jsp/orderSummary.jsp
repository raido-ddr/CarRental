<fmt:setBundle basename="l10n.user_captions" var="captions" />

<html>
    <table class="table table-bordered">
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.order.id" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.orderId}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.real.name" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userFirstName} ${summary.userLastName}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.email" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userEmail}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.dob" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.userDateOfBirth}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.license.expiry" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.licenseExpiryDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.car" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.carMake} ${summary.carModel}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.start.date" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.startDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.return.date" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.returnDate}" /></dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl class="dl-horizontal">
                    <dt><fmt:message key="os.caption.order.value" bundle="${captions}" /></dt>
                    <dd><c:out value="${summary.orderValue}" /></dd>
                </dl>
            </td>
        </tr>
    </table>
</html>
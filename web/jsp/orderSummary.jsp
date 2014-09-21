<fmt:setBundle basename="l10n.order_summary" var="captions" />

<html>
    <div>
        <div>
            <strong><fmt:message key="caption.order.id" bundle="${captions}" /></strong>
            <div><c:out value="${summary.orderId}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.real.name" bundle="${captions}" /></strong>
            <div><c:out value="${summary.userFirstName} ${summary.userLastName}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.email" bundle="${captions}" /></strong>
            <div><c:out value="${summary.userEmail}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.dob" bundle="${captions}" /></strong>
            <div><c:out value="${summary.userDateOfBirth}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.license.expiry" bundle="${captions}" /></strong>
            <div><c:out value="${summary.licenseExpiryDate}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.car" bundle="${captions}" /></strong>
            <div><c:out value="${summary.carMake} ${summary.carModel}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.start.date" bundle="${captions}" /></strong>
            <div><c:out value="${summary.startDate}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.return.date" bundle="${captions}" /></strong>
            <div><c:out value="${summary.returnDate}" /></div>
        </div>
        <div>
            <strong><fmt:message key="caption.order.value" bundle="${captions}" /></strong>
            <div><c:out value="${summary.orderValue}" /></div>
        </div>
    </div>
</html>
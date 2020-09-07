<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Change status</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <form method="post" class="createtrip" action="${pageContext.request.contextPath}/setstatus">
        <div>
            <select name="tripstatus">
                <option value=" "><fmt:message key="trip.status" bundle="${msg}"/></option>
                <option value="INPROGRESS"><fmt:message key="trip.status.inprogress" bundle="${msg}"/></option>
                <option value="CLOSED"><fmt:message key="trip.status.closed" bundle="${msg}"/></option>
                <option value="CANCELED"><fmt:message key="trip.status.canceled" bundle="${msg}"/></option>
            </select>
        </div>
        <div>
            <fmt:message key="submit" bundle="${msg}" var="submit"/>
            <input type="submit" value="${submit}">
        </div>
    </form>
</section>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

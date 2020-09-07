<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Users</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <h1><fmt:message key="trip.set.car" bundle="${msg}" /></h1>

    <form method="post" class="addcar" action="${pageContext.request.contextPath}/setcar">
        <div>
            <select name="carId">
                <option value=" "><fmt:message key="cars" bundle="${msg}"/></option>
                <c:forEach items="${cars}" var="car">
                    <option value="${car.id}">${car.brand} ${car.model}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <fmt:message key="submit" bundle="${msg}" var="submit"/>
            <input type="submit" value="${submit}">
        </div>
    </form>
    </div>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

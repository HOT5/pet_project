<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Delete Car</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <h1><fmt:message key="delete.car" bundle="${msg}" /></h1>

    <form method="post" class="deletecar" action="${pageContext.request.contextPath}/deletecar" style="margin: 10px">
        <input type="hidden" value="${deleteCarId}" name="deleteCarId">
        <div>
            <p><fmt:message key="delete.car.message" bundle="${msg}" /></p>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Db Error</title>
</head>

<body>
<jsp:include page="/jsp/parts/header.jsp"/>
<section>

    <b><fmt:message key="db.error.info" bundle="${msg}"/></b>
    <p><fmt:message key="db.error.message" bundle="${msg}"/></p>

    <b><fmt:message key="db.error.details" bundle="${msg}"/></b>
    <div>
        <p style="color: #c9590c"><fmt:message key="${errorMessage}" bundle="${msg}" /></p>
    </div>

</section>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
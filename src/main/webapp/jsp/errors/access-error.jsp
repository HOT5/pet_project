<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Access error</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<b><fmt:message key="access.error.info" bundle="${msg}"/></b>
<p><fmt:message key="access.error.message" bundle="${msg}"/></p>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

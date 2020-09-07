<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="inf" uri="taglib" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Admin</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>


<h1><fmt:message key="hello" bundle="${msg}"/><inf:UserData /></h1>
<p><a href="${pageContext.request.contextPath}/trips"><fmt:message key="user.available.trips" bundle="${msg}"/></a></p>
<p><a href="${pageContext.request.contextPath}/usertrips"><fmt:message key="user.trips" bundle="${msg}"/></a></p>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Home page</title>
</head>
<body>

<jsp:include page="/jsp/parts/header.jsp"/>


<div>
    <h1><fmt:message key="index.welcome" bundle="${msg}"/></h1>
</div>
<div>
    <p><fmt:message key="index.welcome.text" bundle="${msg}"/></p>
</div>


<jsp:include page="/jsp/parts/footer.jsp"/>

</body>
</html>

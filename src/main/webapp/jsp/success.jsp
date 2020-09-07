<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="inf" uri="taglib" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Success</title>
</head>

<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<div>
    <section>
        <h2><fmt:message key="success" bundle="${msg}"/></h2>
        <b><fmt:message key="success.message" bundle="${msg}"/></b><br>
        <p><fmt:message key="success.info" bundle="${msg}"/></p> <inf:UserData /><br>
        <b><fmt:message key="success.singin" bundle="${msg}"/></b>
    </section>
</div>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<fmt:requestEncoding value="UTF-8"/>

<header>
    <h1>Auto base</h1>
    <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
</header>

<form id="lang">
    <select id="language" name="lang" onchange="submit()" style="position: absolute; top: 50px; right: 15px">
        <option value=" ">Language</option>
        <option value="ru">Russian</option>
        <option value="en">English</option>
    </select>
</form>

<nav id="nav_bar">
    <ul>
        <c:choose>
            <c:when test="${sessionScope.userId==null}">
                <li><a href="${pageContext.request.contextPath}/login"><fmt:message key="login" bundle="${msg}"/></a></li>
                <li><a href="${pageContext.request.contextPath}/singin"><fmt:message key="registration" bundle="${msg}"/></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/logout"><fmt:message key="exit" bundle="${msg}"/></a></li>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.role=='CLIENT'}">
            <li><a href="${pageContext.request.contextPath}/user"><fmt:message key="home" bundle="${msg}"/></a></li>
        </c:if>
        <c:if test="${sessionScope.role=='ADMIN'}">
            <li><a href="${pageContext.request.contextPath}/admin"><fmt:message key="admin" bundle="${msg}"/></a></li>
        </c:if>
        <c:if test="${sessionScope.role=='MANAGER'}">
            <li><a href="${pageContext.request.contextPath}/manager"><fmt:message key="manager" bundle="${msg}"/></a></li>
        </c:if>
    </ul>
</nav>
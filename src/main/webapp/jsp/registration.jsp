<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Registration</title>

    <script type="text/javascript">
        function refreshCheckboxValue(checkbox) {
            checkbox.value = checkbox.checked;
        }
    </script>
</head>

<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <c:choose>
    <c:when test="${sessionScope.role=='CLIENT'}">
    <h1><fmt:message key="update.user" bundle="${msg}"/></h1>
    <form method="post" class="update-form" action="${pageContext.request.contextPath}/updateuser" style="margin: 10px">
        </c:when>
        <c:otherwise>
        <h1><fmt:message key="registration" bundle="${msg}"/></h1>
        <form method="post" class="reg-form" action="${pageContext.request.contextPath}/singin" style="margin: 10px">
            </c:otherwise>
            </c:choose>
            <div>
                <input type="hidden" value="${updateUserId}" name="updateUserId">
                <label for="firstname"><fmt:message key="registration.firstname" bundle="${msg}"/></label>
                <input id="firstname" type="text" name="firstname" required> <br/>
            </div>
            <div>
                <label for="lastname"><fmt:message key="registration.lastname" bundle="${msg}"/></label>
                <input id="lastname" type="text" name="lastname" required><br/>
            </div>
            <div>
                <label for="email"><fmt:message key="registration.email" bundle="${msg}"/></label>
                <input id="email" type="email" name="email" required><br/><br/>
            </div>
            <div>
                <label for="login"><fmt:message key="registration.login" bundle="${msg}"/></label>
                <input id="login" type="text" name="login" minlength="3" required><br/>
            </div>
            <div>
                <label for="password"><fmt:message key="registration.password" bundle="${msg}"/></label>
                <input id="password" type="password" name="password" minlength="3" required><br/>
            </div>
            <c:if test="${sessionScope.role=='ADMIN'}">
                <div>
                    <label for="isManager"><fmt:message key="isManager" bundle="${msg}"/></label>
                    <input id="isManager" type="checkbox" name="isManager" onclick="refreshCheckboxValue(this)"
                           value="false"/>
                </div>
            </c:if>

            <div>
                <fmt:message key="submit" bundle="${msg}" var="submit"/>
                <input type="submit" value="${submit}">
            </div>

            <div>
                <p style="color: #c9590c"><fmt:message key="${errorMessage}" bundle="${msg}"/></p>
            </div>

        </form>
</section>
<jsp:include page="/jsp/parts/footer.jsp"/>
</body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
    <h1><fmt:message key="users" bundle="${msg}" /></h1>

    <div style="overflow-x:auto;">
        <table  id="users">
            <tr>
                <th><fmt:message key="user.id" bundle="${msg}" /></th>
                <th><fmt:message key="registration.firstname" bundle="${msg}" /></th>
                <th><fmt:message key="registration.lastname" bundle="${msg}" /></th>
                <th><fmt:message key="registration.login" bundle="${msg}" /></th>
                <th><fmt:message key="registration.email" bundle="${msg}" /></th>
                <th><fmt:message key="role" bundle="${msg}" /></th>
                <th> </th>

            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${(user.firstName)}</td>
                    <td>${(user.lastName)}</td>
                    <td>${(user.login)}</td>
                    <td>${(user.email)}</td>
                    <td>${(user.role)}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

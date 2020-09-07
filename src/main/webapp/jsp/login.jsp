<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Login</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>


<section>

    <h1><fmt:message key="login" bundle="${msg}" /></h1>
    <p><fmt:message key="login.message" bundle="${msg}" /></p>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <label for="login"><fmt:message key="registration.login"  bundle="${msg}" /></label>
            <input id="login" type="text" name="login" minlength="3" required><br/>
        </div>

        <div>
            <label for="password"><fmt:message key="registration.password" bundle="${msg}" /></label>
            <input id="password" type="password" name="password" minlength="3" required><br/>
        </div>

        <div>
            <fmt:message key="submit" bundle="${msg}" var="submit"/>
            <input type="submit" value="${submit}">
        </div>

        <div>
            <p style="color: red"><fmt:message key="${errorMessage}" bundle="${msg}" /></p>
        </div>

    </form>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

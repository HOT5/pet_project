<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${sessionScope['locale']}"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="${bundleFile}" var="msg"/>
<div>
    <footer>
        <p><fmt:message key="copyright" bundle="${msg}"/></p>
    </footer>
</div>
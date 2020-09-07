<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>New Trip</title>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <form method="post" class="createtrip" action="${pageContext.request.contextPath}/newtrip">
        <div>
            <label for="tripname"><fmt:message key="trip.name" bundle="${msg}"/></label>
            <input id="tripname" type="text" name="tripname" minlength="4" required><br/>
        </div>
        <div>
            <select name="tripcartype">
                <option value=" "><fmt:message key="type.msg" bundle="${msg}"/></option>
                <option value="Sedan"><fmt:message key="car.sedan" bundle="${msg}"/></option>
                <option value="Wagon"><fmt:message key="car.wagon" bundle="${msg}"/></option>
                <option value="Coupe"><fmt:message key="car.coupe" bundle="${msg}"/></option>
                <option value="SUV"><fmt:message key="car.suv" bundle="${msg}"/></option>
                <option value="Electric"><fmt:message key="car.electric" bundle="${msg}"/></option>
            </select>
        </div>
        <div>
            <fmt:message key="submit" bundle="${msg}" var="submit"/>
            <input type="submit" value="${submit}">
        </div>
    </form>
</section>

<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

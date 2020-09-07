<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Update Car</title>
    <script type="text/javascript">
        function refreshCheckboxValue(checkbox) {
            checkbox.value = checkbox.checked;
        }
    </script>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>

<section>
    <h1><fmt:message key="update.car" bundle="${msg}"/></h1>

    <form method="post" class="updatecar" action="${pageContext.request.contextPath}/updatecar" style="margin: 10px">
        <input type="hidden" value="${updateCarId}" name="updateCarId">
        <div>
            <label for="carbrand"><fmt:message key="brand.msg" bundle="${msg}"/></label>
            <input id="carbrand" type="text" name="carbrand" minlength="4" required> <br/>
        </div>
        <div>
            <label for="carmodel"><fmt:message key="model.msg" bundle="${msg}"/></label>
            <input id="carmodel" type="text" name="carmodel" minlength="1" required><br/>
        </div>

        <div>
            <select name="cartype">
                <option value=" "><fmt:message key="type.msg" bundle="${msg}"/></option>
                <option value="Sedan"><fmt:message key="car.sedan" bundle="${msg}"/></option>
                <option value="Wagon"><fmt:message key="car.wagon" bundle="${msg}"/></option>
                <option value="Coupe"><fmt:message key="car.coupe" bundle="${msg}"/></option>
                <option value="SUV"><fmt:message key="car.suv" bundle="${msg}"/></option>
                <option value="Electric"><fmt:message key="car.electric" bundle="${msg}"/></option>
            </select>
        </div>
        <div>
            <label for="isServiceable"><fmt:message key="isServiceable" bundle="${msg}"/></label>
            <input id="isServiceable" type="checkbox" name="isServiceable" onclick="refreshCheckboxValue(this)"
                   value="true"/>
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

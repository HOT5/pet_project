<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope['locale']}"/>
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg"/>
    <title>Cars</title>

    <script type="text/javascript">
        function refreshCheckboxValue(checkbox) {
            checkbox.value = checkbox.checked;
        }
    </script>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>


<section>
    <h1><fmt:message key="trips" bundle="${msg}"/></h1>
    <br/>
    <br/>

    <div>
        <form id="sort">
            <select name="sort" onchange="submit()">
                <option value=" "><fmt:message key="sort" bundle="${msg}"/></option>
                <option value="id"><fmt:message key="sort.by.id" bundle="${msg}"/></option>
                <option value="date"><fmt:message key="sort.by.date" bundle="${msg}"/></option>
            </select>
        </form>
    </div>


    <div style="overflow-x:auto;">
        <table id="trips">
            <tr>
                <th><fmt:message key="trip.id" bundle="${msg}"/></th>
                <th><fmt:message key="trip.name" bundle="${msg}"/></th>
                <th><fmt:message key="trip.date" bundle="${msg}"/></th>
                <th><fmt:message key="trip.status" bundle="${msg}"/></th>
                <th><fmt:message key="trip.car.type" bundle="${msg}"/></th>
                <th><fmt:message key="trip.car" bundle="${msg}"/></th>
                <th></th>
            </tr>
            <c:forEach items="${trips}" var="trip">
                <tr>
                    <td>${(trip.id)}</td>
                    <td>${(trip.name)}</td>
                    <td>${(trip.created)}</td>
                    <td>${trip.status}</td>
                    <td>${trip.type}</td>
                    <td>${trip.car.brand} ${trip.car.model}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/closetrip?updateTripId=${(trip.id)}&"><fmt:message
                                key="trip.complete" bundle="${msg}"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

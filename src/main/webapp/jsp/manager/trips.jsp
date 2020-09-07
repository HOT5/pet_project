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
        <c:choose>
            <c:when test="${sessionScope.role=='ADMIN' || sessionScope.role=='MANAGER'}">
                <form id="sort">
                    <select name="sort" onchange="submit()">
                        <option value=" "><fmt:message key="sort" bundle="${msg}"/></option>
                        <option value="id"><fmt:message key="sort.by.id" bundle="${msg}"/></option>
                        <option value="date"><fmt:message key="sort.by.date" bundle="${msg}"/></option>
                    </select>
                </form>
                <form id="sortType">
                    <select name="type" onchange="submit()">
                        <option value=" "><fmt:message key="trip.status" bundle="${msg}"/></option>
                        <option value="OPEN"><fmt:message key="trip.status.open" bundle="${msg}"/></option>
                        <option value="INPROGRESS"><fmt:message key="trip.status.inprogress" bundle="${msg}"/></option>
                        <option value="CLOSED"><fmt:message key="trip.status.closed" bundle="${msg}"/></option>
                        <option value="CANCELED"><fmt:message key="trip.status.canceled" bundle="${msg}"/></option>
                    </select>
                </form>
            </c:when>
            <c:otherwise>
                <form id="sort">
                    <select name="sort" onchange="submit()">
                        <option value=" "><fmt:message key="sort" bundle="${msg}"/></option>
                        <option value="id"><fmt:message key="sort.by.id" bundle="${msg}"/></option>
                        <option value="date"><fmt:message key="sort.by.date" bundle="${msg}"/></option>
                    </select>
                </form>
            </c:otherwise>
        </c:choose>
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
                <th><fmt:message key="trip.user" bundle="${msg}"/></th>
                <th></th>
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
                    <td>${trip.user.firstName} ${trip.user.lastName}</td>
                    <c:choose>
                        <c:when test="${sessionScope.role=='ADMIN' || sessionScope.role=='MANAGER'}">
                            <td>
                                <a href="${pageContext.request.contextPath}/setstatus?updateTripId=${(trip.id)}"><fmt:message
                                        key="trip.set.status" bundle="${msg}"/></a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/setcar?updateTripId=${(trip.id)}&carType=${(trip.type)}"><fmt:message
                                        key="trip.set.car" bundle="${msg}"/></a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="${pageContext.request.contextPath}/setuser?updateTripId=${(trip.id)}&userId=${sessionScope.userId}"><fmt:message
                                        key="trip.submit" bundle="${msg}"/></a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

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
        function refreshCheckboxValue(checkbox){
            checkbox.value = checkbox.checked;
        }
    </script>
</head>
<body>
<jsp:include page="/jsp/parts/header.jsp"/>


<section>
    <h1><fmt:message key="cars" bundle="${msg}" /></h1>

    <div>
        <form id="make">
            <select name="carBrand" onchange="submit()">
                <option value=" "><fmt:message key="brand.msg" bundle="${msg}" /></option>
                <c:forEach items="${carBrands}" var="brand">
                    <option value="${brand}">${brand}</option>
                </c:forEach>
            </select>
        </form>

        <form id="type">
            <select name="carType" onchange="submit()">
                <option value=" "><fmt:message key="type.msg" bundle="${msg}" /></option>
                <c:forEach items="${carTypes}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select>
        </form>

        <form id="sort">
            <select name="sort" onchange="submit()">
                <option value=" "><fmt:message key="sort" bundle="${msg}" /></option>
                <option value="name"><fmt:message key="sort.by.name" bundle="${msg}" /></option>
            </select>
        </form>
    </div>



    <div style="overflow-x:auto;">
        <table id="cars">
            <tr>
                <th><fmt:message key="brand.msg" bundle="${msg}" /></th>
                <th><fmt:message key="model.msg" bundle="${msg}" /></th>
                <th><fmt:message key="type.msg" bundle="${msg}" /></th>
                <th><fmt:message key="service.msg" bundle="${msg}" /></th>
                <th> </th>
                <c:if test="${sessionScope.role=='ADMIN'}">
                    <th> </th>
                    <th> </th>
                </c:if>
            </tr>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td>${(car.brand)}</td>
                    <td>${(car.model)}</td>
                    <td>${(car.type)}</td>
                    <td>${car.serviceable}</td>
                    <c:if test="${sessionScope.role=='ADMIN'}">
                        <td>
                            <a href="${pageContext.request.contextPath}/updatecar?updateCarId=${(car.id)}"><fmt:message key="update.car" bundle="${msg}" /></a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/deletecar?deleteCarId=${(car.id)}"><fmt:message key="delete.car" bundle="${msg}" /></a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>


<jsp:include page="/jsp/parts/footer.jsp"/>
</body>
</html>

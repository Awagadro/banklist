<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty accountDTO.account}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty accountDTO.account}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty accountDTO.account}">
    <c:url value="/addAccount" var="var"/>
</c:if>
<c:if test="${!empty accountDTO.account}">
    <c:url value="/editAccount" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty accountDTO.account}">
        <input type="hidden" name="id" value="${accountDTO.id}">
    </c:if>
    <label name="userId" value="${accountDTO.userId}">User</label>
    <select name="userId">
        <c:forEach items="${usersChoices}" var="user">
            <option value="${user.id}">${user.name} ${user.surName}</option>
        </c:forEach>
    </select>
    <label for="account">Account</label>
    <input type="text" name="account" id="account">

    <c:if test="${empty accountDTO.account}">
        <input type="submit" value="Add new account">
    </c:if>
    <c:if test="${!empty accountDTO.account}">
        <input type="submit" value="Edit account">
    </c:if>
</form>
</body>
</html>

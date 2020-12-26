<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>USERS</title>
</head>
<body>

<h2>Users</h2>

<c:url value="/add" var="add"/>
<a href="${add}">Add new user</a>

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surName</th>
        <th>action</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surName}</td>
            <td>
                <a href="/edit/${user.id}">edit</a>
                <a href="/delete/${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:url value="/accounts" var="accounts"/>
<a href="${accounts}">See all accounts</a>
</body>
</html>

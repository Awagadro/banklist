<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ACCOUNTS</title>
</head>
<body>

<h2>Accounts</h2>

<c:url value="/addAccount" var="add"/>
<a href="${add}">Add new userAccount</a>

<table>
    <tr>
        <th>id</th>
        <th>user</th>
        <th>userAccount</th>
        <th>action</th>
    </tr>
    <c:forEach var="account" items="${accountsList}">
        <tr>
            <td>${account.id}</td>
            <td>${account.userName}</td>
            <td>${account.account}</td>
            <td>
                <a href="/editAccount/${account.id}">edit</a>
                <a href="/deleteAccount/${account.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:url value="/" var="users"/>
<a href="/">See all users</a>
</body>
</html>
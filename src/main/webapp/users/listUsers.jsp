<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>List of Users</h2>
        </div>
        <p><a href='<c:url value="/add-user" />'>Add new</a></p>
        <table>
            <thead>
            <tr>
                <th>No</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="user" items="${userList}">
                <c:set var="count" value="${count + 1}" scope="page" />
                <tr>
                    <td>${count}</td>
                    <td>${user.username}</td>
                    <td><a href="/ServletApp_war_exploded/list-user-vinyl/>">Vinyl Collection</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="/ServletApp_war_exploded/delete-user?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/ServletApp_war_exploded'">Back to main</button>
</div>
</body>
</html>

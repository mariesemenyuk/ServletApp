<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vinyl Collection</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Common Vinyl Collection</h2>
        </div>
        <p><a href='<c:url value="/add-vinyl" />'>Add new</a></p>
        <table>
            <thead>
            <tr>
                <th>No</th>
                <th>Author</th>
                <th>Title</th>
                <th>Country Issued</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="vinyl" items="${vinylList}">
                <c:set var="count" value="${count + 1}" scope="page" />
                <tr>
                    <td>${count}</td>
                    <td>${vinyl.author}</td>
                    <td>${vinyl.title}</td>
                    <td>${vinyl.countryIssued}</td>
                    <td>${vinyl.price}</td>
                    <td><a href="/edit-vinyl?id=<c:out value='${vinyl.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="/delete-vinyl?id=<c:out value='${vinyl.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>

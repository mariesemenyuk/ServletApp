<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page not found</title>
</head>
<body>
<h2>Page not found</h2>
<c:set var="error" value="${error}" scope="page" />
<h2>${error}</h2>
<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>

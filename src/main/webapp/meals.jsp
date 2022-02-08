<javatime:parseLocalDateTime value="${date}" pattern="yyyy-MM-dd" var="parsedDate"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<div>
    <p>
        <a href="index.html">Home</a>
    </p>
</div>
<br>

<div>
    <form method="post">
        <p>add meal</p>
    </form>
</div>

<div>
    <table border="1" cellpadding="6" cellspacing="1">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody style="margin: 3%">
        <c:forEach var="meal" items="${meals}">
            <c:set var="color" value="green"/>
            <tr style="color:${meal.excess ? 'greenyellow' : 'red'}">
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>updating</td>
                <td>deleting</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

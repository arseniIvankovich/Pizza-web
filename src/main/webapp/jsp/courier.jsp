<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arseni
  Date: 4/30/23
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Courier</title>
    <link rel="stylesheet" href="../css/courier.css">
</head>

<body>

<div class="wrapper">
    <p class="courier-text">Список заказов</p>

    <table class="styled-table">
        <thead>
        <tr>
            <th>E-mail</th>
            <th>Время доставки</th>
            <th>Способ оплаты</th>
            <th>Статус заказа</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.email}</td>
            <td>${user.firstName_lastName}</td>
            <td>${user.telephone}</td>
            <td>${user.order.status}</td>
            <td class="email-field"></td>
            <td class="status"></td>
        </tr>
        </c:forEach>

        </tbody>
    </table>

    <form class="status-form" action="/courier" method="post">
        <div class="input-box">
            <label for="status-form-input">E-mail</label>
            <input type="text" class="status-form-input" id="status-form-input" name="email">
        </div>
        <input type="submit" class="status-button" value="Изменить статус" >
    </form>
</div>


<script src="../js/courier.js"></script>
</body>

</html>
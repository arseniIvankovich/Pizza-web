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
        <tr>
            <td class="email-field"></td>
            <td></td>
            <td></td>
            <td class="status"></td>
        </tr>
        </tbody>
    </table>

    <form class="status-form">
        <div class="input-box">
            <label for="status-form-input">E-mail</label>
            <input type="text" class="status-form-input" id="status-form-input" name="email">
        </div>
        <input type="submit" class="status-button" value="Изменить статус">
    </form>
</div>

<script src="../js/courier.js"></script>
</body>

</html>

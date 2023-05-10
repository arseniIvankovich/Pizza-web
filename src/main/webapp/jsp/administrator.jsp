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
    <title>Administrator</title>
    <link rel="stylesheet" href="../css/administrator.css">
</head>

<body>

<div class="wrapper">
    <p class="administrator-text">Список пользователей</p>

    <table class="styled-table">
        <thead>
            <tr>
                <th>E-mail</th>
                <th>Имя и фамилия</th>
                <th>Телефон</th>
            </tr>
        </thead>

        <tbody>
        <tr>
            <td class="email-field"></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>

    <form class="user-form">
        <div class="input-box">
            <label for="user-form-input">E-mail</label>
            <input type="text" class="user-form-input" id="user-form-input" name="email">
        </div>
        <input type="submit" class="user-button" value="Удалить пользователя">
    </form>
</div>

<script src="../js/administrator.js"></script>
</body>

</html>

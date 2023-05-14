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
    <title>Register form</title>
    <link rel="stylesheet" href="../css/register-style.css">
</head>

<body>

<div class="wrapper">
    <div class="main">
        <c:if test="${streetError == true}">
            <script>
                alert("");
            </script>
        </c:if>
        <div class="form-section">
            <form method="post" action="/register" class="left-form">
                <div class="personal-header"><p class="personal-header-text">Регистрация</p></div>
                <div class="input-box">
                    <label for="login-form-street">Улица</label>
                    <input type="text" class="login-form-input" id="login-form-street" name="street">
                </div>
                <div class="input-box">
                    <label for="login-form-house">Дом</label>
                    <input type="text" class="login-form-input" id="login-form-house" name="house">
                </div>
                <div class="input-box">
                    <label for="login-form-entrance">Подъезд</label>
                    <input type="number" class="login-form-input" id="login-form-entrance" name="entrance">
                </div>

                <div class="input-box">
                    <label for="login-form-flat">Квартира</label>
                    <input type="number" class="login-form-input" id="login-form-flat" name="flat">
                </div>
                <div class="input-box">
                    <label for="login-form-name">Имя и Фамилия</label>
                    <input type="text" class="login-form-input" id="login-form-name" name="firstSecondName">
                </div>
                <div class="input-box">
                    <label for="login-form-email">Email</label>
                    <input type="email" class="login-form-input" id="login-form-email" name="email">
                </div>
                <div class="input-box">
                    <label for="login-form-phone">Телефон</label>
                    <input type="text" class="login-form-input" id="login-form-phone" name="telephone">
                </div>
                <div class="input-box">
                    <label for="login-form-date">Пароль</label>
                    <input type="text" class="login-form-input" id="login-form-date" name="password">
                </div>
                <div class="button-box">
                    <input type="submit" value="Зарегистрироваться" class="save-button">
                </div>
                <div>
                    <a href="/" class="login-form-register-ref">
                        Вернуться на главную
                    </a>
                </div>
                <div>
                    <a href="/login" class="login-form-register-ref">
                        Уже есть аккаунт?
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>



<script src="../js/register.js"></script>
</body>

</html>

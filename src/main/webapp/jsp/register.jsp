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
        <div class="form-section">
            <form action="" class="left-form">
                <div class="personal-header"><p class="personal-header-text">Регистрация</p></div>
                <div class="input-box">
                    <label for="login-form-street">Улица</label>
                    <input type="text" class="login-form-input" id="login-form-street">
                </div>
                <div class="input-box">
                    <label for="login-form-house">Дом</label>
                    <input type="text" class="login-form-input" id="login-form-house">
                </div>
                <div class="input-box">
                    <label for="login-form-entrance">Подъезд</label>
                    <input type="text" class="login-form-input" id="login-form-entrance">
                </div>

                <div class="input-box">
                    <label for="login-form-flat">Квартира</label>
                    <input type="text" class="login-form-input" id="login-form-flat">
                </div>
                <div class="input-box">
                    <label for="login-form-name">Имя и Фамилия</label>
                    <input type="text" class="login-form-input" id="login-form-name">
                </div>
                <div class="input-box">
                    <label for="login-form-email">Email</label>
                    <input type="text" class="login-form-input" id="login-form-email">
                </div>
                <div class="input-box">
                    <label for="login-form-phone">Телефон</label>
                    <input type="text" class="login-form-input" id="login-form-phone">
                </div>
                <div class="input-box">
                    <label for="login-form-date">Дата рождения</label>
                    <input type="text" class="login-form-input" id="login-form-date">
                </div>
                <div class="button-box">
                    <button type="button" class="save-button">
                        <p class="save-button-text">Зарегистрироваться</p>
                    </button>
                </div>
                <div>
                    <a href="/" class="login-form-register-ref">
                        Вернуться на главную
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>



<script src="../js/register.js"></script>
</body>

</html>

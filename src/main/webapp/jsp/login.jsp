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
    <title>Log in form</title>
    <link rel="stylesheet" href="../css/login.css">
</head>

<body>

<div class="wrapper">
    <div class="main">
        <div class="form-section">
            <form method="post" action="/login" class="left-form">
                <div class="personal-header"><p class="personal-header-text">Вход в аккаунт</p></div>
                <div class="input-box">
                    <label for="login-form-email">Email</label>
                    <input type="text" class="login-form-input" id="login-form-email" name="email">
                </div>
                <div class="input-box">
                    <label for="login-form-date">Пароль</label>
                    <input type="password" class="login-form-input" id="login-form-date" name="password">
                </div>
                <div class="button-box">
                    <input type="submit" value="Войти" class="save-button">
                </div>
                <div>
                    <a href="/" class="login-form-register-ref">
                        Вернуться на главную
                    </a>
                </div>
                <div>
                    <a href="/register" class="login-form-register-ref">
                        Зарегистрироваться
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>



<script src="../js/register.js"></script>
</body>

</html>

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
    <title>Pizzeria profile</title>
    <link rel="stylesheet" href="../css/profile-style.css">
</head>

<body>
<header class="header">
    <div class="wrapper">
        <div class="header-wrapper">
            <a href="/" class="header-logo-link">
                <div class="header-logo-box">
                    <img src="../img/pizza-5-svgrepo-com.svg" alt="Pizzeria Logo" class="header-logo-pic">
                    <p class="header-logo-text">
                        Pizzeria
                    </p>
                </div>
            </a>

            <nav class="header-nav">
                <ul class="header-list">
                    <li class="header-item">
                        <a href="./index.jsp" class="header-link">Меню</a>
                    </li>
                    <li class="header-item">
                        <a href="/vacancy" class="header-link">Вакансии</a>
                    </li>
                </ul>
            </nav>

            <a href="#form" class="header-form-link js-scroll">
                <div class="header-logo-box">
                    <div class="header-busket-box">
                        <a href="/order" class="busket-link">
                            <div class="busket">
                                <div class="busket-quantity">
                                    <button class="busket-button-quantity">
                                        <p class="busket-button-quantity-text">0</p>
                                    </button>
                                    <img src="../img/trolley-svgrepo-com (1).svg" alt="Busket Logo"
                                         class="header-busket-pic">
                                </div>
                                <p class="busket-text">
                                    Корзина
                                </p>
                            </div>
                        </a>
                        <button class="busket-button-orig">
                            <p class="busket-button-text-orig">0.00 BYN</p>
                        </button>
                    </div>
                    <form method="get" action="/logout" class="left-form">
                    <input type="submit" name="logout" value="Выйти">
                    </form>
                </div>
            </a>
        </div>
    </div>
</header>




<div class="wrapper">
    <div class="main">
        <div class="form-section">
            <form action="/profile" class="left-form">
                <div class="personal-header"><p>Личные данные профиля</p></div>
                <div class="input-box">
                    <label for="login-form-street">Улица</label>
                    <input type="text" class="login-form-input" id="login-form-street" name="streetP" value="<%= request.getParameter("streetP") %>">
                </div>
                <div class="input-box">
                    <label for="login-form-house">Дом</label>
                    <input type="text" class="login-form-input" id="login-form-house" name="houseP">
                </div>
                <div class="input-box">
                    <label for="login-form-entrance">Подъезд</label>
                    <input type="text" class="login-form-input" id="login-form-entrance" name="entranceP">
                </div>

                <div class="input-box">
                    <label for="login-form-flat">Квартира</label>
                    <input type="text" class="login-form-input" id="login-form-flat" name="flatP">
                </div>
                <div class="input-box">
                    <label for="login-form-name">Имя и Фамилия</label>
                    <input type="text" class="login-form-input" id="login-form-name" name="firstSecondP">
                </div>
                <div class="input-box">
                    <label for="login-form-email">Email</label>
                    <input type="text" class="login-form-input" id="login-form-email" name="emailP">
                </div>
                <div class="input-box">
                    <label for="login-form-phone">Телефон</label>
                    <input type="text" class="login-form-input" id="login-form-phone" name="telephoneP">
                </div>
                <div class="button-box">
                    <input type="submit" name="logout" value="Сохранить информацию">
                </div>
            </form>
        </div>
    </div>
</div>


<footer class="footer">
    <div class="wrapper">
        <div class="footer-wrapper">
            <div class="footer-info">
                <div class="piece-of-info">
                    <p class="info-title">
                        О нас
                    </p>
                    <ul type="disc" class="info-text">
                        <li>первая пиццерия, созданная на ФПМИ</li>
                        <li>различные контакты</li>
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Доставка
                    </p>
                    <ul type="disc" class="info-text">
                        <li>что-то</li>
                        <li>что-то</li>
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Спонсоры
                    </p>
                    <ul type="disc" class="info-text">
                        <li>что-то</li>
                        <li>что-то</li>
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Соотрудничающие компании
                    </p>
                    <ul type="disc" class="info-text">
                        <li>что-то</li>
                        <li>что-то</li>
                    </ul>
                </div>
            </div>
            <div class="footer-icons">
                <div class="social-networks">
                    <img src="../img/instagram-svgrepo-com.svg" alt="Instagram" class="network-pic">
                    <img src="../img/facebook-svgrepo-com (1).svg" alt="Facebook" class="network-pic">
                    <img src="../img/twitter-svgrepo-com.svg" alt="Twitter" class="network-pic">
                </div>
                <div class="sponsors">
                    <img src="../img/visa-svgrepo-com.svg" alt="Visa" class="sponsor-pic">
                    <img src="../img/mastercard-svgrepo-com.svg" alt="Visa" class="sponsor-pic">
                </div>
            </div>
        </div>
    </div>
</footer>



<script src="../js/profile.js"></script>
</body>

</html>
<%--
  Created by IntelliJ IDEA.
  User: arseni
  Date: 4/30/23
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
                        <a href="/" class="header-link">Меню</a>
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
                                    <div class="busket-button-quantity">
                                        <p class="busket-button-quantity-text">0</p>
                                    </div>
                                    <img src="../img/trolley-svgrepo-com (1).svg" alt="Busket Logo"
                                         class="header-busket-pic">
                                </div>
                                <p class="busket-text">
                                    Корзина
                                </p>
                            </div>
                        </a>
                        <div class="busket-button-orig">
                            <p class="busket-button-text-orig">0.00 BYN</p>
                        </div>
                    </div>
                    <form method="get" action="/logout">
                        <input type="submit" name="logout" class="logout-button" value="Выйти">
                    </form>
                </div>
            </a>
        </div>
    </div>
</header>




<div class="wrapper">
    <div class="main">
        <c:if test="${streetError == true}">
            <script>
                alert("Нет такой улицы");
            </script>
        </c:if>
        <c:if test="${houseNumberError == true}">
            <script>
                alert("Введен неверный номер дома");
            </script>
        </c:if>
        <c:if test="${entranceError == true}">
            <script>
                alert("Введите неверный подъезд");
            </script>
        </c:if>
        <c:if test="${flatNumberError == true}">
            <script>
                alert("Введен неврный номер квартиры");
            </script>
        </c:if>
        <c:if test="${nameError == true}">
            <script>
                alert("Введите имя");
            </script>
        </c:if>
        <c:if test="${emailError == true}">
            <script>
                alert("Введите email");
            </script>
        </c:if>
        <c:if test="${telephoneError == true}">
            <script>
                alert("Введите телефон");
            </script>
        </c:if>
        <div class="form-section">
            <form  method="post" action="/profile" class="left-form">
                <input type="text" class="profile-json" value="<c:out value="${user}"/>">
                <div class="personal-header"><p>Личные данные профиля</p></div>
                <div class="input-box">
                    <label for="login-form-street">Улица</label>
                    <input type="text" class="login-form-input" id="login-form-street" name="street">
                </div>
                <div class="input-box">
                    <label for="login-form-house">Дом</label>
                    <input type="number"  class="login-form-input" id="login-form-house" name="house" >
                </div>
                <div class="input-box">
                    <label for="login-form-entrance">Подъезд</label>
                    <input type="number" class="login-form-input" id="login-form-entrance" name="entrance" >
                </div>

                <div class="input-box">
                    <label for="login-form-flat">Квартира</label>
                    <input type="number"  class="login-form-input" id="login-form-flat" name="flat" >
                </div>
                <div class="input-box">
                    <label for="login-form-name">Имя и Фамилия</label>
                    <input type="text" class="login-form-input" id="login-form-name" name="firstSecond">
                </div>
                <div class="input-box">
                    <label for="login-form-email">Email</label>
                    <input type="text" class="login-form-input" id="login-form-email" name="email">
                </div>
                <div class="input-box">
                    <label for="login-form-phone">Телефон</label>
                    <input type="text" class="login-form-input" id="login-form-phone" name="telephone">
                </div>

                <input type="submit" name="logout" class="save-changes-button" value="Сохранить информацию">
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
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Доставка
                    </p>
                    <ul type="disc" class="info-text">
                        <li>доставка 30 минут</li>
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Спонсоры
                    </p>
                    <ul type="disc" class="info-text">
                        <li>Казанцева Ольга Геннадьевна</li>
                        <li>Сакович Вадим Юрьевич</li>
                    </ul>
                </div>

                <div class="piece-of-info">
                    <p class="info-title">
                        Соотрудничающие компании
                    </p>
                    <ul type="disc" class="info-text">
                        <li>BeHealthy</li>
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
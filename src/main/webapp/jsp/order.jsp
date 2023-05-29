<%--
  Created by IntelliJ IDEA.
  User: arseni
  Date: 4/30/23
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="en">

    <head lang="ru">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pizzeria</title>
        <link rel="stylesheet" href="../choices/Choices-master/public/assets/styles/choices.min.css" />
        <link rel="stylesheet" href="../css/order-style.css">
    </head>

    <body>



    <input type="submit" class="session" name="sessionAttribute" value="${sessionScope.userId}">

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

                <div class="header-telephone-basket">
                    <div class="header-telephone-box">
                        <div class="call">
                            <img src="../img/phone-svgrepo-com.svg" alt="Phone Logo" class="header-phone-pic">
                            <p class="call-number">
                                1442
                            </p>
                        </div>
                        <p class="work-time">
                            8:00-23:00
                        </p>
                    </div>
                    <form method="post" action="/" class="login-form">
                    <input type="submit" class="profile-button-orig" name="profileButton" value="Войти">
                    </form>
                </div>


            </div>
        </div>


        <div class="login-box" id="login-box">
            <h3 class="login-title">
                Войти в аккаунт
            </h3>

            <form action="" class="login-form">
                <label for="login-form-e-mail">E-mail</label>
                <input type="text" class="login-form-input" id="login-form-e-mail">
                <label for="login-form-password">Пароль</label>
                <input type="text" class="login-form-input" id="login-form-password">
                <input type="submit" class="login-sign-in-button">Войти
                <span class="login-form-empty-line"></span>
                <div class="login-form-register-ref-box">
                    <p class="login-form-register-text">Еще нет аккаунта?</p>
                    <a href="/register" class="login-form-register-ref">
                        Зарегистрироваться
                    </a>
                </div>
            </form>
        </div>
    </header>




    <div class="wrapper">
<%--        <div >--%>
            <form class="menu" method="post" action="/order">
            <div class="left-box">

                <p class="order-header">Оформление заказа</p>
                <p class="busket-header">Корзина</p>

                <div class="order-refs">
                    <a href="/" class="change-order">Изменить заказ</a>
                    <button class="busket-button">
                        <p class="busket-button-text">0.00 BYN</p>
                    </button>
                        <input type="text" class="pizza" name="pizza">
                        <input type="text" class="drinks" name="drinks">
                    <input type="submit" name="order" class="order-button" value="Оформить заказ">
                </div>
            </div>

            <div class="right-box">
                <p class="address-header">Адрес доставки:</p>

                <ul type="disc" class="info-text-vacancy">
                    <li class="current-address"><c:out value="${user.addresses.street}"/> Дом <c:out value="${user.addresses.houseNumber}"/>
                        Пд.<c:out value="${user.addresses.entrance}"/> Кв.<c:out value="${user.addresses.flatNumber}"/></li>
                    <li class="point-change-address"><a href="/profile" class="change-address">Изменить адрес</a></li>
                </ul>

                <p class="address-header">Способ оплаты:</p>

                <select name="select" class="payment-form-select">
                    <option value="Картой">Картой</option>
                    <option value="Наличными" selected="selected">Наличными</option>
                </select>

                <p class="address-header">Комментарий к заказу:</p>
                <textarea type="text" class="text-area"></textarea>
            </div>
            </form>
<%--        </div>--%>
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


    <script src="../choices/Choices-master/public/assets/scripts/choices.min.js"></script>

    <script src="../js/order.js"></script>
    </body>

    </html>

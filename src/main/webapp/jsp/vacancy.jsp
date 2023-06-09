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
    <title>Pizzeria vacancies</title>
    <link rel="stylesheet" href="../choices/Choices-master/public/assets/styles/choices.min.css" />
    <link rel="stylesheet" href="../css/vacancy-style.css">
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
            </div>

            <a href="#form" class="header-form-link js-scroll">
                <div class="header-logo-box">
                    <img src="../img/form-svgrepo-com.svg" alt="Form link" class="header-form-pic">
                    <p class="header-form-text">
                        Заполнить анкету
                    </p>
                </div>
            </a>
        </div>
    </div>
</header>




<div class="wrapper">
    <div class="main">
        <div class="main-header">
            <p class="main-header-text">
                Список вакансий
            </p>
            <p class="main-header-text">
                Фильтр вакансий
            </p>
        </div>

        <div class="vacancy-section">
            <div class="vacancy-card">
                <div class="vacancy-card-pic-box">
                    <img src="../img/vacancies/pizzamaker.jpg" alt="Vacancy"
                         class="vacancy-card-pic">
                </div>
                <p class="vacancy-card-title">
                    Пиццер
                </p>
                <div class="description">
                    <ul type="disc" class="info-text-vacancy">
                        <li>зарплата от 1000 рублей в месяц;</li>
                        <li>испытательный срок - 30 дней;</li>
                        <li>профессиональный и карьерный рост;</li>
                        <li>почасовая оплата;</li>
                        <li>обучение на месте;</li>
                        <li>что ты будешь делать: готовить вкуснейшую пиццу,
                            общаться с другими поварами,
                            поддерживать порядок на рабочем месте;</li>
                        <li>скидка 40% на продукцию компании.</li>
                    </ul>
                </div>
            </div>

            <div class="vacancy-card">
                <div class="vacancy-card-pic-box">
                    <img src="../img/vacancies/delivery_foot.jpeg" alt="Vacancy"
                         class="vacancy-card-pic">
                </div>
                <p class="vacancy-card-title">
                    Пеший курьер доставки
                </p>
                <div class="description">
                    <ul type="disc" class="info-text-vacancy">
                        <li>зарплата от 500 рублей в месяц;</li>
                        <li>испытательный срок - 60 дней;</li>
                        <li>профессиональный и карьерный рост;</li>
                        <li>почасовая оплата;</li>
                        <li>обучение на месте;</li>
                        <li>что ты будешь делать: доставлять заказы пешком и на велосипеде,
                            общаться с другими курьерами,
                            поддерживать порядок на рабочем месте;</li>
                        <li>скидка 40% на продукцию компании;</li>
                        <li>ежемесячное премирование.</li>
                    </ul>
                </div>
            </div>

            <div class="vacancy-card">
                <div class="vacancy-card-pic-box">
                    <img src="../img/vacancies/delivery_car.jpg" alt="Vacancy"
                         class="vacancy-card-pic">
                </div>
                <p class="vacancy-card-title">
                    Курьер доставки
                </p>
                <div class="description">
                    <ul type="disc" class="info-text-vacancy">
                        <li>зарплата от 750 рублей в месяц;</li>
                        <li>испытательный срок - 45 дней;</li>
                        <li>профессиональный и карьерный рост;</li>
                        <li>почасовая оплата;</li>
                        <li>обучение на месте;</li>
                        <li>амортизация автомобиля до 200 рублей;</li>
                        <li>что ты будешь делать: доставлять заказы на своем автомобиле,
                            общаться с другими участникамми коллектива,
                            поддерживать порядок на рабочем месте;</li>
                        <li>гибкий график;</li>
                        <li>скидка 40% на продукцию компании.</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="main-header">
            <p id="form" class="main-header-text">
                Заполнить анкету
            </p>
        </div>

        <div class="form-section">
            <form method="post" action="/vacancy" class="left-form">
                <input type="text" class="vacancy-json" value="<c:out value="${user}"/>">
                <div class="input-box">
                    <label>Адрес:</label>
                    <p class="request-text" id="address"></p>
                </div>

                <div class="input-box">
                    <label>Имя и Фамилия:</label>
                    <p class="request-text" id="name"></p>
                </div>
                <div class="input-box">
                    <label>Email:</label>
                    <p class="request-text" id="email"></p>
                </div>
                <div class="input-box">
                    <label>Телефон:</label>
                    <p class="request-text" id="phone"></p>
                </div>
                <div class="input-box">
                    <label for="login-form-position">Должность:</label>
                    <select class="login-form-select" id="login-form-position" name="choicer">
                        <option value="Пиццер">Пиццер</option>
                        <option value="Водитель-курьер">Водитель-курьер</option>
                        <option value="Пеший курьер" selected="selected">Пеший курьер</option>
                    </select>
                </div>
                <div class="send-buttons">
                    <input type="submit" value="Отправить заявку" class="order-button">
                    <a href="/profile" class="change-profile">Изменить данные</a>
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

<script src="../js/vacancy.js"></script>
</body>

</html>

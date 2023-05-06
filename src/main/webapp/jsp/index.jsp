<%--
  Created by IntelliJ IDEA.
  User: arseni
  Date: 4/18/23
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizzeria</title>
    <link rel="stylesheet" href="../choices/Choices-master/public/assets/styles/choices.min.css" />
    <link href="../css/index-style.css" rel="stylesheet" type="text/css">
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
                        <a href="#drinks" class="header-link js-scroll">Напитки</a>
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

                <div class="header-busket-box">
                    <a href="/order" class="busket-link">
                        <div class="busket">
                            <div class="busket-quantity">
                                <button class="busket-button-quantity">
                                    <p class="busket-button-quantity-text">0</p>
                                </button>
                                <img src="../img/trolley-svgrepo-com%20(1).svg" alt="Busket Logo"
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

            <input type="submit" class="profile-button-orig" name="profileButton" value="Войти">
        </div>
    </div>


    <div class="login-box" id="login-box">
        <h3 class="login-title">
            Войти в аккаунт
        </h3>

        <form method="post" action="/" class="login-form" >
            <label for="login-form-e-mail">E-mail</label>
            <input type="text" class="login-form-input" id="login-form-e-mail" name="email">
            <label for="login-form-password">Пароль</label>
            <input type="text" class="login-form-input" id="login-form-password" name="password">
            <input type="submit" class="login-sign-in-button" value="Войти">
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
    <div class="menu">
        <div class="menu-header">
            <p class="menu-header-text">
                пицца
            </p>
            <p class="menu-header-text">
                фильтр
            </p>
        </div>

        <div class="pizza-section">

            <div class="pizza-card" id="pizza-1">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/peperoni.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">Пепперони</p>
                <div class="box-choices">
                    <select id="size-1" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select id="dough-1" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">пицца-соус, пепперони, сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-1">16.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-1">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div class="pizza-card" id="pizza-2">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/4_сыра.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">4 сыра</p>
                <div class="box-choices">
                    <select name="" id="size-2" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select name="" id="dough-2" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">сырный соус, сливочный сыр, сыр фета, сыр дорблю (может отличаться от изображения на сайте), сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-2">18.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-2">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div class="pizza-card" id="pizza-3">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/гавайская.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">Гавайская</p>
                <div class="box-choices">
                    <select name="" id="size-3" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select name="" id="dough-3" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-3">12.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-3">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div class="pizza-card" id="pizza-4">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/барбекю.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">Барбекю</p>
                <div class="box-choices">
                    <select name="" id="size-4" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select name="" id="dough-4" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">пицца-соус, грудинка (свинина), филе цыпленка, свежий лук, соус барбекю, сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-4">16.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-4">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div class="pizza-card"id="pizza-5">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/с%20креветкамиjpg.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">Сальмоне</p>
                <div class="box-choices">
                    <select name="" id="size-5" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select name="" id="dough-5" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">соус пармеджано, креветки, сливочный сыр, сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-5">14.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-5">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div class="pizza-card"id="pizza-6">
                <div class="pizza-card-pic-box">
                    <img src="../img/pizzas/деревенская.jpg" alt="Pizza"
                         class="pizza-card-pic">
                </div>
                <p class="pizza-card-title">Деревенская</p>
                <div class="box-choices">
                    <select name="" id="size-6" class="menu-choices size-select">
                        <option value="Big">Большой</option>
                        <option value="Mid" selected="selected">Средний</option>
                    </select>
                    <select name="" id="dough-6" class="menu-choices dough-select">
                        <option value="Thick">Толстое</option>
                        <option value="Thin" selected="selected">Тонкое</option>
                    </select>
                </div>
                <p class="ingredients-text">американский соус ранч, грудинка (свинина), свежий лук, соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик</p>
                <div class="pizza-card-buttons">
                    <button class="busket-button">
                        <p class="busket-button-text" id="price-6">18.99 BYN</p>
                    </button>
                    <button class="profile-button" id="busket-6">
                        <p class="profile-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>
        </div>

        <div id="drinks" class="menu-header">
            <p class="menu-header-text">
                напитки
            </p>
            <p class="menu-header-text">
                фильтр
            </p>
        </div>

        <div class="drinks-section">
            <div id="drink-1" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/cola.png" alt="Drink"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Coca-Cola, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-1">1.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-1">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-2" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/cola_big.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Coca-Cola, 1</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-2">2.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-2">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-3" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/cola_zero_.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Coca-Cola Zero, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-3">1.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-3">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-4" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/cola_zero_big.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Coca-Cola Zero, 1</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-4">2.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-4">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-5" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/fanta.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Fanta, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-5">1.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-5">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-6" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/fanta_big.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Fanta, 1</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-6">2.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-6">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-7" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/sprite.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Sprite, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-7">1.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-7">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-8" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/sprite_big.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Sprite, 1</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-8">2.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-8">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-9" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/whiskey.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Ballentine's, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-9">62.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-9">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>

            <div id="drink-10" class="drink-card">
                <div class="drink-card-pic-box">
                    <img src="../img/drinks/water.png" alt="Pizza"
                         class="drink-card-pic">
                </div>
                <p class="drink-card-title">Bonaqua, 0.5</p>
                <div class="drink-card-buttons">
                    <button class="drink-price-button">
                        <p class="drink-price-button-text" id="dprice-10">1.99 BYN</p>
                    </button>
                    <button class="drink-button" id="dbusket-10">
                        <p class="drink-button-text">
                            В корзину
                        </p>
                    </button>
                </div>
            </div>
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
                    <img src="../img/facebook-svgrepo-com%20(1).svg" alt="Facebook" class="network-pic">
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

<script src="../js/main.js"></script>
</body>

</html>

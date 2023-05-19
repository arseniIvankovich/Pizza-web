import React from "react";
import "../css/Index.css";

class Index extends React.Component {
    render() {
        return (
            <div className="wrapper">
                <div className="menu">
                    <div className="menu-header">
                        <p className="menu-header-text">
                            пицца
                        </p>
                        <p className="menu-header-text">
                            фильтр
                        </p>
                    </div>

                    <div className="pizza-section">
                        <div className="pizza-card" id="pizza-1">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/peperoni.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">Пепперони</p>
                            <div className="box-choices">
                                <select id="size-1" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select id="dough-1" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">пицца-соус, пепперони, сыр моцарелла, базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-1">16.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-1">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div className="pizza-card" id="pizza-2">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/4_сыра.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">4 сыра</p>
                            <div className="box-choices">
                                <select name="" id="size-2" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select name="" id="dough-2" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">сырный соус, сливочный сыр, сыр фета, сыр дорблю (может
                                отличаться от изображения на сайте), сыр моцарелла, базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-2">18.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-2">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div className="pizza-card" id="pizza-3">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/гавайская.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">Гавайская</p>
                            <div className="box-choices">
                                <select name="" id="size-3" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select name="" id="dough-3" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">сырный соус, ветчина, филе цыпленка, ананасы, сыр моцарелла,
                                базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-3">12.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-3">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div className="pizza-card" id="pizza-4">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/барбекю.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">Барбекю</p>
                            <div className="box-choices">
                                <select name="" id="size-4" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select name="" id="dough-4" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">пицца-соус, грудинка (свинина), филе цыпленка, свежий лук,
                                соус барбекю, сыр моцарелла, базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-4">16.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-4">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div className="pizza-card" id="pizza-5">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/с%20креветкамиjpg.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">Сальмоне</p>
                            <div className="box-choices">
                                <select name="" id="size-5" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select name="" id="dough-5" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">соус пармеджано, креветки, сливочный сыр, сыр моцарелла,
                                базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-5">14.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-5">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div className="pizza-card" id="pizza-6">
                            <div className="pizza-card-pic-box">
                                <img src="../img/pizzas/деревенская.jpg" alt="Pizza"
                                     className="pizza-card-pic" />
                            </div>
                            <p className="pizza-card-title">Деревенская</p>
                            <div className="box-choices">
                                <select name="" id="size-6" className="menu-choices size-select">
                                    <option value="Mid" selected="selected">Средний</option>
                                    <option value="Big">Большой</option>
                                </select>
                                <select name="" id="dough-6" className="menu-choices dough-select">
                                    <option value="Thin" selected="selected">Тонкое</option>
                                    <option value="Thick">Толстое</option>
                                </select>
                            </div>
                            <p className="ingredients-text">американский соус ранч, грудинка (свинина), свежий лук,
                                соленые огурцы, свежие шампиньоны, сыр моцарелла, базилик</p>
                            <div className="pizza-card-buttons">
                                <button className="busket-button">
                                    <p className="busket-button-text" id="price-6">18.99 BYN</p>
                                </button>
                                <button className="profile-button" id="busket-6">
                                    <p className="profile-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>
                    </div>

                    <div id="drinks" className="menu-header">
                        <p className="menu-header-text">
                            напитки
                        </p>
                        <p className="menu-header-text">
                            фильтр
                        </p>
                    </div>

                    <div className="drinks-section">
                        <div id="drink-1" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/cola.png" alt="Drink"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Coca-Cola, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-1">1.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-1">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-2" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/cola_big.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Coca-Cola, 1</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-2">2.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-2">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-3" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/cola_zero_.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Coca-Cola Zero, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-3">1.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-3">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-4" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/cola_zero_big.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Coca-Cola Zero, 1</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-4">2.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-4">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-5" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/fanta.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Fanta, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-5">1.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-5">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-6" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/fanta_big.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Fanta, 1</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-6">2.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-6">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-7" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/sprite.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Sprite, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-7">1.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-7">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-8" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/sprite_big.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Sprite, 1</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-8">2.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-8">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-9" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/whiskey.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Ballentine's, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-9">62.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-9">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>

                        <div id="drink-10" className="drink-card">
                            <div className="drink-card-pic-box">
                                <img src="../img/drinks/water.png" alt="Pizza"
                                     className="drink-card-pic" />
                            </div>
                            <p className="drink-card-title">Bonaqua, 0.5</p>
                            <div className="drink-card-buttons">
                                <button className="drink-price-button">
                                    <p className="drink-price-button-text" id="dprice-10">1.99 BYN</p>
                                </button>
                                <button className="drink-button" id="dbusket-10">
                                    <p className="drink-button-text">
                                        В корзину
                                    </p>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Index;
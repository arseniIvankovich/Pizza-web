import React, {useEffect} from "react";
import "../choices/Choices-master/public/assets/scripts/choices.min.js";

import "../css/Index.css";
import "../choices/Choices-master/public/assets/styles/choices.min.css";


let ids = [
    1, 5, 9, 13, 17, 21
]

let capacities = [
    0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 0.5
]

function sizeSelect(price_name, size_name) {
    var text = document.getElementById(price_name).textContent;
    var index = text.search(" ");
    if (document.getElementById(size_name).options[0].value === "Big") {
        document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) + 5).toFixed(2) + " BYN";
    }
    else {
        document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) - 5).toFixed(2) + " BYN";
    }
}

function doughSelect(price_name, dough_name) {
    var text = document.getElementById(price_name).textContent;
    var index = text.search(" ");
    if (document.getElementById(dough_name).options[0].value === "Thick") {
        document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) + 2).toFixed(2) + " BYN";
    }
    else {
        document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) - 2).toFixed(2) + " BYN";
    }
}

function pizzaToBusket(element_id) {
    const price_name = "price-" + element_id;
    let pizza_card = document.getElementById("pizza-" + element_id);
    let object = JSON.parse(localStorage["pizza"]);
    let flag = false;
    let ind = 0;
    let bool_size1 = true, bool_dough1 = true;
    if (pizza_card.querySelector(".size-select").options[0].textContent === "Большой") bool_size1 = false;
    if (pizza_card.querySelector(".dough-select").options[0].textContent === "Толстое") bool_dough1 = false;
    for (let i = 0; i < object.length; i++) {
        if (object[i].name === pizza_card.querySelector(".pizza-card-title").textContent
            && object[i].size === bool_size1
            && object[i].doughType === bool_dough1)
        {
            flag = true;
            ind = i;
        }
    }
    if (flag) object[ind].counter = object[ind].counter + 1;
    else {
        let pizza_index = pizza_card.querySelector(".busket-button-text").textContent.search(" ");
        let pizza_price = parseFloat(pizza_card.querySelector(".busket-button-text").textContent.substring(0, pizza_index));
        let addition = 0;
        let w = 0;
        let bool_size = true, bool_dough = true;
        if (pizza_card.querySelector(".size-select").options[0].textContent === "Большой") bool_size = false;
        if (pizza_card.querySelector(".dough-select").options[0].textContent === "Толстое") bool_dough = false;
        if (!bool_dough && bool_size) {
            addition = 1;
            w = 50;
        }
        if (bool_dough && !bool_size) {
            addition = 2;
            w = 150;
        }
        if (!bool_dough && !bool_size) {
            addition = 3;
            w = 200;
        }
        let pizza_item = {
            id: ids[element_id - 1] + addition,
            name: pizza_card.querySelector(".pizza-card-title").textContent,
            size: bool_size,
            doughType: bool_dough,
            counter: 1,
            weight: 500 + w,
            ingredients: pizza_card.querySelector(".ingredients-text").textContent,
            price: pizza_price.toFixed(2)
        }
        object.push(pizza_item);
    }
    localStorage["pizza"] = JSON.stringify(object);
    console.log(object);

    var text = document.getElementById(price_name).textContent;
    var index = text.search(" ");
    //var busket_index = busket.textContent.search(" ");
    const prev_price = parseFloat(localStorage["busket-price"]);
    var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
    localStorage["busket-price"] = new_price.toFixed(2);
    //busket.textContent = new_price.toFixed(2) + " BYN";
    const new_quantity = parseInt(localStorage["busket-quantity"]) + 1;
    localStorage['busket-quantity'] = new_quantity;
}

function drinkToBusket(element_id) {
    const price_name = "dprice-" + element_id;

    let drink_card = document.getElementById("drink-" + element_id);
    let object = JSON.parse(localStorage["drinks"]);
    let flag = false;
    let ind = 0;
    for (let i = 0; i < object.length; i++) {
        if ((object[i].name + ", " + object[i].capacity) === drink_card.querySelector(".drink-card-title").textContent)
        {
            flag = true;
            ind = i;
        }
    }
    if (flag) object[ind].counter = object[ind].counter + 1;
    else {
        let name_index = drink_card.querySelector(".drink-card-title").textContent.search(",");
        let drink_index = drink_card.querySelector(".drink-price-button-text").textContent.search(" ");
        let drink_price = parseFloat(drink_card.querySelector(".drink-price-button-text").textContent.substring(0, drink_index));
        let drink_item = {
            id: element_id,
            name: drink_card.querySelector(".drink-card-title").textContent.substring(0, name_index),
            counter: 1,
            capacity: capacities[element_id - 1],
            price: drink_price.toFixed(2)
        }
        object.push(drink_item);
    }
    localStorage["drinks"] = JSON.stringify(object);

    console.log(object);

    var text = document.getElementById(price_name).textContent;
    var index = text.search(" ");
    //var busket_index = busket.textContent.search(" ");
    const prev_price = parseFloat(localStorage["busket-price"]);
    var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
    localStorage["busket-price"] = new_price.toFixed(2);
    //busket.textContent = new_price.toFixed(2) + " BYN";
    const new_quantity = parseInt(localStorage["busket-quantity"]) + 1;
    localStorage['busket-quantity'] = new_quantity;
}

function Index() {
    useEffect(() => {
        document.querySelectorAll(".menu-choices").forEach(element => {
            // eslint-disable-next-line no-undef
            new Choices(element, {
                searchEnabled: false,
                itemSelectText: ""
            })
        });

        if (typeof localStorage["pizza"] === 'undefined') {
            localStorage["pizza"] = JSON.stringify([]);
        }
        if (typeof localStorage["drinks"] === 'undefined') {
            localStorage["drinks"] = JSON.stringify([]);
        }

        if (typeof localStorage["busket-price"] === 'undefined') {
            localStorage["busket-price"] = '0.00';
        }
        if (typeof localStorage["busket-quantity"] === 'undefined') {
            localStorage["busket-quantity"] = '0';
        }

        (function () {

            const smoothScroll = function (targetEl, duration) {
                let target = document.querySelector(targetEl);
                let targetPosition = target.getBoundingClientRect().top;
                let startPosition = window.pageYOffset;
                let startTime = null;

                const ease = function(t,b,c,d) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * t * t + b;
                    t--;
                    return -c / 2 * (t * (t - 2) - 1) + b;
                };

                const animation = function(currentTime){
                    if (startTime === null) startTime = currentTime;
                    const timeElapsed = currentTime - startTime;
                    const run = ease(timeElapsed, startPosition, targetPosition, duration);
                    window.scrollTo(0,run);
                    if (timeElapsed < duration) requestAnimationFrame(animation);
                };
                requestAnimationFrame(animation);

            };

            const scrollTo = function () {
                const links = document.querySelectorAll('.js-scroll');
                links.forEach(each => {
                    each.addEventListener('click', function () {
                        const currentTarget = this.getAttribute('href');
                        smoothScroll(currentTarget, 800);
                    });
                });
            };
            scrollTo();
        }());
    });

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
                            <img src="./img/pizzas/peperoni.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">Пепперони</p>
                        <div className="box-choices">
                            <select id="size-1" className="menu-choices size-select" onChange={() => sizeSelect("price-1", "size-1")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select id="dough-1" className="menu-choices dough-select" onChange={() => doughSelect("price-1", "dough-1")}>
                                <option value="Thin" selected="selected">Тонкое</option>
                                <option value="Thick">Толстое</option>
                            </select>
                        </div>
                        <p className="ingredients-text">пицца-соус, пепперони, сыр моцарелла, базилик</p>
                        <div className="pizza-card-buttons">
                            <button className="busket-button">
                                <p className="busket-button-text" id="price-1">16.99 BYN</p>
                            </button>
                            <button className="pizza-button" id="busket-1" onClick={() => pizzaToBusket("1")}>
                                <p className="profile-button-text">
                                    В корзину
                                </p>
                            </button>
                        </div>
                    </div>

                    <div className="pizza-card" id="pizza-2">
                        <div className="pizza-card-pic-box">
                            <img src="./img/pizzas/4_sira.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">4 сыра</p>
                        <div className="box-choices">
                            <select name="" id="size-2" className="menu-choices size-select" onChange={() => sizeSelect("price-2", "size-2")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select name="" id="dough-2" className="menu-choices dough-select" onChange={() => doughSelect("price-2", "dough-2")}>
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
                            <button className="pizza-button" id="busket-2" onClick={() => pizzaToBusket("2")}>
                                <p className="profile-button-text">
                                    В корзину
                                </p>
                            </button>
                        </div>
                    </div>

                    <div className="pizza-card" id="pizza-3">
                        <div className="pizza-card-pic-box">
                            <img src="./img/pizzas/hawaii.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">Гавайская</p>
                        <div className="box-choices">
                            <select name="" id="size-3" className="menu-choices size-select" onChange={() => sizeSelect("price-3", "size-3")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select name="" id="dough-3" className="menu-choices dough-select" onChange={() => doughSelect("price-3", "dough-3")}>
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
                            <button className="pizza-button" id="busket-3" onClick={() => pizzaToBusket("3")}>
                                <p className="profile-button-text">
                                    В корзину
                                </p>
                            </button>
                        </div>
                    </div>

                    <div className="pizza-card" id="pizza-4">
                        <div className="pizza-card-pic-box">
                            <img src="./img/pizzas/barbecu.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">Барбекю</p>
                        <div className="box-choices">
                            <select name="" id="size-4" className="menu-choices size-select" onChange={() => sizeSelect("price-4", "size-4")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select name="" id="dough-4" className="menu-choices dough-select" onChange={() => doughSelect("price-4", "dough-4")}>
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
                            <button className="pizza-button" id="busket-4" onClick={() => pizzaToBusket("4")}>
                                <p className="profile-button-text">
                                    В корзину
                                </p>
                            </button>
                        </div>
                    </div>

                    <div className="pizza-card" id="pizza-5">
                        <div className="pizza-card-pic-box">
                            <img src="./img/pizzas/krevetki.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">Сальмоне</p>
                        <div className="box-choices">
                            <select name="" id="size-5" className="menu-choices size-select" onChange={() => sizeSelect("price-5", "size-5")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select name="" id="dough-5" className="menu-choices dough-select" onChange={() => doughSelect("price-5", "dough-5")}>
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
                            <button className="pizza-button" id="busket-5" onClick={() => pizzaToBusket("5")}>
                                <p className="profile-button-text">
                                    В корзину
                                </p>
                            </button>
                        </div>
                    </div>

                    <div className="pizza-card" id="pizza-6">
                        <div className="pizza-card-pic-box">
                            <img src="./img/pizzas/derev.jpg" alt="Pizza"
                                 className="pizza-card-pic" />
                        </div>
                        <p className="pizza-card-title">Деревенская</p>
                        <div className="box-choices">
                            <select name="" id="size-6" className="menu-choices size-select" onChange={() => sizeSelect("price-6", "size-6")}>
                                <option value="Mid" selected="selected">Средний</option>
                                <option value="Big">Большой</option>
                            </select>
                            <select name="" id="dough-6" className="menu-choices dough-select" onChange={() => doughSelect("price-6", "dough-6")}>
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
                            <button className="pizza-button" id="busket-6" onClick={() => pizzaToBusket("6")}>
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
                            <button className="drink-button" id="dbusket-1" onClick={() => drinkToBusket("1")}>
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
                            <button className="drink-button" id="dbusket-2" onClick={() => drinkToBusket("2")}>
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
                            <button className="drink-button" id="dbusket-3" onClick={() => drinkToBusket("3")}>
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
                            <button className="drink-button" id="dbusket-4" onClick={() => drinkToBusket("4")}>
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
                            <button className="drink-button" id="dbusket-5" onClick={() => drinkToBusket("5")}>
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
                            <button className="drink-button" id="dbusket-6" onClick={() => drinkToBusket("6")}>
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
                            <button className="drink-button" id="dbusket-7" onClick={() => drinkToBusket("7")}>
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
                            <button className="drink-button" id="dbusket-8" onClick={() => drinkToBusket("8")}>
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
                            <button className="drink-button" id="dbusket-9" onClick={() => drinkToBusket("9")}>
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
                            <button className="drink-button" id="dbusket-10" onClick={() => drinkToBusket("10")}>
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

export default Index;
import React, {useEffect} from "react";
import "../choices/Choices-master/public/assets/scripts/choices.min.js";

import "../css/Order.css";
import "../choices/Choices-master/public/assets/styles/choices.min.css";
import {NavLink} from "react-router-dom";
import $ from "jquery";


let pizza_images = new Map([
    ['Пепперони', "../img/pizzas/peperoni.jpg"],
    ['4 сыра', "../img/pizzas/4_sira.jpg"],
    ['Гавайская', "../img/pizzas/hawaii.jpg"],
    ['Барбекю', "../img/pizzas/barbecu.jpg"],
    ['Сальмоне', "../img/pizzas/krevetki.jpg"],
    ['Деревенская', "../img/pizzas/derev.jpg"]
]);

let drink_images = new Map([
    ['Coca-Cola, 0.5', "../img/drinks/cola.png"],
    ['Coca-Cola, 1', "../img/drinks/cola_big.png"],
    ['Coca-Cola Zero, 0.5', "../img/drinks/cola_zero_.png"],
    ['Coca-Cola Zero, 1', "../img/drinks/cola_zero_big.png"],
    ['Fanta, 0.5', "../img/drinks/fanta.png"],
    ['Fanta, 1', "../img/drinks/fanta_big.png"],
    ['Sprite, 0.5', "../img/drinks/sprite.png"],
    ['Sprite, 1', "../img/drinks/sprite_big.png"],
    ['Ballentine\'s, 0.5', "../img/drinks/whiskey.png"],
    ['Bonaqua, 0.5', "../img/drinks/water.png"]
]);

function sendHttpPostRequest() {
    var urlEndPoint = 'http://localhost:8083/order';
    var object = {
        username: "abcdefgh",
        password: "12345678"
    };
    var objectJSON = JSON.stringify(object);
    $.ajax({
        type: "POST",
        url: urlEndPoint,
        dataType: 'json',
        data: {object: objectJSON},
        success: function(response) {
            console.log("ura");
        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}

function loadPizza() {
    // if (document.querySelector(".session").value !== '') {
    //     document.querySelector(".profile-button-orig").value = "Личный кабинет";
    // }

    let next_elem = document.querySelector(".order-refs");

    let pizza = JSON.parse(localStorage["pizza"]);
    for (let i = 0; i < pizza.length; i++) {
        let size = "Средний", dough = "Тонкое";
        if (pizza[i].size === false) size = "Большой";
        if (pizza[i].doughType === false) dough = "Толстое";
        next_elem.insertAdjacentHTML("beforebegin", `<div id="` + (i + 1) + `" class="order-item">
        <img src="` + pizza_images.get(pizza[i].name) + `" alt="Order item" class="order-item-pic">
        <div class="description-box">
        <p class="order-item-title">` + pizza[i].name + `</p>
        <p class="order-item-size">` + size + `</p>
        <p class="order-item-dough">` + dough + `</p>
        </div>
        <div class="order-item-quantity">
            <input type="image" src="./img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus" />
            <div class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + pizza[i].counter + `</p>
            </div>
            <input type="image" src="./img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus" />
        </div>
        <div class="busket-button-orig">
            <p class="busket-button-text-orig">` + (parseFloat(pizza[i].price) * pizza[i].counter).toFixed(2) + ` BYN</p>
        </div>
        </div>`);
    }
}

function loadDrinks() {
    let next_elem = document.querySelector(".order-refs");

    let drinks = JSON.parse(localStorage["drinks"]);
    for (let i = 0; i < drinks.length; i++) {
        next_elem.insertAdjacentHTML("beforebegin", `<div id="` + (10000 + i + 1) + `" class="order-item">
        <img src="` + drink_images.get(drinks[i].name + ', ' + drinks[i].capacity) + `" alt="Order item" class="order-item-pic">
        <p class="order-item-title">` + drinks[i].name + ', ' + drinks[i].capacity + `</p>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus" />
            <div class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + drinks[i].counter + `</p>
            </div>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus" />
        </div>
        <div class="busket-button-orig">
            <p class="busket-button-text-orig">` + (parseFloat(drinks[i].price) * drinks[i].counter).toFixed(2)  + ` BYN</p>
        </div>
        </div>`);
    }
}

function Order() {

    useEffect(() => {
        document.querySelectorAll(".payment-form-select").forEach(element => {
            // eslint-disable-next-line no-undef
            new Choices(element, {
                searchEnabled: false,
                itemSelectText: ""
            })
        });

        loadPizza();

        loadDrinks();

        document.querySelector(".busket-button-text-1").textContent = parseFloat(localStorage["busket-price"]).toFixed(2) + " BYN";

        let total_button = document.querySelector(".busket-button-text-1");

        let order_item = document.querySelectorAll(".order-item");

        order_item.forEach(element => {
            let price_button = element.querySelector(".busket-button-text-orig");

            let minus = element.querySelector(".order-item-sign-minus");
            minus.addEventListener("click", function() {
                let flag = false;//pizza
                if (element.id / 10000 >= 1) flag = true;//drink
                let object;
                let ind;
                let bool_size = true, bool_dough = true;
                if (!flag) {
                    if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
                    if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
                    object = JSON.parse(localStorage["pizza"]);
                    ind = -1;
                    for (let i = 0; i < object.length; i++) {
                        if (object[i].name === element.querySelector(".order-item-title").textContent
                            && object[i].size === bool_size
                            && object[i].doughType === bool_dough)
                        {
                            ind = i;
                            break;
                        }
                    }
                }
                else {
                    object = JSON.parse(localStorage["drinks"]);
                    ind = -1;
                    for (let i = 0; i < object.length; i++) {
                        if (object[i].name + ", " + object[i].capacity === element.querySelector(".order-item-title").textContent)
                        {
                            ind = i;
                            break;
                        }
                    }
                }
                //////////////////////////////////////////////////////////////////////////////////////
                console.log(object[ind]);
                localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) - 1;
                localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) - object[ind].price).toFixed(2);
                total_button.textContent = localStorage["busket-price"] + " BYN";
                if (object[ind].counter > 1) {
                    object[ind].counter = object[ind].counter - 1;
                    price_button.textContent = (object[ind].counter * object[ind].price).toFixed(2) + " BYN";
                    let quantity_field = element.querySelector(".busket-button-quantity-text");
                    quantity_field.textContent = (parseInt(quantity_field.textContent) - 1).toString();
                }
                else {
                    let new_object = [];
                    for (let i = 0; i < ind; i++) {
                        new_object.push(object[i]);
                    }
                    for (let i = ind + 1; i < object.length; i++) {
                        new_object.push(object[i]);
                    }
                    object = new_object;
                    element.style.display = 'none';
                }

                if (!flag) {
                    localStorage["pizza"] = JSON.stringify(object);
                    //document.querySelector(".pizza").value = localStorage["pizza"];
                }
                else {
                    localStorage["drinks"] = JSON.stringify(object);
                    //document.querySelector(".drinks").value = localStorage["drinks"];
                }
            });
        });

        order_item.forEach(element => {
            let price_button = element.querySelector(".busket-button-text-orig");
            let plus = element.querySelector(".order-item-sign-plus");
            plus.addEventListener("click", function() {
                let flag = false;//pizza
                if (element.id / 10000 >= 1) flag = true;//drink
                let object;
                let ind;
                let bool_size = true, bool_dough = true;
                if (!flag) {
                    if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
                    if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
                    object = JSON.parse(localStorage["pizza"]);
                    ind = -1;
                    for (let i = 0; i < object.length; i++) {
                        if (object[i].name === element.querySelector(".order-item-title").textContent
                            && object[i].size === bool_size
                            && object[i].doughType === bool_dough)
                        {
                            ind = i;
                            break;
                        }
                    }
                }
                else {
                    object = JSON.parse(localStorage["drinks"]);
                    ind = -1;
                    for (let i = 0; i < object.length; i++) {
                        if (object[i].name + ", " + object[i].capacity === element.querySelector(".order-item-title").textContent)
                        {
                            ind = i;
                            break;
                        }
                    }
                }
                //////////////////////////////////////////////////////////////////////////////////////
                console.log(object[ind]);
                localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) + 1;
                localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) + parseFloat(object[ind].price)).toFixed(2);
                total_button.textContent = localStorage["busket-price"] + " BYN";
                object[ind].counter = object[ind].counter + 1;
                price_button.textContent = (object[ind].counter * object[ind].price).toFixed(2) + " BYN";
                let quantity_field = element.querySelector(".busket-button-quantity-text");
                quantity_field.textContent = (parseInt(quantity_field.textContent) + 1).toString();

                if (!flag) {
                    localStorage["pizza"] = JSON.stringify(object);
                    //document.querySelector(".pizza").value = localStorage["pizza"];
                }
                else {
                    localStorage["drinks"] = JSON.stringify(object);
                    //document.querySelector(".drinks").value = localStorage["drinks"];
                }
            });
        });
    });

    return (
        <div className="wrapper">
            <div className="menu-1">
                <div className="left-box">

                    <p className="order-header">Оформление заказа</p>
                    <p className="busket-header">Корзина</p>

                    <div className="order-refs">
                        <NavLink to="/" className="change-order">Изменить заказ</NavLink>
                        <div className="busket-button-1">
                            <p className="busket-button-text-1">0.00 BYN</p>
                        </div>
                        <input type="submit" name="order" className="order-button" value="Оформить заказ" onClick={() => sendHttpPostRequest()} />
                    </div>
                </div>

                <div className="right-box">
                    <p className="address-header">Адрес доставки:</p>

                    <ul type="disc" className="info-text-order">
                        <li className="current-address">Дом Пд. Кв.</li>
                        <li className="point-change-address"><a href="/profile" className="change-address">Изменить адрес</a></li>
                    </ul>

                    <p className="address-header">Способ оплаты:</p>

                    <select name="select" className="payment-form-select">
                        <option value="Картой">Картой</option>
                        <option value="Наличными" selected="selected">Наличными</option>
                    </select>

                    <p className="address-header">Комментарий к заказу:</p>
                    <textarea type="text" className="text-area"></textarea>
                </div>
            </div>
        </div>
    );
}



// let total_button = document.querySelector(".busket-button-text-1");
//
// let order_item = document.querySelectorAll(".order-item");
//
// order_item.forEach(element => {
//     //let price_button = element.querySelector(".busket-button-text-orig");
//
//     let minus = element.querySelector(".order-item-sign-minus");
//     minus.addEventListener("click", function() {
//         let flag = false;//pizza
//         if (element.id / 10000 >= 1) flag = true;//drink
//         let object;
//         let ind;
//         let bool_size = true, bool_dough = true;
//         if (!flag) {
//             if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
//             if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
//             object = JSON.parse(localStorage["pizza"]);
//             ind = -1;
//             for (let i = 0; i < object.length; i++) {
//                 if (object[i].name === element.querySelector(".order-item-title").textContent
//                     && object[i].size === bool_size
//                     && object[i].doughType === bool_dough)
//                 {
//                     ind = i;
//                     break;
//                 }
//             }
//         }
//         else {
//             object = JSON.parse(localStorage["drinks"]);
//             ind = -1;
//             for (let i = 0; i < object.length; i++) {
//                 if (object[i].name + ", " + object[i].capacity === element.querySelector(".order-item-title").textContent)
//                 {
//                     ind = i;
//                     break;
//                 }
//             }
//         }
//         //////////////////////////////////////////////////////////////////////////////////////
//         console.log(object[ind]);
//         localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) - 1;
//         localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) - object[ind].price).toFixed(2);
//         total_button.textContent = localStorage["busket-price"] + " BYN";
//         if (object[ind].counter > 1) {
//             object[ind].counter = object[ind].counter - 1;
//             //price_button.textContent = (object[ind].counter * object[ind].price).toFixed(2) + " BYN";
//             //let quantity_field = element.querySelector(".busket-button-quantity-text");
//             //quantity_field.textContent = (parseInt(quantity_field.textContent) - 1).toString();
//         }
//         else {
//             let new_object = [];
//             for (let i = 0; i < ind; i++) {
//                 new_object.push(object[i]);
//             }
//             for (let i = ind + 1; i < object.length; i++) {
//                 new_object.push(object[i]);
//             }
//             object = new_object;
//             element.style.display = 'none';
//         }
//
//         if (!flag) {
//             localStorage["pizza"] = JSON.stringify(object);
//             //document.querySelector(".pizza").value = localStorage["pizza"];
//         }
//         else {
//             localStorage["drinks"] = JSON.stringify(object);
//             //document.querySelector(".drinks").value = localStorage["drinks"];
//         }
//     });
// });
//
// order_item.forEach(element => {
//     //let price_button = element.querySelector(".busket-button-text-orig");
//     let plus = element.querySelector(".order-item-sign-plus");
//     plus.addEventListener("click", function() {
//         let flag = false;//pizza
//         if (element.id / 10000 >= 1) flag = true;//drink
//         let object;
//         let ind;
//         let bool_size = true, bool_dough = true;
//         if (!flag) {
//             if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
//             if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
//             object = JSON.parse(localStorage["pizza"]);
//             ind = -1;
//             for (let i = 0; i < object.length; i++) {
//                 if (object[i].name === element.querySelector(".order-item-title").textContent
//                     && object[i].size === bool_size
//                     && object[i].doughType === bool_dough)
//                 {
//                     ind = i;
//                     break;
//                 }
//             }
//         }
//         else {
//             object = JSON.parse(localStorage["drinks"]);
//             ind = -1;
//             for (let i = 0; i < object.length; i++) {
//                 if (object[i].name + ", " + object[i].capacity === element.querySelector(".order-item-title").textContent)
//                 {
//                     ind = i;
//                     break;
//                 }
//             }
//         }
//         //////////////////////////////////////////////////////////////////////////////////////
//         console.log(object[ind]);
//         localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) + 1;
//         localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) + parseFloat(object[ind].price)).toFixed(2);
//         total_button.textContent = localStorage["busket-price"] + " BYN";
//         //object[ind].counter = object[ind].counter + 1;
//         //price_button.textContent = (object[ind].counter * object[ind].price).toFixed(2) + " BYN";
//         //let quantity_field = element.querySelector(".busket-button-quantity-text");
//         //quantity_field.textContent = (parseInt(quantity_field.textContent) + 1).toString();
//
//         if (!flag) {
//             localStorage["pizza"] = JSON.stringify(object);
//             //document.querySelector(".pizza").value = localStorage["pizza"];
//         }
//         else {
//             localStorage["drinks"] = JSON.stringify(object);
//             //document.querySelector(".drinks").value = localStorage["drinks"];
//         }
//     });
// });

export default Order;
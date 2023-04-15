
let profileButton = document.querySelector('.profile-button-text-orig');

function loadData() {
    let busket = document.querySelector(".busket-button-text");
    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";

    if (typeof localStorage["is-logged-in"] === 'undefined') {
        localStorage["is-logged-in"] = 'false';
    }
    if (localStorage["is-logged-in"] == 'true') {
        profileButton.textContent = 'Личный кабинет';
    }
    else if (localStorage["is-logged-in"] == 'false') {
        profileButton.textContent = 'Войти';
    }

    if (localStorage["is-logged-in"] == 'false') {
        document.querySelector('.current-address').textContent = 'Для начала войдите в аккаунт или зарегистрируйтесь';
        document.querySelector('.point-change-address').style.display = 'none';
    }
    else if (localStorage["is-logged-in"] == 'true') {
        document.querySelector('.current-address').textContent = 'Текущий адрес';
        document.querySelector('.point-change-address').style.display = 'block';
    }


    let next_elem = document.querySelector(".order-refs");
    for (let i = 1; i <= 6; i++) {
        if (localStorage["quantity-pizza-Mid-Thin-" + i] > 0) {
            next_elem.insertAdjacentHTML("beforebegin", `<div id="` + "pizza-Mid-Thin-" + i + `" class="order-item">
        <img src="` + localStorage["pizza-image-" + i] + `" alt="Order item" class="order-item-pic">
        <div class="description-box">
        <p class="order-item-title">` + localStorage["pizza-name-" + i] + `</p>
        <p class="order-item-size">Средняя</p>
        <p class="order-item-dough">Тонкое</p>
        </div>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + localStorage["quantity-pizza-Mid-Thin-" + i] + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + localStorage["price-pizza-Mid-Thin-" + i] + ` BYN</p>
        </button>
        </div>`);
        }
    }

    for (let i = 1; i <= 6; i++) {
        if (localStorage["quantity-pizza-Mid-Thick-" + i] > 0) {
            next_elem.insertAdjacentHTML("beforebegin", `<div id="` + "pizza-Mid-Thick-" + i + `" class="order-item">
        <img src="` + localStorage["pizza-image-" + i] + `" alt="Order item" class="order-item-pic">
        <div class="description-box">
        <p class="order-item-title">` + localStorage["pizza-name-" + i] + `</p>
        <p class="order-item-size">Средняя</p>
        <p class="order-item-dough">Толстое</p>
        </div>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + localStorage["quantity-pizza-Mid-Thick-" + i] + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + localStorage["price-pizza-Mid-Thick-" + i] + ` BYN</p>
        </button>
        </div>`);
        }
    }

    for (let i = 1; i <= 6; i++) {
        if (localStorage["quantity-pizza-Big-Thin-" + i] > 0) {
            next_elem.insertAdjacentHTML("beforebegin", `<div id="` + "pizza-Big-Thin-" + i + `" class="order-item">
        <img src="` + localStorage["pizza-image-" + i] + `" alt="Order item" class="order-item-pic">
        <div class="description-box">
        <p class="order-item-title">` + localStorage["pizza-name-" + i] + `</p>
        <p class="order-item-size">Большая</p>
        <p class="order-item-dough">Тонкое</p>
        </div>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + localStorage["quantity-pizza-Big-Thin-" + i] + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + localStorage["price-pizza-Big-Thin-" + i] + ` BYN</p>
        </button>
        </div>`);
        }
    }

    for (let i = 1; i <= 6; i++) {
        if (localStorage["quantity-pizza-Big-Thick-" + i] > 0) {
            next_elem.insertAdjacentHTML("beforebegin", `<div id="` + "pizza-Big-Thick-" + i + `" class="order-item">
        <img src="` + localStorage["pizza-image-" + i] + `" alt="Order item" class="order-item-pic">
        <div class="description-box">
        <p class="order-item-title">` + localStorage["pizza-name-" + i] + `</p>
        <p class="order-item-size">Большая</p>
        <p class="order-item-dough">Толстое</p>
        </div>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + localStorage["quantity-pizza-Big-Thick-" + i] + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + localStorage["price-pizza-Big-Thick-" + i] + ` BYN</p>
        </button>
        </div>`);
        }
    }

    for (let i = 1; i <= 10; i++) {
        if (localStorage["quantity-drink-" + i] > 0) {
            next_elem.insertAdjacentHTML("beforebegin", `<div id="` + "drink-" + i + `" class="order-item">
        <img src="` + localStorage["drink-image-" + i] + `" alt="Order item" class="order-item-pic">
        <p class="order-item-title">` + localStorage["drink-name-" + i] + `</p>
        <div class="order-item-quantity">
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + localStorage["quantity-drink-" + i] + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + localStorage["price-drink-" + i] + ` BYN</p>
        </button>
        </div>`);
        }
    }
}

loadData();

//localStorage.clear();


const element1 = document.querySelectorAll(".payment-form-select");

element1.forEach(element => {
    new Choices(element, {
        searchEnabled: false,
        itemSelectText: ""
    })
});

//

let total_price = 0;
let total_button = document.querySelector(".busket-button-text");

let order_item = document.querySelectorAll(".order-item");

order_item.forEach(element => {
    let price_button = element.querySelector(".busket-button-text-orig");

    let button_index = price_button.textContent.search(" ");
    total_price += parseFloat(price_button.textContent.substring(0, button_index));

    let localprice = "price-" + element.id;
    let localquantity = "quantity-" + element.id;
    console.log(localquantity);

    let minus = element.querySelector(".order-item-sign-minus");
    minus.addEventListener("click", function() {
        let quantity_field = element.querySelector(".busket-button-quantity-text");
        let quantity = parseInt(quantity_field.textContent);
        if (quantity > 0) {

            localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) - parseFloat(localStorage[localprice]) / parseInt(localStorage[localquantity])).toFixed(2);
            localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) - 1;
            localStorage[localprice] = (parseFloat(localStorage[localprice]) - parseFloat(localStorage[localprice]) / parseInt(localStorage[localquantity])).toFixed(2);
            localStorage[localquantity] = quantity - 1;

            
            price_button.textContent = localStorage[localprice] + " BYN";
            quantity_field.textContent = quantity - 1;
            total_button.textContent = localStorage["busket-price"] + " BYN";
            if (quantity == 1) {
                element.style.display = 'none';
            }
        }
    });
    let plus = element.querySelector(".order-item-sign-plus");
    plus.addEventListener("click", function() {
        let quantity_field = element.querySelector(".busket-button-quantity-text");
        let quantity = parseInt(quantity_field.textContent);

        console.log(parseFloat(localStorage[localprice]));
        localStorage["busket-price"] = (parseFloat(localStorage["busket-price"]) + parseFloat(localStorage[localprice]) / parseInt(localStorage[localquantity])).toFixed(2);
        localStorage["busket-quantity"] = parseInt(localStorage["busket-quantity"]) + 1;
        localStorage[localprice] = (parseFloat(localStorage[localprice]) + parseFloat(localStorage[localprice]) / parseInt(localStorage[localquantity])).toFixed(2);
        localStorage[localquantity] = quantity + 1;
            
        price_button.textContent = localStorage[localprice] + " BYN";
        quantity_field.textContent = quantity + 1;
        total_button.textContent = localStorage["busket-price"] + " BYN";
    });
});


(function () {
    const loginButton = document.querySelector('.profile-button-orig');
    const loginForm = document.querySelector('.login-box');
    const toggleForm = function () {
        loginForm.classList.toggle("open");
    }

    loginButton.addEventListener("click", function (e) {
        if (localStorage["is-logged-in"] == 'false') {
            e.stopPropagation();
            toggleForm();
        }
        else {
            let loginButton = document.querySelector(".profile-button-orig");
            let loginText = document.querySelector(".profile-button-text-orig");
            window.open("../html/profile.html", "_self");
        }
    });

    document.addEventListener("click", function (e) {
        const target = e.target;
        const its_form = target == loginForm || loginForm.contains(target);
        const its_button = target == loginButton;
        const form_is_active = loginForm.classList.contains("open");

        if (!its_form && !its_button && form_is_active) {
            toggleForm();
        }
    });
}());

(function () {
    const button = document.querySelector('.login-sign-in-button');
    button.addEventListener("click", function () {
        localStorage["is-logged-in"] = 'true';
        profileButton.textContent = 'Личный кабинет';
    });
}());


let loginFlag = 0;

document.querySelector('#login-form-phone-label').style.display = 'none';
document.querySelector('#login-form-phone').style.display = 'none';

function registerRefClicked() {
    if (!loginFlag) {
        document.querySelector('.login-title').textContent = 'Создать аккаунт';
        document.querySelectorAll('.login-element').forEach(button => {
            button.style.display = 'none';
        });
        document.querySelector('#login-form-phone-label').style.display = 'block';
        document.querySelector('#login-form-phone').style.display = 'block';
        document.querySelector('.login-sign-in-button').textContent = 'Зарегистрироваться';
        document.querySelector('.login-form-ref').style.display = 'none';
        document.querySelector('.login-form-register-text').textContent = 'Уже есть аккаунт?';
        document.querySelector('.login-form-register-ref').textContent = 'Войти';
        document.querySelector('.login-box').style.marginTop = '-210px';
        loginFlag = 1;
    }
    else if (loginFlag) {
        document.querySelector('.login-title').textContent = 'Войти в аккаунт';
        document.querySelectorAll('.login-element').forEach(button => {
            button.style.display = 'block';
        });
        document.querySelector('#login-form-phone-label').style.display = 'none';
        document.querySelector('#login-form-phone').style.display = 'none';
        document.querySelector('.login-sign-in-button').textContent = 'Войти';
        document.querySelector('.login-form-ref').style.display = 'block';
        document.querySelector('.login-form-register-text').textContent = "Еще нет аккаунта?";
        document.querySelector('.login-form-register-ref').textContent = 'Зарегистрироваться';
        document.querySelector('.login-box').style.marginTop = '-150px';
        loginFlag = 0;
    }
}

let pizza_images = [
    "../img/pizzas/пепперони.jpg",
    "../img/pizzas/4_сыра.jpg",
    "../img/pizzas/гавайская.jpg",
    "../img/pizzas/барбекю.jpg",
    "../img/pizzas/с креветкамиjpg.jpg",
    "../img/pizzas/деревенская.jpg"
]

let drink_images = [
    "../img/drinks/cola.png",
    "../img/drinks/cola_big.png",
    "../img/drinks/cola_zero_.png",
    "../img/drinks/cola_zero_big.png",
    "../img/drinks/fanta.png",
    "../img/drinks/fanta_big.png",
    "../img/drinks/sprite.png",
    "../img/drinks/sprite_big.png",
    "../img/drinks/whiskey.png",
    "../img/drinks/water.png"
]

let profileButton = document.querySelector('.profile-button-text-orig');

function loadData() {
    var busket = document.querySelector(".busket-button-text-orig");
    var busket_quantity = document.querySelector(".busket-button-quantity-text");
    if (typeof localStorage["is-logged-in"] === 'undefined') {
        localStorage["is-logged-in"] = 'false';
    }
    if (localStorage["is-logged-in"] == 'true') {
        profileButton.textContent = 'Личный кабинет';
    }
    else if (localStorage["is-logged-in"] == 'false') {
        profileButton.textContent = 'Войти';
    }
    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    if (typeof localStorage["busket-quantity"] === 'undefined') {
        localStorage["busket-quantity"] = '0';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";
    busket_quantity.textContent = localStorage["busket-quantity"];

    //for pizza

    for (let index = 1; index <= 6; index++) {
        if (typeof localStorage["quantity-pizza-Mid-Thin-" + index] === 'undefined') {
            localStorage["quantity-pizza-Mid-Thin-" + index] = 0; 
        }
        if (typeof localStorage["quantity-pizza-Mid-Thick-" + index] === 'undefined') {
            localStorage["quantity-pizza-Mid-Thick-" + index] = 0; 
        }
        if (typeof localStorage["quantity-pizza-Big-Thin-" + index] === 'undefined') {
            localStorage["quantity-pizza-Big-Thin-" + index] = 0; 
        }
        if (typeof localStorage["quantity-pizza-Big-Thick-" + index] === 'undefined') {
            localStorage["quantity-pizza-Big-Thick-" + index] = 0; 
        }
    }

    //for drinks

    for (let index = 1; index <= 10; index++) {
        if (typeof localStorage["quantity-drink-" + index] === 'undefined') {
            localStorage["quantity-drink-" + index] = 0;
        }
    }
}

localStorage.clear();

loadData();

for (let index = 1; index <= 6; index++) {
    console.log(localStorage["quantity-pizza-" + index]);
}

//pizza price according to size

var size_select_elements = document.querySelectorAll(".size-select");
size_select_elements.forEach(element => {
    const element_id = element.id.charAt(5);
    const price_name = "price-" + element_id;
    element.addEventListener("change", function() {
        var text = document.getElementById(price_name).textContent;
        var index = text.search(" ");
        if (element.options[0].value == "Big") {
            document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) + 5).toFixed(2) + " BYN";
        }
        else {
            document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) - 5).toFixed(2) + " BYN";
        }
    });
});

//pizza price according to dough type

var dough_select_elements = document.querySelectorAll(".dough-select");
dough_select_elements.forEach(element => {
    const element_id = element.id.charAt(6);
    const price_name = "price-" + element_id;
    element.addEventListener("change", function() {
        var text = document.getElementById(price_name).textContent;
        var index = text.search(" ");
        if (element.options[0].value == "Thick") {
            document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) + 2).toFixed(2) + " BYN";
        }
        else {
            document.getElementById(price_name).textContent
            = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) - 2).toFixed(2) + " BYN";
        }
    });
});

//

var busket = document.querySelector(".busket-button-text-orig");
var busket_quantity = document.querySelector(".busket-button-quantity-text");

var pizza_buskets = document.querySelectorAll(".profile-button");
pizza_buskets.forEach(element => {
    const element_id = element.id.charAt(7);
    const price_name = "price-" + element_id;
    const pizza = document.getElementById("pizza-" + element_id);
    element.addEventListener("click", function() {
        var text = document.getElementById(price_name).textContent;
        var index = text.search(" ");
        var busket_index = busket.textContent.search(" ");
        const prev_price = parseFloat(busket.textContent.substring(0, busket_index));
        var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
        localStorage["busket-price"] = new_price.toFixed(2);
        busket.textContent = new_price.toFixed(2) + " BYN";
        const new_quantity = parseInt(busket_quantity.textContent) + 1;

        localStorage['busket-quantity'] = new_quantity;
        localStorage["quantity-pizza-" + pizza.querySelector(".size-select").options[0].value +
         "-" + pizza.querySelector(".dough-select").options[0].value + "-" + element_id] =
        parseInt(localStorage["quantity-pizza-" + pizza.querySelector(".size-select").options[0].value +
        "-" + pizza.querySelector(".dough-select").options[0].value + "-" + element_id]) + 1;
        localStorage["pizza-name-" + element_id] = pizza.querySelector(".pizza-card-title").textContent;
        console.log(localStorage["pizza-name-" + element_id]);
        localStorage["pizza-image-" + element_id] = pizza_images[element_id - 1];
        localStorage["price-pizza-" + pizza.querySelector(".size-select").options[0].value +
        "-" + pizza.querySelector(".dough-select").options[0].value + "-" + element_id] = 
        (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) *
        parseFloat(localStorage["quantity-pizza-" + pizza.querySelector(".size-select").options[0].value +
         "-" + pizza.querySelector(".dough-select").options[0].value + "-" + element_id])).toFixed(2);

        busket_quantity.textContent = new_quantity;
    });
});

var drink_buskets = document.querySelectorAll(".drink-button");
drink_buskets.forEach(element => {
    var button_index = element.id.search("-");
    const element_id = element.id.substring(button_index + 1);
    const price_name = "dprice-" + element_id;
    const pizza = document.getElementById("drink-" + element_id);
    element.addEventListener("click", function() {
        var text = document.getElementById(price_name).textContent;
        var index = text.search(" ");
        var busket_index = busket.textContent.search(" ");
        const prev_price = parseFloat(busket.textContent.substring(0, busket_index));
        var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
        localStorage["busket-price"] = new_price.toFixed(2);
        busket.textContent = new_price.toFixed(2) + " BYN";
        const new_quantity = parseInt(busket_quantity.textContent) + 1;
        localStorage['busket-quantity'] = new_quantity;
        localStorage["quantity-drink-" + element_id] = parseInt(localStorage["quantity-drink-" + element_id]) + 1;
        localStorage["drink-name-" + element_id] = pizza.querySelector(".drink-card-title").textContent;
        localStorage["drink-image-" + element_id] = drink_images[element_id - 1];
        localStorage["price-drink-" + element_id] = parseFloat(document.getElementById(price_name).textContent.substring(0, index)).toFixed(2)
         * localStorage["quantity-drink-" + element_id];

        busket_quantity.textContent = new_quantity;
    });
});

//

const element1 = document.querySelectorAll(".menu-choices");

element1.forEach(element => {
    new Choices(element, {
        searchEnabled: false,
        itemSelectText: ""
    })
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
            window.open("./frontend/html/profile.html", "_self");
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

// Scroll to anchors
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
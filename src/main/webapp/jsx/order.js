

var pizza_images = new Map([['Пепперони', "../img/pizzas/peperoni.jpg"], ['4 сыра', "../img/pizzas/4_сыра.jpg"], ['Гавайская', "../img/pizzas/гавайская.jpg"], ['Барбекю', "../img/pizzas/барбекю.jpg"], ['Сальмоне', "../img/pizzas/с креветкамиjpg.jpg"], ['Деревенская', "../img/pizzas/деревенская.jpg"]]);

var drink_images = new Map([['Coca-Cola, 0.5', "../img/drinks/cola.png"], ['Coca-Cola, 1', "../img/drinks/cola_big.png"], ['Coca-Cola Zero, 0.5', "../img/drinks/cola_zero_.png"], ['Coca-Cola Zero, 1', "../img/drinks/cola_zero_big.png"], ['Fanta, 0.5', "../img/drinks/fanta.png"], ['Fanta, 1', "../img/drinks/fanta_big.png"], ['Sprite, 0.5', "../img/drinks/sprite.png"], ['Sprite, 1', "../img/drinks/sprite_big.png"], ['Ballentine\'s, 0.5', "../img/drinks/whiskey.png"], ['Bonaqua, 0.5', "../img/drinks/water.png"]]);

var profileButton = document.querySelector('.profile-button-text-orig');

function loadPizza() {
    if (document.querySelector(".session").value !== '') {
        document.querySelector(".profile-button-orig").value = "Личный кабинет";
    }

    var next_elem = document.querySelector(".order-refs");

    var pizza = JSON.parse(localStorage["pizza"]);
    for (var i = 0; i < pizza.length; i++) {
        var size = "Средний",
            dough = "Тонкое";
        if (pizza[i].size === false) size = "Большой";
        if (pizza[i].doughType === false) dough = "Толстое";
        next_elem.insertAdjacentHTML("beforebegin", '<div id="' + (i + 1) + '" class="order-item">\n        <img src="' + pizza_images.get(pizza[i].name) + '" alt="Order item" class="order-item-pic">\n        <div class="description-box">\n        <p class="order-item-title">' + pizza[i].name + '</p>\n        <p class="order-item-size">' + size + '</p>\n        <p class="order-item-dough">' + dough + '</p>\n        </div>\n        <div class="order-item-quantity">\n            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">\n            <button class="busket-button-quantity">\n                <p class="busket-button-quantity-text">' + pizza[i].counter + '</p>\n            </button>\n            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">\n        </div>\n        <button class="busket-button-orig">\n            <p class="busket-button-text-orig">' + parseFloat(pizza[i].price) * pizza[i].counter + ' BYN</p>\n        </button>\n        </div>');
    }
}

function loadDrinks() {
    var next_elem = document.querySelector(".order-refs");

    var drinks = JSON.parse(localStorage["drinks"]);
    for (var i = 0; i < drinks.length; i++) {
        next_elem.insertAdjacentHTML("beforebegin", '<div id="' + (10000 + i + 1) + '" class="order-item">\n        <img src="' + drink_images.get(drinks[i].name + ', ' + drinks[i].capacity) + '" alt="Order item" class="order-item-pic">\n        <p class="order-item-title">' + drinks[i].name + ', ' + drinks[i].capacity + '</p>\n        <div class="order-item-quantity">\n            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">\n            <button class="busket-button-quantity">\n                <p class="busket-button-quantity-text">' + drinks[i].counter + '</p>\n            </button>\n            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">\n        </div>\n        <button class="busket-button-orig">\n            <p class="busket-button-text-orig">' + parseFloat(drinks[i].price) * drinks[i].counter + ' BYN</p>\n        </button>\n        </div>');
    }
}

function loadData() {
    var busket = document.querySelector(".busket-button-text");
    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";

    loadPizza();

    loadDrinks();

    document.querySelector(".pizza").value = localStorage["pizza"];

    document.querySelector(".drinks").value = localStorage["drinks"];
}

loadData();

//localStorage.clear();

var element1 = document.querySelectorAll(".payment-form-select");

element1.forEach(function (element) {
    new Choices(element, {
        searchEnabled: false,
        itemSelectText: ""
    });
});

////////////////////////////////////////////////////////////////////////////////////////////////////


//order items listeners


var total_price = 0;
var total_button = document.querySelector(".busket-button-text");

var order_item = document.querySelectorAll(".order-item");

order_item.forEach(function (element) {
    var price_button = element.querySelector(".busket-button-text-orig");

    var minus = element.querySelector(".order-item-sign-minus");
    minus.addEventListener("click", function () {
        var flag = false; //pizza
        if (element.id / 10000 >= 1) flag = true; //drink
        var object = void 0;
        var ind = void 0;
        var bool_size = true,
            bool_dough = true;
        if (!flag) {
            if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
            if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
            object = JSON.parse(localStorage["pizza"]);
            ind = -1;
            for (var i = 0; i < object.length; i++) {
                if (object[i].name === element.querySelector(".order-item-title").textContent && object[i].size === bool_size && object[i].doughType === bool_dough) {
                    ind = i;
                    break;
                }
            }
        } else {
            object = JSON.parse(localStorage["drinks"]);
            ind = -1;
            for (var _i = 0; _i < object.length; _i++) {
                if (object[_i].name + ", " + object[_i].capacity === element.querySelector(".order-item-title").textContent) {
                    ind = _i;
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
            var quantity_field = element.querySelector(".busket-button-quantity-text");
            quantity_field.textContent = (parseInt(quantity_field.textContent) - 1).toString();
        } else {
            var new_object = [];
            for (var _i2 = 0; _i2 < ind; _i2++) {
                new_object.push(object[_i2]);
            }
            for (var _i3 = ind + 1; _i3 < object.length; _i3++) {
                new_object.push(object[_i3]);
            }
            object = new_object;
            element.style.display = 'none';
        }

        if (!flag) {
            localStorage["pizza"] = JSON.stringify(object);
            document.querySelector(".pizza").value = localStorage["pizza"];
        } else {
            localStorage["drinks"] = JSON.stringify(object);
            document.querySelector(".drinks").value = localStorage["drinks"];
        }
    });
});

order_item.forEach(function (element) {
    var price_button = element.querySelector(".busket-button-text-orig");
    var plus = element.querySelector(".order-item-sign-plus");
    plus.addEventListener("click", function () {
        var flag = false; //pizza
        if (element.id / 10000 >= 1) flag = true; //drink
        var object = void 0;
        var ind = void 0;
        var bool_size = true,
            bool_dough = true;
        if (!flag) {
            if (element.querySelector(".order-item-size").textContent === "Большой") bool_size = false;
            if (element.querySelector(".order-item-dough").textContent === "Толстое") bool_dough = false;
            object = JSON.parse(localStorage["pizza"]);
            ind = -1;
            for (var i = 0; i < object.length; i++) {
                if (object[i].name === element.querySelector(".order-item-title").textContent && object[i].size === bool_size && object[i].doughType === bool_dough) {
                    ind = i;
                    break;
                }
            }
        } else {
            object = JSON.parse(localStorage["drinks"]);
            ind = -1;
            for (var _i4 = 0; _i4 < object.length; _i4++) {
                if (object[_i4].name + ", " + object[_i4].capacity === element.querySelector(".order-item-title").textContent) {
                    ind = _i4;
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
        var quantity_field = element.querySelector(".busket-button-quantity-text");
        quantity_field.textContent = (parseInt(quantity_field.textContent) + 1).toString();

        if (!flag) {
            localStorage["pizza"] = JSON.stringify(object);
            document.querySelector(".pizza").value = localStorage["pizza"];
        } else {
            localStorage["drinks"] = JSON.stringify(object);
            document.querySelector(".drinks").value = localStorage["drinks"];
        }
    });
});

////////////////////////////////////////////////////////////////////////

var orderButton = document.querySelector(".order-button");
orderButton.addEventListener("click", function (e) {
    localStorage["pizza"] = JSON.stringify([]);
    localStorage["drinks"] = JSON.stringify([]);
    localStorage["busket-price"] = '0.00';
    localStorage["busket-quantity"] = '0';
    location.reload();
});
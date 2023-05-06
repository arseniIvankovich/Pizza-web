

let pizza_images = new Map([
    ['Пепперони', "../img/pizzas/peperoni.jpg"],
    ['4 сыра', "../img/pizzas/4_сыра.jpg"],
    ['Гавайская', "../img/pizzas/гавайская.jpg"],
    ['Барбекю', "../img/pizzas/барбекю.jpg"],
    ['Сальмоне', "../img/pizzas/с креветкамиjpg.jpg"],
    ['Деревенская', "../img/pizzas/деревенская.jpg"]
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



let profileButton = document.querySelector('.profile-button-text-orig');

function loadPizza() {
    if (document.querySelector(".session").value !== '') {
        document.querySelector(".profile-button-orig").value = "Личный кабинет";
    }

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
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + pizza[i].counter + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + (parseFloat(pizza[i].price) * pizza[i].counter) + ` BYN</p>
        </button>
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
            <input type="image" src="../img/minus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-minus">
            <button class="busket-button-quantity">
                <p class="busket-button-quantity-text">` + drinks[i].counter + `</p>
            </button>
            <input type="image" src="../img/plus-svgrepo-com.svg" alt="Order item sign" class="order-item-sign-plus">
        </div>
        <button class="busket-button-orig">
            <p class="busket-button-text-orig">` + (parseFloat(drinks[i].price) * drinks[i].counter)  + ` BYN</p>
        </button>
        </div>`);
    }
}

function loadData() {
    let busket = document.querySelector(".busket-button-text");
    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";

    if (typeof localStorage["is-logged-in"] === 'undefined') {
        localStorage["is-logged-in"] = 'false';
    }
    if (localStorage["is-logged-in"] === 'true') {
        profileButton.textContent = 'Личный кабинет';
    }
    else if (localStorage["is-logged-in"] === 'false') {
        profileButton.textContent = 'Войти';
    }

    if (localStorage["is-logged-in"] === 'false') {
        document.querySelector('.current-address').textContent = 'Для начала войдите в аккаунт или зарегистрируйтесь';
        document.querySelector('.point-change-address').style.display = 'none';
    }
    else if (localStorage["is-logged-in"] === 'true') {
        document.querySelector('.current-address').textContent = 'Текущий адрес';
        document.querySelector('.point-change-address').style.display = 'block';
    }

    loadPizza();

    loadDrinks();

    document.cookie = "pizza=" + localStorage["pizza"];

    document.cookie = "drinks=" + localStorage["drinks"];
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





////////////////////////////////////////////////////////////////////////////////////////////////////


//order items listeners


let total_price = 0;
let total_button = document.querySelector(".busket-button-text");

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
            document.cookie = "pizza=" + localStorage["pizza"];
        }
        else {
            localStorage["drinks"] = JSON.stringify(object);
            document.cookie = "drinks=" + localStorage["drinks"];
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
            document.cookie = "pizza=" + localStorage["pizza"];
        }
        else {
            localStorage["drinks"] = JSON.stringify(object);
            document.cookie = "drinks=" + localStorage["drinks"];
        }
    });
});

////////////////////////////////////////////////////////////////////////

let orderButton = document.querySelector(".order-button");
orderButton.addEventListener("click", function (e) {

});
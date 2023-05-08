
let ids = [
    1, 5, 9, 13, 17, 21
]

let capacities = [
    0.5, 1, 0.5, 1, 0.5, 1, 0.5, 1, 0.5, 0.5
]

let pizza_images = [
    "../img/pizzas/peperoni.jpg",
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



function loadData() {
    if (document.querySelector(".session").value !== '') {
        document.querySelector(".profile-button-orig").value = "Личный кабинет";
    }

    var busket = document.querySelector(".busket-button-text-orig");
    var busket_quantity = document.querySelector(".busket-button-quantity-text");
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
    busket.textContent = localStorage["busket-price"] + " BYN";
    busket_quantity.textContent = localStorage["busket-quantity"];
}

//localStorage.clear();

loadData();

//pizza price according to size

var size_select_elements = document.querySelectorAll(".size-select");
size_select_elements.forEach(element => {
    const element_id = element.id.charAt(5);
    const price_name = "price-" + element_id;
    element.addEventListener("change", function() {
        var text = document.getElementById(price_name).textContent;
        var index = text.search(" ");
        if (element.options[0].value === "Big") {
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
        if (element.options[0].value === "Thick") {
            document.getElementById(price_name).textContent
                = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) + 2).toFixed(2) + " BYN";
        }
        else {
            document.getElementById(price_name).textContent
                = (parseFloat(document.getElementById(price_name).textContent.substring(0, index)) - 2).toFixed(2) + " BYN";
        }
    });
});



const element1 = document.querySelectorAll(".menu-choices");

element1.forEach(element => {
    new Choices(element, {
        searchEnabled: false,
        itemSelectText: ""
    })
});


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

////////////////////////////////////////////////////////////////////////////////////

var busket = document.querySelector(".busket-button-text-orig");
var busket_quantity = document.querySelector(".busket-button-quantity-text");

var pizza_buskets = document.querySelectorAll(".profile-button");

var drink_buskets = document.querySelectorAll(".drink-button");

pizza_buskets.forEach(element => {
    element.addEventListener("click", function() {
        const element_id = element.id.charAt(7);
        const price_name = "price-" + element_id;
        let pizza_card = document.getElementById("pizza-" + element_id);
        let object = JSON.parse(localStorage["pizza"]);
        let flag = false;
        let ind = 0;
        let bool_size1 = true, bool_dough1 = true;
        if (pizza_card.querySelector(".size-select").options[0].textContent === "Большой") bool_size1 = false;
        if (pizza_card.querySelector(".dough-select").options[0].textContent === "Толстое") bool_size1 = false;
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
            if (pizza_card.querySelector(".dough-select").options[0].textContent === "Толстое") bool_size = false;
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
        var busket_index = busket.textContent.search(" ");
        const prev_price = parseFloat(busket.textContent.substring(0, busket_index));
        var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
        localStorage["busket-price"] = new_price.toFixed(2);
        busket.textContent = new_price.toFixed(2) + " BYN";
        const new_quantity = parseInt(busket_quantity.textContent) + 1;
        localStorage['busket-quantity'] = new_quantity;

        busket_quantity.textContent = new_quantity;
    });
});

drink_buskets.forEach(element => {
    element.addEventListener("click", function() {
        var button_index = element.id.search("-");
        const element_id = element.id.substring(button_index + 1);
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
        var busket_index = busket.textContent.search(" ");
        const prev_price = parseFloat(busket.textContent.substring(0, busket_index));
        var new_price = prev_price + parseFloat(document.getElementById(price_name).textContent.substring(0, index));
        localStorage["busket-price"] = new_price.toFixed(2);
        busket.textContent = new_price.toFixed(2) + " BYN";
        const new_quantity = parseInt(busket_quantity.textContent) + 1;
        localStorage['busket-quantity'] = new_quantity;

        busket_quantity.textContent = new_quantity;
    });
});
function loadData() {
    var busket = document.querySelector(".busket-button-text-orig");
    var busket_quantity = document.querySelector(".busket-button-quantity-text");
    if (typeof localStorage["busket-price"] === 'undefined') {
        localStorage["busket-price"] = '0.00';
    }
    if (typeof localStorage["busket-quantity"] === 'undefined') {
        localStorage["busket-quantity"] = '0';
    }
    busket.textContent = localStorage["busket-price"] + " BYN";
    busket_quantity.textContent = localStorage["busket-quantity"];

    ///////////////////////////////////////////////////

    let vacancyText = JSON.parse(document.querySelector(".vacancy-json").value);
    let form = document.querySelector(".left-form");
    form.querySelector("#address").textContent = "Ул. " + vacancyText.addresses.street + ", Д. " + vacancyText.addresses.houseNumber + ", Пд. " + vacancyText.addresses.entrance + ", Кв. " + vacancyText.addresses.flatNumber;
    form.querySelector("#name").textContent = vacancyText.firstName_lastName;
    form.querySelector("#email").textContent = vacancyText.email;
    form.querySelector("#phone").textContent = vacancyText.telephone;
}

loadData();

const element1 = document.querySelectorAll(".login-form-select");

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
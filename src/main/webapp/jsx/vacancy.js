
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

    var vacancyText = JSON.parse(document.querySelector(".vacancy-json").value);
    var form = document.querySelector(".left-form");
    form.querySelector("#address").textContent = "Ул. " + vacancyText.addresses.street + ", Д. " + vacancyText.addresses.houseNumber + ", Пд. " + vacancyText.addresses.entrance + ", Кв. " + vacancyText.addresses.flatNumber;
    form.querySelector("#name").textContent = vacancyText.firstName_lastName;
    form.querySelector("#email").textContent = vacancyText.email;
    form.querySelector("#phone").textContent = vacancyText.telephone;
}

loadData();

var element1 = document.querySelectorAll(".login-form-select");

element1.forEach(function (element) {
    new Choices(element, {
        searchEnabled: false,
        itemSelectText: ""
    });
});

// Scroll to anchors
(function () {

    var smoothScroll = function smoothScroll(targetEl, duration) {
        var target = document.querySelector(targetEl);
        var targetPosition = target.getBoundingClientRect().top;
        var startPosition = window.pageYOffset;
        var startTime = null;

        var ease = function ease(t, b, c, d) {
            t /= d / 2;
            if (t < 1) return c / 2 * t * t + b;
            t--;
            return -c / 2 * (t * (t - 2) - 1) + b;
        };

        var animation = function animation(currentTime) {
            if (startTime === null) startTime = currentTime;
            var timeElapsed = currentTime - startTime;
            var run = ease(timeElapsed, startPosition, targetPosition, duration);
            window.scrollTo(0, run);
            if (timeElapsed < duration) requestAnimationFrame(animation);
        };
        requestAnimationFrame(animation);
    };

    var scrollTo = function scrollTo() {
        var links = document.querySelectorAll('.js-scroll');
        links.forEach(function (each) {
            each.addEventListener('click', function () {
                var currentTarget = this.getAttribute('href');
                smoothScroll(currentTarget, 800);
            });
        });
    };
    scrollTo();
})();
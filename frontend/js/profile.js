const logoutButton = document.querySelector('.profile-button-orig');

logoutButton.addEventListener("click", function () {
    localStorage["is-logged-in"] = 'false';
    window.open("../../index.html", "_self");
});

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
}

loadData();
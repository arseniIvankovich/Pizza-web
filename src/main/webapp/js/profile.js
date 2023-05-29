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

    let profileText = document.querySelector(".profile-json");
    profileText = JSON.parse(profileText.value);
    let form = document.querySelector(".left-form");
    form.querySelector("#login-form-street").value = profileText.addresses.street;
    form.querySelector("#login-form-house").value = profileText.addresses.houseNumber;
    form.querySelector("#login-form-entrance").value = profileText.addresses.entrance;
    form.querySelector("#login-form-flat").value = profileText.addresses.flatNumber;
    form.querySelector("#login-form-name").value = profileText.firstName_lastName;
    form.querySelector("#login-form-email").value = profileText.email;
    form.querySelector("#login-form-phone").value = profileText.telephone;
}

loadData();
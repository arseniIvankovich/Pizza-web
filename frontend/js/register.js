
let button = document.querySelector('.save-button');

button.addEventListener("click", function () {
    localStorage["is-logged-in"] = 'true';
    window.open("../../index.html", "_self");
    window.open("https://ya.ru/", "_self");
});
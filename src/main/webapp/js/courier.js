
document.querySelector(".status-button").addEventListener("click", function() {
    let email = document.getElementById("status-form-input").value;
    let count = 0;
    document.querySelectorAll(".added-row").forEach(function(elem) {
        if (elem.querySelector(".email-field").textContent === email) {
            if (elem.querySelector(".status").textContent === "оформлен") {
                elem.querySelector(".status").textContent = "принят";
            }
            else {
                elem.querySelector(".status").textContent = "оформлен";
            }
            count += 1;
        }
    });
    if (count === 0) alert("Пользователя с таким e-mail не существует");
});
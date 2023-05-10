//
// document.querySelector(".user-button").addEventListener("click", function() {
//     let email = document.getElementById("user-form-input").value;
//     let count = 0;
//     document.querySelectorAll(".added-row").forEach(function(elem) {
//         if (elem.querySelector(".email-field").textContent === email) {
//             let isSure = confirm("Вы уверены, что хотите удалить пользователя?");
//             if (isSure) {
//                 elem.remove();
//             }
//             count += 1;
//         }
//     });
//     if (count === 0) alert("Пользователя с таким e-mail не существует");
// });
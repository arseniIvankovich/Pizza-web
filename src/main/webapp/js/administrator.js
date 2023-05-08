const ROWS = [
    [1, 1, 1],
    [2, 1, 1],
    [3, 1, 1],
    [4, 1, 1],
    [5, 1, 1],
    [6, 1, 1],
]

ROWS.forEach(function(item) {
    let row = document.createElement("tr");
    row.classList.add("added-row");
    row.innerHTML = `
        <td>${item[0]}</td>
        <td>${item[1]}</td>
        <td>${item[2]}</td>
        <td>
            <input type="submit" class="row-button" value="Удалить">
        </td>
    `
    document.querySelector(".styled-table").querySelector("tbody").append(row);
})

document.querySelectorAll(".row-button").forEach(function(elem) {
    elem.addEventListener("click", function() {
        let isSure = confirm("Вы уверены, что хотите удалить пользователя?");
        if (isSure) {
            let row = elem.offsetParent.offsetParent;
            row.remove();
        }

        //location.reload();
    });
});
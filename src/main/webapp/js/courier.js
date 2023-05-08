const ROWS = [
    [1, 1, 1, "оформлен"],
    [2, 1, 1, "оформлен"],
    [3, 1, 1, "оформлен"],
    [4, 1, 1, "оформлен"],
    [5, 1, 1, "оформлен"],
    [6, 1, 1, "оформлен"],
]

ROWS.forEach(function(item) {
    let row = document.createElement("tr");
    row.classList.add("added-row");
    row.innerHTML = `
        <td>${item[0]}</td>
        <td>${item[1]}</td>
        <td>${item[2]}</td>
        <td class="status">${item[3]}</td>
        <td>
            <input type="submit" class="row-button" value="Изменить статус">
        </td>
    `
    document.querySelector(".styled-table").querySelector("tbody").append(row);
})

document.querySelectorAll(".row-button").forEach(function(elem) {
    elem.addEventListener("click", function() {
        let row = elem.offsetParent.offsetParent;
        if (row.querySelector(".status").textContent === "оформлен") {
            row.querySelector(".status").textContent = "принят";
        }
        else {
            row.querySelector(".status").textContent = "оформлен";
        }

        //location.reload();
    });
});
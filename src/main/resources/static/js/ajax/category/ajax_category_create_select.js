$(document).ready(function () {
    findAllNameBigCategory();
    findAllNameMediumCategory();
});

function findAllNameBigCategory() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/category/showBig",
        success: function (bigCategories) {
            const listSize = Object.keys(bigCategories).length;
            if (bigCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                       <option value ="${bigCategory.id}" >${bigCategory.name}</option>
                    `;
                });
                $("#big-category-value").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

function findAllNameMediumCategory() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/category/medium-category",
        success: function (mediumCategories) {
            const listSize = Object.keys(mediumCategories).length;
            if (mediumCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {
                let contentRow = '';
                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                       <option value="${mediumCategory.id}">${mediumCategory.name}</option>
                    `;
                });
                $("#medium-category-value").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

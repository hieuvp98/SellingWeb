$(document).ready(function () {
    //============ Get All Big Category ========================
    $.ajax({
        type: "GET",
        url: "http://localhost:9990/api/v1/public/category/small-category",
        success: function (smallCategories) {
            const listSize = Object.keys(smallCategories).length;
            if (smallCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                var contentRow = '';

                $("#column-small-category").html(
                    "<td> Id</td>" +
                    "<td> Name</td>" +
                    "<td> Medium Category</td>" +
                    "<td> Action</td>"
                );
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                        <tr>
                        <td> ${smallCategory.id} </td>
                        <td> ${smallCategory.name} </td>
                        <td> ${smallCategory.mediumCategory.name} </td>
                        <td>
                                <a href="">update</a>&nbsp;
                                <a href="">delete</a>
                        </td>
                        </tr>
                    `;
                })
                $("#row-small-category").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

    //===================================== Create ========================

})

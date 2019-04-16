$(document).ready(function () {
    //============ Get All Medium Category ========================
    $.ajax({
        type: "GET",
        url: "http://localhost:9990/api/v1/public/category/medium-category",
        success: function (mediumCategories) {
            const listSize = Object.keys(mediumCategories).length;
            if (mediumCategories.check == "fail") {
                alert("Medium Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                var contentRow = '';

                $("#column-medium-category").html(
                    "<td> Id</td>" +
                    "<td> Name</td>" +
                    "<td> Big Category</td>" +
                    "<td> Action</td>"
                );
                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                        <tr>
                        <td> ${mediumCategory.id} </td>
                        <td> ${mediumCategory.name} </td>
                        <td> ${mediumCategory.bigCategory.name} </td>
                        <td>
                                <a href="">update</a>&nbsp;
                                <a href="">delete</a>
                        </td>
                        </tr>
                    `;
                })
                $("#row-medium-category").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

})

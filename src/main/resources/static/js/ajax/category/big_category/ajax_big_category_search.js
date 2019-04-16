$(document).ready(function () {
    //============ Get All Big Category ========================
    $.ajax({
        type: "GET",
        url: "http://localhost:9990/api/v1/public/category/showBig",
        success: function (bigCategories) {
            const listSize = Object.keys(bigCategories).length;
            if (bigCategories.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                var contentRow = '';

                $("#column-big-category").html(
                    "<td> Id</td>" +
                    "<td> Name</td>" +
                    "<td> Action</td>"
                );
                bigCategories.map(function (bigCategory) {
                    contentRow += `
                        <tr>
                        <td> ${bigCategory.id} </td>
                        <td> ${bigCategory.name} </td>
                        <td>
                                <a href="">update</a>&nbsp;
                                <a href="">delete</a>
                        </td>
                        </tr>
                    `;
                })
                $("#row-big-category").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

    //===================================== Create ========================

})

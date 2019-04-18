$(document).ready(function () {
    findAllBigCategory();
});

function findAllBigCategory() {
    //============ Get All Small Category ========================
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

                var url = window.location.origin;
                smallCategories.map(function (smallCategory) {
                    contentRow += `
                        <tr>
                        <td> ${smallCategory.id} </td>
                        <td> ${smallCategory.name} </td>
                        <td> ${smallCategory.mediumCategory.name} </td>
                        <td>
                                <a href="${url}/admin/update-category/${smallCategory.id}" name="${smallCategory.id}"  class="update-small-category" style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                              <span name="${smallCategory.id}" class="delete-small-category" style="cursor: pointer;color: red">delete</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })
                $("#row-small-category").html(contentRow);
                //============ delete =============
                deleteSmallCategory();
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

}

//============ Delete Big Category ========================
function deleteSmallCategory() {

    $('.delete-small-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            dataType: "json",
            url: "/api/v1/admin/category/delete-small?id=" + id,
            timeout: 30000,

            success: function () {
                alert('DELETE SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
            }
        });
    });

}

$(document).ready(function () {
    findAllBigCategory();

});

function findAllBigCategory() {
    //============ Get All Medium Category ========================
    $.ajax({
        type: "GET",
        url: "/api/v1/public/category/medium-category",
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
                var url = window.location.origin;
                mediumCategories.map(function (mediumCategory) {
                    contentRow += `
                        <tr>
                        <td> ${mediumCategory.id} </td>
                        <td> ${mediumCategory.name} </td>
                        <td> ${mediumCategory.bigCategory.name} </td>
                        <td>
                              <a href="${url}/admin/update-category/${mediumCategory.id}" name="${mediumCategory.id}"  class="update-medium-category" style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                              <span name="${mediumCategory.id}" class="delete-medium-category" style="cursor: pointer;color: red">delete</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })
                $("#row-medium-category").html(contentRow);
                //============ delete =============
                deleteMediumCategory();
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })
}

//============ Delete Big Category ========================
function deleteMediumCategory() {

    $('.delete-medium-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            dataType: "json",
            url: "/api/v1/admin/category/delete-medium?id=" + id,
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

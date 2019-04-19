$(document).ready(function () {
    findAllPartnerPage(1);
    findAllPagPartnerNumber();
});
//==================================page=============================.unbind('click')
function pagePartner(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" name="${i}" ">${i}</a></li> `;
    }
    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
}

function findAllPagPartnerNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/partner/find-all/size",
        success: function (size) {
            console.log(size);
            pagePartner(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                findAllPartnerPage(page);
            });
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
}


function findAllPartnerPage(page) {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/partner/find-all?page="+page ,
        success: function (partners) {
            const listSize = Object.keys(partners).length;

            if (partners.check == "fail") {
                alert("Product isEmpty! Name not found!");
                return;
            }

            if (listSize > 0) {

                let contentRow = '';

                $("#column-partner").html(
                    "<td> ID</td>" +
                    "<td> Name </td>" +
                    "<td> Img Url </td>" +
                    "<td> Present </td>" +
                    "<td> Action</td>"
                );

                const url = window.location.origin;
                partners.map(function (partner) {
                    contentRow += `
                        <tr>
                        <td> ${partner.id} </td>
                        <td> ${partner.name} </td>
                        <td> ${partner.imgUrl} </td>
                        <td> ${partner.present} </td>
                       
                        <td>
                              <a href="${url}/admin/update-partner/${partner.id}" name="${partner.id}"   style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                              <span name="${partner.id}" class="delete-partner" style="cursor: pointer;color: red">delete</span>&nbsp;
                        </td>
                        </tr>
                    `;
                })


                $("#row-partner").html(contentRow);

                //===== delete =======
                deletePartner();
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

//============ Delete PRODUCT ========================
function deletePartner() {
    $('.delete-partner').click(function () {
        const id = $(this).attr("name");
        console.log("id-partner " + id);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/delete-partner?id=" + id,
            timeout: 30000,
            success: function () {
                alert('DELETE SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('DELETE FAIL');
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

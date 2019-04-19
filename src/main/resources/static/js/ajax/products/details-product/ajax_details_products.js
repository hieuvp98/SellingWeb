$(document).ready(function () {
    findAllPageDetailsProductNumber();
    searchDetailsProductByName(1);
});

//==================================page=============================
function pageDetailsProduct(size) {
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


function findAllPageDetailsProductNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/details-products/size",
        success: function (size) {

            findAllDetailsProduct(1);
            pageDetailsProduct(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                findAllDetailsProduct(page);
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

function findAllPageDetailsProductByNameNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/details-product/name/size",
        success: function (size) {
            pageProduct(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                searchDetailsProductByName(page);
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

function displayOnTableDetailsProduct(detailsProducts) {
    const listSize = Object.keys(detailsProducts).length;

    if (detailsProducts.check == "fail") {
        alert("Details Product isEmpty! Name not found!");
        return;
    }

    if (listSize > 0) {

        var contentRow = '';

        $("#column-details-product").html(
            "<td> ID</td>" +
            "<td> Name </td>" +
            "<td>Product Status</td>" +
            "<td> guarantee </td>" +
            "<td> present </td>" +
            "<td> Action</td>"
        );

        const url = window.location.origin;
        detailsProducts.map(function (detailsProduct) {
            contentRow += `
                        <tr>
                        <td> ${detailsProduct.id} </td>
                        <td> ${detailsProduct.product.name} </td>
                        <td> ${detailsProduct.productStatus} </td>
                        <td> ${detailsProduct.guarantee} </td>
                        <td> ${detailsProduct.present} </td>
                        <td>
                              <a href="${url}/admin/update-details-product/${detailsProduct.id}" name="${detailsProduct.id}"   style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                              <span name="${detailsProduct.id}" class="delete-details-product" style="cursor: pointer;color: red">delete</span>&nbsp;
                        </td>
                        </tr>
                    `;
        });
        $("#row-details-product").html(contentRow);
        $(".body-main .table-responsive tr td").css({
            "max-width": "200px",
            "overflow": "-webkit-paged-y"
        });
        //===== delete =======
        deleteDetailsProduct();
    }
}

//============ FIND ALL DETAILS PRODUCT ========================
function findAllDetailsProduct(page) {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/details-products?page=" + page,
        success: function (detailsProducts) {
            displayOnTableDetailsProduct(detailsProducts);
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })
}

//============ DELETE PRODUCT ========================
function deleteDetailsProduct() {
    $('.delete-details-product').click(function () {
        const id = $(this).attr("name");
        console.log("id-details-product " + id);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/delete-details-product?id=" + id,
            timeout: 30000,
            success: function () {
                alert('DELETE DETAILS PRODUCT SUCCESS');
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('DELETE DETAILS PRODUCT FAIL');
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

//=========================== SEARCH BY NAME ===================================
function searchDetailsProductByName(page) {
    $("#input-search").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == 13) {
            const nameProduct = $('#input-search').val();
            $.ajax({
                type: "GET",
                url: "/api/v1/public/products/details-product?name=" + nameProduct + "&page=" + page,
                success: function (detailsProducts) {
                    displayOnTableDetailsProduct(detailsProducts);
                    findAllPageDetailsProductByNameNumber();

                },
                error: function (e) {
                    console.log("Error: " + e);
                }
            })
        }
    });
}


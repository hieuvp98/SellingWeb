$(document).ready(function () {
    findAllPageProductNumber();
    searchProductByName(1);
});


//==================================page=============================.unbind('click')

function pageProduct(size) {
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

function findAllPageProductNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/size",
        success: function (size) {

            findAllProductBySize(1);
            pageProduct(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                findAllProductBySize(page);
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

function findAllPageProductByNameNumber() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/name/size",
        success: function (size) {
            pageProduct(size);
            $('.page').click(function () {
                const page = $(this).attr("name");
                searchProductByName(page);
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


//============ FIND ALL PRODUCT ========================
function findAllProductBySize(page) {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/find-all?page=" + page,
        success: function (products) {
            displayOnTable(products);
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })
}


function displayOnTable(products) {
    const listSize = Object.keys(products).length;
    if (products.check == "fail") {
        alert("Product isEmpty! Name not found!");
        return;
    }
    if (listSize > 0) {
        let contentRow = '';
        $("#column-product").html(
            "<td> ID</td>" +
            "<td> Name </td>" +
            "<td> Origin Cost</td>" +
            "<td> Sale Cost </td>" +
            "<td> Img Url </td>" +
            "<td> Init Date </td>" +
            "<td> View </td>" +
            "<td> Small Category </td>" +
            "<td> Partner</td>" +
            "<td> Action</td>"
        );
        const url = window.location.origin;
        products.map(function (product) {
            contentRow += `
                        <tr>
                        <td> ${product.id} </td>
                        <td> ${product.name} </td>
                        <td> ${product.originCost} </td>
                        <td> ${product.saleCost} </td>
                        <td> ${product.imgUrl} </td>
                        <td> ${product.initDate} </td>
                        <td> ${product.view} </td>
                        <td> ${product.smallCategory.name} </td>
                        <td> ${product.partner.name} </td>
                        <td>
                              <a href="${url}/admin/update-product/${product.id}" name="${product.id}"  class="update-product" style="cursor: pointer;color: #4285F4">update</a>&nbsp;
                              <span name="${product.id}" class="delete-product" style="cursor: pointer;color: red">delete</span>&nbsp;
                        </td>
                        </tr>
                    `;
        });
        $("#row-product").html(contentRow);
        $(".body-main .table-responsive tr td").css({
            "max-width": "180px",
            "overflow": "-webkit-paged-y"
        });
        //===== delete =======
        deleteProduct();
    }

}

//============ Delete PRODUCT ========================
function deleteProduct() {
    $('.delete-product').click(function () {
        const id = $(this).attr("name");
        console.log("id-product " + id);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/delete-product?id=" + id,
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

//=========================== SEARCH BY NAME ===================================
function searchProductByName(page) {
    $("#input-search").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == 13) {
            const nameProduct = $('#input-search').val();
            $.ajax({
                type: "GET",
                url: "/api/v1/public/products?name=" + nameProduct + "&page=" + page,
                success: function (products) {
                    findAllPageProductByNameNumber();
                    displayOnTable(products);
                },
                error: function (e) {
                    console.log("Error: " + e);
                }
            })
        }
    });
}




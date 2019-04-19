$(document).ready(function () {
    findAllProductNameByDetails();
    clickBtnDetailsProductChangeSubmit();
});

//============ CREATE DETAILS PRODUCT ========================

function createDetailsProduct() {

    let idProduct = '';
    $('#product-value').click(function () {
        idProduct = $(this).val();
    });

    let productStatus = '';
    $('#product-status-value').click(function () {
        productStatus = $(this).val();
    });

    $('#btn-create-details-product').click(function () {

        const guarantee = $("#guarantee-value").val();
        const present = $('#present-value').val();

        const detailsProduct = {
            "productStatus": productStatus,
            "guarantee": guarantee,
            "present": present
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/admin/product-details?product-id=" + idProduct,
            data: JSON.stringify(detailsProduct),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $("#btn-create-details-product").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("CREATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $(this).prop("disabled", true);
            }
        })
    });
}

// ============ FIND DETAILS PRODUCT BY ID ===================

function findDetailsProductById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/api/v1/public/products/find-details-product-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateProduct(result);
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

//============ UPDATE DETAILS PRODUCT ========================
function updateProduct(data) {
    $("#product-value").prop("disabled", true);

    $('#product-status').val(data.productStatus);
    $('#guarantee-value').val(data.guarantee);
    $('#present-value').val(data.present);
    $('#btn-create-details-product').click(function () {
        data.productStatus = $('#product-status').val();
        data.guarantee = $('#guarantee-value').val();
        data.present = $('#present-value').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/product-details",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-details-product').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("UPDATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $('#btn-create-details-product').prop("disabled", true);

            }
        });
    });
}

function clickBtnDetailsProductChangeSubmit() {
    const urlCreateCategory = window.location.pathname;
    console.log(urlCreateCategory);
    const str = urlCreateCategory.split('/');
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findDetailsProductById(id)
    } else createDetailsProduct();

}

function findAllProductNameByDetails() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/details-products",
        success: function (detailsProducts) {
            findAllProductValue(detailsProducts);
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })
}

function findAllProductValue(detailsProducts) {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/products/all",
        success: function (products) {
            const listSize = Object.keys(products).length;
            if (products.check == "fail") {
                alert("products isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {
                let contentRow = '';
                products.map(function (product) {
                    contentRow += `
                       <option id="option${product.id}" value="${product.id}">${product.name}</option>
                    `;

                });

                $("#product-value").html(
                    ` <option selected="selected" disabled="disabled">Product Name</option>`+
                    contentRow);
                products.map(function (product) {
                    detailsProducts.map(function (detailsProduct) {
                        if (product.id === detailsProduct.product.id) {
                            const id = `#option${product.id}`;
                            $(id).attr("disabled", "disabled");
                        }
                    })
                })

            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}


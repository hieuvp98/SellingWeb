$(document).ready(function () {
    findAllPartner();
    clickBtnProductChangeSubmit();
});

//============ CREATE PRODUCT ========================

function createProduct() {

    $("#big-category-value").prop("disabled", true);
    $("#medium-category-value").prop("disabled", true);

    let idSmallCategory = '';
    $('#small-category-value').click(function () {
        idSmallCategory = $(this).val();
    });

    let idPartner = '';
    $('#partner-value').click(function () {
        idPartner = $(this).val();
    });

    $('#btn-create-product').click(function () {

        const nameProduct = $("#name-product").val();
        const originCost = $('#origin-cost').val();
        const saleCost = $('#sale-cost').val();
        const product = {
            "name": nameProduct,
            "originCost": originCost,
            "saleCost": saleCost,
            "imgUrl": "abc",
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/admin/product?small-category-id=" + idSmallCategory + "&partner-id=" + idPartner,
            data: JSON.stringify(product),
            cache: false,
            timeout: 300000,
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $('#btn-create-product').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("CREATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $('#btn-create-product').prop("disabled", true);
            }
        })
    });
}

// ============ FIND PRODUCT BY ID ===================

function findProductById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/api/v1/public/products/findProductById?id=" + id,
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

//============ UPDATE PRODUCT ========================
function updateProduct(data) {
    $("#big-category-value").prop("disabled", true);
    $("#medium-category-value").prop("disabled", true);
    $("#small-category-value").prop("disabled", true);
    $("#partner-value").prop("disabled", true);

    $('#name-product').val(data.name);
    $('#origin-cost').val(data.originCost);
    $('#sale-cost').val(data.saleCost);
    $('#btn-create-product').click(function () {
        data.name = $('#name-product').val();
        data.originCost = $('#origin-cost').val();
        data.saleCost = $('#sale-cost').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/admin/product",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('UPDATE SUCCESS');
                $('#btn-create-product').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("UPDATE ERROR ");
                console.log('jqXHR:');
                console.log(jqXHR);
                console.log('textStatus:');
                console.log(textStatus);
                console.log('errorThrown:');
                console.log(errorThrown);
                $('#btn-create-product').prop("disabled", true);

            }
        });
    });
}

function clickBtnProductChangeSubmit() {
    const urlCreateCategory = window.location.pathname;
    console.log(urlCreateCategory);
    const str = urlCreateCategory.split('/');
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findProductById(id)
    } else createProduct();

}


function findAllPartner() {
    $.ajax({
        type: "GET",
        url: "/api/v1/public/partner/all",
        success: function (partners) {
            const listSize = Object.keys(partners).length;
            if (partners.check == "fail") {
                alert("Category isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                let contentRow = '';
                partners.map(function (partner) {
                    contentRow += `
                       <option value ="${partner.id}" >${partner.name}</option>
                    `;
                });
                $("#partner-value").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    });
}

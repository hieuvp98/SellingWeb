$(document).ready(function () {
    clickBtnChangeSubmit();
});

//============ Create Big Category ========================
function createCategory() {
    $('#btn-create-medium-category').click(function () {
        const nameMediumCategory = $("#name-medium-category").val();
        console.log(nameMediumCategory);
        let idBigCategory = '';
        $('#big-category-value').click(function () {
            idBigCategory = $('this').val();
            console.log(idBigCategory);
        });
        const bigCategory = {
            "name": nameMediumCategory,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:9990/api/v1/admin/category/medium?big-id=" + idBigCategory,
            data: JSON.stringify(bigCategory),
            cache: false,
            timeout: 300000,
            dataType: 'json',
            success: function (data) {
                alert("CREATE SUCCESS : " + data.name);
                $("#btn-create-medium-category").prop("disabled", true);
            },
            error: function (err) {
                alert("CREATE ERROR : " + err.toString() );
            }
        })
    });
}

//============ Find Big Category By Id ===================

function findMediumCategoryById(id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:9990/api/v1/public/category/findMediumCategoryById?idMedium=" + id,
        timeout: 30000,
        success: function (result) {
            updateBigCategory(result);
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

// ============ UPDATE Big Category ========================
function updateBigCategory(data) {

    $('#name-medium-category').val(data.name);

    $('#btn-create-medium-category').click(function () {
        data.name = $('#name-medium-category').val();
        const idBigCategory = $('#big-category-value').val();
        console.log(data);
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            url: "http://localhost:9990/api/v1/admin/category/big",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('SUCCESS');
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

function clickBtnChangeSubmit() {
    const urlCreateCategory = window.location.pathname;
    console.log(urlCreateCategory);
    const str = urlCreateCategory.split('/');
    const id = str[str.length - 1];
    (id - 1) !== NaN ? findMediumCategoryById(id) : createCategory();
}

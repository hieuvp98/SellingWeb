$(document).ready(function () {
    //============ Get All Product ========================
    $.ajax({
        type: "GET",
        url: "http://localhost:9990/api/v1/public/products/details-products",
        success: function (detailsProducts) {

            const listSize = Object.keys(detailsProducts).length;

            if (detailsProducts.check == "fail") {
                alert("Details product isEmpty! Name not found!");
                return;
            }

            if (listSize > 0) {

                var contentRow = '';

                $("#column-details-product").html(
                    "<td> ID </td>" +
                    "<td> Product </td>" +
                    "<td> Product Status</td>" +
                    "<td> Guarantee</td>" +
                    "<td> Present </td>" +
                    "<td> Action</td>"
                );
                detailsProducts.map(function (detailsProduct) {
                    contentRow += `
                        <tr>
                        <td> ${detailsProduct.id} </td>
                        <td> ${detailsProduct.product.name} </td>
                        <td> ${detailsProduct.productStatus} </td>
                        <td> ${detailsProduct.guarantee} </td>
                        <td> ${detailsProduct.present} </td>
                        <td>
                                <a href="">update</a>&nbsp;
                                <a href="">delete</a>
                        </td>
                        </tr>
                    `;
                })
                $("#row-details-product").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

    //===================================== Create ========================

})

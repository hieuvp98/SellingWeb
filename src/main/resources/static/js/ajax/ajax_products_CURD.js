$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:9990/api/v1/public/products/bySmallCategory?id=1",
        success: function (products) {
            console.log("dm");
            const listSize = Object.keys(products).length;
            console.log("size: " + listSize)
            if (products.check == "fail") {
                alert("Course isEmpty! Name not found!");
                return;
            }
            if (listSize > 0) {

                var contentRow = '';

                $("#column").html(
                    "<td> Name </td>" +
                    "<td> Origin Cost</td>" +
                    "<td> Sale Cost </td>" +
                    "<td> imgUrl </td>" +
                    "<td> initDate </td>" +
                    "<td> view </td>" +
                    "<td> Small Category </td>" +
                    "<td> Partner</td>" +
                    "<td> Action</td>"
                );
                products.map(function (product) {
                    contentRow += `
                        <tr>
                        <td> ${product.name} </td>
                        <td> ${product.originCost} </td>
                        <td> ${product.saleCost} </td>
                        <td> ${product.imgUrl} </td>
                        <td> ${product.initDate} </td>
                        <td> ${product.view} </td>
                        <td> ${product.smallCategory.name} </td>
                        <td> ${product.partner.name} </td>
                        <td>
                                <a href="">update</a>&nbsp;
                                <a href="">delete</a>
                        </td>
                        </tr>
                    `;
                })
                $("#row").html(contentRow);
            }
        },
        error: function (e) {
            console.log("Error: " + e);
        }
    })

})

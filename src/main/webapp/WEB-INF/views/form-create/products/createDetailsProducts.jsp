<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="/resources/js/ajax/products/details-product/ajax_details_products_change.js"></script>

<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary" id="btnBack">
        <a href="${pageContext.request.contextPath}/admin/details-product" style="text-decoration: none;color: white;">  Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm Chi tiết sản phẩm</h2>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Tình trạng </p>
                <select class="form-control" id="product-status" name="product-status-value">
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
            </div>

            <div class="myInput">
                <p>Thời gian bảo hành</p>
                <input type="number" class="form-control" name="name" id="guarantee-value" placeholder="Bảo hành">
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Product</p>
                <select class="form-control"  id="product-value"> </select>
            </div>
            <div class="myInput">
                <p>Giới thiệu </p>
                <textarea class="form-control" rows="5" id="present-value"></textarea>
            </div>
        </div>
        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-details-product">Submit</button>
        </div>
    </div>

</div>
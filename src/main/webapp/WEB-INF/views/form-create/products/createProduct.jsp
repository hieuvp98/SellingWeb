<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="/resources/js/ajax/category/ajax_category_create_select.js"></script>
<script src="/resources/js/ajax/products/product/ajax_product_change.js"></script>
<div class="container">
    <div class="form-product">
        <button type="button" class="btn btn-primary" >
            <a href="${pageContext.request.contextPath}/admin/product" style="text-decoration: none;color: white">Quay lại</a>
        </button>
        <div class="title">
            <h2 class=" font-weight-bold">Thêm sản phẩm</h2>
        </div>
        <div class="name-and-partner">
            <div class="myInput">
                <p>Tên sản phẩm:</p>
                <input type="text" class="form-control" name="name" id="name-product" placeholder="Tên sản phẩm">
            </div>
            <div class="myInput">
                <p>Hãng sản xuất:</p>
                <select class="form-control" name="partner" id="partner-value"></select>
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Giá gốc:</p>
                <input type="number" class="form-control" name="origin-cost" id="origin-cost" placeholder="Giá gốc">
            </div>
            <div class="myInput">
                <p>Giá khuyến mãi:</p>
                <input type="number" class="form-control" name="sale-cost" id="sale-cost"
                       placeholder="Giá khuyến mãi">
            </div>
        </div>

        <div class="double-input" >
            <div class="myInput">
                <p>Mục lớn:</p>
                <select class="form-control" name="big-category" id="big-category-value">
                    <option value="apple">Apple</option>
                    <option value="samsung">Samsung</option>
                </select>
            </div>
            <div class="myInput">
                <p>Mục thứ hai:</p>
                <select class="form-control" name="medium-category" id="medium-category-value">
                    <option value="apple">Apple</option>
                    <option value="samsung">Samsung</option>
                </select>
            </div>
        </div>

        <div class="double-input">
            <div class="myInput">
                <p>Mục thứ ba:</p>
                <select class="form-control"  placeholder="small category" id="small-category-value">
                </select>
            </div>
            <div class="myInput">
                <p>Ảnh đại diện:</p>
                <input type="file" class="form-control-file" name="image" id="image">
            </div>
        </div>
        <div class="submit">
            <button type="button" class="btn btn-success" id="btn-create-product">Submit</button>
        </div>
    </div>

</div>
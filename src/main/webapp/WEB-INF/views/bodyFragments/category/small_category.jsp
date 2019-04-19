<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="/resources/js/ajax/category/small_category/ajax_small_category.js"></script>
<h3 class="title-body" style="text-align: center">
    Quản Lý Chuyên Mục Bé
</h3>
<div class="from-find float-right">
    <input type="text" name="search" id="input-search" placeholder="search..."
           class="input-search float-left">
    <div class="fas fa-search float-right" id="icon-search"></div>
</div>
<button type="button" class="btn btn-success float-left btn-search">
    <a href="${pageContext.request.contextPath}/admin/create-category" style="text-decoration: none;color: white">
        <i class="fas fa-plus"></i>Create
    </a>
</button>
<!-- TABLE -->
<div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
    <table class="table text-center">
        <thead>
        <tr id="column-small-category" style="font-weight: 600"></tr>
        </thead>
        <tbody id="row-small-category"></tbody>
    </table>
</div>
<!-- END_TABLE -->
<div class="pageable">
    <ul class="pagination"></ul>
</div>
<!-- MENU_RIGHT -->
<div class="col-5 col-sm-4 col-md-3 col-lg-3 col-xl-2 menu-right">
    <div class="logo-top">
        <img src="/resources/img/home-admin/logo-bksoftware.png" alt="" class="img-fluid">
    </div>
    <div class="menu-list set-height" style="overflow-y:auto;">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/admin/" id="home">
                    <i class="fas fa-home"></i>
                    <span>Home</span>
                </a>
            </li>
            <li>
                <a id="menu1" class="menu-iteam-click">
                    <i class="fas fa-book-open"></i>
                    <span>Product</span>
                </a>
                <ul class="menu-extra not-view">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/product" id="menu-extra1">
                                    <span>
                                        Product
                                    </span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/details-product" id="menu-extra2" class="menu-extra-click">
                                    <span>
                                        Details Product
                                    </span>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a id="menu2" class="menu-iteam-click">
                    <i class="fas fa-book-open"></i>
                    <span>Category</span>
                </a>
                <ul class="menu-extra not-view">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/big-category" id="menu-extra3">
                                    <span>
                                        Big Category
                                    </span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/medium-category" id="menu-extra4" class="menu-extra-click">
                                    <span>
                                        Medium Category
                                    </span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/small-category" id="menu-extra5" class="menu-extra-click">
                                    <span>
                                        Small Category
                                    </span>
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</div>

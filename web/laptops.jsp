<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Laptops</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/star-rating-svg.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onload="showLaptops();">
<%@ include file="header.html" %>
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Laptops</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <%@ include file="sidebar.html" %>
            <div class="col-md-9">
                <div class="filter-wrap" style="margin-bottom: 20px;">
                    <div class="filter-content">
                        <form class="woocommerce-ordering" method="get">
                            <select name="orderby" class="orderby" onchange="sortItems(2,this.selectedIndex);">
                                <option value="dafault">Default sorting</option>
                                <option value="popularity">Sort by popularity</option>
                                <option value="price-desc">Sort by price: high to low</option>
                                <option value="price-asc">Sort by price: low to high</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div id="content-add">
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.html" %>
<%@ include file="register.html" %>
<%@ include file="singlepr.html" %>
<%@ include file="search.html" %>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.sticky.js"></script>
<script src="js/jquery.easing.1.3.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/showReg.js"></script>
<script type="text/javascript" src="js/auth.js"></script>
<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>
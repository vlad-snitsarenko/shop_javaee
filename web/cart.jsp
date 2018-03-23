<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cart</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/star-rating-svg.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onload="loadCart()">
<%@ include file="header.html" %>
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Shopping Cart</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="product-content-right">
                <div class="woocommerce">
                    <form>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th class="product-remove">&nbsp;</th>
                                <th class="product-thumbnail">&nbsp;</th>
                                <th class="product-name">Product</th>
                                <th class="product-price">Price</th>
                                <th class="product-quantity">Quantity</th>
                                <th class="product-subtotal">Total</th>
                            </tr>
                            </thead>
                            <tbody id="cart-add">
                            <tr>
                                <td class="actions" colspan="6">
                                    <div class="cart_totals ">
                                        <h2>Cart Totals</h2>
                                        <table cellspacing="0">
                                            <tbody>
                                            <tr class="order-total">
                                                <th>Order Total</th>
                                                <td><strong><span class="amount">BYN </span><span id="orderTotal"
                                                                                                  class="amount"></span></strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th></th>
                                                <td><a style="width: 100px; border-radius: 0px;" id="buyProducts"
                                                       class="btn btn-primary">Buy</a></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                    <div class="cart-collaterals">
                    </div>
                </div>
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
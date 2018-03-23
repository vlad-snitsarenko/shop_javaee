<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/star-rating-svg.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
</head>
<body onload="loadUserInfo();">
<%@ include file="header.html" %>
<article>
    <div class="container">
        <div class="scroll">
            <a href="#mainBlock" class="move"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></a>
        </div>
        <div class="row info" id="mainBlock">
            <div class="col-sm-3">
                <div class="list-group" id="block-menu">
                    <a aria-controls="userInfo" role="tab" data-toggle="tab" href="#userInfo"
                       class="list-group-item active">User Profile Info</a>
                    <a aria-controls="purchasesHistory" role="tab" data-toggle="tab" href="#purchasesHistory"
                       class="list-group-item">Purchases History</a>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="tab-content">
                    <div id="message" class="alert hidden" role="alert">
                    </div>
                    <div role="tabpanel" class="tab-pane active" id="userInfo">
                        <form class="form-horizontal" id="form-userInfo">
                            <div class="form-group">
                                <label for="loginValue" class="col-sm-2 control-label">Login</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="loginValue" disabled>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="emailValue" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="emailValue" disabled>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="dateBirthday" class="col-sm-2 control-label">Birthday Date</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="dateBirthday" disabled>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                        </form>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="purchasesHistory">
                        <div>
                            <div class="single-product-area" style="padding: 0 0 20px;">
                                <div class="zigzag-bottom"></div>
                                <div class="container">
                                    <div class="row">
                                        <div class="product-content-right">
                                            <div class="woocommerce">
                                                <table cellspacing="0" class="shop_table cart"
                                                       style="width: 80% !important;">
                                                    <thead>
                                                    <tr>
                                                        <th class="product-price">Order date</th>
                                                        <th class="product-thumbnail">&nbsp;</th>
                                                        <th class="product-name">Brand</th>
                                                        <th class="product-price">Title</th>
                                                        <th class="product-quantity">Quantity</th>
                                                        <th class="product-subtotal">Total</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="purchasesHistory">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</article>
<%@ include file="singlepr.html" %>
<%@ include file="search.html" %>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.sticky.js"></script>
<script src="js/jquery.easing.1.3.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/bxslider.min.js"></script>
<script type="text/javascript" src="js/script.slider.js"></script>
<script type="text/javascript" src="js/auth.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>
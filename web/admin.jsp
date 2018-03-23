<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin/E_Shop</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/star-rating-svg.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
</head>
<body onload="load();">
<%@ include file="header.html" %>
<article>
    <div class="container">
        <div class="scroll">
            <a href="#mainBlock" class="move"><span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></a>
        </div>
        <div class="row info" id="mainBlock">
            <div class="col-sm-3">
                <div class="list-group" id="block-menu">
                    <a aria-controls="addProduct" role="tab" data-toggle="tab" href="#addProduct"
                       class="list-group-item active">Add Products</a>
                    <a aria-controls="editProducts" role="tab" data-toggle="tab" href="#editProducts"
                       class="list-group-item">Edit/Delete products</a>
                    <a aria-controls="showOptions" role="tab" data-toggle="tab" href="#showOptions"
                       class="list-group-item">Choose option</a>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="tab-content">
                    <div id="message" class="alert hidden" role="alert">
                    </div>
                    <div role="tabpanel" class="tab-pane active" id="addProduct">
                        <form class="form-horizontal" id="form-addProduct">
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">Title</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="title" placeholder="">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="cat-list" class="col-sm-2 control-label">Choose category</label>
                                <div class="col-sm-5">
                                    <select class="form-control" id="cat-list">
                                        <option>Phones</option>
                                        <option>Laptops</option>
                                        <option>Tablets</option>
                                        <option>eReaders</option>
                                    </select>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="descr" class="col-sm-2 control-label">Description</label>
                                <div class="col-sm-5">
                                    <textarea name="text" id="descr" cols="40" rows="3"></textarea>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="image" class="col-sm-2 control-label">Image</label>
                                <div class="col-sm-5">
                                    <input type="file" class="form-control" id="image">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="quantity" class="col-sm-2 control-label">Quantity</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="quantity" placeholder="">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="p_id" class="col-sm-2 control-label">Price_id</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="p_id" placeholder="">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="brand" class="col-sm-2 control-label">Brand</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="brand" placeholder="">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="os" class="col-sm-2 control-label">OS</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="os" placeholder="">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="screen" class="col-sm-2 control-label">Screen(inch)</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="screen" placeholder="4.5">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <label for="storage" class="col-sm-2 control-label">Storage(GB)</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="storage" placeholder="45">
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-9 col-sm-3 text-right">
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="editProducts">
                        <div>
                            <div class="single-product-area">
                                <div class="form-group">
                                    <label for="cat-list-edit" class="col-sm-3 control-label">Choose category</label>
                                    <div class="col-sm-5">
                                        <select class="form-control" id="cat-list-edit"
                                                onchange="showProductsForAdmin(this.selectedIndex+1);">
                                            <option>Phones</option>
                                            <option>Laptops</option>
                                            <option>Tablets</option>
                                            <option>eReaders</option>
                                        </select>
                                    </div>
                                    <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                                </div>
                                <div class="zigzag-bottom"></div>
                                <div class="container">
                                    <div class="row">
                                        <div class="product-content-right">
                                            <div class="woocommerce">
                                                <table cellspacing="0" class="shop_table cart"
                                                       style="width: 80% !important;">
                                                    <thead>
                                                    <tr>
                                                        <th class="product-remove">&nbsp;</th>
                                                        <th class="product-thumbnail">&nbsp;</th>
                                                        <th class="product-name">Brand</th>
                                                        <th class="product-price">Title</th>
                                                        <th class="product-quantity">Quantity</th>
                                                        <th class="product-subtotal">Edit</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="editProducts">
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
                    <div role="tabpanel" class="tab-pane" id="showOptions">
                        <form class="form-horizontal" id="form-changeOptions" onsubmit="return changeOption();">
                            <div class="form-group">
                                <label for="opt-list" class="col-sm-2 control-label">Choose category</label>
                                <div class="col-sm-5">
                                    <select class="form-control" id="opt-list">
                                        <option>Last added</option>
                                        <option>Most Popular</option>
                                    </select>
                                </div>
                                <span class="col-sm-offset-2 col-sm-10 help-block"></span>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-9 col-sm-3 text-right">
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</article>
<%@ include file="edit.html" %>
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
<script type="text/javascript" src="js/admin.js"></script>
<script src="js/jquery.star-rating-svg.js"></script>
</body>
</html>

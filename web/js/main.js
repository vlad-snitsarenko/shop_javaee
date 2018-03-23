var products;
var cart = [];
var jsonCart;
var productsForSort;
var jsonValue;
var optionValue;

function loadProducts() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/shop/GetProductsServlet', false);
    xhr.send();
    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText);
    } else {
        products = JSON.parse(xhr.responseText);
        productsForSort = products;
    }
}

function sortItems(id_cat, option_id) {
    if (option_id === 0) {
        loadProducts();
        showProducts(id_cat, products, 'standart');
    } else if (option_id === 1) {
        products = productsForSort.sort(function (a, b) {
            return parseFloat(b.rating) - parseFloat(a.rating);
        });
        showProducts(id_cat, products);
    } else if (option_id === 2) {
        products = productsForSort.sort(function (a, b) {
            return parseFloat(b.price) - parseFloat(a.price);
        });
        showProducts(id_cat, products);
    } else if (option_id === 3) {
        products = productsForSort.sort(function (a, b) {
            return parseFloat(a.price) - parseFloat(b.price);
        });
        showProducts(id_cat, products);
    }
}

function loadProductsOptions() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/shop/GetProductsOptionsServlet', false);
    xhr.send();
    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText);
    } else {
        products = JSON.parse(xhr.responseText);
    }
}

function checkOptionValue() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/shop/CheckOptionServlet', false);
    xhr.send();
    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.statusText);
    } else {
        jsonValue = xhr.responseText;
    }
}

function showProductsOptions() {
    showUserMenu();
    $('#active-home').addClass('active');
    loadProductsOptions();
    checkOptionValue();
    optionValue = JSON.parse(jsonValue);
    var head = $('h2.section-title');
    var textHead;
    if (optionValue.option == "popular") {
        textHead = 'Most Popular Products';
    } else if (optionValue.option == "last") {
        textHead = 'Latest Products';
    }

    var html = '';
    var cnt = 0;
    for (var i in products) {

        html += `<div class="single-product">`;
        html += `<div class="product-f-image">`;
        html += `<img `;
        if (products[i].hasOwnProperty('id_category')) {
            if (products[i].id_category == 1) {
                html += ` style="max-width: 68%; margin-left: 34px;" `;
            }
            if (products[i].id_category == 3) {
                html += ` style="max-width: 70%; margin-left: 32px;" `;
            }
            if (products[i].id_category == 2) {
                html += ` style="max-width: 100%; margin:0;" `;
            }

            html += `src="${products[i].image}" alt="">`;
            html += `<div class="product-hover">` +
                `<a class="add-to-cart-link" data-id=${products[i].id_product} onclick="addOrder(this)"><i class="fa fa-shopping-cart"></i> Add to cart</a>` +
                `<a id= "${products[i].id_product}" data-id="${products[i].id_product}" onclick="showSingleProduct(this);" class="view-details-link"><i class="fa fa-link"></i> See details</a>` +
                `</div></div><h2><a id="${products[i].id_product}" data-id="${products[i].id_product}" onclick="showSingleProduct(this);">${products[i].brand} ${products[i].title}</a></h2>`;
            html += `<div id="rating-${products[i].id_product}" data-rating=${products[i].rating}></div>` +
                `<div class="product-carousel-price">` +
                `<ins>BYN ${products[i].price}</ins></div></div>`;

        }
        $('.product-carousel').append(html);
        fillRatingItems();
        head.html(textHead);
        owlOptionsProductsCarousel();

    }
}

function owlOptionsProductsCarousel() {
    $('.product-carousel').owlCarousel({
        loop: true,
        nav: true,
        margin: 20,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },
            600: {
                items: 3,
            },
            1000: {
                items: 5,
            }
        }
    });
}

function showProducts(category_id, products, opt) {

    showCartCount();

    var html = '';
    var cnt = 0;
    for (var i in products) {
        if (products[i].id_category == category_id) {

            html += '<div class="col-md-4 col-sm-6"><div class="single-shop-product"><div class="product-upper">';

            html += `<img src="${products[i].image}" alt=""></div>`;
            html += `<h2><a id= "${products[i].id_product}" data-id="${products[i].id_product}" onclick="showSingleProduct(this);">${products[i].brand} ${products[i].title}</a></h2>`;
            html += `<p class="single-descr"> OS: ${products[i].os}, Screen-size: ${products[i].screen}", Storage: ${products[i].storage}GB</p>`;
            html += `<div id="rating-${products[i].id_product}" data-rating=${products[i].rating}></div>`;
            html += `<div class="product-carousel-price"><ins>BYN ${products[i].price}</ins>`;
            html += `</div><div class="product-option-shop"><a class ="add_to_cart_button" data-id=${products[i].id_product} onclick="addOrder(this)">Add to cart</a></div></div></div>`;
        }
    }
    $('div#content-add').html(html);
    if (opt == 'standart') {
        fillFilterSidebar(category_id);
    }
    fillRatingItems();
}

function updateRating(id_product, rating) {
    var data = 'id_product=' + id_product + '&rating=' + rating;
    var URL = '/shop/UpdateRatingServlet';
    postRequest(URL, data, function (data) {
        location.reload();
    });
}

function fillRatingItems() {
    var item;
    var rate;

    for (var key in products) {
        item = products[key].id_product;
        rate = $('#rating-' + item + '').attr('data-rating');
        if (!isAuthorized()) {
            $('#rating-' + item + '').starRating({
                starSize: 25,
                initialRating: rate,
                readOnly: true
            });

        } else
            $('#rating-' + item + '').starRating({
                starSize: 25,
                initialRating: rate,
                callback: function (currentRating, $el) {

                    if (isAuthorized()) {
                        alert("Rated,thanks for your vote");
                        updateRating($el[0].id.replace('rating-', ''), currentRating);
                    }
                }
            });
    }
}

function showSingleProduct(a_id) {
    $('div#search').modal('hide');
    var id = $(a_id).attr('data-id');
    var html = '';
    loadProducts();

    for (var key in products) {
        if (products[key].id_product == id) {
            html += `<div class="row"><div class="col-sm-4" style=" width: 20.333333%;">
                    <div class="product-images"><div class="product-main-img">
                    <img src="${products[key].image}" alt=""></div></div>
                    </div><div class="col-sm-8" style="padding: 0px;margin: 0px;">
                    <div role="tabpanel"><div class="tab-content" style="margin: 0px;">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <h2></h2><h2 class="product-name">${products[key].brand} ${products[key].title}</h2>
                    <p>${products[key].description}</p><p> OS: ${products[key].os}, Screen-size: ${products[key].screen}", Storage: ${products[key].storage}GB</p></div></div></div></div><div class="col-sm-4">`;
            html += `<div class="product-inner">
                    <div id="rating-single-${products[key].id_product}" data-rating="${products[key].rating}" style=""></div> 
                    <div class="product-inner-price" style="margin-top: 10px; margin-bottom: 0;"><span style="margin-left: 10px;">BYN </span><ins>${products[key].price}</ins></div>
                    <form action="" class="cart" style="margin-top: 15px;margin-bottom: 15px;">
                    <div class="quantity"></div><a class ="add_to_cart_button" data-id=${products[key].id_product} onclick="addOrder(this)" style="width: 150px;height: 32px;padding: 5px;text-align: center;">Add to cart</a>
                    </form></div>`;
        }
    }

    $('div#single-add').html(html);
    fillSingleRating(id);
    $('#single-product').modal('show');

}

function fillSingleRating(id) {
    var rate;
    $(document).ready(function ($) {


        rate = $('#rating-single-' + id).attr('data-rating');
    });
    if (!isAuthorized()) {
        $('#rating-single-' + id).starRating({
            starSize: 25,
            initialRating: rate,
            readOnly: true
        });

    } else
        $('#rating-single-' + id).starRating({
            starSize: 25,
            initialRating: rate,
            callback: function (currentRating, $el) {

                if (isAuthorized()) {
                    alert("Rated,thanks for your vote");
                    updateRating($el[0].id.replace('rating-single-', ''), currentRating);
                }
            }
        });
}

function incCount() {
    var cartCount = 0;
    if (getLSObject('cartCount') === null) {
        cartCount = 0;
    } else {
        cartCount = getLSObject('cartCount');
    }
    cartCount++;


    setLSObject('cartCount', cartCount);
    showCartCount();
}

function decCount() {
    var cartCount = 0;
    if (getLSObject('cartCount') === null) {
        cartCount = 0;
    } else {
        cartCount = getLSObject('cartCount');
    }
    cartCount--;

    setLSObject('cartCount', cartCount);
    showCartCount();
}

function searchProducts() {
    var searchText = $('input#search').val().toLowerCase();

    var html = '';
    var countSearch = 0;
    loadProducts();

    for (var i in products) {
        if (~((products[i].title.toLowerCase()).indexOf(searchText)) || ~((products[i].brand.toLowerCase()).indexOf(searchText)) || ~((products[i].os.toLowerCase()).indexOf(searchText)) || ~((products[i].storage.toLowerCase()).indexOf(searchText))) {
            countSearch++;
            html += '<div class="col-md-3 col-sm-6"><div class="single-shop-product"><div class="product-upper">';
            html += `<img src="${products[i].image}" alt=""></div>`;
            html += `<h2><a id= "${products[i].id_product}" data-id="${products[i].id_product}" onclick="showSingleProduct(this);">${products[i].brand} ${products[i].title}</a></h2>`;
            html += `<p class="single-descr"> OS: ${products[i].os}, Screen-size: ${products[i].screen}", Storage: ${products[i].storage}GB</p>`;
            html += `<div class="product-carousel-price"><ins>BYN 120,00</ins>`;
            html += '</div><div class="product-option-shop"><a class ="add_to_cart_button" onclick= "incCount()">Add to cart</a></div></div></div>';
        }
    }
    if (countSearch === 0) {
        html = '<h3>Not found! Change search options.</h3>';
    }
    $('span#titleSearch').html(' (' + countSearch + ')');
    $('div#search-add').html('');
    $('div#search-add').html(html);
    $('div#search').modal('show');
}

function showCartCount() {
    var count = getLSObject('cartCount');
    $('.product-count').html(count);
}

function calcTotal(a_id, type) {
    var id_product = $(a_id).attr('data-id');
    var priceSpan = $('span#price' + id_product + '').html();
    var newTotal = 0;
    var totalSpan = $('span#total' + id_product + '').html();

    var totalCostSpan = $('span#orderTotal');
    var totalCost = $('span#orderTotal').html();
    var newTotalCost = 0;

    if (type == 'plus') {
        newTotal = (parseFloat(totalSpan) + parseFloat(priceSpan)).toFixed(2);
        newTotalCost = (parseFloat(totalCost) + parseFloat(priceSpan)).toFixed(2);
    }
    if (type == 'minus') {
        newTotal = (parseFloat(totalSpan) - parseFloat(priceSpan)).toFixed(2);
        newTotalCost = (parseFloat(totalCost) - parseFloat(priceSpan)).toFixed(2);
    }
    $('span#total' + id_product + '').html(newTotal);
    totalCostSpan.html(newTotalCost);
}

function incInputSize(a_id) {

    var id_product = $(a_id).attr('data-id');
    var inpVal = $('input#inp' + id_product + '').val();

    if (inpVal < 5) {
        inpVal++;
        calcTotal(a_id, 'plus');
        $('input#inp' + id_product + '').val(inpVal);
        addOrder(a_id);

        $('input#inp' + id_product + '').attr('value', inpVal);
    }
    else alert("You cant buy more then 5 items");
}

function decInputSize(a_id) {

    var id_product = $(a_id).attr('data-id');
    var inpVal = $('input#inp' + id_product + '').val();
    if (inpVal > 1) {

        inpVal--;
        calcTotal(a_id, 'minus');
        $('input#inp' + id_product + '').val(inpVal);

        reduceOrder(a_id);
        $('input#inp' + id_product + '').attr('value', inpVal);
    } else deleteOrder(a_id);
}

function deleteOrder(a_id) {

    if (!isAuthorized()) {
        delGoods(a_id);
    }
    var id_product = $(a_id).attr('data-id');
    var inpVal = $('input#inp' + id_product + '').val();
    for (var i = 0; i < inpVal; i++) {
        decCount();
    }
    if (isAuthorized()) {

        var id_order = $(a_id).attr('data-order-id');
        var order = 'id_order=' + id_order;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                console.log(xhr.responseText);
                location.reload();
            }
        };
        xhr.open('POST', '/shop/DeleteOrderServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(order);
    }


    showCartCount();
}

function reduceOrder(a_id) {


    var id_pr = $(a_id).attr('data-id');
    var id_p = parseInt(id_pr);
    var date = getFormattedDate(new Date());
    var total;
    for (var i in products) {
        if (products[i].id_product == id_pr) {
            total = parseFloat(products[i].price);
        }
    }

    //если залогинился
    if (isAuthorized()) {
        var user = getUserObj('user');
        var login = getUserLogin(user);

        var order = 'login=' + login + '&id_product=' + id_pr;

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                console.log(xhr.responseText);
            }
        };
        xhr.open('POST', '/shop/ReduceOrderServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(order);
    }
    else {
        cart = getCart();
        //не пустая
        var flag = false;
        cart.forEach(function (element, index) {
            if (element.id_product == id_p) {
                element.quantity--;
                element.total -= total;
                flag = true;
            }

        });
        saveCart();
    }
    decCount();
}

function addOrder(a_id) {
    incCount();
    showCartCount();

    var id_pr = $(a_id).attr('data-id');
    var id_p = parseInt(id_pr);
    var date = getFormattedDate(new Date());
    var total;
    for (var i in products) {
        if (products[i].id_product == id_pr) {
            total = parseFloat(products[i].price);
        }
    }

    if (isAuthorized()) {
        var user = getUserObj('user');
        var login = getUserLogin(user);

        var quantity = 1;

        var order = 'login=' + login + '&id_product=' + id_pr + '&date=' + date +
            '&quantity=' + quantity + '&total=' + total;

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                console.log(xhr.responseText);
            }
        };
        xhr.open('POST', '/shop/AddOrderServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(order);
    }
    else {
        cart = getCart();

        if (cart === null) {
            cart = [];
            cart[0] = {id_product: id_p, date: date, quantity: 1, total: total};

            saveCart();
        } else {
            var flag = false;
            cart.forEach(function (element, index) {
                if (element.id_product == id_p) {
                    element.quantity++;
                    element.total += total;
                    flag = true;
                }
            });
            if (!flag) {
                cart.push({id_product: id_p, date: date, quantity: 1, total: total});
            }
            saveCart();
        }
    }
    if ((window.location.href != "http://localhost:8080/shop/cart.jsp")) {
        var answer = confirm("Product sucsessfully added. You want go to cart?");
        if (answer) {
            window.location.href = "http://localhost:8080/shop/cart.jsp";
        }
    }

}

function loadOrders() {
    if (isAuthorized()) {
        var user = getUserObj('user');
        var login = getUserLogin(user);
        var ordersLoad = 'login=' + login;

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                jsonCart = xhr.responseText;
            }
        };
        xhr.open('POST', '/shop/GetOrdersServlet', false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(ordersLoad);
    }
}

function setLSObject(key, value) {
    if (isAuthorized()) {
        var user = getUserObj('user');
        var login = getUserLogin(user);
        localStorage.setItem(key + login, JSON.stringify(value));
    } else {
        localStorage.setItem(key, JSON.stringify(value));
    }
}

function getLSObject(key) {
    if (isAuthorized()) {
        var user = getUserObj('user');
        var login = getUserLogin(user);
        return (JSON.parse(localStorage.getItem(key + login)));
    } else {
        return (JSON.parse(localStorage.getItem(key)));
    }
}

function isJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}

function loadCart() {
    showUserMenu();
    $('#active-cart').addClass('active');
    showCart();
}

function showCart() {
    var totalOrderCost = 0;
    var totalCostSpan = $('span#orderTotal');
    loadProducts();
    if (isAuthorized()) {
        loadOrders();
        if (isJsonString(jsonCart)) {
            cart = JSON.parse(jsonCart);
        }
    } else {
        cart = getCart();
    }
    if (isEmpty(cart)) {
        var html = '';
        for (var key in cart) {
            var order_id = cart[key].id_order;
            var id_pr = cart[key].id_product;
            var quant = cart[key].quantity;
            var total = cart[key].total;
            if (!isAuthorized()) {
                totalOrderCost += cart[key].total;
            }
            var brand;
            var title;
            var img;
            for (var i in products) {
                if (products[i].id_product == id_pr) {
                    title = products[i].title;
                    brand = products[i].brand;
                    img = products[i].image;
                    price = (products[i].price).replace(',', '.');
                    total = ((parseFloat(price)) * quant).toFixed(2);
                    if (isAuthorized()) {
                        totalOrderCost += ((parseFloat(price)) * quant);
                    }
                    html += '<tr class="cart_item"><td class="product-remove"><a title="Remove this item" data-id="' + id_pr + '" data-order-id="' + order_id + '" class="remove" onclick="deleteOrder(this)">x</a></td>';
                    html += '<td class="product-thumbnail"><a href=""><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="' + img + '"></a></td>';
                    html += '<td class="product-name"><a href="">' + brand + ' ' + title + '</a></td>';
                    html += '<td class="product-price"><span class="amount">BYN </span><span id="price' + id_pr + '" class="amount">' + price + '</span></td>';
                    html += '<td class="product-quantity"><div class="quantity buttons_added"><input type="button" data-order-id="' + order_id + '" data-id="' + id_pr + '" class="minus" value="-" onclick="decInputSize(this)">';
                    html += '<input type="text" disabled size="1" id="inp' + id_pr + '" class="input-text qty text" title="Qty" value="' + quant + '" min="0" step="1">';
                    html += '<input type="button" data-id="' + id_pr + '" class="plus" value="+" onclick="incInputSize(this)"></div></td>';
                    html += '<td class="product-subtotal"><span class="amount">BYN </span><span id="total' + id_pr + '" class class="amount">' + total + '</span></td></tr>';
                }
            }
        }
        $('tbody#cart-add').prepend(html);
        totalCostSpan.html(totalOrderCost.toFixed(2));
    }
    else {
        $('div.single-product-area').css("height", 250);
        $('div.product-content-right').html('<h3 style="text-align: center">Your cart is empty</h3>');
    }
}

function buyItems() {
    if (isAuthorized()) {
        buyOrder();
    } else {
        alert("You need login/register");
        showLoginForm();
    }

}

function buyOrder() {
    var id_product;
    var pr_quantity;
    var user = getUserObj('user');
    var login = getUserLogin(user);
    for (var key in cart) {
        id_product = cart[key].id_product;
        pr_quantity = cart[key].quantity;
        var data = "id_product=" + id_product + '&quantity=' + pr_quantity + '&login=' + login;
        var URL = '/shop/BuyProductsServlet';
        postRequest(URL, data, function (status) {
        });
    }
    setLSObject('cartCount', 0);
    showCartCount();
    cart = null;
    alert("Sucsesfully added");
    showCart();
}

function fillFilterSidebar(id) {
    var brand_name;
    var os_name;
    var screen_size;
    var storage_size;

    var brand = {};
    var os = {};
    var screen = {};
    var storage = {};

    for (var i in products) {
        if (products[i].id_category == id) {
            brand_name = products[i].brand;
            os_name = products[i].os;
            screen_size = products[i].screen;
            storage_size = products[i].storage;

            if (brand_name in brand) {
                brand[brand_name]++;
            } else
                brand[brand_name] = 1;

            if (os_name in os) {
                os[os_name]++;
            } else
                os[os_name] = 1;

            if (screen_size in screen) {
                screen[screen_size]++;
            } else
                screen[screen_size] = 1;

            if (storage_size in storage) {
                storage[storage_size]++;
            } else
                storage[storage_size] = 1;
        }
    }
    var html_brands = '';
    var html_os = '';
    var html_screen = '';
    var html_storage = '';
    for (var key  in brand) {
        html_brands += '<li>' +
            '<label class="filter-checkbox-item"">' +
            '<span class="i-checkbox">' +
            '<input type="checkbox" name="filter" class="i-checkbox__real" onchange="filterProducts(this);" data-category-id="' + id + '" value="' + key + '">' +
            '<span class="i-checkbox__faux"></span>' +
            '</span>' +
            '<span class="filter-checkbox-text">' + key + '</span>' +
            '</label>' +
            '</li>';
    }
    for (var key  in os) {
        html_os += '<li>' +
            '<label class="filter-checkbox-item"">' +
            '<span class="i-checkbox">' +
            '<input type="checkbox" name="filter" class="i-checkbox__real" onchange="filterProducts(this);" data-category-id="' + id + '"  value="' + key + '">' +
            '<span class="i-checkbox__faux"></span>' +
            '</span>' +
            '<span class="filter-checkbox-text">' + key + '</span>' +
            '</label>' +
            '</li>';
    }
    for (var key  in screen) {
        html_screen += '<li>' +
            '<label class="filter-checkbox-item"">' +
            '<span class="i-checkbox">' +
            '<input type="checkbox" name="filter" class="i-checkbox__real" onchange="filterProducts(this);" data-category-id="' + id + '" value="' + key + '">' +
            '<span class="i-checkbox__faux"></span>' +
            '</span>' +
            '<span class="filter-checkbox-text">' + key + '"</span>' +
            '</label>' +
            '</li>';
    }
    for (var key  in storage) {
        html_storage += '<li>' +
            '<label class="filter-checkbox-item"">' +
            '<span class="i-checkbox">' +
            '<input type="checkbox" name="filter" class="i-checkbox__real" onchange="filterProducts(this);" data-category-id="' + id + '"  value="' + key + '">' +
            '<span class="i-checkbox__faux"></span>' +
            '</span>' +
            '<span class="filter-checkbox-text">' + key + ' GB</span>' +
            '</label>' +
            '</li>';
    }
    $('ul#brands-add').html(html_brands);
    $('ul#screen-add').html(html_screen);
    $('ul#os-add').html(html_os);
    $('ul#storage-add').html(html_storage);

}

function showReaders() {
    showUserMenu();
    $('#active-readers').addClass('active');
    loadProducts();

    showProducts(4, products, 'standart');
}

function showTablets() {
    showUserMenu();
    $('#active-tablets').addClass('active');
    loadProducts();

    showProducts(3, products, 'standart');
}

function showPhones() {
    showUserMenu();
    $('#active-phones').addClass('active');
    loadProducts();

    showProducts(1, products, 'standart');
}

function showLaptops() {
    showUserMenu();
    $('#active-laptops').addClass('active');
    loadProducts();

    showProducts(2, products, 'standart');
}

function filterProducts(ref_a) {
    var brandValues = [];
    var screenValues = [];
    var osValues = [];
    var storageValues = [];
    productsForSort = [];
    var filterCategory = $(ref_a).attr('data-category-id');
    $("#brands-add li input:checkbox[name=filter]:checked").each(function () {
        brandValues.push($(this).val());
    });
    $("#screen-add li input:checkbox[name=filter]:checked").each(function () {
        screenValues.push($(this).val());
    });
    $("#os-add li input:checkbox[name=filter]:checked").each(function () {
        osValues.push($(this).val());
    });
    $("#storage-add li input:checkbox[name=filter]:checked").each(function () {
        storageValues.push($(this).val());
    });

    if (brandValues.length === 0) {
        $("#brands-add li input:checkbox[name=filter]:not(:checked)").each(function () {
            brandValues.push($(this).val());
        });
    }
    if (screenValues.length === 0) {
        $("#screen-add li input:checkbox[name=filter]:not(:checked)").each(function () {
            screenValues.push($(this).val());
        });
    }
    if (osValues.length === 0) {
        $("#os-add li input:checkbox[name=filter]:not(:checked)").each(function () {
            osValues.push($(this).val());
        });
    }
    if (storageValues.length === 0) {
        $("#storage-add li input:checkbox[name=filter]:not(:checked)").each(function () {
            storageValues.push($(this).val());
        });
    }

    var data = 'id_cat=' + filterCategory + '&brands=' + brandValues.join(";") + '&screen=' + screenValues.join(";") +
        '&os=' + osValues.join(";") + '&storage=' + storageValues.join(";");
    var URL = '/shop/FilterProductsServlet';
    postRequest(URL, data, function (data) {
        if (data === null) {
            $('div#content-add').html('<h3>Nothing to show</h3>');
        } else {
            products = data;
            productsForSort = products;
            var html = '';
            var cnt = 0;
            for (var i in products) {
                html += '<div class="col-md-4 col-sm-6"><div class="single-shop-product"><div class="product-upper">';
                html += `<img src="${products[i].image}" alt=""></div>`;
                html += `<h2><a id= "${products[i].id_product}" data-id="${products[i].id_product}" onclick="showSingleProduct(this);">${products[i].brand} ${products[i].title}</a></h2>`;
                html += `<p class="single-descr"> OS: ${products[i].os}, Screen-size: ${products[i].screen}", Storage: ${products[i].storage}GB</p>`;
                html += `<div id="rating-${products[i].id_product}" data-rating=${products[i].rating}></div>`;
                html += `<div class="product-carousel-price"><ins>BYN ${products[i].price}</ins>`;
                html += `</div><div class="product-option-shop"><a class ="add_to_cart_button" data-id=${products[i].id_product} onclick="addOrder(this)">Add to cart</a></div></div></div>`;
            }
            $('div#content-add').html(html);
            fillRatingItems();
        }
    });

}

function getFormattedDate(date) {
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return day + '.' + month + '.' + year;
}

function filter_array(test_array) {
    var index = -1,
        arr_length = test_array ? test_array.length : 0,
        resIndex = -1,
        result = [];

    while (++index < arr_length) {
        var value = test_array[index];

        if (value) {
            result[++resIndex] = value;
        }
    }

    return result;
}

function delGoods(a_id) {
    var id = $(a_id).attr('data-id');
    for (var key in cart) {
        if (cart[key].id_product == id) {
            delete cart[key];
        }
    }
    cart = filter_array(cart);
    saveCart();
    location.reload();
}

function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
}

function getCart() {
    return JSON.parse(localStorage.getItem('cart'));
}

function isEmpty(object) {
    for (var key in object)
        if (object.hasOwnProperty(key)) return true;
    return false;
}

jQuery(document).ready(function ($) {
    showCartCount();
    $('#buyProducts').click(function (e) {
        e.preventDefault();
        buyItems();
    });
    $(".mainmenu-area").sticky({topSpacing: 0});

    $('.related-products-carousel').owlCarousel({
        loop: true,
        nav: true,
        margin: 20,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },
            600: {
                items: 2,
            },
            1000: {
                items: 2,
            },
            1200: {
                items: 3,
            }
        }
    });

    $('.brand-list').owlCarousel({
        loop: true,
        nav: true,
        margin: 20,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },
            600: {
                items: 3,
            },
            1000: {
                items: 4,
            }
        }
    });

    $(".navbar-nav li a").click(function () {
        $(".navbar-collapse").removeClass('in');
    });

    var nav_scroll = $('.nav s.navbar-nav li a');
    if (nav_scroll !== undefined) {
        nav_scroll.bind('click', function (event) {
            var $anchor = $(this);
            var headerH = $('.header-area').outerHeight();
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top - headerH + "px"
            }, 1200, 'easeInOutExpo');

            event.preventDefault();
        });
    }
    $('body').scrollspy({
        target: '.navbar-collapse',
        offset: 95
    })
});

  

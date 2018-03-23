$(document).on('shown.bs.tab', '#block-menu > a', function (event) {

    var active = $(event.target);
    var classList = active.attr("class");

    var elements = $('#block-menu').children();
    elements.attr('class', classList);

    active.attr('class', classList + ' active');
    if (active.attr('aria-controls') == 'purchasesHistory') {
        showProductsHistory();
    }
});

var jsonOrdersHistory;
var orders;
var products;

function loadOrdersHistory() {
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
                jsonOrdersHistory = xhr.responseText;

            }
        };
        xhr.open('POST', '/shop/GetOrdersHistoryServlet', false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(ordersLoad);
    }
}
function showProductsHistory() {
    if (isAuthorized()) {
        loadProducts();
        loadOrdersHistory();
        if (isJsonString(jsonOrdersHistory)) {
            orders = JSON.parse(jsonOrdersHistory);
        }
    }
    if (isEmpty(orders)) {
        var storage;
        var id_p;
        var html = '';
        for (var key in orders) {
            var order_id = orders[key].id_order;
            var id_pr = orders[key].id_product;
            var quant = orders[key].quantity;
            var total = orders[key].total;
            var date = orders[key].date;
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
                    html += '<tr class="cart_item"><td class="product-price">' + date + '</td>';
                    html += '<td class="product-thumbnail"><a href=""><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="' + img + '" style="height: 60px; width: auto;"></a></td>';
                    html += '<td class="product-name"><a href="">' + brand + '</a></td>';
                    html += '<td class="product-price">' + title + '</td>';
                    html += '<td class="product-quantity">';
                    html += '<input type="text" disabled size="1" id="inp" class="input-text qty text" title="Qty" value="' + quant + '" min="0" step="1">';
                    html += '</td>';
                    html += '<td class="product-subtotal"><span class="amount">' + total + '</span></td></tr>';
                }
            }
        }
        $('tbody#purchasesHistory').html(html);
    } else {

        $('div.product-content-right').html('<h3 style="text-align: center">You did not buy anything</h3>');
    }

}

function loadUserInfo() {
    showUserMenu();
    if (isAuthorized()) {
        var user = getUserObj('user');
        var userLogin = getUserLogin(user);
        var userEmail = user.email;
        var userBirthday = user.dob;

        $('#loginValue').val(userLogin);
        $('#emailValue').val(userEmail);
        $('#dateBirthday').val(userBirthday);

    } else {
        window.location.href = "http://localhost:8080/shop/index.jsp";
    }
}
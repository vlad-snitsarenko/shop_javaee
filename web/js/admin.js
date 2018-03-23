function load() {
    if (!isAuthorized() || !isAdmin()) {
        window.location.href = "http://localhost:8080/shop/index.jsp";
    }
    showUserMenu();
    changeProduct();
    addProduct();
}

function createSelectList(id, list, key) {
    var select = document.getElementById(id);

    function removeOptions() {
        var length = select.options.length;
        if (length > 1) {
            select.options.remove(length - 1);
            removeOptions();
        }
    }

    removeOptions();

    list.forEach(function (listValue) {
        var option = document.createElement('OPTION');
        option.value = listValue[key];
        option.text = listValue[key];
        select.appendChild(option);
    });
}

$(document).on('shown.bs.tab', '#block-menu > a', function (event) {
    hideMessage();
    var active = $(event.target);
    var classList = active.attr("class");

    var elements = $('#block-menu').children();
    elements.attr('class', classList);
    active.attr('class', classList + ' active');
    if (active.attr('aria-controls') == 'addProduct') {
    }

    if (active.attr('aria-controls') == 'editProducts') {
        var cat = localStorage.getItem('cat');
        if (cat === null) cat = 1;
        showProductsForAdmin(cat);

    }
});
function showProductsForAdmin(act_id) {
    localStorage.setItem('cat', act_id);
    loadProducts();
    var div = $('tbody#editProducts');
    var html = '', title, brand, img, quant, storage, id_p;
    for (var i in products) {
        if (products[i].id_category == act_id) {
            id_p = products[i].id_product;
            quant = products[i].quantity;
            title = products[i].title;
            brand = products[i].brand;
            img = products[i].image;
            storage = products[i].storage;
            html += '<tr class="cart_item"><td class="product-remove"><a title="Remove this item" data-cat-id="' + act_id + '" data-id="' + id_p + '" class="remove" onclick="deleteProduct(this)">x</a></td>';
            html += '<td class="product-thumbnail"><a href=""><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="' + img + '" style="height: 60px; width: auto;"></a></td>';
            html += '<td class="product-name"><a href="">' + brand + '</a></td>';
            html += '<td class="product-price">' + title + '</td>';
            html += '<td class="product-quantity">';
            html += '<input type="text" disabled size="1" id="inp" class="input-text qty text" title="Qty" value="' + quant + '" min="0" step="1">';
            html += '</td>';
            html += '<td class="product-remove"> <a title="Remove this item" data-id="' + id_p + '" class="remove" onclick="showEditProduct(this)" style="text-transform: unset;">Edit</a></td></tr>';
        }


    }
    div.html(html);
}

function deleteProduct(a_id) {
    var flag = confirm("You are really wanna delete this item");
    if (flag) {
        var id_product = $(a_id).attr('data-id');
        var id_cat = $(a_id).attr('data-cat-id');

        var data = 'id_product=' + id_product;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4)
                return;
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {

                showProductsForAdmin(id_cat);

            }
        };
        xhr.open('POST', '/shop/DeleteProductServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(data);
    }


}
function changeOption() {
    var options = document.getElementById('opt-list');
    var optionId = options.selectedIndex;
    var optionValue = "";
    if (optionId === 0) {
        optionValue = "last";
    } else if (optionId === 1) {
        optionValue = "popular";
    }

    var data = "option_value=" + optionValue;
    var URL = '/shop/ChangeOptionServlet';

    postRequest(URL, data, function (status) {
        if ('status' in status) {
            showMessage('alert-success', status.status);
        }
        else if ('error' in status) {
            showMessage('alert-danger', status.error);
        }
    });
    return false;
}

function postRequestFileAdd(url, data, callback) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4)
            return;
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log(xhr.responseText);
            callback(xhr.responseText);
        }
    };
    xhr.open('POST', url, true);

    xhr.send(data);
}

function addProduct() {
    document.getElementById("form-addProduct").addEventListener("submit", function (event) {
        event.preventDefault();
        hideMessage();
        var title = document.getElementById('title').value;
        var categories = document.getElementById('cat-list');
        var category = categories.selectedIndex + 1;
        var descr = document.getElementById('descr').value;
        var blobimage = document.getElementById('image').files[0];
        var quant = document.getElementById('quantity').value;
        var brand = document.getElementById('brand').value;
        var os = document.getElementById('os').value;
        var screen = document.getElementById('screen').value;
        var storage = document.getElementById('storage').value;
        var p_id = document.getElementById('p_id').value;
        console.log(blobimage);

        markErrorField(this);
        var error = '';

        if (!title) {
            error = 'Enter title';
            markErrorField(this, document.getElementById('title'), error);
        }

        if (!descr) {
            error = 'Enter description';
            markErrorField(this, this.elements['descr'], error);
        }
        if (!category) {
            error = 'Choose category';
            markErrorField(this, categories, error);
        }
        if (!image) {
            error = 'Enter image path';
            markErrorField(this, this.elements['image'], error);
        }
        if (!quant) {
            error = 'Enter quantity';
            markErrorField(this, this.elements['quantity'], error);
        }
        if (!brand) {
            error = 'Enter brand name';
            markErrorField(this, this.elements['brand'], error);
        }
        if (!os) {
            error = 'Enter os';
            markErrorField(this, this.elements['os'], error);
        }
        if (!screen) {
            error = 'Enter screen-size';
            markErrorField(this, this.elements['screen'], error);
        }
        if (!storage) {
            error = 'Enter storage size';
            markErrorField(this, this.elements['storage'], error);
        }
        if (!p_id) {
            error = 'Enter price id';
            markErrorField(this, this.elements['p_id'], error);
        }
        if (!error) {
            var file = new FormData();
            file.append("image", blobimage);
            var URL_FILE = '/shop/FileUploadServlet';
            var imagePath;
            postRequestFileAdd(URL_FILE, file, function (path) {
                if (path !== null) {
                    imagePath = path;
                    var data = 'title=' + title + '&id_category=' + category + '&description=' + descr +
                        '&image=' + imagePath + '&quantity=' + quant + '&brand=' + brand + '&os=' + os +
                        '&screen=' + screen + '&storage=' + storage + '&p_id=' + p_id;
                    var URL = '/shop/AddProductServlet';
                    postRequest(URL, data, function (status) {
                        if ('status' in status) {
                            showMessage('alert-success', status.status);
                        }
                        else if ('error' in status) {
                            showMessage('alert-danger', status.error);
                        }
                    });
                }
            });
        } else {
            window.scrollTo(0, 0);
            return;
        }
        window.scrollTo(0, 0);
    });
}

function showEditProduct(a_id) {
    hideMessage();
    var id = $(a_id).attr('data-id');
    var html = '';
    loadProducts();

    for (var key in products) {
        if (products[key].id_product == id) {
            $('#title-change').attr('data-id', '' + products[key].id_product);
            $('#title-change').val(products[key].title);
            if (products[key].id_category === 1) {
                $('#cat-list-change').val('Phones');
            } else if (products[key].id_category === 2) {
                $('#cat-list-change').val('Laptops');
            } else if (products[key].id_category === 3) {
                $('#cat-list-change').val('Tablets');
            }
            else if (products[key].id_category === 3) {
                $('#cat-list-change').val('eReaders');
            }
            $('#descr-change').val(products[key].description);
            $('#image-old').val(products[key].image);
            $('#quantity-change').val(products[key].quantity);
            $('#brand-change').val(products[key].brand);
            $('#os-change').val(products[key].os);
            $('#screen-change').val(products[key].screen);
            $('#storage-change').val(products[key].storage);
            $('#p_id-change').val(products[key].p_id);
        }
    }
    $('#change-product').modal('show');
}

function changeProduct() {
    document.getElementById("form-changeProduct").addEventListener("submit", function (event) {
        event.preventDefault();
        var id_pr = $('#title-change').attr('data-id');
        var imageChoosen = false;
        var blobimage;
        var title = document.getElementById('title-change').value;
        var categories = document.getElementById('cat-list-change');
        var category = categories.selectedIndex + 1;
        var descr = document.getElementById('descr-change').value;
        if (document.getElementById("image-change").files.length === 0) {
            console.log("no files selected");

        } else {
            imageChoosen = true;
            blobimage = document.getElementById('image-change').files[0];
        }
        var image = document.getElementById('image-old').value;
        var quant = document.getElementById('quantity-change').value;
        var brand = document.getElementById('brand-change').value;
        var os = document.getElementById('os-change').value;
        var screen = document.getElementById('screen-change').value;
        var storage = document.getElementById('storage-change').value;
        var p_id = document.getElementById('p_id-change').value;

        markErrorField(this);
        var error = '';

        if (!title) {
            error = 'Enter title';
            markErrorField(this, document.getElementById('title-change'), error);
        }

        if (!descr) {
            error = 'Enter description';
            markErrorField(this, this.elements['descr-change'], error);
        }
        if (!category) {
            error = 'Choose category';
            markErrorField(this, categories, error);
        }
        if (!image) {
            error = 'Enter image path';
            markErrorField(this, this.elements['image-change'], error);
        }
        if (!quant) {
            error = 'Enter quantity';
            markErrorField(this, this.elements['quantity-change'], error);
        }
        if (!brand) {
            error = 'Enter brand name';
            markErrorField(this, this.elements['brand-change'], error);
        }
        if (!os) {
            error = 'Enter os';
            markErrorField(this, this.elements['os-change'], error);
        }
        if (!screen) {
            error = 'Enter screen-size';
            markErrorField(this, this.elements['screen-change'], error);
        }
        if (!storage) {
            error = 'Enter storage size';
            markErrorField(this, this.elements['storage-change'], error);
        }
        if (!p_id) {
            error = 'Enter price id';
            markErrorField(this, this.elements['p_id-change'], error);
        }

        if (!error) {
            if (imageChoosen) {
                var file = new FormData();
                file.append("image", blobimage);
                var URL_FILE = '/shop/FileUploadServlet';
                var imagePath;
                postRequestFileAdd(URL_FILE, file, function (path) {
                    if (path !== null) {
                        imagePath = path;

                        var data = "title=" + title + '&id_category=' + category + '&description=' + descr +
                            '&image=' + imagePath + '&quantity=' + quant + '&brand=' + brand + '&os=' + os +
                            '&screen=' + screen + '&storage=' + storage + '&p_id=' + p_id + '&id_pr=' + id_pr;
                        var URL = '/shop/UpdateProductServlet';

                        postRequest(URL, data, function (status) {
                            if ('status' in status) {
                                showMessage('alert-success', status.status);
                            }
                            else if ('error' in status) {
                                showMessage('alert-danger', status.error);
                            }
                        });
                    }
                });
            } else {
                var data = "title=" + title + '&id_category=' + category + '&description=' + descr +
                    '&image=' + image + '&quantity=' + quant + '&brand=' + brand + '&os=' + os +
                    '&screen=' + screen + '&storage=' + storage + '&p_id=' + p_id + '&id_pr=' + id_pr;
                var URL = '/shop/UpdateProductServlet';

                postRequest(URL, data, function (status) {
                    if ('status' in status) {
                        showMessage('alert-success', status.status);
                    }
                    else if ('error' in status) {
                        showMessage('alert-danger', status.error);
                    }
                });
            }
        } else {
            window.scrollTo(0, 0);
            return;
        }
        $('#change-product').modal('hide');
        window.scrollTo(0, 0);
    });
}

function showMessage(style, msg) {
    var div = document.getElementById('message');
    div.className = 'alert ' + style;
    div.innerHTML = msg;
}

function hideMessage() {
    var div = document.getElementById('message');
    div.className = 'alert hidden';
    div.innerHTML = '';
}

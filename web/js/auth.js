var createUser = function (data, callback) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4)
            return;
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            callback(xhr.responseText);
        }
    };
    xhr.open('POST', '/shop/AddUserServlet', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(data);
};

function postRequest(url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4)
            return;
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            callback(JSON.parse(xhr.responseText));
        }
    };
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}
function checkLogin(form, login) {
    var flag = true;
    if (!login) {
        flag = false;
    }
    return flag;
}

function checkEmail(form, email) {
    var flag = true;
    if (!email) {
        flag = false;
    }
    return flag;
}


function checkPassword(form, passwd) {
    var flag = true;
    if (!passwd) {
        flag = false;
    }
    return flag;
}

function checkConfirmPasswd(form, passwd, confpasswd) {
    var flag = true;
    if (passwd != confpasswd) {
        flag = false;
    }
    return flag;
}

function registration() {
    var form = document.getElementById('form-reg');
    markErrorField(form);
    var error = '';
    var login = form.elements.reglogin.value;
    var email = form.elements.regemail.value;
    var passwd = form.elements.regpasswd.value;
    var confpasswd = form.elements.confpasswd.value;
    var dob = form.elements.dob.value;
    if (!checkEmail(form, email)) {
        error = "Wrong email";
        markErrorField(form, form.elements['regemail'], error);
    } else if (!checkPassword(form, passwd)) {
        error = "Password is empty";
        markErrorField(form, form.elements['regpasswd'], error);
    } else if (!checkConfirmPasswd(form, passwd, confpasswd)) {
        error = "Password confirmation does not equal to Password";
        markErrorField(form, form.elements["confpasswd"], error);
    }
    if (!error) {
        var URL = '/shop/AddUserServlet';
        var data = 'login=' + login + '&email=' + email + '&password=' + passwd + '&dob=' + dob;
        postRequest(URL, data, function (user) {
            if ('error' in user) {
                error = user.error;
                markErrorField(form, form.elements['reglogin'], error);
            }
            else {
                window.location.href = "http://localhost:8080/shop/index.jsp";
            }
        });
    }
}

function login_user() {
    var form = document.getElementById('form-login');
    markErrorField(form);
    var error = '';
    var login = form.elements.login.value;
    var passwd = form.elements.passwd.value;

    var query = 'login=' + login + '&password=' + passwd;
    var URL = '/shop/GetUserServlet';
    postRequest(URL, query, function (user) {
        if (user.login !== '') {
            setUserObj('user', user);
            window.location.href = "http://localhost:8080/shop/index.jsp";
        }
        else if ('error' in user) {
            error += user.error;
            markErrorField(form, form.elements.passwd, error);
        }
    });

}

function logout() {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/shop/index.jsp";
}

function setUserObj(key, value) {
    sessionStorage.setItem(key, JSON.stringify(value));
}

function getUserObj(key) {
    return JSON.parse(sessionStorage.getItem(key));
}

function getUserLogin(user) {
    var login = '';
    if (user.login != 'null') {
        login = user.login;
    }
    return login;
}

function showLoginForm() {
    $('#authorization').modal('show');
}

function checkPermissionDecorator(f) {
    return function () {
        if (isAdmin()) {
            return f.apply(this, arguments);
        }
        window.location.href = "http://localhost:8080/shop/index.jsp";
    };
}

function showAdminMenu() {
    var ul = logged.getElementsByTagName('ul')[0];
    var li = document.createElement('LI');
    var a = document.createElement('A');

    li.setAttribute('data-target', 'admin');
    a.href = 'http://localhost:8080/shop/admin.jsp';
    a.text = 'Administration';

    li.appendChild(a);
    ul.insertBefore(li, ul.getElementsByTagName('li')[1]);
}

function showUserMenu() {
    var login = document.getElementById('login');
    var logged = document.getElementById('logged');
    if (isAuthorized()) {
        if (isAdmin()) {
            showAdminMenu();
        }
        showUserLogin();
        login.classList.add('hidden');
        logged.classList.remove('hidden');
    }
    else {
        login.classList.remove('hidden');
        logged.classList.add('hidden');
    }

    function showUserLogin() {
        var user = getUserObj('user');
        var userLogin = getUserLogin(user);

        var a = document.getElementById('username');
        var span = document.createElement('SPAN');
        span.className = 'caret';

        a.text = userLogin;
        a.appendChild(span);
    }

}

function isAuthorized() {
    return (getUserObj('user') != null);
}

function isAdmin(key) {
    var user = getUserObj('user');
    return user.role == 2;
}

function markErrorField(obj, _element, error) {
    var allLabels = obj.getElementsByTagName('LABEL');
    if (_element) {
        for (var i = 0; i < allLabels.length; i++) {
            if (allLabels[i].htmlFor == _element.id) {
                var parent = allLabels[i].parentNode;
                parent.classList.add('has-error');
                parent.getElementsByTagName('SPAN')[0].textContent = error;
            }
        }
    } else {
        for (var x = 0; x < allLabels.length; x++) {
            var parentN = allLabels[x].parentNode;
            parentN.classList.remove('has-error');
            var span = parentN.getElementsByTagName('SPAN')[0];
            if (span) {
                span.textContent = "";
            }
        }
    }
}

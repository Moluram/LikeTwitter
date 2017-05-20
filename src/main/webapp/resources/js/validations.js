function notEmptyTest(id, errorDiv, inputDiv, emptyMessage) {
    if (inputDiv.value === "" || !(/\S/.test(inputDiv.value))) {
        createErrorDiv(id, inputDiv, emptyMessage);
        return false;
    } else {
        if (errorDiv) {
            document.getElementById(id + "Div").removeChild(errorDiv)
        }
        return true;
    }
}
function validate(id, inputDiv, emptyMessage) {
    var errorDiv = document.getElementById(id + ".errors");
    return notEmptyTest(id, errorDiv, inputDiv, emptyMessage);
}

function makeErrorDiv(id, message) {
    var div = document.createElement("div");
    div.className = "alert alert-danger";
    div.innerHTML = message;
    div.setAttribute("id", id + ".errors");
    return div;
}

function createErrorDiv(id, inputDiv, message) {
    var errorDiv = document.getElementById(id + ".errors");
    if (!errorDiv) {
        var div = makeErrorDiv(id, message);
        document.getElementById(id + "Div").insertBefore(div, inputDiv);
    } else {
        errorDiv.innerHTML = message
    }
}

function validateEmail(id, inputDiv, message) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(inputDiv.value)){
        createErrorDiv(id, inputDiv, message);
    }
}

function validateUsername(id, inputDiv, message) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: '/signup/test-username',
        data: "username=" + inputDiv.value,
        dataType: 'json',
        async: false,
        timeout: 100000,
        success: function (data) {
            if (!data) {
                var errorDiv = document.getElementById(id + ".errors");
                if (!errorDiv) {
                    var div = makeErrorDiv(id, message);
                    document.getElementById(id + "Div").insertBefore(div, inputDiv);
                } else {
                    errorDiv.innerHTML = message
                }
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE", e)
        }
    });
}

function validatePasswords(id, passId, inputDiv, message) {
    if (inputDiv.value !== document.getElementById(passId + "Input").value) {
        createErrorDiv(id, inputDiv, message);
    }
}

function validateSubmit(arrayId, message) {
    for (var i in arrayId) {
        if (document.getElementById(arrayId[i] + ".errors")) {
            event.preventDefault();
            alert(message);
            return false;
        }
    }
}
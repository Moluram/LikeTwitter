/**
 * Contains all our own js funtion
 */
jQuery(document).ready(function ($) {
    var substringMatcher = function () {
        var strs = [];
        return function findMatches(q, cb) {
            $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: '/search',
                data: "search_username=" + q,
                dataType: 'json',
                async: false,
                timeout: 100000,
                success: function (data) {
                    strs = data
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE", e)
                }
            });


            var matches;

            // an array that will be populated with substring matches
            matches = [];

            // regex used to determine if a string contains the substring `q`
            substrRegex = new RegExp(q, 'i');

            // iterate through the pool of strings and for any string that
            // contains the substring `q`, add it to the `matches` array
            $.each(strs, function (i, str) {
                if (substrRegex.test(str)) {
                    matches.push(str);
                }
            });

            cb(matches);
        };
    };
    // instantiate the typeahead UI
    $('#search_username').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    }, {
        source: substringMatcher(),

        // This will be appended to "tt-dataset-" to form the class name of the suggestion menu.
        name: 'usersList',

        // the key from the array we want to display (name,id,email,etc...)
        templates: {
            empty: [
                '<div class="list-group search-results-dropdown"><div class="list-group-item">Nothing found.</div></div>'
            ],
            header: [
                '<div class="list-group search-results-dropdown">'
            ],
            suggestion: function (data) {
                return '<a href="' + '/' + data + '" class="pull-left">' + data + '</a>'
            }
        }
    })
    // Set the Options for "Bloodhound" suggestion engine

});


jQuery(document).ready(function ($) {
    $(".add-comment-form").submit(function (event) {
        event.preventDefault();
        var form = $(this);
        var formData = form.serializeArray().reduce(
            function (obj, item) {
                obj[item.name] = item.value;
                return obj;
            }, {});

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "comments",
            data: JSON.stringify(formData),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                form[0].reset();
                hideComments(data.tweetId);
                loadComments(data.tweetId,data.publisher);
                form.find("button").prop("disabled", true);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE", e)
            }
        });

    });

});

function like(id, username) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "tweet?id=" + id + "&username=" + username,
            timeout: 100000,
            success: function (data) {
                console.log(data);
                document.getElementById("likes" + id).textContent = "     " + data;
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function resendEmail(text) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "/signup/resendRegistrationToken",
            timeout: 100000,
            success: function () {
                document.getElementById("resendEmail").innerHTML = text;
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function subscribe(username) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: "subscribe/" + username,
            timeout: 100000,
            success: function (data) {
                if (!data) {
                    $("#subscribe" + username).addClass("hidden");
                    $("#unsubscribe" + username).removeClass("hidden");
                } else {
                    $("#subscribe" + username).removeClass("hidden");
                    $("#unsubscribe" + username).addClass("hidden");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function unsubscribe(username, url) {
    console.log(url);
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: url,
            timeout: 100000,
            success: function () {
                $("#" + username).addClass("hidden");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function loadComments(tweetId, sessionUsername) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "comments?tweetId=" + tweetId,
            timeout: 100000,
            success: function insertComments(strComments) {
                comments = JSON.parse(strComments);
                var tweet = $("#tweet_" + tweetId);
                var htmlComments = tweet.find("#comments");
                tweet.find("#show-comments-btn").addClass("hidden");
                tweet.find("#hide-comments-btn").removeClass("hidden");
                tweet.find(".add-comment-form").removeClass("hidden");
                insertAsTree(htmlComments, comments, 0, sessionUsername);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function hideComments(tweetId) {
    var tweet = $("#tweet_" + tweetId);
    var htmlComments = tweet.find("#comments");
    tweet.find("#hide-comments-btn").addClass("hidden");
    tweet.find("#show-comments-btn").removeClass("hidden");
    tweet.find(".add-comment-form").addClass("hidden");
    htmlComments.html("");
}


function insertAsTree(divToInsert, comments, depth, sessionUsername) {

    for (var i in comments) {
        var htmlComment = makeComment(comments[i], depth, sessionUsername);
        htmlComment
            .appendTo(divToInsert);
        if (comments[i].child) {
            insertAsTree(divToInsert, comments[i].child, depth + 1, sessionUsername)
        }
    }
}

function makeComment(comment, depth, sessionUsername) {
    var div = $(document.createElement("div"));
    var level;
    if (depth < 3) {
        level = depth;
    } else {
        level = 3;
    }
    divHtml = "<div class='media row'>" +
        "<div class='col-md-1'>" +
        "<a class='media-left' href='/" + comment.publisher + "'> " +
        "<img class='img-circle comment-publisher-photo' src='/files/" + comment.publisher_photo + "'>" +
        "</a>" +
        "</div>" +
        "<div class='col-md-8'>" +
        "<a class='media-left' href='/" + comment.publisher + "'> " + "@" + comment.publisher + "</a>" +
        "</br>" +
        "<p>" + comment.text +
        "</p>" +
        "</div>";
    if (comment.publisher === sessionUsername) {
        divHtml += "<div class='col-md-1'>" +
            "<a onclick='deleteComment(" + comment.id + ")' class='delete-comment-btn pull-right'>&#10006;</a>" +
            "</div>";
    }
    divHtml += "<div class='col-md-2 pull-right'>" +
        "<p><small>" + comment.date + "</small></p>" +
        "</div>" +
        " </div>";
    div
        .addClass("comment-wrapper")
        .addClass("comment-level-" + level)
        .html(divHtml)
        .attr("id", "comment_" + comment.id)
        .click(responseToComment);
    return div;
}

function responseToComment() {
    var parentComment = $(this);
    var parentId = parentComment.attr("id").split('_')[1];
    var form = parentComment.closest(".tweet").find(".add-comment-form");
    form.find("input[name='parentId']").val(parentId);
    setCaretPosition(form.find(".new-comment-text")[0], 0);
}

function deleteComment(commentId) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: "/comments/" + commentId + "/delete",
            timeout: 100000,
            success: function () {
                $("#comment_" + commentId).remove();
            }
            ,
            error: function (e) {
                console.log("ERROR: ", e);
            }
            ,
            done: function () {
                console.log("DONE");
            }
        })
        ;
    });
}

jQuery(document).ready(function () {
    $(".new-comment-text").keyup(function () {
        text = $(this);
        submitButton = text.siblings("button");
        if (text.val().length > 0) {
            submitButton.prop("disabled", false);
        } else {
            submitButton.prop("disabled", true);
        }
    })
});


function setCaretPosition(el, caretPos) {

    el.value = el.value;
    // ^ this is used to not only get "focus", but
    // to make sure we don't have it everything -selected-
    // (it causes an issue in chrome, and having it doesn't hurt any other browser)

    if (el !== null) {

        if (el.createTextRange) {
            var range = el.createTextRange();
            range.move('character', caretPos);
            range.select();
            return true;
        }

        else {
            // (el.selectionStart === 0 added for Firefox bug)
            if (el.selectionStart || el.selectionStart === 0) {
                el.focus();
                el.setSelectionRange(caretPos, caretPos);
                return true;
            }

            else { // fail city, fortunately this never happens (as far as I've tested) :)
                el.focus();
                return false;
            }
        }
    }
}

function resetPassword(message, username) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: "settings/reset-password",
            data: {
                'username': username
            },
            timeout: 100000,
            success: function insertComments(answer) {
                if (answer) {
                    document.getElementById("resetPasswordLabel").textContent = message;
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function postUsername(messageTrue, messageFalse) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: "reset-password",
            data: {
                'username': document.getElementById("username").value
            },
            timeout: 100000,
            success: function insertComments(answer) {
                if (answer) {
                    $("resetPassword").removeClass("hidden");
                    document.getElementById("resetPassword").innerHTML = '<h4>' + messageTrue + '</h4>';
                } else {
                    $("resetPassword").removeClass("hidden");
                    document.getElementById("resetPassword").innerHTML = '<h4>' + messageFalse + '</h4>';
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}
var pageNumber = 0;
function more(username, hideButton, showButton) {
    jQuery(document).ready(function ($) {
        pageNumber += 1;
        $.ajax({
            type: "GET",
            url: "/news/get",
            data: {
                'page': pageNumber
            },
            timeout: 100000,
            success: function insertTweets(data) {
                var text = document.getElementById("news");
                for (var i in data) {
                    text.innerHTML += "<div class='row animated fadeInUp delay tweet' id='tweet_" + data[i].id + "'>" +
                        "<link rel='stylesheet'" +
                        "href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css'/>" +
                        "<div class='well'>" +
                        "<div class='media'>" +
                        "<div class='col-md-3'>" +
                        "<a class='pull-left' href='#'>" +
                        "<img class='img-circle user-mini-photo-tweet'" +
                        "src='/files/" + data[i].photoMin + "'>" +
                        "</a>" +
                        "</div>" +
                        "<div class='col-md-9'>" +
                        "<div class='row'>" +
                        "<div class='col-md-2'>" +
                        "<a href='" + data[i].ownerUsername + "'@" + data[i].ownerUsername +
                        "</div>" +
                        "<div class='col-md-10'>|" +
                        "<span><i class='glyphicon glyphicon-calendar'></i>" +
                        data[i] +
                        "</span>" +
                        "</div>" +
                        "</div>" +
                        "<div class='row'>" +
                        data[i].text +
                        "</div>" +
                        "<div class='row'>" +
                        "<button class='btn btn-sm btn-success'" +
                        "onclick='like(" + data[i].id + ",'" + username + "')'>" +
                        "<span id='likes${data[i].id}' class='glyphicon glyphicon-thumbs-up'>" +
                        data[i].numberOfLikes +
                        "</span>" +
                        "</button>" +
                        "</div>" +
                        "</div>" +

                        "<button class='btn btn-default' onclick='loadComments(" + data[i].id + ",\"" + username + "\")'" +
                        "id='show-comments-btn'>" + showButton +
                        "</button>" +
                        "<button class='btn btn-default hidden' onclick='hideComments(" + data[i].id + ")'" +
                        "id='hide-comments-btn'>" + hideButton +
                        "</button>" +
                        "<div class='row'></div>" +
                        "<div id='comments'></div>" +
                        "<div class='add-new-comment'>" +
                        "<form action='/comments' method='POST'" +
                        "class='add-comment-form hidden'>" +
                        "<input type='text' name='text' placeholder='Input comment'" +
                        "class='new-comment-text' autocomplete='off'>" +
                        "<input type='hidden' name='author' value='" + username + "'>" +
                        "<input type='hidden' name='tweetId' value='" + data[i].id + "'>" +
                        "<input type='hidden' name='parentId' value=''>" +
                        "<button type='submit' class='btn btn-success'>Add</button>" +
                        "</form>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>"
                }
                ;

            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function updateNews(username, hideButton, showButton) {
    pageNumber = 0;
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "/news/get",
            data: {
                'page': pageNumber
            },
            timeout: 100000,
            success: function insertComments(data) {
                var text = document.getElementById("news");
                text.innerHTML = "";
                for (var i in data) {
                    text.innerHTML += "<div class='row animated fadeInUp delay tweet' id='tweet_" + data[i].id + "'>" +
                        "<link rel='stylesheet'" +
                        "href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css'/>" +
                        "<div class='well'>" +
                        "<div class='media'>" +
                        "<div class='col-md-3'>" +
                        "<a class='pull-left' href='#'>" +
                        "<img class='media-object img-rounded img-thumbnail img-responsive'" +
                        "src='/files/" + data[i].photoOwner + "'>" +
                        "</a>" +
                        "</div>" +
                        "<div class='col-md-9'>" +
                        "<div class='row'>" +
                        "<div class='col-md-2'>" +
                        "<a href='" + data[i].ownerUsername + "'@" + data[i].ownerUsername +
                        "</div>" +
                        "<div class='col-md-10'>|" +
                        "<span><i class='glyphicon glyphicon-calendar'></i>" +
                        data[i] +
                        "</span>" +
                        "</div>" +
                        "</div>" +
                        "<div class='row'>" +
                        data[i].text +
                        "</div>" +
                        "<div class='row'>" +
                        "<button class='btn btn-sm btn-success'" +
                        "onclick='like(" + data[i].id + ",'" + username + "')'>" +
                        "<span id='likes${data[i].id}' class='glyphicon glyphicon-thumbs-up'>" +
                        data[i].numberOfLikes +
                        "</span>" +
                        "</button>" +
                        "</div>" +
                        "</div>" +

                        "<button class='btn btn-default' onclick='loadComments(" + data[i].id + ",\"" + username + "\")'" +
                        "id='show-comments-btn'>" + showButton +
                        "</button>" +
                        "<button class='btn btn-default hidden' onclick='hideComments(" + data[i].id + ")'" +
                        "id='hide-comments-btn'>" + hideButton +
                        "</button>" +
                        "<div class='row'></div>" +
                        "<div id='comments'></div>" +
                        "<div class='add-new-comment'>" +
                        "<form action='/comments' method='POST'" +
                        "class='add-comment-form hidden'>" +
                        "<input type='text' name='text' placeholder='Input comment'" +
                        "class='new-comment-text' autocomplete='off'>" +
                        "<input type='hidden' name='author' value='" + username + "'>" +
                        "<input type='hidden' name='tweetId' value='" + data[i].id + "'>" +
                        "<input type='hidden' name='parentId' value=''>" +
                        "<button type='submit' class='btn btn-success'>Add</button>" +
                        "</form>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>"
                }
                ;

            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function banUser(userId) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "POST",
            url: "/admin/ban",
            data: {
                "userId": userId
            },
            timeout: 100000,
            success: function () {
                console.log("log")
                $("#user_" + userId).find("#ban-btn").removeClass("btn-danger")
                    .addClass("btn-success")
                    .attr("id", "unban-btn")
                    .attr("onclick", "unBanUser(" + userId + ")")
                    .html("Unban");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}


function unBanUser(userId) {
    jQuery(document).ready(function ($) {
        var data = new Object();
        data.userId = userId;
        $.ajax({
            type: "POST",
            url: "/admin/unban",
            data: {
                "userId": userId
            },
            timeout: 100000,
            success: function () {
                $("#user_" + userId).find("#unban-btn").removeClass("btn-success")
                    .addClass("btn-danger")
                    .attr("id", "ban-btn")
                    .attr("onclick", "banUser(" + userId + ")")
                    .html("Ban");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function () {
                console.log("DONE");
            }
        });
    });
}

function pagination(strCount, strCurrentPage) {
    jQuery(document).ready(function ($) {
        var pagination = $("#user-pagination");
        var pageCount = parseInt(strCount);
        var currentPage = parseInt(strCurrentPage);

        if (pageCount <= 1) {
            return;
        }
        for (var i = 1; i <= pageCount; i++) {
            // var page=i+1;
            // if(pageCount>10) {
            //     if(pageCount-currentPage+1<=10){
            //         if(currentPage)
            //         page=pageCount-9+i;
            //     }else{
            //         page=currentPage+i;
            //     }
            // }else{
            //     if(i>pageCount-1){
            //         return;
            //     }
            // }
            pagination.append(createPaginatioinLi(i))
        }
    });
}

function createPaginatioinLi(page) {
    var li = $(document.createElement("li"));
    var a = $(document.createElement("a")).attr("href", "/admin/users?page=" + page).html(page);
    return li.html(a);
}

function send(id, message) {
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "/settings/" + id,
        data: {
            value: document.getElementById(id + "Input").value
        },
        timeout: 100000,
        success: function () {
            document.getElementById(id + "Btn").innerHTML = message;
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function () {
            console.log("DONE");
        }
    });
}
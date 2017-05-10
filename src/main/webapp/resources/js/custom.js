/**
 * Created by Nikolay on 01.05.2017.
 */

jQuery(document).ready(function ($) {
    var productsearcher = new Bloodhound({
        datumTokenizer: function (d) {
            return Bloodhound.tokenizers.whitespace(d.value);
        },
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        replace: function (url, uriEncodedQuery) {
            return url + "#" + uriEncodedQuery;
            // the part after the hash is not sent to the server
        },
        remote: {
            url: '/search',
            ajax: {
                type: "GET",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    partialSearchString: 'fire',
                    category: 'all'
                }),
                success: function (data) {
                    console.log("Got data successfully");
                    console.log(data);
                }
            }
        }
    });

    // initialize the bloodhound suggestion engine
    productsearcher.initialize();

    // instantiate the typeahead UI
    $('#q').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    }, {
        source: productsearcher.ttAdapter(),

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
                return '<a href="' + '/' + data + '" class="list-group-item">' + data + '</a>'
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
                loadComments(data.tweetId);
                form.find("button").prop("disabled",true);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE", e)
            },
        });

    });

});

function like(id, username, owner) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "tweet?id=" + id + "&username=" + username + "&owner=" + owner,
            timeout: 100000,
            success: function (data) {
                console.log(data)
                document.getElementById("likes" + id).textContent = "     " + data;
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
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
            success: function (data) {
                document.getElementById("resendEmail").textContent = text;
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
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
            done: function (e) {
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
            success: function (data) {
                $("#" + username).addClass("hidden");
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    });
}

function loadComments(tweetId) {
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
                insertAsTree(htmlComments, comments, 0);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
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
    tweet.find("#add-comment-form").addClass("hidden");
    htmlComments.html("");
}


function insertAsTree(divToInsert, comments, depth) {

    for (var i in comments) {
        var htmlComment = makeComment(comments[i],depth);
        htmlComment
            .appendTo(divToInsert)
        if (comments[i].child) {
            insertAsTree(divToInsert, comments[i].child, depth + 1)
        }
    }
}

function makeComment(comment, depth) {
    var div = $(document.createElement("div"));
    var level;
    if(depth<3){
        level=depth;
    }else{
        level=3;
    }
    div
        .addClass("comment-wrapper")
        .addClass("comment-level-"+level)
        .html("<div class='media'> <p class='pull-right'><small>" + comment.date + "</small></p>" +
            "<h4 class='media-heading user_name'>@" + comment.publisher + "</h4>" +
            "<a class='media-left' href='#'> <img class='img-circle comment-publisher-photo' src='/files/" + comment.publisher_photo + "'></a>" +
            "<div class='media-body'>" + comment.text +
            "</div> </div>")
        .attr("id", comment.id)
        .click(responseToComment);
    return div;
}

function responseToComment() {
    var parentComment = $(this);
    var parentId = parentComment.attr("id");
    var form = parentComment.closest(".tweet").find(".add-comment-form");
    form.find("input[name='parentId']").val(parentId);
    setCaretPosition(form.find(".new-comment-text")[0], 0);
}

jQuery(document).ready(function () {
    $(".new-comment-text").keyup(function () {
        text=$(this);
        submitButton=text.siblings("button");
        if(text.val().length>0){
            submitButton.prop("disabled",false);
        }else{
            submitButton.prop("disabled",true);
        }
    })
})


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
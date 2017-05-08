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
    $("#add-comment-form").submit(function (event) {
        event.preventDefault();

        var formData = $('#add-comment-form').serializeArray().reduce(
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
                hideComments();
                loadComments(
                    $("#add-comment-form")
                        .find("input[name='tweetId']")
                        .val()
                );
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE",e)
            },
        });

    });

});

function loadComments(tweetId) {
    jQuery(document).ready(function ($) {
        $.ajax({
            type: "GET",
            url: "comments?tweetId=" + tweetId,
            timeout: 100000,
            success: insertComments,
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    });
}

function hideComments() {
    var htmlComments = $("#comments");
    $("#hide-comments-btn").addClass("hidden");
    $("#show-comments-btn").removeClass("hidden");
    $("#add-comment-form").addClass("hidden");
    htmlComments.html("");
}

function insertComments(strComments) {
    console.log(strComments)
    comments = JSON.parse(strComments);
    var htmlComments = $("#comments");
    $("#show-comments-btn").addClass("hidden");
    $("#hide-comments-btn").removeClass("hidden");
    $("#add-comment-form").removeClass("hidden");
    insertAsTree(comments, 0);
}

function insertAsTree(comments, depth) {

    for (var i in comments) {
        p = $(document.createElement("p"));
        p.css("margin-left", depth * 30)
            .html("<div class='media'> <p class='pull-right'><small>" + comments[i].date + "</small></p>" +
                "<h4 class='media-heading user_name'>@" + comments[i].publisher + "</h4>" +
                "<a class='media-left' href='#'> <img src='/files/" + comments[i].publisher_photo + "'></a>" +
                "<div class='media-body'>"  + comments[i].text +
                "</div> </div>")
            .attr("id",comments[i].id)
            .appendTo($("#comments"))
            .click(responseToComment);
        if (comments[i].child) {
            insertAsTree(comments[i].child, depth + 1)
        }
    }
}

function responseToComment(){
    parentId=$(this).attr("id");
    $("#add-comment-form").find("input[name='parentId']").val(parentId);
    setCaretPosition("new-comment-text",0);
}

function setCaretPosition(elemId, caretPos) {
    var el = document.getElementById(elemId);

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

            else  { // fail city, fortunately this never happens (as far as I've tested) :)
                el.focus();
                return false;
            }
        }
    }
}
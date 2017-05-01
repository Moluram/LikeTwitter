/**
 * Created by Nikolay on 01.05.2017.
 */
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
    .html(comments[i].text)
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
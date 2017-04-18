function checkMultipleDelete(multiple_commit_message, deny, ok, nothing_checked_message, close_button  ){
    var checkboxes = document.getElementsByName("news_check");
    var isChecked = false;
    var message;
    for(var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    if(isChecked == true) {
        message = multiple_commit_message;
        document.getElementById('modal-footer').innerHTML = '<input type="submit" value="ok" class="btn btn-common submit" />' +
            '<input type="button"  class="btn btn-common submit" data-dismiss="modal"  value="deny" />';
    }
    else{
        message = nothing_checked_message;
        document.getElementById('modal-footer').innerHTML = '<button type="button" class="btn btn-default" data-dismiss="modal">close_button</button>';
    }
    document.getElementById('modal-body').innerHTML = message;


}


function goBack() {
    window.history.back();
}

function closeModal() {
    $('#incorrectLoginInput').hide();
}

$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

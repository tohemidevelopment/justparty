/**
 * Created by xce35l7 on 25.11.2015.
 */
//Configure AJAX and csrf
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function sendInvitationAnswer(id){
    var req = new XMLHttpRequest();

    // get selected answer
    var select = document.getElementById("select_" + id);
    var accepted = select.options[select.selectedIndex].getAttribute('name');

    // remove Nothing selected option if existing
    if (select.length > 3){
        select.options.remove(0);
    }

    // open connectiom
    req.open("GET",  "accepted?id="+id+"&accepted="+accepted, true);

    // put handler for response
    req.onreadystatechange = function receive() {
        if (req.readyState==4) {
            var answer = req.responseText;
            html = "";
            if(answer != ""){
                html= answer;
            }

            document.getElementById("alerts").innerHTML=html;
        }
    };

    req.send();

}

function showCurrentEvents() {
//TODO: implement
}

function showInvitations() {
//TODO: to be implemented as soon as invitations are implemented

}

function showGeneral(){
//TODO: implement
}

function showLinks(){
//TODO: implement
}

function showBringWith(){
//TODO: implement
}

function showGuestlist(){
//TODO: implement
}

function showOwnedEvents() {
//TODO: implement
}

function showCanceldEvents() {
//TODO: to be implemented as soon as events can be canceled
}

function showPastEvents() {
//TODO: to be implemented when Events have dates
}


/**
 * Created by xce35l7 on 25.11.2015.
 */
//Configure AJAX and csrf
$(function ()
{
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options)
    {
        xhr.setRequestHeader(header, token);
    });
});

function sendInvitationAnswer(id)
{
    var req = new XMLHttpRequest();

    // get selected answer
    var select = document.getElementById("select_" + id);
    var accepted = select.options[select.selectedIndex].getAttribute('name');

    // remove Nothing selected option if existing
    if (select.length > 3)
    {
        select.options.remove(0);
    }

    // open connectiom
    req.open("GET", "accepted?id=" + id + "&accepted=" + accepted, true);

    // put handler for response
    req.onreadystatechange = function receive()
    {
        if (req.readyState == 4)
        {
            var answer = req.responseText;

            document.getElementById("alerts").innerHTML = answer;
        }
    };

    req.send();

}

function showCurrentEvents()
{
//TODO: implement
}

function showInvitations()
{
//TODO: to be implemented as soon as invitations are implemented

}

function showGeneral()
{
    $('#general').addClass("active");
    $('#links').removeClass("active");
    $('#bringwith').removeClass("active");
    $('#guestlist').removeClass("active");

    $('#general_text').show();
    $('#link_text').hide();
    $('#bringwith_text').hide();
    $('#guestlist_text').hide();
}

function showLinks()
{
    $('#general').removeClass("active");
    $('#links').addClass("active");
    $('#bringwith').removeClass("active");
    $('#guestlist').removeClass("active");

    $('#general_text').hide();
    $('#link_text').show();
    $('#bringwith_text').hide();
    $('#guestlist_text').hide();
}

function showBringWith()
{
    $('#general').removeClass("active");
    $('#links').removeClass("active");
    $('#bringwith').addClass("active");
    $('#guestlist').removeClass("active");

    $('#general_text').hide();
    $('#link_text').hide();
    $('#bringwith_text').show();
    $('#guestlist_text').hide();
}

function showGuestlist()
{
    $('#general').removeClass("active");
    $('#links').removeClass("active");
    $('#bringwith').removeClass("active");
    $('#guestlist').addClass("active");

    $('#general_text').hide();
    $('#link_text').hide();
    $('#bringwith_text').hide();
    $('#guestlist_text').show();
}

var eventDataChanges = {};

function invitePerson(email, message)
{
    //TODO: Implement that the person gets an email and that that person is save in the database

    const URL = 'invited';
    var data = {
        email: email,
        message: message,
        id: id
    };
    $.ajax({
        url: URL,
        type: 'POST',
        data: data,
        success: success,
        error: error
    });

    function success(data, textStatus, jqXHR)
    {
        //TODO: success alert
    }

    function error(data, textStatus, jqXHR)
    {
        //TODO: error alert
    }
}

function updateEventData(id)
{
    var newValue = document.getElementById(id).value;
    eventDataChanges[id] = newValue;
}

function sendEventDataChanges(id)
{
    const URL = '/eventdata';
    var data = JSON.stringify(eventDataChanges);
    $.ajax({
        url: URL,
        type: 'POST',
        data: data,
        headers: {id: id},
        processData: false,
        contentType: "application/json; charset=utf-8"
    });
}

function showOwnedEvents()
{
//TODO: implement
}

function showCanceldEvents()
{
//TODO: to be implemented as soon as events can be canceled
}

function showPastEvents()
{
//TODO: to be implemented when Events have dates
}


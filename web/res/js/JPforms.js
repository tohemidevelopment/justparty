/**
 * Created by xce35l7 on 12.11.2015.
 */
    //DEPRECATED
    // use instead:
    // submitForm('logoutForm');
function logoutFormSubmit() {
    submitForm("logoutForm");
}

function submitForm(formId){
    document.getElementById(formId).submit();
}

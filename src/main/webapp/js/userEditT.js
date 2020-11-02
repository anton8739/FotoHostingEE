userEditUser=document.querySelector("#userEditSubmit");
userEditUser.addEventListener('click', editUserCommand.bind(null));

function editUserCommand() {
    var request= new XMLHttpRequest();
    var form=document.forms.editUserForm;
    var userName=form.elements.name.value;
    var userSurname=form.elements.surname.value;
    var userEmail=form.elements.email.value;
    var userLogin=form.elements.login.value;

    var body="command=edit-user&userName="+userName+
        "&userSurname="+userSurname+"&userEmail="+userEmail+"&userLogin="+userLogin
        +"&userId="+window.JUserId;
    console.log(body);
    request.open('POST', 'controller',true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(body);
    request.addEventListener("readystatechange", serverAnswer.bind(null));
    function serverAnswer(){
        if (request.readyState === 4 && request.status === 200) {
            var Answer=request.responseText;

            var message = {
                login : "",
                errorMessage:""
            }
            console.log(Answer);
            message=JSON.parse(decodeURI(Answer),message);
        }
        document.querySelector(".editUserForm").style.display='none';
        document.querySelector(".userEditMessage").textContent=message.errorMessage;
    }
}

userEditUserExit=document.querySelector("[id^='UserMenuLeftEdit']");
userEditUserExit.addEventListener('click', function () {
    document.querySelector(".editUserForm").style.display='block';
    document.querySelector(".userEditMessage").textContent="";
});
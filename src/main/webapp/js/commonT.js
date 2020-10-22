contact=document.querySelector(".contactHref");

contact.addEventListener('click', function () {
   var contactPage=document.querySelector(".contactPage");
   if(contactPage.style.display ==='none' || contactPage.style.display ===""){
       contactPage.style.display='block';
   } else {
       contactPage.style.display='none';
   }
});

editUser=document.querySelector(".editUser");
editUser.addEventListener('click', function () {
    var fotoEditPage=document.querySelector(".userEditPage");
    if(fotoEditPage.style.display ==='none' || fotoEditPage.style.display ===""){
        fotoEditPage.style.display='block';
    }
});

closeEditUser=document.querySelector(".userEditPageClose");
closeEditUser.addEventListener('click', function () {
    var fotoEditPage=document.querySelector(".userEditPage");
    document.querySelector(".editUserForm").style.display='block';
    document.querySelector(".userEditPageMain").style.display='block';
    document.querySelector(".userEditMessage").style.display='none';
    fotoEditPage.style.display='none';

});

userEditUser=document.querySelector(".userEditPageSubmit");
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
    +"&userId="+JUserId;
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
        document.querySelector(".userEditPageMain").style.display='none';
        document.querySelector(".userEditMessage").style.display='block';
        document.querySelector(".userEditMessage").textContent=message.errorMessage;

    }
}

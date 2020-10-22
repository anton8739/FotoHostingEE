loginforgotPassword=document.querySelector(".fogotPassword");
loginforgotPassword.addEventListener('click', function () {
    var rules=document.querySelector(".fogotPasswordForm");
    if(rules.style.display ==='none' || rules.style.display ===""){
        document.getElementById("fogotPasswordTextID").style.display='block';
        document.getElementById("forgotPasswordSubmitID").style.display='block';
        rules.style.display='block';
    }
});

closeloginforgotPassword=document.querySelector(".fogotPasswordClose");
closeloginforgotPassword.addEventListener('click', function () {
    var rules=document.querySelector(".fogotPasswordForm");
    rules.style.display='none';

});

loginforgotPasswordSendEmail=document.querySelector(".forgotPasswordSubmit");
loginforgotPasswordSendEmail.addEventListener('click', sendEmailCommand.bind(null));

function sendEmailCommand() {
    var request= new XMLHttpRequest();
    var form=document.forms.forgotPassword;
    var commandInput=form.elements.loginemail.value;
    console.log("done");
    var body="command=forgot-password&login-email="+commandInput;
    console.log(body);
    request.open('POST', 'controller',true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(body);
    request.addEventListener("readystatechange", serverAnswer.bind(null));
    function serverAnswer(){
        if (request.readyState === 4 && request.status === 200) {
            var message = {
                status : ""
            }
            var Answer=request.responseText;
            message=JSON.parse(Answer,message);
            console.log(message.status);

            document.getElementById("fogotPasswordTitleID").textContent= message.status;
            document.getElementById("fogotPasswordTextID").style.display='none';
            document.getElementById("forgotPasswordSubmitID").style.display='none';
        }
    }
}


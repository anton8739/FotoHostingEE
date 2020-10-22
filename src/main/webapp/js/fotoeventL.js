
commentlike=document.querySelectorAll("[id^='likeCommentCommand']");

commentlike.forEach(function (value, index, listObj) {
    value.addEventListener('click', likeCommentcommand.bind(null,value));
});

function likeCommentcommand(currentLike){
    var commentId=currentLike.getAttribute("name");
    var userId=jLoginedUserId;
    var request= new XMLHttpRequest();
    var url="controller?command=like-comment&commentId="+commentId+"&userId="+userId;
    request.open('GET', url);
    request.setRequestHeader("Content-Type", "application/json");
    request.send();
    request.addEventListener("readystatechange", regAnswerCommentLike.bind(null,commentId));
    function regAnswerCommentLike(commentId){
        if (request.readyState === 4 && request.status === 200) {
            var comment = {
                numberOflikes : 0,
                isLiked : 0
            }
            var Answer=request.responseText;
            comment=JSON.parse(Answer, comment);
            if (comment.isLiked ===1) {
                document.getElementById("likeCommentImg"+commentId).src="img/likeON.png";
            } else {
                document.getElementById("likeCommentImg"+commentId).src="img/likeOff.png";
            }
            document.getElementById("likeCommentSpan"+commentId).textContent=comment.numberOflikes;

        }
    }
}

fotolike=document.querySelector("[id=fotoPageFotoLike]");
fotolike.addEventListener('click', likeFotoPageCommand.bind(null,fotolike));
function likeFotoPageCommand() {
    var fotoId=jFotoId;
    var userId=jLoginedUserId;
    var request= new XMLHttpRequest();
    var url="controller?command=like-img&fotoId="+fotoId+"&userId="+userId;
    request.open('GET', url);
    request.setRequestHeader("Content-Type", "application/json");
    request.send();
    request.addEventListener("readystatechange", regAnswer.bind(null,fotoId));
    function regAnswer(fotoId){
        if (request.readyState === 4 && request.status === 200) {
            var foto = {
                numberOflikes : 0,
                isLiked : 0
            }
            var Answer=request.responseText;
            foto=JSON.parse(encodeURI(Answer), foto);
            if (foto.isLiked ===1) {
                document.getElementById("fotoPageFotoLikeImg").src="img/likeON.png";
            } else {
                document.getElementById("fotoPageFotoLikeImg").src="img/likeOff.png";
            }
            document.getElementById("fotoPageFotoLikeSpan").textContent=foto.numberOflikes;

        }
    }
}

editfoto=document.querySelector(".editFoto");
editfoto.addEventListener('click', function () {
    var fotoEditPage=document.querySelector(".fotoEditPage");
    if(fotoEditPage.style.display ==='none' || fotoEditPage.style.display ===""){
        fotoEditPage.style.display='block';
    }
});

closeEditFoto=document.querySelector(".fotoEditPageClose");
closeEditFoto.addEventListener('click', function () {
    var fotoEditPage=document.querySelector(".fotoEditPage");
    fotoEditPage.style.display='none';

});

fotoEditFoto=document.querySelector(".fotoEditPageSubmit");
fotoEditFoto.addEventListener('click', editFotoCommand.bind(null));

function editFotoCommand() {
    var request= new XMLHttpRequest();
    var form=document.forms.editFotoForm;
    var title=form.elements.title.value;
    var description=form.elements.description.value;
    var body="command=edit-foto&title="+title+"&description="+description+"&fotoId="+JfotoId;
    console.log(body);
    request.open('POST', 'controller',true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send(body);
    request.addEventListener("readystatechange", serverAnswer.bind(null));
    function serverAnswer(){
        if (request.readyState === 4 && request.status === 200) {
            var message = {
                title : "",
                description:""
            }
            var Answer=request.responseText;
            message=JSON.parse(Answer,message);
            document.querySelector(".fotoEditPage").style.display='none';
            document.getElementById("fotoTitle").textContent=message.title;
            document.getElementById("fotoDescription").textContent=message.description;

        }
    }
}
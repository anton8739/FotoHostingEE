fotolike=document.querySelector("[id=fotoPageFotoLike]");
fotolike.addEventListener('click', likeFotoPageCommand.bind(null,fotolike));
function likeFotoPageCommand() {
    var fotoId = jFotoId;
    var userId = jLoginedUserId;
    var request = new XMLHttpRequest();
    var url = "controller?command=like-img&fotoId=" + fotoId + "&userId=" + userId;
    request.open('GET', url);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send();
    request.addEventListener("readystatechange", regAnswer.bind(null, fotoId));

    function regAnswer(fotoId) {
        if (request.readyState === 4 && request.status === 200) {
            var foto = {
                numberOflikes: 0,
                isLiked: 0
            }
            var Answer = request.responseText;
            foto = JSON.parse(Answer, foto);
            if (foto.isLiked === 1) {
                document.getElementById("fotoPageFotoLikeImg").innerHTML = "<i class=\"fa fa-heart\" aria-hidden=\"true\"></i>";
            } else {
                document.getElementById("fotoPageFotoLikeImg").innerHTML = "<i class=\"fa fa-heart-o\" aria-hidden=\"true\"></i>";
            }
            document.getElementById("fotoPageFotoLikeSpan").textContent = foto.numberOflikes;

        }
    }
}

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
                document.getElementById("likeCommentImg"+commentId).innerHTML = "<i class=\"fa fa-heart\" aria-hidden=\"true\"></i>";
            } else {
                document.getElementById("likeCommentImg"+commentId).innerHTML = "<i class=\"fa fa-heart-o\" aria-hidden=\"true\"></i>";
            }
            document.getElementById("likeCommentSpan"+commentId).textContent=comment.numberOflikes;

        }
    }
}
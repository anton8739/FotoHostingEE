
fotolike=document.querySelectorAll("[id^='likefotoCommand']");

fotolike.forEach(function (value, index, listObj) {
    value.addEventListener('click', likefotocommand.bind(null,value));
});

function likefotocommand(currentLike){
    var fotoId=currentLike.getAttribute("name");
    var userId=jLoginedUserId;
    console.log(userId);
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
            foto=JSON.parse(Answer, foto);
            if (foto.isLiked ===1) {
                document.getElementById("likefotoImg"+fotoId).src="img/likeON.png";
            } else {
                document.getElementById("likefotoImg"+fotoId).src="img/likeOff.png";
            }
            document.getElementById("likefotoSpan"+fotoId).textContent=foto.numberOflikes;

        }
    }
}


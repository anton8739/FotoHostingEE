fotolike=document.querySelectorAll("[id^='likefotoCommand']");

fotolike.forEach(function (value, index, listObj) {
    value.addEventListener('click', likefotocommand.bind(null,value));
});

function likefotocommand(currentLike){
    var fotoId=currentLike.getAttribute("name");
    var userId=jLoginedUserId;

    var request= new XMLHttpRequest();
    var url="controller?command=like-img&fotoId="+fotoId+"&userId="+userId;
    console.log(url);
    request.open('GET', url);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
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
                document.getElementById("likefotoImg"+fotoId).innerHTML="<i class=\"fa fa-heart\" aria-hidden=\"true\"></i>";
            } else {
                document.getElementById("likefotoImg"+fotoId).innerHTML="<i class=\"fa fa-heart-o\" aria-hidden=\"true\"></i>";
            }
            document.getElementById("likefotoSpan"+fotoId).textContent=foto.numberOflikes;

        }
    }
}
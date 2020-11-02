followeUnfollow=document.querySelectorAll("[id^='followUnfollow']");
followeUnfollow.forEach(function (value) {
    value.addEventListener('click', followUnfollowCommand.bind(null,value));
});
function followUnfollowCommand(currentElement){
    var userId=currentElement.getAttribute("name");
    var loginedUserId=jLoginedUserId;
    var currentUserId=jcurrentUserId;
    var request= new XMLHttpRequest();
    var url="controller?command=follow-unfollow&userId="+userId+"&currentUserId="+loginedUserId;
    request.open('GET', url);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send();
    request.addEventListener("readystatechange", regAnswer.bind(null,userId));
    function regAnswer(userId) {
        if (request.readyState === 4 && request.status === 200) {
            var user = {
                isFollowed: 0,
                numberOfFollowings: 0
            }
            var Answer = request.responseText;
            user = JSON.parse(Answer, user);
            if (user.isFollowed ===1) {
                document.getElementById("followUnfollowSpan"+userId).className="btn btn-secondary";
                document.getElementById("followUnfollowSpan"+userId).textContent=jTextUnSubscribe;
            } else {
                document.getElementById("followUnfollowSpan"+userId).className="btn btn-info";
                document.getElementById("followUnfollowSpan"+userId).textContent=jTextSubscribe;
            }
            if (currentUserId === loginedUserId){
                document.getElementById("userAccountNumFollowings").textContent=user.numberOfFollowings;
            }

        }
    }
}
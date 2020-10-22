<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<!DOCTYPE html>

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="css/mainC.css" rel="stylesheet">
    <link href="css/cssReset.css" rel="stylesheet">
    <link href="css/fotoZ.css" rel="stylesheet">
    <title>InstaGod</title>
</head>

<body >

<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="fotoPageFotoContener">
<div class="fotoPageFoto">
    <img src="${requestScope.foto.getURL()}">
</div>
<div class="downFotoMenu">
    <div class="downFotoMenuUser">
    <a href="controller?userId=${requestScope.currentUser.getId()}&command=user-account-redirect" ><img src="img/userIcon.png">
        <span >${requestScope.currentUser.getLogin()}</span></a>
    </div>
    <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user}">
    <div class="downFotoMenuEdit" >
        <img src="img/pensil.png"><a class="editFoto"><fmt:message key="buttom.userAccount.edit" bundle="${rb}"/></a></div>
        <div class="fotoEditPage">
            <a class="fotoEditPageClose" ><fmt:message key="js.fotoEdit.buttom.close" bundle="${rb}"/></a>
            <label class="fotoEditPageMain"><fmt:message key="js.fotoEdit.main" bundle="${rb}"/></label>
            <form name="editFotoForm">
                <label class="fotoEditPageTitle"><fmt:message key="js.fotoEdit.name" bundle="${rb}"/></label>
                <div class="fotoEditPageTitleText"><textarea name="title">${requestScope.foto.getTitle()}</textarea></div>
                <label class="fotoEditPageDescription"><fmt:message key="js.fototEdit.descripion" bundle="${rb}"/></label>
                <div class="fotoEditPageDescriptionText"><textarea name="description">${requestScope.foto.getText()}</textarea></div>
                <label class="fotoEditPageSubmit"><fmt:message key="js.fotoEdit.buttom.send" bundle="${rb}"/></label>
            </form>
        </div>
    <div class="downFotoMenuDelete"><img src="img/urna.png">
        <a href="controller?command=delete-img&foto=${requestScope.foto.getName()}"><fmt:message key="buttom.fotodelete" bundle="${rb}"/></a></div>
    </c:if>
    <a class="downFotoMenuDownLoad" href="${requestScope.foto.getURL()}"><img src="img/download.png"></a>
    <div class="downFotoMenuLike">
        <span id="fotoPageFotoLikeSpan">${requestScope.numberOfFotoLike}</span><a id="fotoPageFotoLike">
    <c:choose>
        <c:when test="${requestScope.isLikedFoto == 1}">
            <img id="fotoPageFotoLikeImg" src="img/likeON.png" >
        </c:when>
        <c:otherwise>
        <img id="fotoPageFotoLikeImg" src="img/likeOff.png">
        </c:otherwise>
    </c:choose>
            </a></div>
</div>
<div class="fotoDescription">
    <span>
        <label ><fmt:message key="fotoPage.text.fotoName" bundle="${rb}"/></label><br/>
        <label id="fotoTitle">${requestScope.foto.getTitle()}
        <c:if test="${requestScope.foto.getTitle() == null}">
            нет названия
        </c:if>
        </label>
    </span>
    <span>
        <label ><fmt:message key="fotoPage.text.fotoDescription" bundle="${rb}"/></label><br>
        <label id="fotoDescription">${requestScope.foto.getText()}
        <c:if test="${requestScope.foto.getText() == null}">
            нет названия
        </c:if>
        </label>



    </span>
    <span>
        <label>Дата загрузки</label><br>
        <label >${requestScope.foto.getTimeOfCreation()}</label>
    </span>
</div>
        <c:choose>
            <c:when test="${not empty requestScope.commentMap}">
                <c:forEach items="${requestScope.commentMap}" var="comment">
                <div class="fotoComment">
                    <div class="fotoCommentLeftBlock" >
                        <div  class="fotoCommentUser">
                            <a href="controller?userId=${comment.key.getUserid()}&command=user-account-redirect"><img src="img/userIcon.png">
                             <span >${comment.value.get(2)}</span></a>
                        </div>
                        <div class="fotoCommentData">
                            <span>${comment.key.getTime()}</span>
                            <span>${comment.key.getDate()}</span></div>
                    </div>
                    <div class="fotoCommentText">
                        <span>${comment.key.text}</span>
                 </div>
                    <div class=fotoCommentRightBlock></div>
                    <div class="fotoCommentLike" ><span id="likeCommentSpan${comment.key.id}" >${comment.value.get(0)}</span>
                        <a id="likeCommentCommand${comment.key.id}" name="${comment.key.id}">
                            <c:choose>
                                <c:when test="${comment.value.get(1) == 0}">
                                    <img id="likeCommentImg${comment.key.id}" src="img/likeOff.png">
                                </c:when>
                                <c:otherwise>
                                    <img id="likeCommentImg${comment.key.id}" src="img/likeON.png">
                                </c:otherwise>
                            </c:choose>
                        </a></div>
                    <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user || comment.value.get(2) ==sessionScope.user}">
                    <div class="fotoCommentDelete"><img src="img/urna.png">
                    <a href="foto?userId=${requestScope.currentUser.id}&command=delete-comment&foto=${requestScope.foto.getName()}&commentId=${comment.key.id}"><fmt:message key="buttom.cooment.delete" bundle="${rb}"/></a></div>
                    </c:if>
                </div>
                </c:forEach>

            </c:when>
            <c:otherwise>
                <div class="fotoComment"><fmt:message key="fotoPage.text.noComments" bundle="${rb}"/></div>
            </c:otherwise>
        </c:choose>
    <div class="fotoAddNewComment">
        <div class="fotoAddNewCommentUser" >
            <a href="controller"><img src="img/userIcon.png">
            <span>
            ${sessionScope.user}
            </span>
            </a>
        </div>
        <form method="post" action="controller" accept-charset="UTF-8">
            <input type="hidden" name="userId" value="${requestScope.currentUser.getId()}">
            <input type="hidden" name="command" value="add-new-comment">
            <input type="hidden" name="foto" value="${requestScope.foto.getName()}">
            <div class="fotoAddNewCommentText"><span><textarea name="text" placeholder="<fmt:message key="fotoPage.comment.placeholder" bundle="${rb}"/>"></textarea></span></div>
            <label class="fotoAddNewCommentSubmit"><input type="submit">
        <span><fmt:message key="buttom.comment.send" bundle="${rb}"/></span></label>
        </form>
    </div>
<div class="fotoPageShare">
</div>
    <script>
        var jUserId="${requestScope.currentUser.getId()}";
        var jFotoId="${requestScope.foto.getId()}";
    </script>

</div>
<script>
    var jLoginedUserId=${sessionScope.id};
    var JfotoId=${requestScope.foto.getId()};
</script>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
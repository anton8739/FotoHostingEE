<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>InstaGod</title>
    <link rel="stylesheet" href="css/bootstrap.css" crossorigin="anonymous">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/fotoPage.css">
</head>

<body >
<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <img class="fotoPageMainImg" src="${requestScope.foto.getURL()}">
        </div>
    </div>

</div>
<div id="fotoMenuBar" class="container-fluid">
    <div class="row">
        <div class="col-lg-3"><a  class="fotoUser" href="controller?userId=${requestScope.currentUser.getId()}&command=user-account-redirect"><i class="fa fa-user-circle-o" aria-hidden="true"></i>
            <span>${requestScope.currentUser.getLogin()}</span></a></div>
        <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user}">
        <div class="col-lg-3"><a class="fotoEdit" data-toggle="modal" data-target="#fotoEditModel" href="#"><i  class="fa fa-pencil fa-fw"></i><fmt:message key="fotoPage.text.edit" bundle="${rb}"/></a></div>
        <div class="col-lg-2"><a  class="fotoDelete" href="controller?command=delete-img&foto=${requestScope.foto.getName()}"><i  class="fa fa-trash"></i><fmt:message key="buttom.fotodelete" bundle="${rb}"/></a></div>
        </c:if>
        <div class="col-lg-1"><a  class="fotoDownload" href="${requestScope.foto.getURL()}"><i  class="fa fa-download"></i></a></div>
        <div class="col-lg-1"><span id="fotoPageFotoLikeSpan">${requestScope.numberOfFotoLike} </span><a id="fotoPageFotoLike" class="fotoLike">
            <c:choose>
                <c:when test="${requestScope.isLikedFoto == 1}">
                    <span id="fotoPageFotoLikeImg"><i class="fa fa-heart"></i></span>
                </c:when>
                <c:otherwise>
                <span id="fotoPageFotoLikeImg"> <i class="fa fa-heart-o"></i></span>
                </c:otherwise>
            </c:choose>
        </a></div>
    </div>
</div>
<div id="fotoDescription" class="container-fluid">
    <div class="row">
        <div class="col">
            <span><h2><fmt:message key="fotoPage.text.fotoName" bundle="${rb}"/></h2></span>
            <span id="fotoTitl">${requestScope.foto.getTitle()}
                <c:if test="${requestScope.foto.getTitle() == null}">
                    <fmt:message key="fotoPage.text.noFotoTitle" bundle="${rb}"/>
                </c:if>
            </span>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <span><h2><fmt:message key="fotoPage.text.fotoDescription" bundle="${rb}"/></h2></span>
            <span id="fotoDes">${requestScope.foto.getText()}
        <c:if test="${requestScope.foto.getText() == null}">
            <fmt:message key="fotoPage.text.noFotoDescription" bundle="${rb}"/>
        </c:if></span>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <span><h2><fmt:message key="fotoPage.text.Date" bundle="${rb}"/></h2></span>
            <span>${requestScope.foto.getTimeOfCreation()}</span>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
    <span class="commentBlockTitle"><fmt:message key="fotoPage.text.CommentHeadear" bundle="${rb}"/></span>
    </div>
</div>
<div id="commentEntity" class="container-fluid">
    <c:choose>
    <c:when test="${not empty requestScope.commentMap}">
        <c:forEach items="${requestScope.commentMap}" var="comment">
    <div class="row mt-2">
        <div class="col-lg-2">
            <div class="container">
                <div class="row"><a  class="commentUser" href="controller?userId=${comment.key.getUserid()}&command=user-account-redirect"><i class="fa fa-user-circle-o" aria-hidden="true"></i>
                    <span>${comment.value.get(2)}</span></a></div>
                <div class="row"><span>${comment.key.getTime()}</span></div>
                <div class="row"><span>${comment.key.getDate()}</span></div>
            </div>
        </div>
        <div class="col-lg-5">
            <div class="CommentText"><span>${comment.key.text}</span></div>
        </div>
        <div class="col-lg-2">
            <div id="commentLikeMenu"class="container">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="centered">
                        <span id="likeCommentSpan${comment.key.id}">${comment.value.get(0)} </span>
                            <a class="commentLike" id="likeCommentCommand${comment.key.id}" name="${comment.key.id}">
                                <c:choose>
                                    <c:when test="${comment.value.get(1) == 0}">
                                        <span id="likeCommentImg${comment.key.id}"><i class="fa fa-heart-o"></i></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span id="likeCommentImg${comment.key.id}"><i class="fa fa-heart"></i></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                    </div>
                </div>
                <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user || comment.value.get(2) ==sessionScope.user}">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="centered">
                        <a class="commentDelete" href="foto?userId=${requestScope.currentUser.id}&command=delete-comment&foto=${requestScope.foto.getName()}&commentId=${comment.key.id}">
                            <i  class="fa fa-trash"></i><fmt:message key="buttom.cooment.delete" bundle="${rb}"/></a>
                        </div>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
        
    </div>
        </c:forEach>
    </c:when>
        <c:otherwise>
            <div class="row">
                <fmt:message key="fotoPage.text.noComments" bundle="${rb}"/>
            </div>
        </c:otherwise>
    </c:choose>



</div>

<div id="commentEntity" class="container-fluid">
    <div class="row">
        
        <div class="col-lg-2">
            <div class="container">
                <div class="row"><a  class="commentUser" href="#"><i class="fa fa-user-circle-o" aria-hidden="true"></i><span>${sessionScope.user}</span></a></div>
            </div>
        </div>
        <div class="col-lg-5">
            <div class="CommentText">
                <div class="form-group">
                    <form id="commentForm" method="post" action="controller" accept-charset="UTF-8">
                        <input type="hidden" name="userId" value="${requestScope.currentUser.getId()}">
                        <input type="hidden" name="command" value="add-new-comment">
                        <input type="hidden" name="foto" value="${requestScope.foto.getName()}">
                        <textarea class="form-control" id="commnetTextArea" rows="3" name="text" placeholder="<fmt:message key="fotoPage.comment.placeholder" bundle="${rb}"/>"></textarea>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-2">
            <div id="commentAddButtom"class="container">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="centered">
                        <a class="btn btn-success" href="#" onclick="document.getElementById('commentForm').submit(); return false;"><fmt:message key="buttom.comment.send" bundle="${rb}"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
</div>
<div id="footer" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col"><span>CopyRight <i class="fa fa-copyright" aria-hidden="true"></i> 2020. By Anton Yurovski</span></div>
    </div>   
</div>
<div id="fotoEditModel" class="modal fade">
    <div class="modal-dialog">
        <div id="modalFotoWindow" class="modal-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col">
                    <label class="modalUserEditHeader"><h4><fmt:message key="js.fotoEdit.main" bundle="${rb}"/></h4></label>
                </div>
                <div class="col">
                    <button id="modalUserEditClose" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
                </div>
            </div>
        <div class="row">
                <div class="col">
                   <div id="modalUserEditForm" class="form-group">
                <form name="editFotoForm">
                <label><h5><fmt:message key="js.fotoEdit.name" bundle="${rb}"/></h5></label>
                <textarea name="title" class="form-control" id="commnetTextArea" rows="3">${requestScope.foto.getTitle()}</textarea>
                <small id="emailHelp" class="form-text text-muted"><h6></h6></small>
                <label><h5><fmt:message key="js.fototEdit.descripion" bundle="${rb}"/></h5></label>
                <textarea  name="description" class="form-control" id="commnetTextArea" rows="3">${requestScope.foto.getText()}</textarea>
                <small id="passwordHelp" class="form-text text-muted"><h6></h6></small>
                <a id="fotoEditSubmit" type="submit" class="btn btn-success btn-lg"><fmt:message key="js.fotoEdit.buttom.send" bundle="${rb}"/></a>
                </form>
            </div>
                </div>
            </div>
        </div> 
        </div>
    </div>
    
</div>
<script>
    var jLoginedUserId=${sessionScope.id};
    var jFotoId=${requestScope.foto.getId()};
</script>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
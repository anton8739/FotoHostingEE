<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="http://drawings-girls.ucoz.net/2016/12/dlinnovolosaya-blondinka-s-tigrom.jpg" height="300" alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100 " src="http://drawings-girls.ucoz.net/2016/12/neobichnaya-devushka-s-orujiem.jpg" height="300" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100 " src="http://drawings-girls.ucoz.net/2016/12/misticheskaya-devushka-s-trohpaloi-lapoi.jpg" height="300" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container-fluid" style="background: #e8ebef">
    <div class="row ">
        <div class="col-lg-5 col-md-12 ">
            <div class="row">
                <div class="col-lg-6 col-md-6"><div class="centered"><i id="mainIcon" class="fa fa-user-circle-o" aria-hidden="true"></i></div></div>
                <div class="col-lg-6 col-md-6">
                    <div class="centered">
                        <ul id="left-menu">
                            <li id="UserMenuLeftUserName" ><a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}">${requestScope.currentUser.getLogin()}</a></li>
                            <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user}">
                            <li id="UserMenuLeftEdit"><a data-toggle="modal" data-target="#userEditModel" href="#"><i  class="fa fa-pencil fa-fw"></i><fmt:message key="buttom.userAccount.edit" bundle="${rb}"/></a></li>
                            </c:if>
                                <li >
                                <ul class="list-inline">
                                    <li id="UserMenuLeftFollowers" class="list-inline-item"><a href="controller?command=followings-redirect&userId=${requestScope.currentUser.getId()}"><span id="userAccountNumFollowings">${requestScope.numberOfFollowings} </span> <fmt:message key="buttom.userAccount.followings" bundle="${rb}"/></a></li>
                                    <li id="UserMenuLeftFollowings" class="list-inline-item"><a href="controller?command=followers-redirect&userId=${requestScope.currentUser.getId()}"><span >${requestScope.numberOfFollowers} </span> <fmt:message key="buttom.userAccount.followers" bundle="${rb}"/></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-0"></div>
        <div class="col-lg-3 col-md-12">
            <div class="row">
                <div class="col">
                    <div class="centered">
                        <ul id="right-menu" >
                            <li >
                                <ul class="list-inline">
                                    <li id="UserMenuRightImg" class="list-inline-item"><a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}"><span>${requestScope.numberOfFotos} </span><fmt:message key="buttom.userAccount.img" bundle="${rb}"/></a></li>
                                    <li id="UserMenuRightLikes" class="list-inline-item"><a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}"><span>${requestScope.numberOfLikes} </span><i class="fa fa-heart-o" aria-hidden="true"></i></a></li>
                                </ul>
                            </li>
                            <c:if test="${requestScope.currentUser.login == sessionScope.user}">
                                <li><a href="controller?command=img-page-redirect" class=""><button id="UserMenuRightAddImgButtom" type="button" class="btn btn-success"><fmt:message key="buttom.userAccount.addFoto" bundle="${rb}"/></button></a></li>
                            </c:if>
                                </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="userEditModel" class="modal fade">
    <div class="modal-dialog">
        <div id="modalWindow" class="modal-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <label class="modalUserEditHeader"><h4><fmt:message key="js.userEdit.main" bundle="${rb}"/></h4></label>
                    </div>
                    <div class="col">
                        <button id="modalUserEditClose" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div id="modalUserEditForm" class="form-group">
                            <form class="editUserForm" name="editUserForm">
                                <label ><h5><fmt:message key="js.userEdit.name" bundle="${rb}"/></h5></label>
                                <input name="name" type="text" class="form-control"  value="${sessionScope.loginedUser.getName()}">
                                <small id="emailHelp" class="form-text text-muted"><h6></h6></small>
                                <label ><h5><fmt:message key="js.userEdit.surname" bundle="${rb}"/></h5></label>
                                <input name="surname" type="text" class="form-control"  value="${sessionScope.loginedUser.getSurname()}">
                                <small  class="form-text text-muted"><h6></h6></small>
                                <label ><h5><fmt:message key="js.userEdit.email" bundle="${rb}"/></h5></label>
                                <input name="email" type="email" class="form-control"  value="${sessionScope.loginedUser.getEmail()}">
                                <small  class="form-text text-muted"><h6></h6></small>
                                <label ><h5><fmt:message key="js.userEdit.login" bundle="${rb}"/></h5></label>
                                <input name="login" type="text" class="form-control"  value="${sessionScope.loginedUser.getLogin()}">
                                <small  class="form-text text-muted"><h6></h6></small>
                                <label ><h5><fmt:message key="js.userEdit.password" bundle="${rb}"/></h5></label>
                                <input name="password" type="password" class="form-control" value="${sessionScope.loginedUser.getPassword()}">
                                <small  class="form-text text-muted"><h6></h6></small>
                                <a id="userEditSubmit" type="submit" class="btn btn-success btn-lg" ><fmt:message key="js.userEdit.send" bundle="${rb}"/></a>
                            </form>
                            <div class="userEditMessage"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

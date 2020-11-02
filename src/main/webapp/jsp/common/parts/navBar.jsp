<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand mr-auto" href="controller"><img src="img/logotype.png"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">

        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="buttom.currentLg" bundle="${rb}"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="controller?command=change-locale&language=ru"><fmt:message key="buttom.ru" bundle="${rb}"/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="controller?command=change-locale&language=eng"><fmt:message key="buttom.en" bundle="${rb}"/></a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#userContactModel" href="#"><fmt:message key="buttom.contacts" bundle="${rb}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=userlist-page-redirect"><fmt:message key="buttom.users" bundle="${rb}"/></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input type="hidden" name="command" value="userlist-page-redirect">
            <input class="form-control mr-sm-2" type="text" name="partOfName" placeholder="<fmt:message key="buttom.userAccount.findUser" bundle="${rb}"/>" aria-label="Search">
            <button class="btn btn btn-success my-2 my-sm-0" type="submit"><fmt:message key="buttom.userAccount.find" bundle="${rb}"/></button>
        </form>
        <ul class="navbar-nav mr-0">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="controller?command=user-account-redirect" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${sessionScope.user}
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="controller?command=user-account-redirect"><fmt:message key="buttom.userMenu.prof" bundle="${rb}"/></a>
                    <a class="dropdown-item" href="controller?command=followers-redirect"><fmt:message key="buttom.userMenu.followers" bundle="${rb}"/></a>
                    <a class="dropdown-item" href="controller?command=followings-redirect"><fmt:message key="buttom.userMenu.followings" bundle="${rb}"/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" data-toggle="modal" data-target="#userDeleteAccountModel" href="#"><fmt:message key="buttom.userMenu.delete" bundle="${rb}"/></a>
                    <a class="dropdown-item" href="controller?command=log-out"><fmt:message key="buttom.userMenu.exit" bundle="${rb}"/></a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div id="userDeleteAccountModel" class="modal fade">
    <div class="modal-dialog">
        <div id="modalDeleteAccountWindow" class="modal-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <label class="modalUserEditHeader"><h4><fmt:message key="js.deleteAccount.main" bundle="${rb}"/></h4></label>
                    </div>
                    <div class="col">
                        <button id="modalUserEditClose" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <span><fmt:message key="js.deleteAccount.Text" bundle="${rb}"/></span><br><br>
                        <a href="controller?command=delete-account" class="btn btn-danger btn-lg"><fmt:message key="buttom.userMenu.delete" bundle="${rb}"/></a><br><br>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div id="userContactModel" class="modal fade">
    <div class="modal-dialog">
        <div id="modalContactWindow" class="modal-content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col">
                        <label class="modalUserEditHeader"><h4><fmt:message key="js.contacts.main" bundle="${rb}"/></h4></label>
                    </div>
                    <div class="col">
                        <button id="modalUserEditClose" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <span><fmt:message key="js.contacts.text" bundle="${rb}"/></span><br><br>
                        <span><fmt:message key="js.contacts.name" bundle="${rb}"/></span><br>
                        <span><fmt:message key="js.contacts.email" bundle="${rb}"/></span><br>
                        <span><fmt:message key="js.contacts.mob" bundle="${rb}"/></span><br><br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
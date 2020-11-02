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
        </ul>
        <ul class="navbar-nav mr-0">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=registration-page-redirect" id="navbarDropdown">
                    <fmt:message key="buttom.registrationPage.registration" bundle="${rb}"/>
                </a>
            </li>
        </ul>
    </div>
</nav>
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
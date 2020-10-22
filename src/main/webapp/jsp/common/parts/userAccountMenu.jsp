<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<div class="userMenu">
    <div class="userMenuImg">
    </div>
    <div class="userMenuLeft">
        <div class="userMenuLeftUserName"><a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}"><span>${requestScope.currentUser.getLogin()}</span></a></div>
        <c:if test="${requestScope.currentUser.getLogin() == sessionScope.user}">
            <div class="userMenuLeftEdit" ><img src="img/pensil.png"><a class="editUser"><fmt:message key="buttom.userAccount.edit" bundle="${rb}"/></a></div>
            <div class="userEditPage">

                <a class="userEditPageClose" ><fmt:message key="js.userEdit.buttom.close" bundle="${rb}"/></a>
                <label class="userEditPageMain"><fmt:message key="js.userEdit.main" bundle="${rb}"/></label>
                <form class="editUserForm" name="editUserForm" >
                    <label class="userEditPageTitle"><fmt:message key="js.userEdit.name" bundle="${rb}"/> </label>
                    <div class="userEditPageTitleText"><textarea name="name" >${sessionScope.loginedUser.getName()}</textarea></div>
                    <label class="userEditPageTitle"><fmt:message key="js.userEdit.Surname" bundle="${rb}"/></label>
                    <div class="userEditPageTitleText"><textarea name="surname" >${sessionScope.loginedUser.getSurname()}</textarea></div>
                    <label class="userEditPageTitle"><fmt:message key="js.userEdit.Email" bundle="${rb}"/></label>
                    <div class="userEditPageTitleText"><textarea name="email" >${sessionScope.loginedUser.getEmail()}</textarea></div>
                    <label class="userEditPageTitle"><fmt:message key="js.userEdit.Login" bundle="${rb}"/></label>
                    <div class="userEditPageTitleText"><textarea name="login">${sessionScope.loginedUser.getLogin()}</textarea></div>
                    <label class="userEditPageSubmit"><fmt:message key="js.userEdit.buttom.send" bundle="${rb}"/></label>
                </form><br>
                <div class="userEditMessage"></div>
            </div>
        </c:if>
        <div class="userMenuLeftFollowing"><a href="controller?command=followings-redirect&userId=${requestScope.currentUser.getId()}">
            <span id="userAccountNumFollowings">${requestScope.numberOfFollowings} </span ><fmt:message key="buttom.userAccount.followings" bundle="${rb}"/></a></div>
        <div class="userMenuLeftUserFollowers"><a href="controller?command=followers-redirect&userId=${requestScope.currentUser.getId()}">
            <span >${requestScope.numberOfFollowers} </span><fmt:message key="buttom.userAccount.followers" bundle="${rb}"/></a></div>
    </div>
    <div class="userMenuRight">
        <div class="userMenuRightImgs">
            <a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}"><span>${requestScope.numberOfFotos} </span><fmt:message key="buttom.userAccount.img" bundle="${rb}"/></a>
        </div>
        <div class="userMenuRightLikes">
            <a href="controller?command=user-account-redirect&userId=${requestScope.currentUser.getId()}"><img src="img/likeOff.png"><span>${requestScope.numberOfLikes}</span></a>
        </div>
        <div class="userMenuRightAddImg">
            <c:if test="${requestScope.currentUser.login == sessionScope.user}">
                <a href="?command=img-page-redirect"><fmt:message key="buttom.userAccount.addFoto" bundle="${rb}"/></a>
            </c:if>
        </div>
    </div>
</div>
<script>
    var JUserId=${sessionScope.loginedUser.getId()}
</script>

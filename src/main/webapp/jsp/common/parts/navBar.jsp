<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<div class="mainNav">
    <div class="language">
        <a href="controller?" class="mailLi"><fmt:message key="buttom.currentLg" bundle="${rb}"/></a>
        <ul>
            <li>
                <a href="controller?command=change-locale&language=ru"><fmt:message key="buttom.ru" bundle="${rb}"/></a>
            </li>
            <li>
                <a href="controller?command=change-locale&language=eng"><fmt:message key="buttom.en" bundle="${rb}"/></a>
            </li>
        </ul>
    </div>
    <div class="contact">
        <a class="contactHref" ><fmt:message key="buttom.contacts" bundle="${rb}"/></a>
    </div>
    <div class="logo">
        <a href="controller"><img src="img/logo.png" width="238" height="38" ></a>
    </div>
    <div class="userSearchForm">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="userlist-page-redirect">
            <input type="text" name="partOfName" placeholder="<fmt:message key="buttom.userAccount.findUser" bundle="${rb}"/>">
            <input type="submit" value="<fmt:message key="buttom.userAccount.find" bundle="${rb}"/>">
        </form>
    </div>
    <div class="userAccountMenu">
        <a href="controller?command=user-account-redirect"><img src="img/userIcon.png"><span>${sessionScope.user}</span></a>
        <ul class="hiddenUserAccountMenu">
            <li><a href="controller?command=user-account-redirect"><fmt:message key="buttom.userMenu.prof" bundle="${rb}"/></a></li>
            <li><a href="controller?command=followers-redirect"><fmt:message key="buttom.userMenu.followers" bundle="${rb}"/></a></li>
            <li><a href="controller?command=followings-redirect"><fmt:message key="buttom.userMenu.followings" bundle="${rb}"/></a></li>
            <li><a href="controller?command=delete-account"><fmt:message key="buttom.userMenu.delete" bundle="${rb}"/></a></li>
            <li><a href="controller?command=log-out"><fmt:message key="buttom.userMenu.exit" bundle="${rb}"/></a></li>
        </ul>
    </div>
</div>
<div class="contactPage">
    Anton Yurovski<br>
    email: anton3.ru@mail.ru<br>
    phone: +375298881952
</div>

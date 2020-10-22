<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<div class="mainNav">
    <div class="language">
        <a href="?" class="mailLi"><fmt:message key="buttom.currentLg" bundle="${rb}"/></a>
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
    <div class="logo1">
        <a href="controller"><img src="img/logo.png" width="238" height="38" ></a>
    </div>
    <div class="reg">
        <a href="controller?command=registration-page-redirect"><fmt:message key="buttom.registrationPage.registration" bundle="${rb}"/></a>
    </div>
</div>
<div class="contactPage">
    Anton Yurovski<br>
    email: anton3.ru@mail.ru<br>
    phone: +375298881952
</div>

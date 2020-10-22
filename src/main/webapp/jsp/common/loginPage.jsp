<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link href="css/mainC.css" rel="stylesheet">
    <link href="css/cssReset.css" rel="stylesheet">
    <link href="css/loginR.css" rel="stylesheet">
    <title>InstaGod</title>
</head>

<body >

<c:import url="/jsp/common/parts/guestNavBar.jsp"/>

<div class="center-box">
    <div class="header">
        <h1><fmt:message key="buttom.loginPage.login" bundle="${rb}"/></h1>
    </div>
    <div class="middlePart">
        <div class="formContenet">
        <form class="logInForm" name="form1" method="post" action="controller">
            <input type="hidden" name="command" value="log-in">
                <div class="inputName">
                    <label><fmt:message key="loginPage.text.email" bundle="${rb}"/></label>
                    <input type="text" name="name" placeholder="<fmt:message key="loginPage.text.email" bundle="${rb}"/>">
                </div>
                <div class="inputPassword">
                    <label><fmt:message key="loginPage.text.passoword" bundle="${rb}"/></label>
                    <input type="password" name="password" placeholder="<fmt:message key="loginPage.text.passoword" bundle="${rb}"/>" >
                    <span><a class="fogotPassword"><fmt:message key="loginPage.forgotPassword" bundle="${rb}"/></a></span>
                </div>
                <div class="buttomContener">
                    <input type="submit" value="<fmt:message key="buttom.loginPage.login" bundle="${rb}"/>">
                </div>
        </form>
        </div>
    <div class="fbvk">
        <div class="fbvkOR"><fmt:message key="loginPage.OR" bundle="${rb}"/></div>
        
        <div class="fbvkLinks">
        <h2><span text="<fmt:message key="loginPage.login" bundle="${rb}"/>"></span></h2>
        <ul>
        <li><a>FaceBook</a></li>
        <li><a>Twitter</a></li>
        <li><a>Google</a></li>
        <li><a>VK</a></li>
        </ul>
        </div>
    </div>
    </div>
    <div class="loginPageErrorBlock">
        <span>${requestScope.errorMassage}</span>
    </div>

</div>
<div class="fogotPasswordForm">
    <a class="fogotPasswordClose" ><fmt:message key="js.forgotPassword.buttom.close" bundle="${rb}"/></a>
    <label class="fogotPasswordMain"><fmt:message key="js.forgotPassword.main" bundle="${rb}"/></label>
    <form name="forgotPassword">
        <label id="fogotPasswordTitleID" class="fogotPasswordTitle"><fmt:message key="js.forgotPassword.text" bundle="${rb}"/></label>
        <div id="fogotPasswordTextID" class="fogotPasswordText"><textarea name="loginemail" placeholder="<fmt:message key="js.forgotPassword.text" bundle="${rb}"/>"></textarea></div>
        <label id="forgotPasswordSubmitID" class="forgotPasswordSubmit"><fmt:message key="js.forgotPassword.buttom.send" bundle="${rb}"/></label>
    </form>
</div>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
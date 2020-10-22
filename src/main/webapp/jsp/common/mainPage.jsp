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
    <title>InstaGod</title>
</head>
<body >

<c:import url="/jsp/common/parts/guestNavBar.jsp"/>

<main>   
    <div class="mainText">
    <h1><fmt:message key="mainPage.text.h1" bundle="${rb}"/></h1>
    <h2><fmt:message key="mainPage.text.h2" bundle="${rb}"/></h2>
    </div>
    <div class="mainButtom">
    <a href="?command=login-page-redirect" ><fmt:message key="buttom.loginPage.login" bundle="${rb}"/></a>
    </div>
</main>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
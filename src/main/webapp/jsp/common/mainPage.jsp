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
    <link rel="stylesheet" href="css/mainPage.css">
</head>

<body >
<c:import url="/jsp/common/parts/guestNavBar.jsp"/>
 
<div id="mainText" class="container-fluid">
    <div class="row ">
    <div class="col">
        <div class="centered"><h1><fmt:message key="mainPage.text.h1" bundle="${rb}"/></h1></div>
        <div class="centered"><h2><fmt:message key="mainPage.text.h2" bundle="${rb}"/></h2></div>
    </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row ">
        <div class="col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2">
        <a id="loginButton" class="btn btn-success btn-lg" href="?command=login-page-redirect" ><fmt:message key="buttom.loginPage.login" bundle="${rb}"/></a>
        </div>
    </div>
</div>


<div id="footer" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col"><span>CopyRight <i class="fa fa-copyright" aria-hidden="true"></i> 2020. By Anton Yurovski</span></div>
    </div>   
</div>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
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
    <link href="css/addImgPage.css" rel="stylesheet">
    <title>InstaGod</title>
</head>

<body >

<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="ImgPageContener">
    <div class="ImgPageImg"><img src="img/upLoad.png"></div>
    <div class="ImgPageForm">
        <form enctype="multipart/form-data"  method="post" action="img">
        <label class="ImgPageCustomFile"><input class="custom-file-input" type="file" name="photo" multiple accept="image/*,image/jpeg">
            <span><fmt:message key="buttom.addnewimg.choose" bundle="${rb}"/></span>
            </label>
        <label class="ImgPageCustomSubmit"><input type="submit" >
            <span><fmt:message key="buttom.addnewimg.send" bundle="${rb}"/></span>
            </label>
        </form>
        </div>
    <c:import url="/jsp/common/parts/links.jsp"/>
</div>
</body>
</html>
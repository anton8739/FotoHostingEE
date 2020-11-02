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
    <link rel="stylesheet" href="css/addNewImgCss.css">
</head>

<body >
<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <div id="drop-area">
                <form class="my-form" enctype="multipart/form-data"  method="post" action="img">
                    <p>Загрузите изображения с помощью диалога выбора файлов или перетащив нужные изображения в выделенную область</p>
                    <input type="file" id="fileElem" name="photo" multiple accept="image/*,image/jpeg" onchange="handleFiles(files)">
                    <label class="button" for="fileElem"><fmt:message key="buttom.addnewimg.choose" bundle="${rb}"/></label>
                    <label class="button"><input id="fileSubmit" type="submit" onclick="sendFile()"><fmt:message key="buttom.addnewimg.send" bundle="${rb}"/></label>
                </form>
                <div id="preview">
                </div>
            </div>
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
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
    <link rel="stylesheet" href="css/registrationPage.css">
</head>

<body >

<c:import url="/jsp/common/parts/guestNavBar.jsp"/>
<div id="content" class="container-fluid">
    <div class="row">
        <div class="col-lg-8 offset-lg-2">
            <div class="loginWord">
                <span><h3><fmt:message key="buttom.registrationPage.registration" bundle="${rb}"/></h3></span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-4 offset-lg-2">
            <div id="loginForm" class="form-group">
                <form name="form1" method="post" action="controller">
                    <input type="hidden" name="command" value="registration">
                    <label ><h5><fmt:message key="registrationPage.text.email" bundle="${rb}"/></h5></label>
                    <input type="email" name="email"  class="form-control"   placeholder="<fmt:message key="registrationPage.text.email" bundle="${rb}"/>">
                    <label ><h5><fmt:message key="registrationPage.text.UserName" bundle="${rb}"/></h5></label>
                    <input  type="text" name="login" class="form-control"  placeholder="<fmt:message key="registrationPage.text.UserName" bundle="${rb}"/>">
                    <label ><h5><fmt:message key="registrationPage.text.Password" bundle="${rb}"/></h5></label>
                    <input type="password" name="password"  class="form-control"  placeholder="<fmt:message key="registrationPage.text.Password" bundle="${rb}"/>">

                    <div id="checkBox" class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="checkBoxRules" value="" checked="">
                            <Span ><fmt:message key="registrationPage.text.rules1" bundle="${rb}"/></Span><a href="#"><fmt:message key="registrationPage.text.rules2" bundle="${rb}"/></a>
                            <span><fmt:message key="registrationPage.text.rules3" bundle="${rb}"/></span><a href="#"><fmt:message key="registrationPage.text.rules4" bundle="${rb}"/></a>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-success btn-lg"><fmt:message key="buttom.registrationPage.registration" bundle="${rb}"/></button>
                </form>
                <div style="color: #fd4650">${requestScope.errorMassage}</div>
            </div>

        </div>
        <div class="col-lg-4">

            <div id="myslider3" class="juicyslider">
                <ul>
                    <li><img src="http://drawings-girls.ucoz.net/2016/12/dlinnovolosaya-blondinka-s-tigrom.jpg" alt="Juicy Slider 1"></li>
                    <li><img src="http://drawings-girls.ucoz.net/2016/12/neobichnaya-devushka-s-orujiem.jpg" alt="Juicy Slider 2"></li>
                    <li><img src="http://drawings-girls.ucoz.net/2016/12/misticheskaya-devushka-s-trohpaloi-lapoi.jpg" alt="Juicy Slider 3"></li>
                </ul>
                <div class="mask"></div>
            </div>


        </div>
    </div>
</div>
<div id="footer" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col"><span>CopyRight <i class="fa fa-copyright" aria-hidden="true"></i> 2020. By Anton Yurovski</span></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2017/juicy-slider/css/juicyslider-min.css" />
<script src="https://bootstraptema.ru/plugins/2017/juicy-slider/js/jquery-1.10.2.js"></script>
<script src="https://bootstraptema.ru/plugins/2017/juicy-slider/js/jquery-ui.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2017/juicy-slider/js/juicyslider-min.js"></script>
<script>
    $(function() {
        $('#myslider3').juicyslider({
            width: '100%',
            mask: "strip",
            bgcolor: "#000",
            autoplay: 6000,
            shuffle: false,
            show: {effect: 'puff', duration: 5000},
            hide: {effect: 'puff', duration: 3000},
        });
    });
</script>
</body>
</html>
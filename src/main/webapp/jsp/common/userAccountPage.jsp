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
    <link rel="stylesheet" href="css/userAccountPage.css">
</head>

<body >
<c:import url="/jsp/common/parts/navBar.jsp"/>
<c:import url="/jsp/common/parts/userAccountMenu.jsp"/>
<div id="userMenuBottomBar" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-5"><fmt:message key="userAccount.text.main" bundle="${rb}"/> ${requestScope.currentUser.getLogin()}</div>
        <div class="col-lg-1"><a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=new"><fmt:message key="buttom.userAccount.new" bundle="${rb}"/></a></div>
        <div class="col-lg-1"><a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=old"><fmt:message key="buttom.userAccount.old" bundle="${rb}"/></a></div>
        <div class="col-lg-1"><a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=pop"><fmt:message key="buttom.userAccount.pop" bundle="${rb}"/></a></div>
    </div>
</div>
<div id="userAccountFotos" class="container-fluid">
    <div class="row justify-content-center">
        <c:choose>
        <c:when test="${not empty requestScope.fotoMap}">
            <c:forEach items="${requestScope.fotoMap}" var="foto">
        <div class="col-lg-3 col-md-6">
            <div class="FotoItem">
                <a href="foto?userId=${requestScope.currentUser.getId()}&foto=${foto.key.name}">
                <img src="${foto.key.URL}">
                </a>
            <div class="FotoItemInfo">
            <span id="likefotoSpan${foto.key.id}">${foto.value.get(0)}</span><a id="likefotoCommand${foto.key.id}" name="${foto.key.id}">
                <c:choose>
                    <c:when test="${foto.value.get(1) == 0}">
                        <span id="likefotoImg${foto.key.id}"><i class="fa fa-heart-o" aria-hidden="true"></i></span>
                    </c:when>
                    <c:otherwise>
                        <span id="likefotoImg${foto.key.id}"><i class="fa fa-heart" aria-hidden="true"></i></span>
                    </c:otherwise>
                </c:choose>
            </a>
            <span>${foto.value.get(2)} <i class="fa fa-comments" aria-hidden="true"></i></span>
            </div>
            </div>
        </div>
            </c:forEach>
        </c:when>
            <c:otherwise>
                <div class="col-lg-3 col-md-6">
                    <span style="color: #fefefe"><fmt:message key="userAccount.text.noFoto" bundle="${rb}"/></span>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div id="footer" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col"><span>CopyRight <i class="fa fa-copyright" aria-hidden="true"></i> 2020. By Anton Yurovski</span></div>
    </div>   
</div>
<script>
    var jLoginedUserId=${sessionScope.id};
    var JUserId=${requestScope.currentUser.getId()};
</script>
<c:import url="/jsp/common/parts/links.jsp"/>
<script>
    function res(){
   var width = $('.FotoItem').width();
	 $('.FotoItem').height(width);
} res();

$( window ).resize(function() {
	res();
});
</script>
</body>
</html>
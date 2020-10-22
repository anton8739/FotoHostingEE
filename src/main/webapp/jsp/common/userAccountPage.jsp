<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pageContent" var="rb"/>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' charset="utf-8" />
    <link href="css/mainC.css" rel="stylesheet">
    <link href="css/cssReset.css" rel="stylesheet">
    <link href="css/userAccountR.css" rel="stylesheet">
    <title>InstaGod</title>
</head>

<body >
<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="mainImg">
</div>
<c:import url="/jsp/common/parts/userAccountMenu.jsp"/>
<div class="fotoContener">
    <div class="fotoMenu">
        <div><span> <fmt:message key="userAccount.text.main" bundle="${rb}"/></span><span>${requestScope.currentUser.getLogin()}</span></div>
        <a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=new"><fmt:message key="buttom.userAccount.new" bundle="${rb}"/></a>
        <a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=old"><fmt:message key="buttom.userAccount.old" bundle="${rb}"/></a>
        <a href="controller?userId=${requestScope.currentUser.getId()}&command=foto-sort&sort=pop"><fmt:message key="buttom.userAccount.pop" bundle="${rb}"/></a>
        
    </div>

    <div class="fotoList">
        <c:choose>
        <c:when test="${not empty requestScope.fotoMap}">
            <c:forEach items="${requestScope.fotoMap}" var="foto">
                <div class="fotoItem"  >
                    <a href="foto?userId=${requestScope.currentUser.getId()}&foto=${foto.key.name}"><img src="${foto.key.URL}"></a>
                    <div class="hiddenButtoms" >
                     <a id="likefotoCommand${foto.key.id}" name="${foto.key.id}"  >
                         <c:choose>
                             <c:when test="${foto.value.get(1) == 0}">
                         <img id="likefotoImg${foto.key.id}" src="img/likeOff.png">
                            </c:when>
                            <c:otherwise>
                         <img id="likefotoImg${foto.key.id}" src="img/likeON.png">
                            </c:otherwise>
                         </c:choose>
                    <span id="likefotoSpan${foto.key.id}">${foto.value.get(0)}</span></a>
                        <a href="${foto.key.URL}"><img src="img/comment.png"><span>${foto.value.get(2)}</span></a>
                    </div>
                    </div>
            </c:forEach>

        </c:when>
        <c:otherwise>
        <div class="fotoItem">
            <span><fmt:message key="userAccount.text.noFoto" bundle="${rb}"/></span>
        </div>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<script>
    var jLoginedUserId=${sessionScope.id};
    var jUserId="${requestScope.currentUser.getId()}";
</script>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
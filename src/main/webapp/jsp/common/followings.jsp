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
    <link href="css/followings.css" rel="stylesheet">
    <title>InstaGod</title>
</head>

<body >

<c:import url="/jsp/common/parts/navBar.jsp"/>
<div class="mainImg">
    
</div>
<c:import url="/jsp/common/parts/userAccountMenu.jsp"/>
<div class="userCont">
    <div class="userM">
        <div><span><fmt:message key="followingsPage.text.main" bundle="${rb}"/> </span><span>${requestScope.currentUser.getLogin()}</span></div>
        <a href="controller?userId=${requestScope.currentUser.getId()}&command=user-sort&from=followings&sort=new"><span><fmt:message key="buttom.userAccount.new" bundle="${rb}"/></span></a>
        <a href="controller?userId=${requestScope.currentUser.getId()}&command=user-sort&from=followings&sort=old"><span><fmt:message key="buttom.userAccount.old" bundle="${rb}"/></span></a>
    </div>
    <c:choose>
        <c:when test="${not empty requestScope.followingsMap}">
            <c:forEach items="${requestScope.followingsMap}" var="following">
                <div class="userItem">
                    <div class="userItemImg"><img src="img/userIcon.png"></div>
                    <div class="userItemName"><a href="?command=user-account-redirect&userId=${following.key.getId()}"><span>${following.key.getLogin()}</span></a></div>

                    <div class="userItemUnsubscribe" ><a id="followUnfollowHref${following.key.getId()}" name="${following.key.getId()}" >
                        <c:choose>
                            <c:when test="${following.value == 1}">
                                <span id="followUnfollowSpan${following.key.getId()}" class="userItemSubscribeS2"><fmt:message key="buttom.unsubscribe" bundle="${rb}"/></span>
                            </c:when>
                            <c:otherwise>
                                <span id="followUnfollowSpan${following.key.getId()}" class="userItemSubscribeS1"><fmt:message key="buttom.subscribe" bundle="${rb}"/></span>
                            </c:otherwise>
                        </c:choose>
                    </a></div>
                </div>
            </c:forEach>

        </c:when>
        <c:otherwise>
            <div class="userItem">
                Нет подписок.
            </div>
        </c:otherwise>
    </c:choose>
    <script>
        var jTextSubscribe='<fmt:message key="buttom.subscribe" bundle="${rb}"/>';
        var jTextUnSubscribe='<fmt:message key="buttom.unsubscribe" bundle="${rb}"/>';
        var jLoginedUserId="${sessionScope.id}";
        var jcurrentUserId="${requestScope.currentUser.getId()}";
    </script>
    <c:import url="/jsp/common/parts/links.jsp"/>
</div>
</body>
</html>
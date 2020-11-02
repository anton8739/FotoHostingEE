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
    <link rel="stylesheet" href="css/userLists.css">
</head>

<body >
<c:import url="/jsp/common/parts/navBar.jsp"/>
<c:import url="/jsp/common/parts/userAccountMenu.jsp"/>
<div id="userMenuBottomBar" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-5"><fmt:message key="userListPage.text.main" bundle="${rb}"/> </div>
        <div class="col-lg-1"><a href="controller?userId=${requestScope.currentUser.getId()}&command=user-sort&from=userLists&sort=new&part=${requestScope.get("partOfName")}"><fmt:message key="buttom.userAccount.new" bundle="${rb}"/></a></div>
        <div class="col-lg-1"><a href="controller?userId=${requestScope.currentUser.getId()}&command=user-sort&from=userLists&sort=old&part=${requestScope.get("partOfName")}"><fmt:message key="buttom.userAccount.old" bundle="${rb}"/></a></div>
    </div>
</div>
<div id="folCont" class="container-fluid">
    <c:choose>
        <c:when test="${not empty requestScope.usersMap}">
            <c:forEach items="${requestScope.usersMap}" var="user">
                <div class="row">
                    <div class="col-lg-3 offset-lg-1 col-md-4 col-sm-5">
                        <div class="follower">
                            <a href="#">
                                <i  class="fa fa-user-circle-o fa-fw"></i>
                                <span><a href="?command=user-account-redirect&userId=${user.key.getId()}">${user.key.getLogin()}</a></span></a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4">
                        <div id="followUnfollow${user.key.getId()}" name="${user.key.getId()}" class="folbuttom">
                            <c:choose>
                                <c:when test="${user.value == 1}">
                                    <a class="btn btn-secondary" id="followUnfollowSpan${user.key.getId()}"><fmt:message key="buttom.unsubscribe" bundle="${rb}"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-info" id="followUnfollowSpan${user.key.getId()}"><fmt:message key="buttom.subscribe" bundle="${rb}"/></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="row">
                <fmt:message key="userList.text.userNotFound" bundle="${rb}"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div id="footer" class="container-fluid">
    <div class="row justify-content-center">
        <div class="col"><span>CopyRight <i class="fa fa-copyright" aria-hidden="true"></i> 2020. By Anton Yurovski</span></div>
    </div>   
</div>
<script>
    var jTextSubscribe='<fmt:message key="buttom.subscribe" bundle="${rb}"/>';
    var jTextUnSubscribe='<fmt:message key="buttom.unsubscribe" bundle="${rb}"/>';
    var jLoginedUserId="${sessionScope.id}";
    var jcurrentUserId="${requestScope.currentUser.getId()}";
</script>
<c:import url="/jsp/common/parts/links.jsp"/>
</body>
</html>
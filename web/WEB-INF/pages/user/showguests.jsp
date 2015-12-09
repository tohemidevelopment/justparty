<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 02.12.2015
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="showguests.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-4">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/style.css">
</head>
<body>
<form action="/j_spring_security_logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                justParty
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control"
                           placeholder="<spring:message code="nav.searchplaceholder"/>">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="nav.events"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='${pageContext.request.contextPath}/createEvent'><span><spring:message
                                code="nav.events.create"/> </span></a>
                        </li>
                        <li><a href='${pageContext.request.contextPath}/manageEvent'><span><spring:message
                                code="nav.events.manage"/> </span></a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="nav.settings"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:logoutFormSubmit()"><span><spring:message
                                code="nav.logout"/> </span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="showguests.header"/></h1>
    </div>
    <!-- alerts -->
    <div>
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="${alert_danger}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-warning" role="alert">
                <spring:message code="${alert_warning}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_success}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${alert_success}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_info}">
            <div class="alert alert-info" role="alert">
                <spring:message code="${alert_info}"/>
            </div>
        </c:if>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="showguests.guestlist"/></th>
            <th><spring:message code="showguests.guestlist.accepted"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${guests}" var="element">
            <tr>
                <td>${element.user.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${element.accepted=='ACCEPTED'}">
                            <spring:message code="manager.table.select.accept"/>
                            <span class="glyphicon glyphicon-ok-sign"></span>
                        </c:when>
                        <c:when test="${element.accepted=='DECLINED'}">
                            <spring:message code="manager.table.select.cancel"/>
                            <span class="glyphicon glyphicon-remove-sign"></span>
                        </c:when>
                        <c:when test="${element.accepted=='NOTSURE'}">
                            <spring:message code="manager.table.select.notsure"/>
                            <span class="glyphicon glyphicon-question-sign"></span>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- JS-Libraries requiered for justParty -->
<script src="${pageContext.request.contextPath}/res/js/JPforms.js"></script>
<script src="${pageContext.request.contextPath}/res/js/JPevents.js"></script>
</body>
</html>
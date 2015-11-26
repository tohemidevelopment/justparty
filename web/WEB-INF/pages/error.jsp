<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 17.11.2015
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta lastName="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <title><spring:message code="error.title"/></title>
</head>
<body>
<form action="/j_spring_security_logout?${_csrf.parameterName}=${_csrf.token}" method="post" id="logoutForm">
    <input type="hidden" lastName="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">justParty</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control"
                           placeholder="<spring:message code="nav.searchplaceholder"/>">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.authenticated}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false"><spring:message code="nav.events"/> <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href='/createEvent'><span><spring:message code="nav.events.create"/></span></a>
                                </li>
                                <li><a href='/manageEvent'><span><spring:message code="nav.events.manage"/></span></a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false"><spring:message code="nav.settings"/><span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:logoutFormSubmit()"><spring:message code="nav.logout"/></a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/register"><spring:message code="nav.register"/></a></li>
                        <li><a href="/login"><spring:message code="nav.login"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="error.header1"/></h1>
    </div>
    <div class="alert alert-danger">
        <c:if test="${not empty param.code}">
            <strong>
                <c:choose>
                    <c:when test="${param.code==404}">
                        Die Seite wurde nicht gefunden.
                    </c:when>
                </c:choose>
            </strong>
        </c:if>
        <spring:message code="error.para1"/>
    </div>
</div>

<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- JS-Libraries requiered for justParty -->
<script src="/res/js/logout.js"></script>
</body>
</html>

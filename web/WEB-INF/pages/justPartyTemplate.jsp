<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 02.12.2015
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="manager.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-4">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/style.css">
    <%-- csrf for AJAX--%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
            <form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath}" method="get">
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
                                <li><a href='${pageContext.request.contextPath}/createEvent'><span><spring:message
                                        code="nav.events.create"/> </span></a>
                                </li>
                                <li><a href='${pageContext.request.contextPath}/manageEvent'><span><spring:message
                                        code="nav.events.manage"/> </span></a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true"
                               aria-expanded="false"><spring:message code="nav.settings"/> <span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:submitForm('logoutForm')"><span><spring:message
                                        code="nav.logout"/> </span></a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/register"><spring:message
                                code="nav.register"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/login"><spring:message code="nav.login"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1></h1>
    </div>
    <!-- alerts -->
    <div id="alerts">
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

</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- JS-Libraries requiered for justParty -->
<script src="${pageContext.request.contextPath}/res/js/JPforms.js"></script>
<script src="${pageContext.request.contextPath}/res/js/JPevents.js"></script>
</body>
</html>
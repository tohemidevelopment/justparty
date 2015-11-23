<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta lastName="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <title><spring:message code="login.title"/></title>
    <sec:csrfMetaTags/>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid" id="navbar">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/" style="color: white;">
                justParty
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Suchen...">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <a class="navbar-brand" href="/register" style="color: white;">
                    <spring:message code="nav.register"/>
                </a>
                <a class="navbar-brand" href="/" style="color: white;">
                    <spring:message code="nav.whatis"/>
                </a>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1 id="test"><spring:message code="login.header1"/></h1>
    </div>
    <core:if test="${not empty login_error}">
        <div class="alert alert-danger" role="alert">
                ${login_error}
        </div>
    </core:if>
    <core:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert">
            <spring:message code="${alert_success}"/>
        </div>
    </core:if>
    <form method="post" action="/perform_login" role="form" id="login_form">
        <div class="form-group">
            <label for="usr"><spring:message code="login.label.username"/>:</label>
            <input type="text" class="form-control" name="username" id="usr"
                   placeholder="<spring:message code="login.placeholder.username"/>">
        </div>
        <div class="form-group">
            <label for="pwd"><spring:message code="login.label.pw"/>:</label>
            <input type="password" class="form-control" id="pwd" name="password"
                   placeholder="<spring:message code="login.placeholder.pw"/>">
        </div>
        <input class="form-control" name="submit" type="submit" value="<spring:message code="login.login"/>"/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <spring:message code="login.havenoaccount"/><br>
    <a href="/register">
        <button class="btn btn-default">
            <spring:message code="welcome.registerbtn"/>
        </button>
    </a>
</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

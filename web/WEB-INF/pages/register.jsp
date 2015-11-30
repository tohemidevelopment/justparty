<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="register.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <style>
        .form-control {
            display: inline;
        }
        .control-label {
            text-align: left !important;
        }
    </style>
</head>
<body>
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
                <li><a href="/login"><spring:message code="nav.login"/></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="register.header1"/></h1>
    </div>
    <!-- alerts -->
    <div>
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${alert_danger}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-success" role="alert">
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

    <div>
        <form method="post" action="/register" role="form" class="form-horizontal" id="register_form">
            <div class="form-group">
                <label for="email" class="control-label col-sm-2"><spring:message code="register.label.email"/>:</label>

                <div class="col-sm-7">
                    <input type="text" class="form-control" name="email" id="email"
                           placeholder="<spring:message code="register.placeholder.email"/>" autofocus="autofocus">
                </div>
                <c:if test="${not empty EMAIL}">
                    <label class="control-label col-sm-2">
                        <spring:message code="${EMAIL}"/>
                    </label>
                </c:if>
            </div>
            <div class="form-group">
                <label for="pwd" class="control-label col-sm-2"><spring:message code="register.label.pw"/>:</label>

                <div class="col-sm-7">
                    <input type="password" class="form-control" id="pwd" name="password"
                           placeholder="<spring:message code="register.placeholder.pw"/>">
                </div>
                <c:if test="${not empty PASSWORD}">
                    <label class="control-label col-sm-2">
                        <spring:message code="${PASSWORD}"/>
                    </label>
                </c:if>
            </div>
            <div class="form-group">
                <label for="pwd2" class="control-label col-sm-2"><spring:message
                        code="register.label.pw.repeat"/>:</label>

                <div class="col-sm-7">
                    <input type="password" class="form-control" id="pwd2" name="password_repeat"
                           placeholder="<spring:message code="register.placeholder.pw.repeat"/>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-7">
                    <div class="checkbox">

                        <label><input type="checkbox" name="terms"><spring:message
                                code="register.label.acceptterms"/></label>
                    </div>
                </div>
                <c:if test="${not empty TERMS}">
                    <label class="control-label col-sm-2">
                        <spring:message code="${TERMS}"/>
                    </label>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <input class="form-control" name="submit" type="submit"
                           value="<spring:message code="welcome.registerbtn"/> "/>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</div>

<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>
</html>
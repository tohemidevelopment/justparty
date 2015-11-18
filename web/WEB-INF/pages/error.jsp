<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 17.11.2015
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <title><spring:message code="error.title"/> </title>
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
                    <input type="text" class="form-control"
                           placeholder="<spring:message code="nav.searchplaceholder"/> ">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;"><spring:message code="nav.events"/> <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='/createEvent'><span><spring:message code="nav.events.create"/> </span></a></li>
                        <li><a href='/manageEvent'><span><spring:message code="nav.events.manage"/> </span></a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href='#'><span><spring:message code="nav.events.invitations"/> </span></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;">Einstellungen <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='/login'><span>Log in</span></a></li>
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
        <h1><spring:message code="error.header1"/></h1>
    </div>
    <div class="alert alert-danger"><spring:message code="error.para1"/> </div>
</div>
</body>
</html>

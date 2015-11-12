<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional Bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/res/style.css">
    <title>${message}</title>
</head>

<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid" id="navbar">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/" style="color: white;">LOGO</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Suchen...">
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: white;">Events <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href='/createEvent'><span>Event Erstellen</span></a></li>
                            <li><a href='/manageEvent'><span>Events Managen</span></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href='#'><span>Einladungen zu Events</span></a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: white;">Einstellungen <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href='#'><span>Profil anzeigen</span></a></li>
                            <li><a href='/login'><span>Log in</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="container theme-showcase" role="main">
        <div class="page-header">
            <h1 id="test">${login}</h1>
        </div>
        <core:if test="${not empty login_error}">
            <div class="alert alert-danger" role="alert">
                ${login_error}
            </div>
        </core:if>
        <form method="post" action="/perform_login" role="form" id="login_form">
            <div class="form-group">
                <label for="usr">${username}: </label>
                <input type="text" class="form-control" name="username" id="usr" placeholder="${username}">
            </div>
            <div class="form-group">
                <label for="pwd">${password}: </label>
                <input type="password" class="form-control" id="pwd" name="password" placeholder="${password}">
            </div>
            <input class="form-control" name="submit" type="submit" value="${login}" />
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
    </div>
    <!-- JS-Libraries requiered for Bootstrap -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

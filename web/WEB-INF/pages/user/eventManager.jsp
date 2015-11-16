<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="manager.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/style.css">
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/" style="color: white;">LOGO</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Suchen...">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;">Events <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='${pageContext.request.contextPath}/createEvent'><span>Event Erstellen</span></a>
                        </li>
                        <li><a href='${pageContext.request.contextPath}/manageEvent'><span>Events Managen</span></a>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li><a href='#'><span>Einladungen zu Events</span></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;">Einstellungen <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='#'><span>Profil anzeigen</span></a></li>
                        <li><a href="javascript:logoutFormSubmit()"><span>Logout</span></a></li>
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
        <h1>Manage Deine Events</h1>

        <p>Hier Können Sie Ihre Events Managen</p>
    </div>


    <table class="table">
        <thead>
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#">Aktuelle Events</a></li>
            <li role="presentation"><a href="#">Einladungen</a></li>
            <li role="presentation"><a href="#">Eigene Events</a></li>
            <li role="presentation"><a href="#">Absagen</a></li>
            <li role="presentation"><a href="#">Frühere Events</a></li>
        </ul>
        </thead>
        <tbody>
        <tr>
            <td>Hosted Event 1</td>
            <td>20.10.2015</td>
            <td>
                <button class="btn">Bearbeiten</button>
                <button class="btn">Absagen</button>
            </td>
        </tr>
        <tr>
            <td>Event 2</td>
            <td>21.10.2015</td>
            <td>
                <button class="btn">Annehmen</button>
                <button class="btn">Noch unsicher</button>
                <button class="btn">Absagen</button>
            </td>
        </tr>
        <tr>
            <td>Angenommenes Event</td>
            <td>24.10.2015</td>
            <td>
                <button class="btn">Doch unsicher</button>
                <button class="btn">Absagen</button>
            </td>
        </tr>

        </tbody>
    </table>

</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- JS-Libraries requiered for justParty -->
<script src="${pageContext.request.contextPath}/res/js/logout.js"></script>
</body>
</html>
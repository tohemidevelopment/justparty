<%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 04.11.2015
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="welcome.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/res/style.css">

    <style>
        img.mid {
            display: block;
            margin-left: auto;
            margin-right: auto
        }

    </style>
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

<h2 style="text-align: center;"><spring:message code="welcome.header1"/></h2>

<p style="text-align: center;"><spring:message code="welcome.txt"/></p>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="mid" src="<spring:message code="welcome.car.1.imgsrc"/>" alt="justParty">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.1.header"/></h3>

                <p><spring:message code="welcome.car.1.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.2.imgsrc"/>" alt="planen">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.2.header"/></h3>

                <p><spring:message code="welcome.car.2.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.3.imgsrc"/>" alt="Funktion 3">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.3.header"/></h3>

                <p><spring:message code="welcome.car.3.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.4.imgsrc"/>" alt="Funktion 4" style="">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.4.header"/></h3>

                <p><spring:message code="welcome.car.4.txt"/></p>
            </div>

        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<!--<a href="?lang=de">deutsch</a>
<a href="?lang=en">en</a>

Current Locale : ${pageContext.response.locale}-->

<div class="welcome" id="btnpanel">
    <a href="/register">
        <button type="button" class="btn btn-lg btn-primary"><spring:message code="welcome.registerbtn"/></button>
    </a>
    <a href="/login">
        <button type="button" class="btn btn-lg btn-primary"><spring:message code="welcome.loginbtn"/></button>
    </a>
</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
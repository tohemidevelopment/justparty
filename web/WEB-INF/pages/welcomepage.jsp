<%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 04.11.2015
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
TODO: fill with content

-->

<html>
  <head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional Bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
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
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/" style="color: white;">
            <!-- Placeholder -->
            LOGO
        </a>
      </div>

      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <form class="navbar-form navbar-left" role="search">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="${searchPlaceholder}">
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

  <h2 style="text-align: center;">${header1}</h2>
  <p style="text-align: center;">${description}</p>

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
        <img class="mid" src="https://placeholdit.imgix.net/~text?txtsize=31&txt=Platzhalterbild&w=712&h=450" alt="justParty">
        <div class="carousel-caption">
          <h3>justParty</h3>
          <p>Die Plattform um alle deine Events zu Planen und zu organisieren.</p>
        </div>

      </div>

      <div class="item">
        <img class="mid" src="https://placeholdit.imgix.net/~text?txtsize=31&txt=Platzhalterbild&w=712&h=450" alt="planen">
        <div class="carousel-caption">
          <h3>Vielfältig</h3>
          <p>Du hast die Möglichkeit verschiedenste Events zu erstellen.</p>
        </div>

      </div>

      <div class="item">
        <img class="mid" src="https://placeholdit.imgix.net/~text?txtsize=31&txt=Platzhalterbild&w=712&h=450" alt="Funktion 3">
        <div class="carousel-caption">
          <h3>Sozial</h3>
          <p>Fast alle Einstellungen können du und deine Gäste auf Wunsch gemeinsam treffen.</p>
        </div>

      </div>

      <div class="item">
        <img class="mid" src="https://placeholdit.imgix.net/~text?txtsize=31&txt=Platzhalterbild&w=712&h=450" alt="Funktion 4" style="">
        <div class="carousel-caption">
          <h3>Einfach</h3>
          <p>justParty ist einfach zu bedienen und hilft dir bei allen Entscheidungen</p>
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

<div class="welcomepage" id="btns">
  <a href="/register"><button>${registerBtnText}</button></a>
  <a href="/login"><button>${loginBtnText}</button></a>
</div>

  <!-- JS-Libraries requiered for Bootstrap -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>
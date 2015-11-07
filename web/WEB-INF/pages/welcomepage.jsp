<%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 04.11.2015
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
TODO: fill with content

-->

<html>
  <head>
    <title>just Live, just Love, justParty</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional Bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
  </head>
  <body>

  <nav class="navbar navbar-default">
    <div class="container-fluid" style="background-color: green;">
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
              <li><a href='#'><span>Log in</span></a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

  <h2>${message}</h2>
  <p>${description}</p>

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
        <img src="img_chania.jpg" alt="Funktion 1">
        <div class="carousel-caption">
          <h3>justParty</h3>
          <p>Die Plattform um alle deine Events zu Planen und zu organiesieren.</p>
        </div>

      </div>

      <div class="item">
        <img src="img_chania2.jpg" alt="Funktion 2">
        <div class="carousel-caption">
          <h3>Vielfältig</h3>
          <p>Du hast die Möglichkeit verschiedenste Events zu erstellen.</p>
        </div>

      </div>

      <div class="item">
        <img src="img_flower.jpg" alt="Funktion 3">
        <div class="carousel-caption">
          <h3>Sozial</h3>
          <p>Fast alle Einstellungen können du und deine Gäste zusammen treffen.</p>
        </div>

      </div>

      <div class="item">
        <img src="img_flower2.jpg" alt="Funktion 4">
        <div class="carousel-caption">
          <h3>Chania</h3>
          <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
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

  <p>Noch keinen Account?</p>
  <a href="/register"><button value="Registrieren" >Registrieren</button></a>
  <p>Schon einen Account?</p>
  <a href="/login"><button value="Log In" >Log In</button></a>

  <!-- JS-Libraries requiered for Bootstrap -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </body>
</html>

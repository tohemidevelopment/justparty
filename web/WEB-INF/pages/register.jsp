<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${message}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <style>
        .form-control {
            display: inline;
            width: 90%;
            margin-right: 5px;
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;">Events <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='/createEvent'><span>Event Erstellen</span></a></li>
                        <li><a href='/manageEvent'><span>Events Managen</span></a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href='#'><span>Einladungen zu Events</span></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false" style="color: white;">Einstellungen <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='#'><span>Profil anzeigen</span></a></li>
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
        <h1>${message}</h1>
    </div>
    <div>
        <form method="post" action="/perform_registry" role="form" class="form-horizontal" id="register_form">
            <div class="form-group">
                <label for="usr" class="control-label col-sm-2">${username}: </label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" id="usr" placeholder="${username}">
                    <img src="http://twolske.bplaced.net/smily.png"/>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="control-label col-sm-2">Email: </label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="email" id="email" placeholder="Email">
                    <img src="http://twolske.bplaced.net/smily.png"/>
                </div>
            </div>
            <div class="form-group">
                <label for="pwd" class="control-label col-sm-2">${password}: </label>

                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pwd" name="password" placeholder="${password}">
                    <img src="http://twolske.bplaced.net/smily.png"/>
                </div>
            </div>
            <div class="form-group">
                <label for="pwd2" class="control-label col-sm-2">Wiederholen: </label>

                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pwd2" name="password_repeat"
                           placeholder="${password}">
                    <img src="http://twolske.bplaced.net/smily.png"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox" name="terms">Ich akzeptiere die Nutzungsbedingungen</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input class="form-control" style="width: 20%" name="submit" type="submit" value="Register"/>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

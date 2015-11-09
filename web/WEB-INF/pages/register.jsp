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
                            <li><a href='/login'><span>Log in</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="container theme-showcase" role="main">
        <div class="page-header">
            <h1>${message}</h1>
        </div>
        <div>
        <form method="get" action="/login">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" value="" id="username" /></td>
                    <td><img src="http://twolske.bplaced.net/smily.png"/></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="email" value="" id="email" /></td>
                    <td><img src="http://twolske.bplaced.net/smily.png"/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" value="" id="password" /></td>
                    <td><img src="http://twolske.bplaced.net/smily.png"/></td>
                </tr>
                <tr>
                    <td>Repeat Password: </td>
                    <td><input type="password" value="" id="password1" /></td>
                    <td><img src="http://twolske.bplaced.net/smily.png"/></td>
                </tr>
                <tr>
                    <td><input type="checkbox" value="terms" id="terms" /></td>
                    <td>Ich akzeptiere die Nutzungsbedingungen</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="${message}" /></td>
                    <td></td>
                </tr>
            </table>
        </form>
        </div>
    </div>

    <!-- JS-Libraries requiered for Bootstrap -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>

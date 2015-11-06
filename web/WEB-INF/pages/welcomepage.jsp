<%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 04.11.2015
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>just Live, just Love, justParty</title>
  </head>
  <body>
  <div id='menu_bar'>
    <ul>
      <li><a href='#'><span>Home</span></a></li>
      <li class='sub_menu'><a href='#'><span>Events</span></a>
        <ul>
          <li><a href='#'><span>Event Erstellen</span></a></li>
          <li><a href='#'><span>Events Managen</span></a></li>
          <li><a href='#'><span>Einladungen zu Events</span></a></li>
        </ul>
      </li>
      <li><a href='#'><span>Einstellungen</span></a>
      <ul>
        <li><a href='#'><span>Profil anzeigen</span></a></li>
        <li><a href='#'><span>Ausloggen</span></a></li>
      </ul>
      </li>
    </ul>
  </div>
    <h2>${message}</h2>
    <p>${discription}</p>
  </body>
</html>

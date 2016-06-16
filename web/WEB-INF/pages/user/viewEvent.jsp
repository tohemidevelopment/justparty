<%@ page import="de.tohemi.justparty.datamodel.event.EventType" %>
<%@ page import="de.tohemi.justparty.controller.JPController" %><%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 14.06.2016
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="showguests.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body style="background-color: lightgray;">
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>
<div class="modal fade" id="acceptedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Gästeliste</h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th><spring:message code="showguests.guestlist"/></th>
                        <th><spring:message code="showguests.guestlist.accepted"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${guests}" var="element">
                        <tr>
                            <td>${element.user.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${element.accepted=='ACCEPTED'}">
                                        <spring:message code="guests.table.accept"/>
                                        <span class="glyphicon glyphicon-ok-sign"></span>
                                    </c:when>
                                    <c:when test="${element.accepted=='DECLINED'}">
                                        <spring:message code="guests.table.cancel"/>
                                        <span class="glyphicon glyphicon-remove-sign"></span>
                                    </c:when>
                                    <c:when test="${element.accepted=='NOTSURE'}">
                                        <spring:message code="guests.table.notsure"/>
                                        <span class="glyphicon glyphicon-question-sign"></span>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="container theme-showcase" role="main" >
    <%@include file="../fragments/alerts.jsp"%>
    <table width="800px" align="center" style="background-color: white;">
        <tr>
            <td colspan="2">
                <img src="img/eventHeaders/${eventType}.jpg">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center">
                    <h1>${event.name}</h1>
                    <p>
                        <c:if test="${not empty event.begin}">
                            ${event.begin}
                            <c:if test="${not empty event.end}">
                                - ${event.end}
                            </c:if>
                        </c:if>
                    </p>
                    <p>
                        <c:choose>
                            <c:when test="${not empty event.eventOwner.firstName}">
                                ${event.eventOwner.firstName}
                                <c:if test="${not empty event.eventOwner.lastName}">
                                    ${event.eventOwner.lastName}
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                ${event.eventOwner.email}
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <%--<p>${event.begin} - ${event.end}</p>--%>
                    <%--<p>${event.eventOwner.name}</p>--%>
                </div>
            </td>
        </tr>
        <tr>
            <td width="50%">
                <c:if test="${event.eventOwner.email != currentUser}">
                    <select class="form-control" id="select_${event.id}" onchange="sendInvitationAnswer(${event.id})" style="width: 200px; margin: auto;">
                        <c:if test="${empty acceptedStatus}">
                            <option name="nothingselected" selected="selected">
                                <spring:message code="manager.table.select.nothingselected"/>
                            </option>
                        </c:if>
                        <option ${acceptedStatus == "ACCEPTED" ? "selected='selected'": "" }
                                name="ACCEPTED">
                            <spring:message code="manager.table.select.accept"/>
                        </option>
                        <option ${acceptedStatus == "DECLINED" ? "selected='selected'": "" }
                                name="DECLINED">
                            <spring:message code="manager.table.select.cancel"/>
                        </option>
                        <option ${acceptedStatus == "NOTSURE" ? "selected='selected'": "" }
                                name="NOTSURE">
                            <spring:message code="manager.table.select.notsure"/>
                        </option>
                    </select>
                </c:if>
            </td>
            <td width="50%"></td>
        </tr>
        <tr>
            <td style="padding: 20px"><p>${event.description}</p></td>
            <td style="padding: 20px">
                <a href="#" data-toggle="modal" data-target="#acceptedModal">
                    <h2> ${numberAccepted} Zusagen</h2>
                    <h2>${numberNotSure} unsicher</h2>
                    <h2>${numberInvited} eingeladen</h2>
                </a>
            </td>
        </tr>

        <c:if test="${not empty event.location.address.city}">
            <tr>
                <td colspan="2" text-align="center">
                    <c:choose>
                        <c:when test="${not empty event.location.address.city}">
                            <iframe width="800" height="200" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=${event.location.address.street}%20${event.location.address.houseNumber}%20${event.location.address.zipCode}%20${event.location.address.city}%20${event.location.address.country}&key=AIzaSyC8Yejo3tt37xpLBUz5pMAtbStrRJSHso0" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise>
                            <b>${event.location.name}</b><br>
                            ${event.location.address.street} ${event.location.address.houseNumber} <br>
                            ${event.location.address.zipCode.zipInt} ${event.location.address.city} <br>
                            ${event.location.address.country} <br>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:if>
        <tr>
            <td style="text-align: center; padding:20px">
                <c:if test="${not empty event.spotifyPlaylistLink}">
                    <a href="${event.spotifyPlaylistLink}"><img src="img/logos/spotify.png"></a>
                </c:if>
            </td>
            <td style="text-align: center; padding:20px">
                <c:if test="${not empty event.wishlistLink}">
                    <a href="${event.wishlistLink}" class="btn btn-default"><spring:message code="editevent.link.amazon"/></a>
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center; padding:20px">
                <c:if test="${not empty event.facebookLink}">
                    <a href="${event.facebookLink}"><img src="img/logos/fb.png"></a>
                </c:if>
            </td>
            <td style="text-align: center; padding:20px">
                <c:if test="${not empty event.googlePlusLink}">
                    <a href="${event.googlePlusLink}"><img src="img/logos/gp.png"></a>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <div class="panel panel-primary">
                    <div class="panel-heading">Dinge, die du mitbringen musst:</div>
                    <div class="panel-body" style="min-height:400px; max-height:400px;overflow-y: scroll;">
                        <table class="table table-hover">
                            <c:forEach items="${event.declaration}" var="element">
                                <c:choose>
                                    <c:when test="${element.bringWithByAll}">
                                        <tr>
                                            <td colspan="2">${element.name}</td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${element.user.email == currentUser}">
                                        <tr>
                                            <td>${element.name}</td>
                                            <td><a href="/freePrep?id=${element.id}" class ="btn btn-default">Freigeben</a></td>
                                        </tr>
                                    </c:when>
                                </c:choose>

                            </c:forEach>
                        </table>
                    </div>
                </div>
            </td>
            <td>
                <div class="panel panel-primary">
                    <div class="panel-heading">Dinge, die einer mitbringen muss:</div>
                    <div class="panel-body" style="min-height:400px; max-height:400px;overflow-y: scroll;">
                        <table class="table table-hover">
                            <c:forEach items="${event.declaration}" var="element">
                                <c:if test="${not element.bringWithByAll && empty element.user.email}">
                                    <tr>
                                        <td>${element.name}</td>
                                        <td><a href="/bringPrep?id=${element.id}" class ="btn btn-default">Mitbringen</a></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </table>


</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
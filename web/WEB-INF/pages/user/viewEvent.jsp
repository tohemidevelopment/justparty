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
<body>
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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="showguests.header"/></h1>
    </div>
    <%@include file="../fragments/alerts.jsp"%>
    <table class="table table-hover" width="800px">
        <tr>
            <td colspan="2">
                <img src="/resources/img/eventHeaders/${event.eventType}.jpg">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div align="center">
                    <h1>${event.name}</h1>
                    <p></p>
                    <p>${event.eventOwner.name}</p>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <select class="form-control" id="select_${event.id}" onchange="sendInvitationAnswer(${event.id})">
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
            </td>
            <td></td>
        </tr>
        <tr>
            <td><p>${event.description}</p></td>
            <td>
                <a href="#" data-toggle="modal" data-target="#acceptedModal">
                    <h2> ${numberAccepted} Zusagen</h2>
                    <h2>${numberNotSure} unsicher</h2>
                    <h2>${numberInvited} eingeladen</h2>
                </a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <iframe width="800" height="200" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=${event.location.name}%20${event.location.address.street}%20${event.location.address.houseNumber}%20${event.location.address.zipCode}%20${event.location.address.city}%20${event.location.address.country}&key=AIzaSyC8Yejo3tt37xpLBUz5pMAtbStrRJSHso0" allowfullscreen></iframe>
            </td>
        </tr>
        <tr>
            <td>
                <a href="${event.spotifyPlaylistLink}"><img src="/resources/img/eventHeaders/spotify.png"></a>
            </td>
            <td>
                <a href="${event.amazonWishlistLink}" class="btn btn-default"><spring:message code="editevent.link.amazon"/></a>
            </td>
        </tr>
    </table>


</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 02.12.2015
  Time: 11:47
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

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="showguests.header"/></h1>
    </div>
    <%@include file="../fragments/alerts.jsp"%>

    <table class="table table-hover">
        <tr>
            <td><label><spring:message code="editevent.general.eventname"/></label></td>
            <td colspan="2"><p>${event.name}</p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.general.ort"/></label></td>
            <td><p>
                <%@include file="../fragments/displayLocation.jsp" %>
            </p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.general.zeit.begin"/></label></td>
            <td colspan="2"><p>${event.begin}</p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.general.zeit.ende"/></label></td>
            <td colspan="2"><p>${event.end}</p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.general.beschreibung"/></label></td>
            <td colspan="2"><p>${event.description}</p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.link.spotify"/></label></td>
            <td colspan="2"><p>${event.spotifyPlaylistLink}</p></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.link.amazon"/></label></td>
            <td colspan="2"><p>${event.wishlistLink}</p></td>
        </tr>
        <tr>
            <td><label for="jeder"><spring:message code="eventdata.everybody"/></label></td>
            <td colspan="3">
                <select multiple class="form-control" id="jeder">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="einer"><spring:message code="eventdata.onlyone"/></label></td>
            <td colspan="3">
                <select multiple class="form-control" id="einer">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </td>
        </tr>

    </table>
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

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
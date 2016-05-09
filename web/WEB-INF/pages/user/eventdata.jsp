<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 05.05.2016
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="editevent.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body>
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="eventdata.header"/> </h1>
    </div>
    <!-- alerts -->
    <div>
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="${alert_danger}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-warning" role="alert">
                <spring:message code="${alert_warning}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_success}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${alert_success}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_info}">
            <div class="alert alert-info" role="alert">
                <spring:message code="${alert_info}"/>
            </div>
        </c:if>
    </div>
<table class="table table-hover">
    <tr>
        <td><label for="eventname"><spring:message code="editevent.general.eventname"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="eventname" onchange="updateEventData('eventname');" value="${event.name}"></td>
    </tr>
    <tr>
        <td><label for="location"><spring:message code="editevent.general.ort"/></label></td>
        <td><input type="text" class="form-control" id="location" onchange="updateEventData('location');" value="${event.location}"></td>
    </tr>
    <tr>
        <td><label><spring:message code="editevent.general.zeit.begin"/></label></td>
        <td colspan="2">
            <div class='input-group datetime' id='datetimepicker1'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                <input type='datetime-local' class="form-control" id="begin" onchange="updateEventData('begin');" value="${event.begin}"/>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker1').datetimepicker();
                });
            </script>
        </td>
    </tr>
    <tr>
        <td><label><spring:message code="editevent.general.zeit.ende"/></label></td>
        <td colspan="2">
            <div class='input-group datetime' id='datetimepicker2'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                <input id="end" type='datetime-local' class="form-control" onchange="updateEventData('end');" value="${event.end}"/>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker2').datetimepicker();
                });
            </script>
        </td>
    </tr>
    <%--<tr>
        <td><label for="art"><spring:message code="editevent.general.art"/></label></td>
        <td><input type="text" class="form-control" id="art"></td>
        <td><button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#art_event"><spring:message code="editevent.general.button2"/></button></td>
    </tr>--%>
    <tr>
        <td><label for="description"><spring:message code="editevent.general.beschreibung"/></label></td>
        <td colspan="2"> <textarea class="form-control" rows="5" id="description" onchange="updateEventData('description');">${event.description}</textarea>
        </td>
    </tr>

    <tr>
        <td><label for="guestlist"><spring:message code="eventdata.guestlist"/></label></td>
        <td colspan="2">
            <select multiple class="form-control" id="guestlist">
                <c:forEach items="${event.guests}" var="element">
                    <option>${element.user.email}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
    </tr>
    <tr>
        <td><label for="spotify"><spring:message code="editevent.link.spotify"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="spotify"></td>
    </tr>
    <tr>
        <td><label for="amazon"><spring:message code="editevent.link.amazon"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="amazon"></td>
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
</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 02.12.2015
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="editevent.eventtype.title"/></title>
    <%@include file="../../fragments/head.jsp" %>
</head>
<body>
<%@include file="../../fragments/logoutform.jsp" %>
<%@include file="../../fragments/navbar.jsp" %>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="editevent.eventtype.header"/></h1>
    </div>
    <%@include file="../../fragments/alerts.jsp" %>
    <form action="/edit?" role="form" class="form">
        <div class="form-group">
            <label for="eventtype"><spring:message code="editevent.general.eventtype"/></label>
            <select class="form-control" id="eventtype" name="eventtype" onchange="updateEventData('eventtype');">
            </select>
        </div>
        <input type="hidden" value="${event.id}" name="id"/>
        <input class="form-control" type="submit" value="<spring:message code="nav.next"/> ">
    </form>
</div>
<%@include file="../../fragments/jslibs.jsp" %>
</body>
</html>
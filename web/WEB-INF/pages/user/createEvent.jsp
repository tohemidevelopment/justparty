<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="create.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body>
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h2><spring:message code="create.header1"/></h2>
    </div>
    <%@include file="../fragments/alerts.jsp"%>

    <form method="post" action="/createEvent" role="form" id="createEvent_form">
        <div class="form-group">
            <label for="name">Name: </label>
            <input type="text" class="form-control" name="eventname" id="name" placeholder="Eventname" autofocus="autofocus">
        </div>
        <div class="form-group">
            <input class="form-control" name="submit" type="submit" value="Event erstellen"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>

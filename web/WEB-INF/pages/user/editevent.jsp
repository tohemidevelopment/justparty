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
    <title><spring:message code="editevent.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body>
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="editevent.header"/> </h1>
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
<form action="">
    <div>
        <ul class="nav nav-tabs" role="tablist">
            <li id="general" role="presentation" class="active"><a href="javascript:showGeneral()"><spring:message
                    code="editevent.nav.general"/></a></li>
            <li id="links" role="presentation"><a href="javascript:showLinks()"><spring:message
                    code="editevent.nav.links"/></a></li>
            <li id="bringwith" role="presentation"><a href="javascript:showBringWith()"><spring:message
                    code="editevent.nav.bringwith"/></a></li>
            <li id="guestlist" role="presentation"><a href="javascript:showGuestlist()"><spring:message
                    code="editevent.nav.guestlist"/></a></li>
        </ul>
        <div id="general_text" style="display: block;"><%@include file="../fragments/editEvent/general.jsp"%></div>
        <div id="link_text" style="display: none;"><%@include file="../fragments/editEvent/link.jsp"%></div>
        <div id="bringwith_text" style="display: none;"><%@include file="../fragments/editEvent/bringwith.jsp"%></div>
        <div id="guestlist_text" style="display: none;"><%@include file="../fragments/editEvent/guestlist.jsp"%></div>
    </div>
    <button type="submit" class="btn btn-primary btn-block" onclick="sendEventDataChanges(${event.id});"><spring:message code="editevent.button.save"/></button>
    <button type="button" class="btn btn-default btn-block" onclick="location.href='/manageEvent'"><spring:message code="editevent.button.back"/></button>
</form>
</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
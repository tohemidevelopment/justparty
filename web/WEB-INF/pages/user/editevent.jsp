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

    <div>
        <table class="table header-fixed">
            <thead>
            <tr>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="javascript:showGeneral()"><spring:message
                            code="editevent.nav.general"/></a></li>

                        <li role="presentation"><a href="javascript:showLinks()"><spring:message
                                code="editevent.nav.links"/></a></li>
                        <li role="presentation"><a href="javascript:showBringWith()"><spring:message
                                code="editevent.nav.bringwith"/></a></li>
                        <li role="presentation"><a href="javascript:showGuestlist()"><spring:message
                                code="editevent.nav.guestlist"/></a></li>
                </ul>
            </tr>
            </thead>
            <tbody>
                //TODO: FIll With Content :D
            </tbody>
        </table>
    </div>

</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
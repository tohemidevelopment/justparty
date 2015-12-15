<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 23.11.2015
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="${info_title}"/></title>
    <%@include file="fragments/head.jsp"%>
</head>
<body>
<%@include file="fragments/logoutform.jsp"%>
<%@include file="fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="${info_header}"/></h1>
    </div>
    <div class="alert alert-info" role="alert">
        <c:if test="${not empty info_box_strong}">
            <strong><spring:message code="${info_box_strong}"/></strong>
        </c:if>
        <spring:message code="${info_box}"/>
    </div>
</div>

<%@include file="fragments/jslibs.jsp"%>
</body>
</html>

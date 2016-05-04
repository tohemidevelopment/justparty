<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 17.11.2015
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="error.title"/></title>
    <%@include file="fragments/head.jsp"%>
</head>
<body>
<%@include file="fragments/logoutform.jsp"%>
<%@include file="fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="error.header1"/></h1>
    </div>
    <div class="alert alert-danger">
        <c:if test="${not empty param.code}">
            <strong>
                <c:choose>
                    <c:when test="${param.code==404}">
                        Die Seite wurde nicht gefunden.
                    </c:when>
                </c:choose>
            </strong>
        </c:if>
        <spring:message code="error.para1"/>
    </div>
</div>

<%@include file="fragments/jslibs.jsp"%>
</body>
</html>

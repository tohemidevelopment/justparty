<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 18.04.2015
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="inviteUser.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body>
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main" >
    <div class="page-header">
        <h1><spring:message code="inviteUser.ueberschrift"/></h1>
    </div>
    <%@include file="../fragments/alerts.jsp"%>

//TODO:Insert a Textarea/Textfield with label( and help)?
</div>

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
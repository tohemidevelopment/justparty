<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 23.11.2015
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="info.title"/> </title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="${info_header}"/> </h1>
    </div>
    <div class="alert alert-info" role="alert">
        <spring:message code="${info_box}"/>

    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 17.11.2015
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/res/style.css">
    <title>Error</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="error.header1"/></h1>
    </div>
    <div class="alert alert-danger"><spring:message code="error.para1"/> </div>
</div>
</body>
</html>

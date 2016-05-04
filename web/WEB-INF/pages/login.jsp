<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="login.title"/></title>
    <%@include file="fragments/head.jsp"%>
</head>

<body>
<%@include file="fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1 id="test"><spring:message code="login.header1"/></h1>
    </div>
    <c:if test="${not empty login_error}">
        <div class="alert alert-danger" role="alert">
                ${login_error}
        </div>
    </c:if>
    <%@include file="fragments/alerts.jsp"%>

    <form method="post" action="/perform_login" role="form" id="login_form">
        <div class="form-group">
            <label for="usr"><spring:message code="login.label.username"/>:</label>
            <input type="text" class="form-control" name="username" id="usr"
                   placeholder="<spring:message code="login.placeholder.username"/>"  autofocus="autofocus">
        </div>
        <div class="form-group">
            <label for="pwd"><spring:message code="login.label.pw"/>:</label>
            <input type="password" class="form-control" id="pwd" name="password"
                   placeholder="<spring:message code="login.placeholder.pw"/>">
        </div>
        <input class="form-control" name="submit" type="submit" value="<spring:message code="login.login"/>"/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <spring:message code="login.havenoaccount"/><br>
    <a href="/register">
        <button class="btn btn-default">
            <spring:message code="welcome.registerbtn"/>
        </button>
    </a>
</div>
<%@include file="fragments/jslibs.jsp"%>
</body>
</html>

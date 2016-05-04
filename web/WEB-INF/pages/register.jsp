<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="register.title"/></title>
    <%@include file="fragments/head.jsp" %>
</head>
<body>
<%@include file="fragments/navbar.jsp" %>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="register.header1"/></h1>
    </div>
    <%@include file="fragments/alerts.jsp" %>

    <div>
        <form method="post" action="/register" role="form" class="form" id="register_form">
            <div class="form-group ${not empty EMAIL?'has-error has-feedback':''}">
                <%--
                <label for="email" class="control-label col-sm-2"><spring:message code="register.label.email"/>:</label>
                --%>
                <div class="input-group">
                    <span class="input-group-addon">@</span>
                    <input type="text" class="form-control" name="email" id="email"
                           placeholder="<spring:message code="register.placeholder.email"/>" autofocus="autofocus"
                           aria-describedby="error_email">
                </div>
                <c:if test="${not empty EMAIL}">
                    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                    <span name="error_email" id="error_email" class="help-block">
                        <spring:message code="${EMAIL}"/>
                    </span>
                </c:if>
            </div>
            <div class="form-group ${not empty PASSWORD?'has-error has-feedback':''}">
                <%--
                <label for="pwd" class="control-label col-sm-2"><spring:message code="register.label.pw"/>:</label>
                --%>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-tower"></span> </span>
                    <input type="password" class="form-control" id="pwd" name="password"
                           placeholder="<spring:message code="register.placeholder.pw"/>"
                           aria-describedby="error_password">
                </div>
                <c:if test="${not empty PASSWORD}">
                    <span class="glyphicon glyphicon-flash form-control-feedback" aria-hidden="true"></span>
                    <span name="error_password" id="error_password" class="help-block">
                        <spring:message code="${PASSWORD}"/>
                    </span>
                </c:if>
            </div>
            <div class="form-group ${not empty PASSWORD?'has-error has-feedback':''}">
                <%--
                <label for="pwd2" class="control-label col-sm-2"><spring:message
                        code="register.label.pw.repeat"/>:</label>
                        --%>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-repeat"></span> </span>
                    <input type="password" class="form-control" id="pwd2" name="password_repeat"
                           placeholder="<spring:message code="register.placeholder.pw.repeat"/>">
                </div>
                <c:if test="${not empty PASSWORD}">
                    <span class="glyphicon glyphicon-flash form-control-feedback" aria-hidden="true"></span>
                </c:if>
            </div>

            <div class="form-group ${not empty TERMS?'has-error':''}">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="terms" checked aria-describedby="error_terms">
                        <spring:message code="register.label.acceptterms"/>
                    </label>
                </div>
                <c:if test="${not empty TERMS}">
                    <span name="error_terms" id="error_terms" class="help-block">
                        <spring:message code="${TERMS}"/>
                    </span>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col-sm-3">
                    <input class="form-control" name="submit" type="submit"
                           value="<spring:message code="welcome.registerbtn"/> "/>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</div>

<%@include file="fragments/jslibs.jsp" %>
</body>
</html>
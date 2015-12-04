<!--TODO: Optimize Navbar-->
<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="manager.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/style.css">
</head>
<body>
<form action="/j_spring_security_logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                justParty
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Suchen...">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Events <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href='${pageContext.request.contextPath}/createEvent'><span>Event Erstellen</span></a>
                        </li>
                        <li><a href='${pageContext.request.contextPath}/manageEvent'><span>Events Managen</span></a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Einstellungen <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:logoutFormSubmit()"><span>Logout</span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="manager.header1"/></h1>
    </div>
    <!-- alerts -->
    <div>
        <c:if test="${not empty alert_danger}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${alert_danger}"/>
            </div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-success" role="alert">
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

    <table class="table header-fixed">
        <thead>
        <tr>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="javascript:showCurrentEvents()"><spring:message
                        code="manager.nav.current"/></a></li>
                <!--
                    <li role="presentation"><a href="javascript:showInvitations()"><spring:message
                            code="manager.nav.invites"/></a></li>
                    <li role="presentation"><a href="javascript:showOwnedEvents()"><spring:message
                            code="manager.nav.owned"/></a></li>
                    <li role="presentation"><a href="javascript:showCanceldEvents()"><spring:message
                            code="manager.nav.canceled"/></a></li>
                    <li role="presentation"><a href="javascript:showPastEvents()"><spring:message
                            code="manager.nav.past"/></a></li>-->
            </ul>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${currentevents}" var="element">
            <tr>
                <td class="action">
                    <c:choose>
                        <c:when test="${element.hosted}">
                            <form class="hidden" id="delete_${element.id}" method="post" action="/delete">
                                <input type="hidden" name="id" value="${element.id}">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>
                            <a href="/edit?id=${element.id}" class="btn"><span class="glyphicon glyphicon-pencil"/></a>
                            <a href="javascript:submitForm('delete_${element.id}')" class="btn"><span
                                    class="glyphicon glyphicon-trash"/> </a>
                        </c:when>
                        <c:otherwise>
                            <select class="form-control" id="select_${element.id}">
                                <c:if test="${empty element.accepted}">
                                    <option selected="selected">
                                        <spring:message code="manager.table.select.nothingselected"/>
                                    </option>
                                </c:if>
                                <option ${element.accepted == "ACCEPTED" ? "selected='selected'": "" }
                                        class="select-accept">
                                    <spring:message code="manager.table.select.accept"/>
                                </option>
                                <option ${element.accepted == "DECLINED" ? "selected='selected'": "" }
                                        class="select-cancel">
                                    <spring:message code="manager.table.select.cancel"/>
                                </option>
                                <option ${element.accepted == "NOTSURE" ? "selected='selected'": "" }
                                        class="select-notsure">
                                    <spring:message code="manager.table.select.notsure"/>
                                </option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="name">
                        ${element.name}
                </td>
                <td class="date">
                        ${element.date}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- JS-Libraries requiered for Bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- JS-Libraries requiered for justParty -->
<script src="${pageContext.request.contextPath}/res/js/JPforms.js"></script>
<script src="${pageContext.request.contextPath}/res/js/JPevents.js"></script>
</body>
</html>
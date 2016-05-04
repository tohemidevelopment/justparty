<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 07.11.2015
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="manager.title"/></title>
    <%@include file="../fragments/head.jsp"%>
</head>
<body>
<%@include file="../fragments/logoutform.jsp"%>
<%@include file="../fragments/navbar.jsp"%>

<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1><spring:message code="manager.header1"/></h1>
    </div>
    <%@include file="../fragments/alerts.jsp"%>

    <table class="table header-fixed">
        <thead>
        <tr>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="javascript:showCurrentEvents()"><spring:message
                        code="manager.nav.current"/></a></li>
                <%--
                    <li role="presentation"><a href="javascript:showInvitations()"><spring:message
                            code="manager.nav.invites"/></a></li>
                    <li role="presentation"><a href="javascript:showOwnedEvents()"><spring:message
                            code="manager.nav.owned"/></a></li>
                    <li role="presentation"><a href="javascript:showCanceldEvents()"><spring:message
                            code="manager.nav.canceled"/></a></li>
                    <li role="presentation"><a href="javascript:showPastEvents()"><spring:message
                            code="manager.nav.past"/></a></li>
                            --%>
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
                            <a href="/guests?id=${element.id}" class="btn"><span class="glyphicon glyphicon-list"></span> </a>
                            <a href="/edit?id=${element.id}" class="btn"><span class="glyphicon glyphicon-pencil"/></a>
                            <a href="javascript:submitForm('delete_${element.id}')" class="btn"><span
                                    class="glyphicon glyphicon-trash"/> </a>

                        </c:when>
                        <c:otherwise>
                            <select class="form-control" id="select_${element.id}" onchange="sendInvitationAnswer(${element.id})">
                                <c:if test="${empty element.accepted}">
                                    <option name="nothingselected" selected="selected">
                                        <spring:message code="manager.table.select.nothingselected"/>
                                    </option>
                                </c:if>
                                <option ${element.accepted == "ACCEPTED" ? "selected='selected'": "" }
                                        name="ACCEPTED">
                                    <spring:message code="manager.table.select.accept"/>
                                </option>
                                <option ${element.accepted == "DECLINED" ? "selected='selected'": "" }
                                        name="DECLINED">
                                    <spring:message code="manager.table.select.cancel"/>
                                </option>
                                <option ${element.accepted == "NOTSURE" ? "selected='selected'": "" }
                                        name="NOTSURE">
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

<%@include file="../fragments/jslibs.jsp"%>
</body>
</html>
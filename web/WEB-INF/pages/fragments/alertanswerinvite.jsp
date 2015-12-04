<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 04.12.2015
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${show_alert}">
        <div class="alert alert-warning" role="alert">
            <spring:message code="alert.warning.answerinvite"/>
        </div>
</c:if>
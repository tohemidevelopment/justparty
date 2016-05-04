<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 15.12.2015
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty alert_danger}">
  <div class="alert alert-danger" role="alert">
    <spring:message code="${alert_danger}"/>
  </div>
</c:if>
<c:if test="${not empty alert_warning}">
  <div class="alert alert-warning" role="alert">
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
<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 15.12.2015
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<form action="/j_spring_security_logout" method="post" id="logoutForm">
  <input type="hidden" name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>
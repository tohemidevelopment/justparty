<%--
  Created by IntelliJ IDEA.
  User: xce35l7
  Date: 15.12.2015
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
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
      <form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath}" method="get">
        <div class="form-group">
          <input type="text" class="form-control"
                 placeholder="<spring:message code="nav.searchplaceholder"/>">
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                 aria-haspopup="true"
                 aria-expanded="false"><spring:message code="nav.events"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href='${pageContext.request.contextPath}/createEvent'><span><spring:message
                        code="nav.events.create"/> </span></a>
                </li>
                <li><a href='${pageContext.request.contextPath}/manageEvent'><span><spring:message
                        code="nav.events.manage"/> </span></a>
                </li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                 aria-haspopup="true"
                 aria-expanded="false"><spring:message code="nav.settings"/> <span
                      class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="javascript:submitForm('logoutForm')"><span><spring:message
                        code="nav.logout"/> </span></a></li>
              </ul>
            </li>
          </c:when>
          <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/register"><spring:message
                    code="nav.register"/></a></li>
            <li><a href="${pageContext.request.contextPath}/login"><spring:message code="nav.login"/></a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>

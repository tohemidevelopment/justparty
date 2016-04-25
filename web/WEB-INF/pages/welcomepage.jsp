<%--
  Created by IntelliJ IDEA.
  User: Heiko
  Date: 04.11.2015
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-4" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="welcome.title"/></title>
    <%@include file="fragments/head.jsp"%>
</head>
<body>
<%@include file="fragments/logoutform.jsp"%>
<%@include file="fragments/navbar.jsp"%>
<div class="container theme-showcase" role="main">
    <div class="page-header" style="border:0; text-align: center;">
        <h1><spring:message code="welcome.header1"/></h1>
    </div>
    <%@include file="fragments/alerts.jsp"%>
</div>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="mid" src="<spring:message code="welcome.car.1.imgsrc"/>" alt="justParty">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.1.header"/></h3>

                <p><spring:message code="welcome.car.1.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.2.imgsrc"/>" alt="planen">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.2.header"/></h3>

                <p><spring:message code="welcome.car.2.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.3.imgsrc"/>" alt="Funktion 3">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.3.header"/></h3>

                <p><spring:message code="welcome.car.3.txt"/></p>
            </div>

        </div>

        <div class="item">
            <img class="mid" src="<spring:message code="welcome.car.4.imgsrc"/>" alt="Funktion 4">

            <div class="carousel-caption">
                <h3><spring:message code="welcome.car.4.header"/></h3>

                <p><spring:message code="welcome.car.4.txt"/></p>
            </div>

        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="container theme-showcase" role="main">
    <div id="about">
        <a class="btn btn-lg btn-link" href="#about">
            <h2>
                <spring:message code="nav.whatis"/>
            </h2>
        </a>
    </div>
    <div class="container" id="about-div">
        <spring:message code="welcome.whatis.description"/>
    </div>
</div>
<%@include file="fragments/jslibs.jsp"%>
</body>
</html>
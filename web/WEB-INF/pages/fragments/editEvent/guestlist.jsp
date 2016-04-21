<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 18.04.2016
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover">
    <tr>
    <td colspan="2"><h1><spring:message code="editevent.guestlist.title"/></h1></td>
    </tr>
    <tr>
        <td colspan="2">
            <select multiple class="form-control">
                <option>Testuser</option>
                <option>Via Backend hier einfügen</option>
                <option>Mit Datenbank abfrage</option>
                <option>4</option>
                <option>5</option>
            </select>
        </td>
    </tr>
    <tr>
        <td><button type="button" class="btn btn-primary btn-block"><spring:message code="editevent.guestlist.button1"/></button></td>
        <td><button type="button" class="btn btn-default btn-block"><spring:message code="editevent.guestlist.button2"/></button></td>
    </tr>
</table>

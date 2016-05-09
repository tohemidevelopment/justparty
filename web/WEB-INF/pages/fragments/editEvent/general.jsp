<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 18.04.2016
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover">
    <tr>
        <td><label for="eventname"><spring:message code="editevent.general.eventname"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="eventname" onchange="updateEventData('eventname');"
                               value="${event.name}"></td>
    </tr>
    <tr>
        <td><label for="location"><spring:message code="editevent.general.ort"/></label></td>
        <c:set var="location" value="${event.location}"/>
        <td><input type="text" class="form-control" id="location" onchange="updateEventData('location');"
                   value="<%@include file="../displayLocation.jsp"%>"></td>
        <td>
            <button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#find_ort">
                <spring:message code="editevent.general.button1"/></button>
        </td>
    </tr>
    <tr>
        <td><label><spring:message code="editevent.general.zeit.begin"/></label></td>
        <td colspan="2">
            <div class='input-group datetime' id='datetimepicker1'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                <input type='datetime-local' class="form-control" id="begin" onchange="updateEventData('begin');"
                       value="<fmt:formatDate value='${event.begin}' type='BOTH' pattern="yyyy-MM-dd"/>T<fmt:formatDate value='${event.begin}' type='BOTH' pattern="hh:mm"/>"/>
            </div>
        </td>
    </tr>
    <tr>
        <td><label><spring:message code="editevent.general.zeit.ende"/></label></td>
        <td colspan="2">
            <div class='input-group datetime' id='datetimepicker2'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                <input id="end" type='datetime-local' class="form-control" onchange="updateEventData('end');"
                       value="<fmt:formatDate value='${event.end}' type='BOTH' pattern="yyyy-MM-dd"/>T<fmt:formatDate value='${event.end}' type='BOTH' pattern="hh:mm"/>"/>
            </div>
        </td>
    </tr>
    <%--<tr>
        <td><label for="art"><spring:message code="editevent.general.art"/></label></td>
        <td><input type="text" class="form-control" id="art"></td>
        <td><button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#art_event"><spring:message code="editevent.general.button2"/></button></td>
    </tr>--%>
    <tr>
        <td><label for="description"><spring:message code="editevent.general.beschreibung"/></label></td>
        <td colspan="2"><textarea class="form-control" rows="5" id="description"
                                  onchange="updateEventData('description');">${event.description}</textarea>
        </td>
    </tr>
</table>

<!-- Modal -->
<div class="modal fade" id="find_ort" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel3"><spring:message code="editevent.general.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="name" class="control-label"><spring:message
                                code="editevent.general.modal.ort.name"/></label>
                        <input class="form-control" id="name">
                    </div>
                    <div class="form-group">
                        <label for="street" class="control-label"><spring:message
                                code="editevent.general.modal.ort.street"/></label>
                        <input type="text" class="form-control" id="street">
                    </div>
                    <div class="form-group">
                        <label for="housenumber" class="control-label"><spring:message
                                code="editevent.general.modal.ort.housenumber"/></label>
                        <input type="text" class="form-control" id="housenumber">
                    </div>
                    <div class="form-group">
                        <label for="zip" class="control-label"><spring:message
                                code="editevent.general.modal.ort.zip"/></label>
                        <input type="text" class="form-control" id="zip">
                    </div>
                    <div class="form-group">
                        <label for="city" class="control-label"><spring:message
                                code="editevent.general.modal.ort.city"/></label>
                        <input type="text" class="form-control" id="city">
                    </div>
                    <div class="form-group">
                        <label for="land" class="control-label"><spring:message
                                code="editevent.general.modal.ort.land"/></label>
                        <input type="text" class="form-control" id="land">
                    </div>
                    <div class="form-group">
                        <label for="result" class="control-label"><spring:message
                                code="editevent.general.modal.ort.result"/></label>
                        <textarea type="text" class="form-control" id="result"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.general.modal.ort.button2"/></button>
                <button type="button" class="btn btn-primary"><spring:message
                        code="editevent.general.modal.ort.button1"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="art_event" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel4"><spring:message code="editevent.guestlist.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name4" class="control-label"><spring:message
                                code="editevent.guestlist.modal.email"/></label>
                        <input type="text" class="form-control" id="recipient-name4">
                    </div>
                    <div class="form-group">
                        <label for="message-text4" class="control-label"><spring:message
                                code="editevent.guestlist.modal.nachricht"/></label>
                        <textarea class="form-control" id="message-text4"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.guestlist.modal.button1"/></button>
                <button type="button" class="btn btn-primary"><spring:message
                        code="editevent.guestlist.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>
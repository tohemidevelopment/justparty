<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 18.04.2016
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover">
    <thead>
    <tr>
        <td colspan="2"><h3><spring:message code="editevent.guestlist.title"/></h3></td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="2">
            <select id="guestlistselect" multiple class="form-control">
                <c:forEach items="${event.guests}" var="guest">
                    <option>${guest.user.email}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#newguest">
                <spring:message code="editevent.guestlist.button1"/></button>
        </td>
        <td>
            <button type="button" class="btn btn-default btn-block"><spring:message
                    code="editevent.guestlist.button2"/></button>
        </td>
    </tr>
    </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="newguest" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"><spring:message
                        code="editevent.guestlist.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="recipient-name" class="control-label"><spring:message
                            code="editevent.guestlist.modal.email"/></label>
                    <input type="text" class="form-control" id="recipient-name">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.guestlist.modal.button1"/></button>
                <button type="button" class="btn btn-primary"
                        onclick="invitePerson('recipient-name', 'message-text',${event.id})">
                    <spring:message
                            code="editevent.guestlist.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>


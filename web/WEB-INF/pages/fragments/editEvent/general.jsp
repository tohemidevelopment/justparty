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
            <td><label for="name"><spring:message code="editevent.general.eventname"/></label></td>
            <td colspan="2"><input type="text" class="form-control" id="name" value="${event.name}"></td>
        </tr>
        <tr>
            <td><label for="ort"><spring:message code="editevent.general.ort"/></label></td>
            <td><input type="text" class="form-control" id="ort" value="${event.location}"></td>
            <td><button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#find_ort"><spring:message code="editevent.general.button1"/></button></td>
        </tr>
        <tr>
            <td><label><spring:message code="editevent.general.zeit.begin"/></label></td>
            <td colspan="2">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" value="${event.begin}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <script type="text/javascript">
                    $(function () {
                        $('#datetimepicker1').datetimepicker();
                    });
                </script>
            </td>
            </tr>
        <tr>
            <td><label><spring:message code="editevent.general.zeit.ende"/></label></td>
            <td colspan="2">
                <div class='input-group date' id='datetimepicker2'>
                    <input type='text' class="form-control"  value="${event.end}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <script type="text/javascript">
                    $(function () {
                        $('#datetimepicker2').datetimepicker();
                    });
                </script>
            </td>
        </tr>
        <tr>
            <td><label for="art"><spring:message code="editevent.general.art"/></label></td>
            <td><input type="text" class="form-control" id="art"></td>
            <td><button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#art_event"><spring:message code="editevent.general.button2"/></button></td>
        </tr>
        <tr>
            <td><label for="comment"><spring:message code="editevent.general.beschreibung"/></label></td>
            <td colspan="2"> <textarea class="form-control" rows="5" id="comment">${event.description}</textarea>
            </td>
        </tr>
    </table>

<!-- Modal -->
<div class="modal fade" id="find_ort" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel3"><spring:message code="editevent.guestlist.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name3" class="control-label"><spring:message code="editevent.guestlist.modal.email"/></label>
                        <input type="text" class="form-control" id="recipient-name3">
                    </div>
                    <div class="form-group">
                        <label for="message-text3" class="control-label"><spring:message code="editevent.guestlist.modal.nachricht"/></label>
                        <textarea class="form-control" id="message-text3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="editevent.guestlist.modal.button1"/></button>
                <button type="button" class="btn btn-primary"><spring:message code="editevent.guestlist.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="art_event" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel4"><spring:message code="editevent.guestlist.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name4" class="control-label"><spring:message code="editevent.guestlist.modal.email"/></label>
                        <input type="text" class="form-control" id="recipient-name4">
                    </div>
                    <div class="form-group">
                        <label for="message-text4" class="control-label"><spring:message code="editevent.guestlist.modal.nachricht"/></label>
                        <textarea class="form-control" id="message-text4"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="editevent.guestlist.modal.button1"/></button>
                <button type="button" class="btn btn-primary"><spring:message code="editevent.guestlist.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>
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
        <td colspan="3"><h3><spring:message code="editevent.bringwith.title1"/></h3>
            <p><spring:message code="editevent.bringwith.text1"/></p></td>
    </tr>
    <tr>
        <td colspan="3">
            <select multiple class="form-control" id="byall">
                <c:forEach items="${event.declaration}" var="decl">
                    <c:if test="${decl.bringWithByAll}">
                        <option>${decl.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#byallmodal">
                <spring:message code="editevent.bringwith.button1"/></button>
        </td>
        <%--<td>--%>
            <%--<button type="button" class="btn btn-default btn-block"><spring:message--%>
                    <%--code="editevent.bringwith.button2"/></button>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#edit">--%>
                <%--<spring:message code="editevent.bringwith.button3"/></button>--%>
        <%--</td>--%>
    </tr>
    <tr>
        <td colspan="3"><h3><spring:message code="editevent.bringwith.title2"/></h3>
            <p><spring:message code="editevent.bringwith.text2"/></p></td>
    </tr>
    <tr>
        <td colspan="3">
            <select multiple class="form-control" id="notbyall">
                <c:forEach var="decl" items="${event.declaration}">
                    <c:if test="${!decl.bringWithByAll}">
                        <option>${decl.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#notbyallmodal">
                <spring:message code="editevent.bringwith.button1"/></button>
        </td>
        <%--<td>--%>
            <%--<button type="button" class="btn btn-default btn-block"><spring:message--%>
                    <%--code="editevent.bringwith.button2"/></button>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<button type="button" class="btn btn-default btn-block" data-toggle="modal" data-target="#edit1">--%>
                <%--<spring:message code="editevent.bringwith.button3"/></button>--%>
        <%--</td>--%>
    </tr>
</table>


<!-- Modal -->
<div class="modal fade" id="byallmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1"><spring:message code="editevent.bringwith.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="byalladd" class="control-label"><spring:message
                                code="editevent.bringwith.modal.name"/></label>
                        <input type="text" class="form-control" id="byalladd">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.bringwith.modal.button1"/></button>
                <button type="button" class="btn btn-primary" onclick="updatePreparations('byall',${event.id})">
                    <spring:message
                            code="editevent.bringwith.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="notbyallmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2"><spring:message code="editevent.bringwith.modal.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="notbyalladd" class="control-label"><spring:message
                                code="editevent.bringwith.modal.name"/></label>
                        <input type="text" class="form-control" id="notbyalladd">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.bringwith.modal.button1"/></button>
                <button type="button" class="btn btn-primary" onclick="updatePreparations('notbyall',${event.id})">
                    <spring:message
                            code="editevent.bringwith.modal.button2"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel3"><spring:message
                        code="editevent.bringwith.modal1.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name2" class="control-label"><spring:message
                                code="editevent.bringwith.modal1.name"/></label>
                        <input type="text" class="form-control" id="recipient-name2">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.bringwith.modal1.button1"/></button>
                <button type="button" class="btn btn-primary"><spring:message
                        code="editevent.bringwith.modal1.button2"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel4"><spring:message
                        code="editevent.bringwith.modal1.title"/></h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name3" class="control-label"><spring:message
                                code="editevent.bringwith.modal1.name"/></label>
                        <input type="text" class="form-control" id="recipient-name3">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="editevent.bringwith.modal1.button1"/></button>
                <button type="button" class="btn btn-primary"><spring:message
                        code="editevent.bringwith.modal1.button2"/></button>
            </div>
        </div>
    </div>
</div>
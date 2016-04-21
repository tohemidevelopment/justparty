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
            <td><label for="name">Name des Events:</label></td>
            <td colspan="2"><input type="text" class="form-control" id="name"></td>
        </tr>
        <tr>
            <td><label for="ort">Ort:</label></td>
            <td><input type="text" class="form-control" id="ort"></td>
            <td><button type="button" class="btn btn-default btn-block">Finden</button></td>
        </tr>
        <tr>
            <td><label>Zeit:</label></td>
            <td colspan="2">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
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
            <td><label for="art">Art des Events:</label></td>
            <td><input type="text" class="form-control" id="art"></td>
            <td><button type="button" class="btn btn-default btn-block">Auswählen</button></td>
        </tr>
        <tr>
            <td><label for="comment">Beschreibung</label></td>
            <td colspan="2"> <textarea class="form-control" rows="5" id="comment"></textarea>
            </td>
        </tr>
    </table>


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
        <td><label for="spotify"><spring:message code="editevent.link.spotify"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="spotify"
                               onchange="updateEventData('spotify');" value="${event.SpotifyPlaylistLink}"/></td>
        <td><button type="button" class="btn btn-default btn-block" onclick="window.open('http://play.spotify.com', '_blank');">Spotify</button></td>
    </tr>
    <tr>
        <td><label for="amazon"><spring:message code="editevent.link.amazon"/></label></td>
        <td colspan="2"><input type="text" class="form-control" id="amazon"
                               onchange="updateEventData('amazon');" value="${event.WishlistLink}"/></td>
        <td><button type="button" class="btn btn-default btn-block" onclick="window.open('https://www.amazon.de/gp/registry/wishlist', '_blank');">Amazon</button></td>
    </tr>
</table>

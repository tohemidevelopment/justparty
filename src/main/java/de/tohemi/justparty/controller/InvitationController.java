package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tom on 18.04.2016.
 */

@org.springframework.stereotype.Controller
public class InvitationController extends JPController {

        @RequestMapping(method = RequestMethod.GET, value = INVITE_USER)
        public String printWelcomePage()
        {
            return LogicalViewNames.getNameInviteUsers();
        }
}

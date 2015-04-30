package com.epam.jmp.gamebox.web.admin.handler;

import com.epam.jmp.gamebox.GameboxContext;
import com.epam.jmp.gamebox.web.action.handler.ActionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefreshDeploymentActionHandler implements ActionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        GameboxContext.getInstance().getGameService().refreshDeployments();

        try {
            response.sendRedirect(request.getServletPath() + "/deployment-list");
        } catch (IOException e) {
        }

    }

}

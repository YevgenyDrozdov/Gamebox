package com.epam.jmp.gamebox.web.servlets;

import com.epam.jmp.gamebox.web.action.dispatcher.ActionDispatcher;
import com.epam.jmp.gamebox.web.action.dispatcher.RESTActionDispatcher;
import com.epam.jmp.gamebox.web.admin.handler.ListOfDeploymentsActionHandler;
import com.epam.jmp.gamebox.web.admin.handler.RefreshDeploymentActionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Administration Servlet", urlPatterns = "/admin/*")
public class AdministrationServlet extends HttpServlet {

    private ActionDispatcher actionDispatcher;

    public AdministrationServlet() {
        initializeActionDispatcher();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionDispatcher.dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private void initializeActionDispatcher() {
        RESTActionDispatcher dispatcher = new RESTActionDispatcher();
        dispatcher.mapAction("/deployment-list", new ListOfDeploymentsActionHandler());
        dispatcher.mapAction("/refresh-deployments", new RefreshDeploymentActionHandler());
        actionDispatcher = dispatcher;
    }

}

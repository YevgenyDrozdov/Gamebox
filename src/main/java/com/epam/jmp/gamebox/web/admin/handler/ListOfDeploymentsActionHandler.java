package com.epam.jmp.gamebox.web.admin.handler;

import com.epam.jmp.gamebox.GameboxContext;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.web.action.handler.ActionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListOfDeploymentsActionHandler implements ActionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListOfDeploymentsActionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        List<DeploymentDescriptor> deployments = GameboxContext.getInstance().getDeploymentService().getAllDeployments();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp//admin/deploymentList.jsp");
        try {
            request.setAttribute("deployments", deployments);
            requestDispatcher.forward(request, response);

        } catch (ServletException e) {
            LOGGER.error("Cannot handle the action", e);
        } catch (IOException e) {
            LOGGER.error("Cannot handle the action", e);
        }
    }

}

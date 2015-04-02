package com.epam.jmp.gamebox.web.servlets;

import com.epam.jmp.gamebox.*;
import com.epam.jmp.gamebox.deploy.*;
import com.epam.jmp.gamebox.impl.*;
import com.epam.jmp.gamebox.war.deploy.WarGameDeployAssistant;
import com.epam.jmp.gamebox.war.deploy.WarXmlDeploymentDescriptorLocator;
import com.epam.jmp.gamebox.web.action.ActionDispatcher;
import com.epam.jmp.gamebox.web.action.ActionHandler;
import com.epam.jmp.gamebox.web.action.RESTActionDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GameboxMainServlet", urlPatterns = "/")
public class GameboxMainServlet extends HttpServlet {

    private GameRepository repository;


    ActionDispatcher dispatcher;

    public GameboxMainServlet() {
        repository = new GameRepositoryImpl(new GameIdGeneratorImpl());
        GameDistributionItemFactory distributionItemFactory = new FileSystemGameDistributionItemFactory();
        GameDeployerConfiguration deployerConfiguration = new SystemPropertiesGameDeployerConfiguration();
        FileSystemGameDeployer deployer = new FileSystemGameDeployer();

        WarXmlDeploymentDescriptorLocator deploymentDescriptorLocator = new WarXmlDeploymentDescriptorLocator();
        DeployAssistant assistant = new WarGameDeployAssistant(deploymentDescriptorLocator);
        Map<GameDistributionType, DeployAssistant> assistants = new HashMap<GameDistributionType, DeployAssistant>();
        assistants.put(GameDistributionType.WAR, assistant);

        deployer.setDeployAssistants(assistants);
        deployer.init(deployerConfiguration, distributionItemFactory);

        List<GameDescriptor> gameDescriptors = deployer.deployAllGames();

        for (GameDescriptor descriptor : gameDescriptors) {
            repository.addDeployedGame(descriptor);
        }

        RESTActionDispatcher restDispatcher = new RESTActionDispatcher();
        restDispatcher.mapAction("/", new ActionHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response) {
                Map<String, GameDescriptor> gameDescriptors = repository.getAllDeployedGames();

                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                }

                out.print("<ul>");
                for (Map.Entry<String, GameDescriptor> entry : gameDescriptors.entrySet()) {

                    String gameId = entry.getKey();
                    GameDescriptor descriptor = entry.getValue();

                    out.print("<li>");
                    out.print(gameId + ": " + descriptor.getGameName() + "[" + descriptor.getControllerClass() + "]");
                    out.print("</li>");
                }
                out.print("</ul>");

                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        });

        dispatcher = restDispatcher;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    /*
        Map<String, GameDescriptor> gameDescriptors = repository.getAllDeployedGames();

        PrintWriter out = resp.getWriter();

        out.print("<ul>");
        for (Map.Entry<String, GameDescriptor> entry : gameDescriptors.entrySet()) {

            String gameId = entry.getKey();
            GameDescriptor descriptor = entry.getValue();

            out.print("<li>");
            out.print(gameId + ": " + descriptor.getGameName() + "[" + descriptor.getControllerClass() + "]");
            out.print("</li>");
        }
        out.print("</ul>");

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
    * */
}

package com.epam.jmp.gamebox.web.servlets;

import com.epam.jmp.gamebox.*;
import com.epam.jmp.gamebox.web.action.dispatcher.ActionDispatcher;
import com.epam.jmp.gamebox.web.action.handler.*;
import com.epam.jmp.gamebox.web.action.dispatcher.RESTActionDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameboxMainServlet", urlPatterns = "/rest/*")
public class GameboxMainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameboxMainServlet.class);

    private ActionDispatcher dispatcher;

    public GameboxMainServlet() {
        LOGGER.debug("Starting Gamebox main servlet.");

        initializeActionDispatcher();
        GameboxContext.getInstance().getGameService().refreshDeployments();

        LOGGER.debug("Gamebox main servlet has started.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private void initializeActionDispatcher() {

        LOGGER.debug("Initializing action dispatcher.");

        RESTActionDispatcher restDispatcher = new RESTActionDispatcher();
        restDispatcher.mapAction("/", new ActionHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/gameList.jsp");
                try {
                    requestDispatcher.forward(request, response);

                } catch (ServletException e) {
                } catch (IOException e) {
                }
            }
        });

        restDispatcher.mapAction("/action/startGame/{" + StartGameActionHandler.GAME_ID_PARAMETER_NAME + "}",
                new StartGameActionHandler(GameboxContext.getInstance().getGameService()));

        restDispatcher.mapAction("/{" + GameActionRouterActionHandler.GAME_ID_PARAMETER_NAME + "}/action/{" +
                GameActionRouterActionHandler.ACTION_ID_PARAMETER_NAME + "}", new GameActionRouterActionHandler());

        restDispatcher.mapAction("/miniature/{" + GetGameMiniatureActionHandler.GAME_ID_PARAMETER_NAME + "}",
                new GetGameMiniatureActionHandler());

        restDispatcher.mapAction("/{" + LoadGameModelActionHandler.GAME_ID_PARAMETER_NAME + "}/loadModel",
                new LoadGameModelActionHandler(GameboxContext.getInstance().getGameService()));

        restDispatcher.mapAction("/games", new GameListActionHandler(GameboxContext.getInstance().getGameService()));

        restDispatcher.mapAction("/{" + RenderActionHandler.GAME_ID_PARAMETER_NAME + "}/render",
                new RenderActionHandler(GameboxContext.getInstance().getGameService()));

        restDispatcher.mapAction("/{" + GameResourceActionHandler.GAME_ID_PARAMETER_NAME + "}/js",
                new GameResourceActionHandler(GameboxContext.getInstance().getGameService()));

        restDispatcher.mapAction("/{" + GameResourceActionHandler.GAME_ID_PARAMETER_NAME + "}/css",
                new GameResourceActionHandler(GameboxContext.getInstance().getGameService()));

        dispatcher = restDispatcher;
    }

}

package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.jmp.gamebox.api.impl.GameActionImpl;
import com.epam.jmp.gamebox.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class GameActionRouterActionHandler implements ActionHandler {

    public static String GAME_ID_PARAMETER_NAME = "game-id";
    public static String ACTION_ID_PARAMETER_NAME = "action-id";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String)request.getAttribute(GAME_ID_PARAMETER_NAME);
        String actionId = (String)request.getAttribute(ACTION_ID_PARAMETER_NAME);

        Session gameSession = WebUtils.getGameSession(request, gameId);

        Action action = createGameAction(actionId, request, gameSession);

        Game game = GameboxContext.getInstance().getGameService().getInstantiatedGameById(gameId);
        GameModel model = getGameModel(request, gameId);
        game.getController().processAction(action, model);
    }

    protected GameModel getGameModel(HttpServletRequest request, String gameId) {
        HttpSession httpSession = request.getSession(false);

        if (httpSession != null) {
            GameModel gameModel = (GameModel)httpSession.getAttribute("gamebox-game-model-" + gameId);
            return gameModel;
        }

        return null;
    }

    protected Action createGameAction(String actionId, HttpServletRequest request, Session gameSession) {
        Map<String, String> actionParameters = new HashMap<String, String>();

        while (request.getParameterNames().hasMoreElements()) {
            String parameterName = request.getParameterNames().nextElement();
            String parameterValue = request.getParameter(parameterName);
            actionParameters.put(parameterName, parameterValue);
        }

        return new GameActionImpl(actionId, actionParameters, gameSession);
    }

}

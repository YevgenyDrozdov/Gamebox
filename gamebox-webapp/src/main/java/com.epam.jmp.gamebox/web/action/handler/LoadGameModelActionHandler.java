package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameModel;
import com.epam.jmp.gamebox.Session;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.web.client.ClientGameModel;
import com.epam.jmp.gamebox.web.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoadGameModelActionHandler implements ActionHandler {

    public static String GAME_ID_PARAMETER_NAME = "game-id";

    private GameService gameService;

    public LoadGameModelActionHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String)request.getAttribute(GAME_ID_PARAMETER_NAME);

        ClientGameModel clientModel = new ClientGameModel();
        Map<String, Object> modelParametersFromController = null;

        Game game = gameService.getInstantiatedGameById(gameId);
        if (game != null) {

            Session session = WebUtils.getGameSession(request, gameId);
            GameModel model = WebUtils.getGameModel(request, gameId);

            modelParametersFromController = game.getController().getClientModel(session, model);
            if (modelParametersFromController != null) {
                clientModel.getAttributes().putAll(modelParametersFromController);
            }
        }

        clientModel.setMiniatureUrl(WebUtils.getServletContextPath(request) + "/miniature/" + gameId);
        clientModel.setStartGameUrl(WebUtils.getServletContextPath(request) + "/action/startGame/" + gameId);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientModel);

        try {
            response.getWriter().append(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }



}

package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.web.client.ClientGameModel;
import com.epam.jmp.gamebox.web.util.WebUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameListActionHandler implements ActionHandler {

    private GameService gameService;

    public GameListActionHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Game> games = gameService.getAllDeployedGames();

        List<ClientGameModel> models = createClientGameModels(request, games);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String listOfGames = gson.toJson(models);

        try {
            response.setContentType("application/json");
            response.getWriter().append(listOfGames);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private List<ClientGameModel> createClientGameModels(HttpServletRequest request, Map<String, Game> games) {
        List<ClientGameModel> models = new ArrayList<>();

        for (Map.Entry<String, Game> gameEntry : games.entrySet()) {
            String gameId = gameEntry.getKey();
            Game game = gameEntry.getValue();

            ClientGameModel model = new ClientGameModel();
            model.setGameId(gameId);
            model.setGameName(game.getDescriptor().getGameName());
            model.setGameVersion(game.getDescriptor().getGameVersion());

            model.setMiniatureUrl(WebUtils.getServletContextPath(request) + "/miniature/" + gameId);
            model.setStartGameUrl(WebUtils.getServletContextPath(request) + "/action/startGame/" + gameId);

            models.add(model);
        }

        return models;
    }

}

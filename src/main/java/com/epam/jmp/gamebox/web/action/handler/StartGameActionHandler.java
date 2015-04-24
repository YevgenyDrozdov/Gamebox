package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.services.GameService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartGameActionHandler implements ActionHandler {

    public static String GAME_ID_PARAMETER_NAME = "game-id";

    private GameService gameService;

    public StartGameActionHandler(GameService gameService) {
        this.gameService = gameService;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String)request.getAttribute(GAME_ID_PARAMETER_NAME);
        //gameService.loadGame(gameId);
    }

}

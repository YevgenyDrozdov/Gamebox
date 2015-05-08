package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Action;
import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameModel;
import com.epam.jmp.gamebox.Session;
import com.epam.jmp.gamebox.impl.GameActionImpl;
import com.epam.jmp.gamebox.impl.GameModelImpl;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.web.util.WebUtils;

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

        WebUtils.invalidateGameSession(request, gameId);

        Game game = gameService.getInstantiatedGameById(gameId);
        if (game == null) {
            game = gameService.instantiateGame(gameId);
        }

        Session newSession = WebUtils.getGameSession(request, gameId);
        Action startGameAction = createStartGameAction(request, newSession);
        GameModel model = createNewGameModel();

        game.getController().processAction(startGameAction, model);
    }

    private Action createStartGameAction(HttpServletRequest request, Session newSession) {
        GameActionImpl startGameAction = new GameActionImpl("startGame", null, newSession);
        return startGameAction;
    }

    private GameModel createNewGameModel() {
        return new GameModelImpl();
    }

}

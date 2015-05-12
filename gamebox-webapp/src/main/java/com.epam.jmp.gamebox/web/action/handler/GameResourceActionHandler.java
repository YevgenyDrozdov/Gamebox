package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.services.GameService;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GameResourceActionHandler implements ActionHandler {

    public static final String GAME_ID_PARAMETER_NAME = "game-id";
    public static final String PATH_PARAMETER_NAME = "path";

    private GameService gameService;

    public GameResourceActionHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String)request.getAttribute(GAME_ID_PARAMETER_NAME);
        String resourcePath = (String)request.getParameter(PATH_PARAMETER_NAME);
        Game game = gameService.getInstantiatedGameById(gameId);
        InputStream resourceStream = game.getDescriptor().getDeploymentDescriptor().getClassLoader()
                .getResourceAsStream(resourcePath);

        try {
            OutputStream output = response.getOutputStream();
            IOUtils.copy(resourceStream, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

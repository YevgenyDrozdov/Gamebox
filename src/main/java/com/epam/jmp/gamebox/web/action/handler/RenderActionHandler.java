package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameModel;
import com.epam.jmp.gamebox.View;
import com.epam.jmp.gamebox.services.GameService;
import com.epam.jmp.gamebox.web.util.WebUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class RenderActionHandler implements ActionHandler {

    public static String GAME_ID_PARAMETER_NAME = "game-id";

    private GameService gameService;

    public RenderActionHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String)request.getAttribute(GAME_ID_PARAMETER_NAME);

        Game game = gameService.getInstantiatedGameById(gameId);
        GameModel model = WebUtils.getGameModel(request, gameId);

        View view = game.getController().processRender(model);

        try {
            OutputStream output = response.getOutputStream();

            InputStream beginning = getViewBeginningInputStream(gameId, game);
            IOUtils.copy(beginning, output);

            InputStream viewStream = getViewInputStream(game, view);
            IOUtils.copy(viewStream, output);

            InputStream ending = getViewEndingInputStream(gameId, game);
            IOUtils.copy(ending, output);
        } catch (IOException e) {
        }
    }

    private InputStream getViewBeginningInputStream(String gameId, Game game) {
        List<String> cssResources = game.getDescriptor().getCssResources();
        List<String> jsResources = game.getDescriptor().getJsResources();

        StringBuilder beginning = new StringBuilder();

        beginning.append("<!DOCTYPE html>");
        beginning.append("<html><head>");

        for (String cssResource : cssResources) {
            beginning.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/rest/" + gameId + "/css?path=" + cssResource + "\" />");
        }

        for (String jsResource : jsResources) {
            beginning.append("<script src=\"/rest/" + gameId + "/js?path=" + jsResource + "\"></script>");
        }

        beginning.append("</head><body>");

        ByteArrayInputStream beginningInputStream = new ByteArrayInputStream(beginning.toString().getBytes());
        return beginningInputStream;
    }

    private InputStream getViewEndingInputStream(String gameId, Game game) {
        StringBuilder ending = new StringBuilder();

        ending.append("</body></html>");

        ByteArrayInputStream endingInputStream = new ByteArrayInputStream(ending.toString().getBytes());
        return endingInputStream;
    }

    private InputStream getViewInputStream(Game game, View view) {
        ClassLoader gameClassLoader = game.getDescriptor().getDeploymentDescriptor().getClassLoader();
        String viewName = view.getName();
        return gameClassLoader.getResourceAsStream(viewName);
    }

}

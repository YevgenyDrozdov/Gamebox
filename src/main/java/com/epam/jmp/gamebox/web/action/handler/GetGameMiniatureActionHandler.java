package com.epam.jmp.gamebox.web.action.handler;

import com.epam.jmp.gamebox.Game;
import com.epam.jmp.gamebox.GameDescriptor;
import com.epam.jmp.gamebox.GameboxContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetGameMiniatureActionHandler implements ActionHandler {

    public static String GAME_ID_PARAMETER_NAME = "game-id";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        String gameId = (String) request.getAttribute(GAME_ID_PARAMETER_NAME);

        Game game = GameboxContext.getInstance().getGameService().getDeployedGameById(gameId);
        String miniaturePath = game.getDescriptor().getMiniaturePath();
        InputStream in = game.getDescriptor().getDeploymentDescriptor().getGameClassLoader().getResourceAsStream(miniaturePath);

        try {
            OutputStream out = response.getOutputStream();

            int data = in.read();
            while (data != -1) {
                out.write(data);
                data = in.read();
            }

            in.close();
            out.close();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/octet-stream");
    }

}

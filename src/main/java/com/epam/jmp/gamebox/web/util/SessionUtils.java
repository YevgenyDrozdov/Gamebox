package com.epam.jmp.gamebox.web.util;

import com.epam.jmp.gamebox.Session;
import com.epam.jmp.gamebox.impl.SessionImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public final class SessionUtils {

    public static final String GAME_SESSION_ATTRIBUTE_NAME = "gamebox-game-sessions";

    private SessionUtils() {}

    public static Session getGameSession(HttpServletRequest httpRequest, String gameId, boolean createNew) {

        HttpSession httpSession = httpRequest.getSession(createNew);

        if (httpSession.isNew()) {
            httpSession.setAttribute(GAME_SESSION_ATTRIBUTE_NAME, new HashMap<String, Session>());
        }

        Map<String, Session> gameSessions = (Map<String, Session>)httpSession.getAttribute(GAME_SESSION_ATTRIBUTE_NAME);
        if (gameSessions != null) {
            Session gameSession = gameSessions.get(gameId);

            if (gameSession == null && createNew) {
                gameSession = new SessionImpl();
                gameSessions.put(gameId, gameSession);
            }

            return gameSession;
        }

        return null;
    }

    public static Session getGameSession(HttpServletRequest httpRequest, String gameId) {
        return getGameSession(httpRequest, gameId, true);
    }

}

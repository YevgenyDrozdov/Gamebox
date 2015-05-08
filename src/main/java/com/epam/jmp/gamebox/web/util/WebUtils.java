package com.epam.jmp.gamebox.web.util;

import com.epam.jmp.gamebox.GameModel;
import com.epam.jmp.gamebox.Session;
import com.epam.jmp.gamebox.impl.SessionImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public final class WebUtils {

    public static final String GAME_SESSION_ATTRIBUTE_NAME = "gamebox-game-sessions";

    private WebUtils() {}

    public static Session getGameSession(HttpServletRequest httpRequest, String gameId, boolean createNew) {

        HttpSession httpSession = httpRequest.getSession(createNew);

        if (httpSession != null) {
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
        }

        return null;
    }

    public static Session getGameSession(HttpServletRequest httpRequest, String gameId) {
        return getGameSession(httpRequest, gameId, true);
    }

    public static void invalidateGameSession(HttpServletRequest httpRequest, String gameId) {
        HttpSession httpSession = httpRequest.getSession(false);

        if (httpSession != null) {
            Map<String, Session> gameSessions = (Map<String, Session>)httpSession.getAttribute(GAME_SESSION_ATTRIBUTE_NAME);
            if (gameSessions != null) {
                gameSessions.remove(gameId);
            }
        }
    }

    public static GameModel getGameModel(HttpServletRequest httpRequest, String gameId) {
        Session gameSession = getGameSession(httpRequest, gameId, false);
        if (gameSession != null) {
            GameModel model = ((SessionImpl)gameSession).getGameModel(gameId);
            return model;
        }

        return null;
    }

    public static void saveGameModel(HttpServletRequest httpRequest, String gameId, GameModel model) {
        Session gameSession = getGameSession(httpRequest, gameId, false);
        if (gameSession != null) {
            ((SessionImpl)gameSession).putGameModel(gameId, model);
        }
    }

    public static String getServletContextPath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String webAppContext = request.getContextPath();
        String servletPath = request.getServletPath();

        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(scheme).append("://").append(serverName);

        if (serverPort != 80) {
            baseUrl.append(":").append(serverPort);
        }

        baseUrl.append(webAppContext).append(servletPath);

        return baseUrl.toString();
    }

}

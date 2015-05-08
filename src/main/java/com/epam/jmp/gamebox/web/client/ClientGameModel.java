package com.epam.jmp.gamebox.web.client;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ClientGameModel {

    public static final String GAME_ID_ATTRIBUTE_NAME = "gameId";
    public static final String GAME_NAME_ATTRIBUTE_NAME = "gameName";
    public static final String GAME_VERSION_ATTRIBUTE_NAME = "gameVersion";
    public static final String MINIATURE_URL_ATTRIBUTE_NAME = "miniatureUrl";
    public static final String START_GAME_URL_ATTRIBUTE_NAME = "startGameUrl";

    @SerializedName("attributes")
    private Map<String, Object> attributes = new HashMap<>();

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getMiniatureUrl() {
        return (String)attributes.get(MINIATURE_URL_ATTRIBUTE_NAME);
    }

    public void setMiniatureUrl(String miniatureUrl) {
        this.attributes.put(MINIATURE_URL_ATTRIBUTE_NAME, miniatureUrl);
    }

    public String getStartGameUrl() {
        return (String)attributes.get(START_GAME_URL_ATTRIBUTE_NAME);
    }

    public void setStartGameUrl(String startGameUrl) {
        this.attributes.put(START_GAME_URL_ATTRIBUTE_NAME, startGameUrl);
    }

    public void setGameId(String gameId) {
        attributes.put(GAME_ID_ATTRIBUTE_NAME, gameId);
    }

    public void setGameName(String name) {
        attributes.put(GAME_NAME_ATTRIBUTE_NAME, name);
    }

    public void setGameVersion(String version) {
        attributes.put(GAME_VERSION_ATTRIBUTE_NAME, version);
    }
}

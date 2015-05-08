package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameModel;
import com.epam.jmp.gamebox.Session;

import java.util.HashMap;
import java.util.Map;

public class SessionImpl implements Session {

    private Map<String, GameModel> models = new HashMap<String, GameModel>();
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public void put(String s, Object o) {
        attributes.put(s, o);
    }

    public Object get(String s) {
        return attributes.get(s);
    }

    public GameModel getGameModel(String gameId) {
        return models.get(gameId);
    }

    public void putGameModel(String gameId, GameModel model) {
        models.put(gameId, model);
    }

}

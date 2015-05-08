package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.GameModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameModelImpl implements GameModel {

    private Map<String, Object> modelParameters = new HashMap<>();

    @Override
    public void put(String s, Object o) {
        modelParameters.put(s, o);
    }

    @Override
    public Object get(String s) {
        return modelParameters.get(s);
    }

    @Override
    public Set<String> getAllKeys() {
        return modelParameters.keySet();
    }

}

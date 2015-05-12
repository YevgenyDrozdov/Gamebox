package com.epam.jmp.gamebox.api.impl;
import com.epam.jmp.gamebox.Action;
import com.epam.jmp.gamebox.Session;

import java.util.Map;

public class GameActionImpl implements Action {

    private String actionId;
    private Map<String, String> actionParametersMap;
    private Session session;

    public GameActionImpl(String actionId, Map<String, String> actionParametersMap, Session session) {
        this.actionId = actionId;
        this.actionParametersMap = actionParametersMap;
        this.session = session;
    }

    @Override
    public String getActionId() {
        return actionId;
    }

    @Override
    public Map<String, String> getActionParametersMap() {
        return actionParametersMap;
    }

    @Override
    public String getActionParameter(String s) {
        return actionParametersMap.get(s);
    }

    @Override
    public Session getSession() {
        return session;
    }
}

package com.epam.jmp.gamebox.web.action.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionHandler {

    void handle(HttpServletRequest request, HttpServletResponse response);

}

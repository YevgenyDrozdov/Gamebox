package com.epam.jmp.gamebox.web.action.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionDispatcher {

    void dispatch(HttpServletRequest request, HttpServletResponse response);

}

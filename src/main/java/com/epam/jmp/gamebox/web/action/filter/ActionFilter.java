package com.epam.jmp.gamebox.web.action.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionFilter {

    void apply(HttpServletRequest request, HttpServletResponse response);

}

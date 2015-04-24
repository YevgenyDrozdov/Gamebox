package com.epam.jmp.gamebox.web.action.dispatcher;

import com.epam.jmp.gamebox.web.action.filter.ActionFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

public abstract class ActionDispatcherWithFiltering implements ActionDispatcher {

    private LinkedList<ActionFilter> filtersBefore = new LinkedList<ActionFilter>();
    private LinkedList<ActionFilter> filtersAfter = new LinkedList<ActionFilter>();

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) {
        if (canDispatchAction(request, response)) {
            applyFiltersBeforeAction(request, response);

            dispatchAction(request, response);

            applyFiltersAfterAction(request, response);
        }
    }

    protected void applyFiltersAfterAction(HttpServletRequest request, HttpServletResponse response) {
        for (ActionFilter filter : filtersBefore) {
            filter.apply(request, response);
        }
    }

    protected void applyFiltersBeforeAction(HttpServletRequest request, HttpServletResponse response) {
        for (ActionFilter filter : filtersAfter) {
            filter.apply(request, response);
        }
    }

    protected LinkedList<ActionFilter> getFiltersBefore() {
        return filtersBefore;
    }

    protected LinkedList<ActionFilter> getFiltersAfter() {
        return filtersAfter;
    }

    protected abstract boolean canDispatchAction(HttpServletRequest request, HttpServletResponse response);
    protected abstract void dispatchAction(HttpServletRequest request, HttpServletResponse response);
}

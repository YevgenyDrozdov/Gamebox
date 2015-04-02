package com.epam.jmp.gamebox.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RESTActionDispatcher implements ActionDispatcher {

    private MappingNode mappingTree = new MappingNode("/");

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) {
        String query = request.getServletPath();

        Pair<ActionHandler, Map<String, String>> handlerPair = getActionByMapping(query);

        if (handlerPair != null) {
            ActionHandler handler = handlerPair.getA();

            if (handler != null) {
                handler.handle(request, response);
            }
        }
    }

    public void mapAction(String queryPattern, ActionHandler actionHandler) {
        MappingNode currentNode = mappingTree;

        queryPattern = normalizeQuery(queryPattern);

        String[] queryElements = queryPattern.split("/");

        for (int i = 0; i < queryElements.length; i++) {
            boolean isLastElement = i == queryElements.length - 1;

            String nodeValue = queryElements[i];
            String nodePlaceholderName = null;

            if (nodeValue.startsWith("{") && nodeValue.endsWith("}")) {
                nodePlaceholderName = nodeValue.substring(1, nodeValue.length() - 1);
                nodeValue = "*";
            }

            MappingNode nodeForElement = currentNode.getChildWithValue(nodeValue);
            if (nodeForElement == null) {
                if (nodeValue.equals("*") && nodePlaceholderName != null) {
                    nodeForElement = new VariableValueNode(nodePlaceholderName);
                } else {
                    nodeForElement = new MappingNode(nodeValue);
                }

                currentNode.addChild(nodeForElement);
            }

            if (isLastElement) {
                nodeForElement.setLinkedActionHandler(actionHandler);
            }

            currentNode = nodeForElement;
        }
    }

    protected Pair<ActionHandler, Map<String, String>> getActionByMapping(String query) {
        MappingNode currentNode = mappingTree;

        Map<String, String> resolvedPlaceholders = new HashMap<String, String>();

        query = normalizeQuery(query);

        String[] queryElements = query.split("/");

        for (int i = 0; i < queryElements.length; i++) {
            String queryElement = queryElements[i];

            String nodeValue = queryElement;

            MappingNode nodeForElement = currentNode.getChildWithValue(queryElement);
            if (nodeForElement == null) {
                nodeForElement = currentNode.getChildWithValue("*");
            }

            if (nodeForElement == null) {
                return null;
            }

            if (nodeForElement instanceof VariableValueNode) {
                resolvedPlaceholders.put(((VariableValueNode)nodeForElement).getPlaceholderName(), nodeValue);
            }

            currentNode = nodeForElement;
        }

        return new Pair<ActionHandler, Map<String, String>>(currentNode.getLinkedActionHandler(), resolvedPlaceholders);
    }

    private String normalizeQuery(String query) {
        if (query.startsWith("/")) {
            query = query.substring(1);
        }

        if (query.endsWith("/")) {
            query = query.substring(0, query.length() - 1);
        }

        return query;
    }

    private static class MappingNode {

        private String value;
        private ActionHandler linkedActionHandler;
        private List<MappingNode> children = new LinkedList<MappingNode>();

        private MappingNode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setLinkedActionHandler(ActionHandler linkedActionHandler) {
            this.linkedActionHandler = linkedActionHandler;
        }

        public ActionHandler getLinkedActionHandler() {
            return linkedActionHandler;
        }

        public void addChild(MappingNode child) {
            children.add(child);
        }

        public MappingNode getChildWithValue(String value) {
            for (MappingNode node : children) {
                if (value.equals(node.getValue())) {
                    return node;
                }
            }
            return null;
        }
    }

    private static class VariableValueNode extends MappingNode {
        private String placeholderName;

        private VariableValueNode(String placeholderName) {
            super("*");
            this.placeholderName = placeholderName;
        }

        public String getPlaceholderName() {
            return placeholderName;
        }
    }

    protected static class Pair<T, E> {
        private T a;
        private E b;

        private Pair(T a, E b) {
            this.a = a;
            this.b = b;
        }

        public T getA() {
            return a;
        }

        public E getB() {
            return b;
        }
    }

}

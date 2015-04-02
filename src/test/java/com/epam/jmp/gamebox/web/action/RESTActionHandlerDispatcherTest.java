package com.epam.jmp.gamebox.web.action;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import com.epam.jmp.gamebox.web.action.RESTActionDispatcher.Pair;

public class RESTActionHandlerDispatcherTest {

    private RESTActionDispatcher dispatcher;

    private MappingTestCase[] mappingTestCases = {
            new MappingTestCase("/",
                    new String[] {"/"},
                    new ActionHandlerImpl()),

            new MappingTestCase("/action/game-start/{game-id}",
                    new String[] {"/action/game-start/12345", "/action/game-start/0"},
                    new ActionHandlerImpl()),

            new MappingTestCase("action/game-stop/{game-id}",
                    new String[] {"action/game-stop/12488", "action/game-stop/0"},
                    new ActionHandlerImpl()),

            new MappingTestCase("action/game-start/mode/{mode-id}",
                    new String[] {"action/game-start/mode/fullscreen", "action/game-start/mode/stopped"},
                    new ActionHandlerImpl()),

            new MappingTestCase("action/game-start/{game-id}/{mode-id}",
                    new String[] {"action/game-start/11111/fullscreen", "action/game-start/00000/stopped"},
                    new ActionHandlerImpl()),

            new MappingTestCase("/0/1/{2}/{3}/",
                    new String[] {"0/1/2/3", "/0/1/2/3", "0/1/2/3/", "/0/1/2/3/"},
                    new ActionHandlerImpl())
    };

    private VariableTestCase[] variableTestCases = {
            new VariableTestCase("/action/game-start/0", new HashMap<String, String>() {
                {
                    put("game-id", "0");
                }
            }),

            new VariableTestCase("action/game-start/1/2", new HashMap<String, String>() {
                {
                    put("game-id", "1");
                    put("mode-id", "2");
                }
            })
    };

    @Before
    public void init() {
        dispatcher = new RESTActionDispatcher();

        for (MappingTestCase testCase : mappingTestCases) {
            dispatcher.mapAction(testCase.pattern, testCase.actionHandler);
        }
    }

    @Test
    public void testMapAction() {
        for (MappingTestCase testCase : mappingTestCases) {
            for (String testQuery : testCase.testQueries) {
                Pair<ActionHandler, Map<String, String>> result = dispatcher.getActionByMapping(testQuery);
                Assert.assertTrue(result.getA() == testCase.actionHandler);
            }
        }
    }

    @Test
    public void testVariables() {
        for (VariableTestCase testCase : variableTestCases) {
            Pair<ActionHandler, Map<String, String>> result = dispatcher.getActionByMapping(testCase.testQuery);
            Assert.assertTrue(result.getB().equals(testCase.expectedVariables));
        }
    }

    private static class MappingTestCase {
        public String pattern;
        public String[] testQueries;
        public ActionHandler actionHandler;

        private MappingTestCase(String pattern, String[] testQueries, ActionHandler actionHandler) {
            this.pattern = pattern;
            this.testQueries = testQueries;
            this.actionHandler = actionHandler;
        }
    }

    private static class VariableTestCase {
        public String testQuery;
        public Map<String, String> expectedVariables;

        private VariableTestCase(String testQuery, Map<String, String> expectedVariables) {
            this.testQuery = testQuery;
            this.expectedVariables = expectedVariables;
        }
    }

    private static class ActionHandlerImpl implements ActionHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response) {
        }
    }

}

package com.epam.jmp.gamebox.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void testTrimExtension() {
        List<String> testFileNames = Arrays.asList("testwar.war",
                "test/test.war",
                "test/test",
                "test.test.war");

        List<String> expectedResults = Arrays.asList("testwar",
                "test/test",
                "test/test",
                "test.test");

        Iterator<String> it1 = testFileNames.iterator();
        Iterator<String> it2 = expectedResults.iterator();

        while (it1.hasNext()) {
            String testName = it1.next();
            String expectedResult = it2.next();

            Assert.assertTrue(expectedResult.equals(FileUtils.trimExtension(testName)));
        }
    }

}

/*
 * ShallWeQuestionsTest.java
 * JUnit based test
 *
 * Created on 20. April 2006, 20:49
 */
package org.huberb.nbgzip.operation;

import org.huberb.nbgzip.operation.AskOperation.AskUserOption;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class ShallWeQuestionsTest {

    private ShallWeQuestions swq;

    @Before
    public void setUp() throws Exception {
        this.swq = new ShallWeQuestions();
    }

    /**
     * Test of shallWeAskQuestion method, of class
     * org.huberb.nbgzip.operation.ShallWeQuestions.
     */
    @Test
    public void testShallWeAskQuestion() {
        ShallWeQuestions instance = this.swq;
        boolean result;

        // always ask at the beginning
        result = instance.shallWeAskQuestion();
        assertEquals(true, result);

        // ask if user has answered YES last time
        instance.setAskUserOption(AskUserOption.YES);
        result = instance.shallWeAskQuestion();
        assertEquals(true, result);

        // ask if user has answered NO last time
        instance.setAskUserOption(AskUserOption.NO);
        result = instance.shallWeAskQuestion();
        assertEquals(true, result);

        // don't ask if user has answered YES_TO_ALL last time
        instance.setAskUserOption(AskUserOption.YES_TO_ALL);
        result = instance.shallWeAskQuestion();
        assertEquals(false, result);

        // don't ask if user has answered CANCEL last time
        instance.setAskUserOption(AskUserOption.CANCEL);
        result = instance.shallWeAskQuestion();
        assertEquals(false, result);
    }

    /**
     * Test of shallWeDoOperation method, of class
     * org.huberb.nbgzip.operation.ShallWeQuestions.
     */
    @Test
    public void testShallWeDoOperation() {
        ShallWeQuestions instance = this.swq;
        boolean result;

        // don't do operation if user was not asked
        result = instance.shallWeDoOperation();
        assertEquals(false, result);

        // do operation if user answered YES
        instance.setAskUserOption(AskUserOption.YES);
        result = instance.shallWeDoOperation();
        assertEquals(true, result);

        // do operation if user answered YES
        instance.setAskUserOption(AskUserOption.NO);
        result = instance.shallWeDoOperation();
        assertEquals(false, result);

        // do operation if user answered YES_TO_ALL
        instance.setAskUserOption(AskUserOption.YES_TO_ALL);
        result = instance.shallWeDoOperation();
        assertEquals(true, result);

        // do operation if user answered CANCEL
        instance.setAskUserOption(AskUserOption.CANCEL);
        result = instance.shallWeDoOperation();
        assertEquals(false, result);
    }

    /**
     * Test of shallWeTerminateDoOperation method, of class
     * org.huberb.nbgzip.operation.ShallWeQuestions.
     */
    @Test
    public void testShallWeTerminateDoOperation() {
        ShallWeQuestions instance = this.swq;
        boolean result;

        // continue if user was not asked
        result = instance.shallWeTerminateDoOperation();
        assertEquals(false, result);

        // continue if user answered YES
        instance.setAskUserOption(AskUserOption.YES);
        result = instance.shallWeTerminateDoOperation();
        assertEquals(false, result);

        // continue if user answered YES
        instance.setAskUserOption(AskUserOption.NO);
        result = instance.shallWeTerminateDoOperation();
        assertEquals(false, result);

        // continue if user answered YES
        instance.setAskUserOption(AskUserOption.YES_TO_ALL);
        result = instance.shallWeTerminateDoOperation();
        assertEquals(false, result);

        // DO NOT continue if user answered YES
        instance.setAskUserOption(AskUserOption.CANCEL);
        result = instance.shallWeTerminateDoOperation();
        assertEquals(true, result);
    }

    /**
     * Test of setAskUserOption method, of class
     * org.huberb.nbgzip.operation.ShallWeQuestions. Test of getAskUserOption
     * method, of class org.huberb.nbgzip.operation.ShallWeQuestions.
     */
    @Test
    public void testSetAskUserOption() {
        ShallWeQuestions instance = this.swq;

        assertNull(instance.getAskUserOption());

        instance.setAskUserOption(AskUserOption.YES);
        assertEquals(AskUserOption.YES, instance.getAskUserOption());
        assertTrue(AskUserOption.NO != instance.getAskUserOption());
        assertTrue(AskUserOption.YES_TO_ALL != instance.getAskUserOption());
        assertTrue(AskUserOption.CANCEL != instance.getAskUserOption());
    }

}

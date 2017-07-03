package org.wdd;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountGeneratorTest {

    private AccountGenerator generator = new AccountGenerator();

    @Test
    public void when_hasValidArguments__with_bigger_length__expect_false() throws Exception {
        boolean result = generator.hasValidArguments("-a", "-b");
        assertFalse(result);
    }

    @Test
    public void when_hasValidArguments__with_correct_length_and_help_argument__expect_false() throws Exception {
        boolean result = generator.hasValidArguments("-h");
        assertFalse(result);
    }

    @Test
    public void when_hasValidArguments__with_correct_length_and_invalid_argument__expect_false() throws Exception {
        boolean result = generator.hasValidArguments("-a");
        assertFalse(result);
    }

    @Test
    public void when_hasValidArguments__with_correct_length_and_local_argument__expect_true() throws Exception {
        boolean result = generator.hasValidArguments("-local");
        assertTrue(result);
    }

    @Test
    public void when_hasValidArguments__with_correct_length_and_remote_argument__expect_true() throws Exception {
        boolean result = generator.hasValidArguments("-remote");
        assertTrue(result);
    }


}
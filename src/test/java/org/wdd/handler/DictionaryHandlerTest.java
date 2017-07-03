package org.wdd.handler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class DictionaryHandlerTest {

    private DictionaryHandler handler;
    public @Rule ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        handler = new DictionaryHandler();
    }

    @Test
    public void when_generatePhrase__with_null__expect_IllegalArgumentException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Words indexes can't be empty");

        handler.generatePhrase(null);
    }

    @Test
    public void when_generatePhrase__with_empty__expect_IllegalArgumentException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Words indexes can't be empty");

        handler.generatePhrase(Collections.emptyList());
    }

    @Test
    public void when_generatePhrase__with_normal_sequence__expect_IllegalArgumentException() throws Exception {
        String phrase = handler.generatePhrase(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        String expected = "just love know never want time out there make look eye down";
        assertEquals(expected, phrase);
    }

}
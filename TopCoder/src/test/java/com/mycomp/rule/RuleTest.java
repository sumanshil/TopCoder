package com.mycomp.rule;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testDivisionWithException() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage(containsString("/ by zero"));
        int i = 1 / 0;
    }

    @Test
    public void testNameNotFoundException() {
        expectedException.expect(NameNotFoundException.class);

        expectedException.expectMessage("Name is empty!");

        expectedException.expect(hasProperty("errCode"));
        expectedException.expect(hasProperty("errCode", is(666)));

    }
}

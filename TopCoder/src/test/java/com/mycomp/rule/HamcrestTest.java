package com.mycomp.rule;

import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;

public class HamcrestTest {

    @Test
    public void hasSizeOf3() {
        List<Integer> list = Arrays.asList(5, 2, 4);
    }

    @Test
    public void containsNumbersInAnyOrder() {
        List<Integer> list = Arrays.asList(5, 2, 4);

        assertThat(list, containsInAnyOrder(2, 4, 5));
    }

}

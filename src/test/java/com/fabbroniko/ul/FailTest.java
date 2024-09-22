package com.fabbroniko.ul;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;

public class FailTest {

    @Test
    void shouldFail() {
        fail();
    }
}

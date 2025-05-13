package cn.vgonet.mirror.frameworks.application.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanaryTest {
    @Test
    void test() {
        assertThat("hello").isEqualTo("hello");
    }
}

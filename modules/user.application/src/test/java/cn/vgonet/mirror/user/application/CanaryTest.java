package cn.vgonet.mirror.user.application;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanaryTest {
    @Test
    void test() {
        assertThat("hello").isEqualTo("hello");
    }
}

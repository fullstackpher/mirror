package cn.vgonet.mirror.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeUtilsTest {
    @Test
    void should_transform_time_to_instant() {
        assertThat(TimeUtils.toInstant("2022-02-10 22:22:22")).isEqualTo(Instant.parse("2022-02-10T14:22:22Z"));
    }
}

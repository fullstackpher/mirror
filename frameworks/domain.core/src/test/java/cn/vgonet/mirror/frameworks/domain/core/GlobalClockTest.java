package cn.vgonet.mirror.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalClockTest {
    @Test
    void should_be_able_to_retrieve_time_from_underlying_clock() {
        GlobalClockResetter.reset(Clock.fixed(Instant.parse("2020-01-01T00:00:00Z"), ZoneId.of("Asia/Shanghai")));
        assertThat(GlobalClock.now()).isEqualTo(Instant.parse("2020-01-01T00:00:00Z"));
    }

    @Test
    void should_be_able_to_retrieve_local_now_from_underlying_clock() {
        GlobalClockResetter.reset(Clock.fixed(Instant.parse("2019-12-31T16:00:00Z"), ZoneId.of("Asia/Shanghai")));
        assertThat(GlobalClock.localNow()).isEqualTo(ZonedDateTime.parse("2020-01-01T00:00:00+08:00[Asia/Shanghai]"));
    }
}

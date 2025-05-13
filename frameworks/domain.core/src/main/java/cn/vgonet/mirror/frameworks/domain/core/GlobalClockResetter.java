package cn.vgonet.mirror.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Clock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalClockResetter {
    public static void reset(Clock clock) {
        GlobalClock.reset(clock);
    }
}

package cn.vgonet.mirror.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalClock {
    private static Clock clock = Clock.system(ZoneId.of("Asia/Shanghai"));

    public static Instant now() {
        return clock.instant();
    }

    public static ZonedDateTime localNow() {
        return clock.instant().atZone(clock.getZone());
    }

    static void reset(Clock clock) {
        GlobalClock.clock = clock;
    }

//    public static ZoneId zone() {
//        return clock.getZone();
//    }
}

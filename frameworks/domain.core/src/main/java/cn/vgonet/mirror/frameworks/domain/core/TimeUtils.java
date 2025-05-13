package cn.vgonet.mirror.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {
    public static Instant toInstant(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return localDateTime.toInstant(offset);
    }
}

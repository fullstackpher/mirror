package cn.vgonet.mirror.frameworks.domain.core;

import com.fasterxml.uuid.Generators;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UuidBasedIdentityService implements IdentityService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSS");
    private static final long A = 10000000;
    private static final long B = 12219292800L;
    private static final long C = 1000000;
    private static final long D = 100;
    private static final int E = 8;

    @Override
    public String nextIdentity() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

    @Override
    public String nextReadableIdentity() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return formatUuidTimestamp(uuid.timestamp());
    }

    private String formatUuidTimestamp(long timestamp) {
        return FORMATTER.format(Instant.ofEpochSecond(timestamp / A - B, timestamp % C * D).atZone(ZoneOffset.ofHours(E)));
    }
}

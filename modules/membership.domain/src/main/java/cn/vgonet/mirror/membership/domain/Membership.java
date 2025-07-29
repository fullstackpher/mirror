package cn.vgonet.mirror.membership.domain;

import java.time.LocalDateTime;

public class Membership {
    private final String id;
    private final String level;
    private final LocalDateTime createAt;
    private final Boolean isActive;

    public Membership(String id, String level, LocalDateTime createAt, Boolean isActive) {
        this.id = id;
        this.level = level;
        this.createAt = createAt;
        this.isActive = isActive;
    }

    public String id() {
        return id;
    }

    public String level() {
        return level;
    }

    public LocalDateTime createAt() {
        return createAt;
    }

    public Boolean isActive() {
        return isActive;
    }
}

package cn.vgonet.mirror.membership.domain;

import java.time.LocalDateTime;

public class Membership {
    private final String id;
    private final String level;
    private final LocalDateTime createdAt;
    private final Boolean isActive;

    public Membership(String id, String level, LocalDateTime createdAt, Boolean isActive) {
        this.id = id;
        this.level = level;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public String id() {
        return id;
    }

    public String level() {
        return level;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Boolean isActive() {
        return isActive;
    }
}

package cn.vgonet.mirror.membership.domain;

import java.time.LocalDateTime;

public class Membership {
    private final String id;
    private String level;
    private final LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private final Boolean isActive;

    public Membership(String id, String level, LocalDateTime createdAt, LocalDateTime expiredAt, Boolean isActive) {
        this.id = id;
        this.level = level;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.isActive = isActive;
    }

    public void upgrade(String level, Long expiredAt) {
        this.level = level;
        this.expiredAt = this.expiredAt.plusYears(expiredAt);
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

    public LocalDateTime expiredAt() {
        return expiredAt;
    }

    public Boolean isActive() {
        return isActive;
    }
}

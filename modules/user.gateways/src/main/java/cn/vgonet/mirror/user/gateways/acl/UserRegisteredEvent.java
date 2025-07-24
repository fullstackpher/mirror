package cn.vgonet.mirror.user.gateways.acl;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class UserRegisteredEvent {
    private final String userId;
    private final String username;
    private final String email;
    private final String referralCode;
    private final LocalDateTime registerDate;

    public UserRegisteredEvent(String userId, String username, String email, String s1, LocalDateTime localDateTime) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.referralCode = s1;
        this.registerDate = localDateTime;
    }
}

package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserProfileOutput {
    private final String userId;
    private final String username;
    private final String email;
    private final LocalDateTime registerDate;
    private final String referralCode;
    private final boolean active;

    public GetUserProfileOutput(User user) {
        this.userId = user.userId();
        this.username = user.username();
        this.email = user.email();
        this.registerDate = user.registerDate();
        this.referralCode = user.referralCode();
        this.active = user.isActive();
    }
}

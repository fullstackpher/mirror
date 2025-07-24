package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserProfileOutput {
    private String userId;
    private String username;
    private String email;
    private LocalDateTime registerDate;
    private String referralCode;
    private boolean active;

    public GetUserProfileOutput(User user) {
        this.userId = user.userId();
        this.username = user.username();
        this.email = user.email();
        this.registerDate = user.registerDate();
        this.referralCode = user.referralCode();
        this.active = user.isActive();
    }
}

package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.user.domain.User;
import lombok.Getter;

@Getter
public class LoginUserOutput {
    private final String userId;
    private final String username;
    private final String email;
    private final String token;

    public LoginUserOutput(User user, String token) {
        this.userId = user.userId();
        this.username = user.username();
        this.email = user.email();
        this.token = token;
    }
}

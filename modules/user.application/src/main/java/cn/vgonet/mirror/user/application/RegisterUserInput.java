package cn.vgonet.mirror.user.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterUserInput {
    private String username;
    private String email;
    private String password;
    private String referralCode;
}

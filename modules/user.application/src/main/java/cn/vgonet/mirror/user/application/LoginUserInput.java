package cn.vgonet.mirror.user.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserInput {
    private String username;
    private String password;
}

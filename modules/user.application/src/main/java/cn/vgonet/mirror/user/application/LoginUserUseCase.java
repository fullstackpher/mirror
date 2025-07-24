package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;
import cn.vgonet.mirror.user.domain.UserValid;

@UseCase
public class LoginUserUseCase {

    private final UserRepository userRepository;
    private final UserService userService;

    public LoginUserUseCase(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Object execute(LoginUserInput input) {
        UserValid userValid = new UserValid(userRepository, userService);
        userValid.check(input.getUsername(), input.getPassword());
        User user = userValid.validForUserName(input.getUsername(), input.getPassword());
        String token = userService.generateToken(user);
        return new LoginUserOutput(user, token);
    }
}

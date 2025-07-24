package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.user.domain.*;

@UseCase
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterUserUseCase(UserRepository userRepository,
                              UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Object execute(RegisterUserInput input) {
        User user = userService.createUser(
            input.getUsername(),
            input.getEmail(),
            input.getPassword(),
            input.getReferralCode()
        );
        userRepository.save(user);
        userService.publishUserRegisteredEvent(user);
        return userRepository.userForUserName(input.getUsername()).userId();
    }
}

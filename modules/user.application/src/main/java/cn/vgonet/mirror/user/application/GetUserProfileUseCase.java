package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserValid;

@UseCase
public class GetUserProfileUseCase {

    private final UserRepository userRepository;

    public GetUserProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object execute(String userId) {
        UserValid userValid = new UserValid(userRepository, null);
        User user = userValid.validForUserId(userId);
        return new GetUserProfileOutput(user);
    }
}

package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;

@UseCase
public class RegisterUserUseCase {
//    private static final String DEFAULT_LEVEL = "普通会员";
//    private static final int DEFAULT_EXPIRED_AT = 9999;
    private final UserRepository userRepository;
    private final UserService userService;

    public RegisterUserUseCase(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Object execute(RegisterUserInput input) {
        User user = userService.createUser(input.getUsername(), input.getEmail(), input.getPassword(), input.getReferralCode());
//        membershipRepository.save(new Membership(
//                UUID.randomUUID().toString(),
//                DEFAULT_LEVEL,
//            LocalDateTime.now(),
//            LocalDateTime.now().plusYears(DEFAULT_EXPIRED_AT),
//            true
//        ));
        userRepository.save(user);
        return userRepository.userForUserName(input.getUsername()).userId();
    }
}

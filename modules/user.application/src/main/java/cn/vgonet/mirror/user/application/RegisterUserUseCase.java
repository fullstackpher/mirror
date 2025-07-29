package cn.vgonet.mirror.user.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;
import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@UseCase
public class RegisterUserUseCase {
    private static final String DEFAULT_LEVEL = "普通会员";
    private static final int DEFAULT_EXPIRED_AT = 9999;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MembershipRepository membershipRepository;

    public RegisterUserUseCase(UserRepository userRepository, UserService userService, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.membershipRepository = membershipRepository;
    }

    public Object execute(RegisterUserInput input) {
        User user = userService.createUser(input.getUsername(), input.getEmail(), input.getPassword(), input.getReferralCode());
        Membership membership = new Membership(
                UUID.randomUUID().toString(),
                DEFAULT_LEVEL,
                LocalDateTime.now(),
                LocalDateTime.now().plusYears(DEFAULT_EXPIRED_AT),
                true
        );
        membershipRepository.save(membership);
        user.assignMembership(membership.id());
        userRepository.save(user);
        return userRepository.userForUserName(input.getUsername()).userId();
    }
}

package cn.vgonet.mirror.user.gateways;

import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;
import cn.vgonet.mirror.user.gateways.acl.JwtTokenService;
import cn.vgonet.mirror.user.gateways.acl.AuthUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AuthUserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;
    private ApplicationEventPublisher eventPublisher;
    private static final String VALID_USERNAME = "validUser123";
    private static final String VALID_EMAIL = "valid@email.com";
    private static final String VALID_PASSWORD = "validPass123";
    private static final String REFERRAL_CODE = "referralCode";

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtTokenService = mock(JwtTokenService.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        userService = new AuthUserService(passwordEncoder, jwtTokenService, eventPublisher, userRepository);
    }

    @Test
    void should_throw_exception_when_resister_user_with_existing_username() {
        given(userRepository.userExistsForUsername(VALID_USERNAME)).willReturn(true);
        Throwable throwable = catchThrowable(() ->
                userService.createUser(VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD, REFERRAL_CODE));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("用户名已存在");
    }

    @Test
    void should_throw_exception_when_resister_user_with_existing_email() {
        given(userRepository.UserExistsForEmail(VALID_EMAIL)).willReturn(true);
        Throwable throwable = catchThrowable(() ->
                userService.createUser(VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD, REFERRAL_CODE));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("邮箱已存在");
    }
}

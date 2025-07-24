package cn.vgonet.mirror.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class UserValidTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = mock(UserService.class);
    }

    @Test
    void should_throw_exception_when_username_is_invalid() {
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, userService).check("", "testpassword"));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("用户名不能为空");
    }

    @Test
    void should_throw_exception_when_password_is_invalid() {
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, userService).check("testusername", ""));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("密码不能为空");
    }

    @Test
    void should_throw_exception_when_username_is_not_exists() {
        given(userRepository.userExistsForUsername("testusername")).willReturn(false);
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, userService).validForUserName("testusername", "testpassword"));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("用户不存在");
    }

    @Test
    void should_throw_exception_when_user_is_active() {
        User user = new User("testuserid", "testusername", "valid@email.com", "testpassword", LocalDateTime.now(), "testreferralcode", false);
        given(userRepository.userForUserName("testusername")).willReturn(user);
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, userService).validForUserName("testusername", "testpassword"));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("账户已被禁用");
    }

    @Test
    void should_throw_exception_when_password_is_wrong() {
        User user = new User("testuserid", "testusername", "valid@email.com", "testpassword", LocalDateTime.now(), "testreferralcode", true);
        given(userRepository.userForUserName("testusername")).willReturn(user);
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, userService).validForUserName("testusername", "wrongpassword"));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("密码错误");
    }

    @Test
    void should_return_user_when_user_id_is_valid() {
        given(userRepository.userForId("testuserid")).willReturn(null);
        Throwable throwable = catchThrowable(() -> new UserValid(userRepository, null).validForUserId("testuserid"));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("用户不存在");
    }
}
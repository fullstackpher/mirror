package cn.vgonet.mirror.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.mock;

class UserTest {
    private UserRepository userRepository;
    private UserService userService;
    private static final LocalDateTime TEST_DATE = LocalDateTime.now();
    private static final String VALID_USER_ID = "1";
    private static final String VALID_USERNAME = "validUser123";
    private static final String VALID_EMAIL = "valid@email.com";
    private static final String VALID_PASSWORD = "validPass123";
    private static final String REFERRAL_CODE = "referralCode";

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = mock(UserService.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_username_is_empty_or_null(String invalidUsername) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, invalidUsername, VALID_EMAIL, VALID_PASSWORD,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("用户名不能为空");
    }

    @ParameterizedTest
    @MethodSource("invalidUsernames")
    void should_throw_exception_when_username_is_invalid(String invalidUsername) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, invalidUsername, VALID_EMAIL, VALID_PASSWORD,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("用户名只能包含字母、数字和下划线，长度3-20位");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_email_is_empty_or_null(String invalidEmail) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, VALID_USERNAME, invalidEmail, VALID_PASSWORD,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("邮箱不能为空");
    }

    @ParameterizedTest
    @MethodSource("invalidEmails")
    void should_throw_exception_when_email_is_invalid(String invalidEmail) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, VALID_USERNAME, invalidEmail, VALID_PASSWORD,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("邮箱格式不正确");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_password_is_empty_or_null(String invalidPassword) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, invalidPassword,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("密码不能为空");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", " ", "   "})
    void should_throw_exception_when_password_is_too_short(String invalidPassword) {
        Throwable throwable = catchThrowable(() ->
                new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, invalidPassword,
                        TEST_DATE, REFERRAL_CODE, true)
        );

        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("密码长度不能少于6位");
    }

    @Test
    void should_create_user_when_all_fields_are_valid() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);

        assertThat(user).isNotNull();
        assertThat(user.username()).isEqualTo(VALID_USERNAME);
        assertThat(user.email()).isEqualTo(VALID_EMAIL);
    }

    // isValidUsername 方法的补充测试
    @ParameterizedTest
    @ValueSource(strings = {"abc", "abcdefghijklmnopqrst", "user_123", "User123", "a1234567890123456789"})
    void should_return_true_when_username_is_valid(String validUsername) {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername(validUsername)).isTrue();
    }

    // isValidEmail 方法的补充测试
    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@domain.co.uk", "user123@test-domain.com", "a@b.co"})
    void should_return_true_when_email_is_valid(String validEmail) {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidEmail(validEmail)).isTrue();
    }

    // isValidPassword 方法的补充测试
    @ParameterizedTest
    @ValueSource(strings = {"123456", "password", "P@ssw0rd", "1234567890"})
    void should_return_true_when_password_is_valid(String validPassword) {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidPassword(validPassword)).isTrue();
    }

    // isValidUsername 方法的边界测试
    @Test
    void should_return_false_when_username_is_exactly_2_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername("ab")).isFalse();
    }

    @Test
    void should_return_true_when_username_is_exactly_3_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername("abc")).isTrue();
    }

    @Test
    void should_return_true_when_username_is_exactly_20_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername("abcdefghijklmnopqrst")).isTrue();
    }

    @Test
    void should_return_false_when_username_is_exactly_21_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername("abcdefghijklmnopqrstu")).isFalse();
    }

    // isValidPassword 方法的边界测试
    @Test
    void should_return_false_when_password_is_exactly_5_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidPassword("12345")).isFalse();
    }

    @Test
    void should_return_true_when_password_is_exactly_6_characters() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidPassword("123456")).isTrue();
    }

    // 针对null输入的显式测试
    @Test
    void should_return_false_when_isValidUsername_receives_null() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidUsername(null)).isFalse();
    }

    @Test
    void should_return_false_when_isValidEmail_receives_null() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidEmail(null)).isFalse();
    }

    @Test
    void should_return_false_when_isValidPassword_receives_null() {
        User user = new User(VALID_USER_ID, VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD,
                TEST_DATE, REFERRAL_CODE, true);
        assertThat(user.isValidPassword(null)).isFalse();
    }

    private static Stream<String> invalidUsernames() {
        return Stream.of(
                "user@name",    // 包含特殊字符
                "ab",           // 太短
                "this_username_is_way_too_long_for_validation", // 太长
                "  username  "  // 包含空格
        );
    }

    private static Stream<String> invalidEmails() {
        return Stream.of(
                "invalid",
                "invalid@",
                "invalid@domain",
                "invalid@.com",
                "@domain.com"
        );
    }
}

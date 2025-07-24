package cn.vgonet.mirror.usecase.user;

import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUserProfileTest extends IntegrationTest {

    private @Resource UserRepository userRepository;
    private @Resource UserService userService;
    private String userId;

    @BeforeEach
    void setUp() {
        String validToken = registerAndLoginUser();
        userId = userService.extractUserIdFromToken(validToken);
    }

    private String registerAndLoginUser() {
        Map<String, Object> registerInfo = new HashMap<>();
        registerInfo.put("username", "testuser");
        registerInfo.put("email", "test@example.com");
        registerInfo.put("password", "password123");
        registerInfo.put("referralCode", "REF123");

        post("/users/register", registerInfo);

        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("username", "testuser");
        loginInfo.put("password", "password123");

        TestResponse response = post("/users/login", loginInfo);
        return response.<String>value("$.data.token");
    }

    @Test
    void should_be_able_to_get_user_profile_successfully_with_valid_token() {
        TestResponse response = get("/users/profile/{userId}", userId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.username")).isEqualTo("testuser");
        assertThat(response.<String>value("$.data.email")).isEqualTo("test@example.com");
        assertThat(response.<String>value("$.data.referralCode")).isEqualTo("REF123");
        assertThat(response.<Boolean>value("$.data.active")).isTrue();
        assertThat(response.<String>value("$.data.registerDate")).isNotNull();
    }

    @Test
    void should_return_true_when_token_is_valid() {
        String validToken = registerAndLoginUser();
        assertThat(userService.validateToken(validToken)).isTrue();
    }

    @Test
    void should_return_false_when_token_is_invalid() {
        String invalidToken = "invalid_token";
        boolean isValidToken = userService.validateToken(invalidToken);
        assertThat(isValidToken).isFalse();
    }

    @Test
    void should_return_null_when_extracting_user_id_from_invalid_token() {
        String invalidToken = "invalid_token";
        String userId = userService.extractUserIdFromToken(invalidToken);
        assertThat(userId).isNull();
    }

    @Test
    void should_return_false_when_token_is_empty() {
        boolean isValidToken = userService.validateToken("");
        assertThat(isValidToken).isFalse();
    }

    @Test
    void should_return_null_when_extracting_user_id_from_empty_token() {
        String userId = userService.extractUserIdFromToken("");
        assertThat(userId).isNull();
    }

    @AfterEach
    void tearDown() {
        userRepository.removeAll();
    }
}

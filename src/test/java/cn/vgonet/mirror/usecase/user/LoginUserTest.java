package cn.vgonet.mirror.usecase.user;

import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import cn.vgonet.mirror.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserTest extends IntegrationTest {
    
    private @Resource UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // 先注册一个用户用于登录测试
        Map<String, Object> registerRequest = new HashMap<>();
        registerRequest.put("username", "testuser");
        registerRequest.put("email", "test@example.com");
        registerRequest.put("password", "password123");
        post("/users/register", registerRequest);
    }

    @Test
    void should_be_able_to_login_successfully() {
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("username", "testuser");
        loginRequest.put("password", "password123");

        TestResponse response = post("/users/login", loginRequest);
        
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.username")).isEqualTo("testuser");
        assertThat(response.<String>value("$.data.email")).isEqualTo("test@example.com");
        assertThat(response.<String>value("$.data.membershipId")).isNotNull();
        assertThat(response.<String>value("$.data.membershipId")).hasSize(36);
        assertThat(response.<String>value("$.data.token")).isNotNull();
        assertThat(response.<String>value("$.data.token")).isNotEmpty();
    }

    @AfterEach
    void tearDown() {
        userRepository.removeAll();
    }
}
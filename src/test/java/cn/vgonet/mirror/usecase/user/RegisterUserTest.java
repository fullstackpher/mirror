package cn.vgonet.mirror.usecase.user;

import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterUserTest extends IntegrationTest {
    
    private @Resource UserRepository userRepository;

    @Test
    void should_be_able_to_register_user_successfully() {
        Map<String, Object> registerInfo = new HashMap<>();
        registerInfo.put("username", "testuser");
        registerInfo.put("email", "test@example.com");
        registerInfo.put("password", "password123");
        registerInfo.put("referralCode", "REF123");

        TestResponse response = post("/users/register", registerInfo);
        
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.id")).hasSize(36);

        User user = userRepository.userForUserName("testuser");
        assertThat(user).isNotNull();
        assertThat(user.username()).isEqualTo("testuser");
        assertThat(user.email()).isEqualTo("test@example.com");
        assertThat(user.referralCode()).isEqualTo("REF123");
        assertThat(user.isActive()).isTrue();

    }

    @AfterEach
    void tearDown() {
        userRepository.removeAll();
    }
}
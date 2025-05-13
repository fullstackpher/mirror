package cn.vgonet.mirror.api;

import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiResponseTest extends IntegrationTest {
    @Test
    void should_wrap_response_body_for_normal_response() {
        TestResponse response = get("/test-normal-response");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.name")).isEqualTo("John");
    }

    @Test
    void should_wrap_response_body_for_error_response() {
        TestResponse response = get("/test-not-found-for-error-response");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("1");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("not-found");
        assertThat(response.<String>value("$.data")).isNull();
    }

    @RestController
    public static class TestController {
        @GetMapping("/test-normal-response")
        public Object test() {
            return ImmutableMap.of("name", "John");
        }
    }
}

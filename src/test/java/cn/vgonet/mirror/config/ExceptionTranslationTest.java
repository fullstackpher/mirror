package cn.vgonet.mirror.config;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.frameworks.domain.core.AggregateNotFoundException;
import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExceptionTranslationTest extends IntegrationTest {
    @Test
    void should_be_able_to_translate_exception() {
        TestResponse response = get("/test-not-found");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("1");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("nothing found");
    }

    @UseCase
    @RestController
    public static class TestController {
        @GetMapping("/test-not-found")
        public void testNotFound() {
            throw new AggregateNotFoundException("nothing found");
        }
    }
}

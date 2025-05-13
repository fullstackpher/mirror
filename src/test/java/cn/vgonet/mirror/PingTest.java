package cn.vgonet.mirror;

import cn.vgonet.mirror.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PingTest extends IntegrationTest {
    @Test
    void should_reply_archegos() {
        assertThat(get("/ping").statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(get("/ping").<String>value("$.errcode")).isEqualTo("0");
        assertThat(get("/ping").<String>value("$.data.pong")).isEqualTo("archegos");
    }

    @Test
    void should_reply_ci_info() {
        assertThat(get("/ci").statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(get("/ci").<String>value("$.errcode")).isEqualTo("0");
        assertThat(get("/ci").<String>value("$.data.ci")).isEqualTo("2021-09-30-001");
    }
}

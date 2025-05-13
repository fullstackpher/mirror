package cn.vgonet.mirror.api;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class ApiResultTest {
    @Test
    void should_be_able_to_construct_successful_result() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        given(request.getURI()).willReturn(URI.create("/not-error"));
        String body = "test";
        ApiResult<?> result = ApiResult.of(request, body);
        assertThat(result.getErrorCode()).isEqualTo("0");
        assertThat(result.getErrorMessage()).isEmpty();
        assertThat(result.getData()).isEqualTo(body);
    }

    @Test
    void should_be_able_to_construct_generic_error_result() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        given(request.getURI()).willReturn(URI.create("/error"));
        Map<String, Object> body = ImmutableMap.of("status", 404, "error", "Not Found", "message", "No message available");
        ApiResult<?> result = ApiResult.of(request, body);
        assertThat(result.getErrorCode()).isEqualTo("1");
        assertThat(result.getErrorMessage()).isEqualTo("not-found");
        assertThat(result.getData()).isNull();
    }

    @Test
    void should_be_able_to_construct_customized_error_result() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        given(request.getURI()).willReturn(URI.create("/error"));
        Map<String, Object> body = ImmutableMap.of("status", 404, "error", "Not Found", "message", "error.user-not-found");
        ApiResult<?> result = ApiResult.of(request, body);
        assertThat(result.getErrorCode()).isEqualTo("1");
        assertThat(result.getErrorMessage()).isEqualTo("error.user-not-found");
        assertThat(result.getData()).isNull();
    }

    @Test
    void should_translate_other_http_errors_into_unknown_errors() {
        HttpRequest request = Mockito.mock(HttpRequest.class);
        given(request.getURI()).willReturn(URI.create("/error"));
        Map<String, Object> body = ImmutableMap.of("status", 404, "error", "Not", "message", "No message available");
        ApiResult<?> result = ApiResult.of(request, body);
        assertThat(result.getErrorCode()).isEqualTo("1");
        assertThat(result.getErrorMessage()).isEqualTo("unknown");
        assertThat(result.getData()).isNull();
    }
}

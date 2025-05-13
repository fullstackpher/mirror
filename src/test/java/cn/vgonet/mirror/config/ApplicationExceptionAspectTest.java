package cn.vgonet.mirror.config;

import cn.vgonet.mirror.frameworks.application.core.AuthenticationException;
import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.frameworks.domain.core.AccessDeniedException;
import cn.vgonet.mirror.test.IntegrationTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ApplicationExceptionAspectTest extends IntegrationTest {
    private @Resource AuthenticationExceptionTest authenticationExceptionTest;

    @Test
    void should_be_able_to_translate_authentication_exceptions() {
        Throwable throwable = catchThrowable(() -> authenticationExceptionTest.throwAuthenticationException());
        assertThat(throwable).isInstanceOf(Application401Exception.class);
        assertThat(throwable).hasMessage("error");
    }

    @Test
    void should_be_able_to_translate_illegal_arguments_exceptions() {
        Throwable throwable = catchThrowable(() -> authenticationExceptionTest.throwIllegalArgumentException());
        assertThat(throwable).isInstanceOf(Application400Exception.class);
        assertThat(throwable).hasMessage("error");
    }

    @Test
    void should_convert_409_authentication_exception() {
        Throwable throwable = catchThrowable(() -> authenticationExceptionTest.throw409Exception());
        assertThat(throwable).isInstanceOf(Application409Exception.class);
        assertThat(throwable).hasMessage("error");
    }

    @Test
    void should_convert_authentication_exception() {
        Throwable throwable = catchThrowable(() -> authenticationExceptionTest.throwOtherException());
        assertThat(throwable).isInstanceOf(ApplicationException.class);
        assertThat(throwable).hasMessage("error");
    }

    @Test
    void should_convert_access_denied_exception() {
        Throwable throwable = catchThrowable(() -> authenticationExceptionTest.throwAccessDeniedException());
        assertThat(throwable).isInstanceOf(Application401Exception.class);
        assertThat(throwable).hasMessage("error");
    }

    @UseCase
    public static class AuthenticationExceptionTest {
        public void throwAuthenticationException() {
            throw new AuthenticationException("error");
        }

        public void throwIllegalArgumentException() {
            throw new IllegalArgumentException("error");
        }

        public void throw409Exception() {
            throw new IllegalStateException("error");
        }

        public void throwOtherException() {
            throw new OtherException("error");
        }

        public void throwAccessDeniedException() {
            throw new AccessDeniedException("error");
        }
    }

    static class OtherException extends RuntimeException {
        public OtherException(String message) {
            super(message);
        }
    }
}

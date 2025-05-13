package cn.vgonet.mirror.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ApplicationExceptionTest {
    private static final String MESSAGE_FROM_RESOLVER = "default-error-message";

    @Test
    public void empty_exception_should_be_resolved_as_default_error_message() {
        ApplicationException exception = new ApplicationException();
        MessageResolver messageResolver = mock(MessageResolver.class);
        given(messageResolver.resolve(ApplicationException.DEFAULT_MESSAGE_CODE)).willReturn(MESSAGE_FROM_RESOLVER);
        assertEquals(MESSAGE_FROM_RESOLVER, exception.resolveMessage(messageResolver));
    }

    @Test
    public void should_resolve_message_from_message_resolver_with_correct_code_and_args() {
        ApplicationException exception = new ApplicationException("error.code.test", new Object[]{1, 2});
        MessageResolver messageResolver = mock(MessageResolver.class);
        given(messageResolver.resolve("error.code.test", 1, 2)).willReturn(MESSAGE_FROM_RESOLVER);
        assertEquals(MESSAGE_FROM_RESOLVER, exception.resolveMessage(messageResolver));
    }

}

package cn.vgonet.mirror.config;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ExceptionTranslatorTest {
    private static final String MESSAGE_FROM_MESSAGE_SOURCE = "stub message";
    private MessageResolver messageResolver;

    @Before
    public void setup() {
        messageResolver = mock(MessageResolver.class);
        given(messageResolver.resolve(anyString(), any())).willReturn(MESSAGE_FROM_MESSAGE_SOURCE);
    }

    @Test
    public void can_convert_application_exception() {
        Map<String, Object> error = new HashMap<>();
        ExceptionTranslator translator = new ExceptionTranslator(messageResolver);
        translator.handle(error, new ApplicationException());
        assertEquals(ImmutableMap.of("message", (Object) MESSAGE_FROM_MESSAGE_SOURCE), error);
    }

    @Test
    public void should_ignore_null_exceptions() {
        Map<String, Object> error = new HashMap<>();
        ExceptionTranslator translator = new ExceptionTranslator(messageResolver);
        translator.handle(error, null);
        assertEquals(ImmutableMap.of(), error);
    }
}

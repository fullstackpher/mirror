package cn.vgonet.mirror.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregateNotFoundExceptionTest {
    @Test
    void should_be_able_to_construct_from_error_id() {
        AggregateNotFoundException exception = new AggregateNotFoundException("error.message-id");
        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception).hasMessage("error.message-id");
    }
}

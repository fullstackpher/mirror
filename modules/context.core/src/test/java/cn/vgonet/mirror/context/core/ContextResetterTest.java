package cn.vgonet.mirror.context.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContextResetterTest {
    private static final Operator OPERATOR = new Operator("test.id", "test.name", null);

    @Test
    void should_be_able_to_reset_context() {
        ContextResetter.reset(OPERATOR);
        assertThat(Context.operator()).isEqualTo(OPERATOR);
    }

    @Test
    void should_be_able_to_clear_context() {
        ContextResetter.reset(OPERATOR);
        ContextResetter.clear();
        assertThat(Context.operator()).isNull();
    }
}
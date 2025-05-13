package cn.vgonet.mirror.context.core;

import cn.vgonet.mirror.frameworks.domain.core.IdAndName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContextTest {
    private static final Operator OPERATOR = new Operator("id", "lisa", new IdAndName("baidu", "Baidu"));

    @BeforeEach
    void setUp() {
        Context.resetOperator(OPERATOR);
    }

    @Test
    void should_be_able_to_hold_current_operator() {
        Context.resetOperator(OPERATOR);
        assertThat(Context.operator()).isEqualTo(OPERATOR);
        assertThat(Context.user()).isEqualTo(new IdAndName("id", "lisa"));
        assertThat(Context.client()).hasValue(new IdAndName("baidu", "Baidu"));
    }
}

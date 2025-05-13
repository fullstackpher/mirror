package cn.vgonet.mirror.context.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperatorTest {
    @Test
    void should_create_with_id_and_name() {
        Operator operator = new Operator("id", "lisa", null);
        assertThat(operator.id()).isEqualTo("id");
        assertThat(operator.name()).isEqualTo("lisa");
    }
}
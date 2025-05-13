package cn.vgonet.mirror.frameworks.domain.core;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionUtilsTest {
    @Test
    void should_normal_input_list() {
        Assertions.assertThat(CollectionUtils.defaultList(ImmutableList.of(1))).isEqualTo(ImmutableList.of(1));
        Assertions.assertThat(CollectionUtils.defaultList(null)).isEqualTo(ImmutableList.of());
    }
}

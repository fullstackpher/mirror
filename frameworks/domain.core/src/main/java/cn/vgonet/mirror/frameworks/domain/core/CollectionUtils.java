package cn.vgonet.mirror.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtils {
    public static <T> List<T> defaultList(@Nullable List<T> list) {
        return list != null ? list : Collections.emptyList();
    }
}

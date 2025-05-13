package cn.vgonet.mirror.context.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextResetter {
    public static void reset(Operator operator) {
        Context.resetOperator(operator);
    }

    public static void clear() {
        Context.resetOperator(null);
    }
}

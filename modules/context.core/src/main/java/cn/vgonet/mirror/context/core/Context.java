package cn.vgonet.mirror.context.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import cn.vgonet.mirror.frameworks.domain.core.IdAndName;

import javax.annotation.Nullable;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Context {
    private static final ThreadLocal<Operator> OPERATOR = new ThreadLocal<>();

    static void resetOperator(@Nullable Operator operator) {
        Context.OPERATOR.set(operator);
    }

    public static Operator operator() {
        return OPERATOR.get();
    }

    public static IdAndName user() {
        return new IdAndName(operator().id(), operator().name());
    }

    public static Optional<IdAndName> client() {
        return operator().client();
    }

//    public static String currentClientId() {
//        return Context.client().map(IdAndName::getId).orElse("");
//    }
}

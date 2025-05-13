package cn.vgonet.mirror.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ExceptionTranslator {
    private final MessageResolver messageResolver;
    private final List<BiFunction<Map<String, Object>, Throwable, Boolean>> handlerChain = new ArrayList<>();

    public ExceptionTranslator(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
        initializeHandlerChain();
    }

    private void initializeHandlerChain() {
        handlerChain.add(this::handleApplicationException);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    void handle(Map<String, Object> error, Throwable throwable) {
        handlerChain.stream().anyMatch(x -> x.apply(error, throwable));
    }

    private Boolean handleApplicationException(Map<String, Object> error, Throwable throwable) {
        if (throwable == null) return false;
        error.put("message", ((ApplicationException) throwable).resolveMessage(messageResolver));
        return true;
    }
}

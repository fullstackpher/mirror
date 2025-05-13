package cn.vgonet.mirror.frameworks.domain.core;

public class AggregateNotFoundException extends DomainException {
    public AggregateNotFoundException(String messageId) {
        super(messageId);
    }
}

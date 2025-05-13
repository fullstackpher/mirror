package cn.vgonet.mirror.frameworks.domain.core;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        this(message, null);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

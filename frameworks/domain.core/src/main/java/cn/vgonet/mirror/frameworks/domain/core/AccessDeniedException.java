package cn.vgonet.mirror.frameworks.domain.core;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}

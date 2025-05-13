package cn.vgonet.mirror.config;

public class ApplicationException extends RuntimeException {
    static final String DEFAULT_MESSAGE_CODE = "error.default-application-error";
    private final Object[] args;

    public ApplicationException() {
        super(DEFAULT_MESSAGE_CODE);
        this.args = new Object[0];
    }

    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.args = new Object[0];
    }

    ApplicationException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public String resolveMessage(MessageResolver messageResolver) {
        return messageResolver.resolve(getMessage(), args);
    }
}

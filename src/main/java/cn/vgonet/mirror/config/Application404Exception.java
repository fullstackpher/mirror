package cn.vgonet.mirror.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Application404Exception extends ApplicationException {
    public Application404Exception(String message, Throwable throwable) {
        super(message, throwable);
    }
}

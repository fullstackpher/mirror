package cn.vgonet.mirror.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Application401Exception extends ApplicationException {
    public Application401Exception(String message, Throwable cause) {
        super(message, cause);
    }
}

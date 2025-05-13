package cn.vgonet.mirror.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Application400Exception extends ApplicationException {
    public Application400Exception(String message, Throwable throwable) {
        super(message, throwable);
    }
}

package cn.vgonet.mirror.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class Application409Exception extends ApplicationException {
    protected Application409Exception(String message, Throwable cause) {
        super(message, cause);
    }
}

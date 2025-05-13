package cn.vgonet.mirror.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ApplicationErrorAttributes extends DefaultErrorAttributes {
    private @Resource ExceptionTranslator exceptionTranslator;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        Map<String, Object> error = super.getErrorAttributes(request, false);
        Throwable throwable = getError(request);
        exceptionTranslator.handle(error, throwable);
        request.setAttribute("javax.servlet.error.status_code", error.get("status"), RequestAttributes.SCOPE_REQUEST);
        return error;
    }
}

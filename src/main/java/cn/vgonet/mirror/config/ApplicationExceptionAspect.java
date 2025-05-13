package cn.vgonet.mirror.config;

import lombok.Generated;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import cn.vgonet.mirror.frameworks.application.core.AuthenticationException;
import cn.vgonet.mirror.frameworks.domain.core.AccessDeniedException;
import cn.vgonet.mirror.frameworks.domain.core.AggregateNotFoundException;


@Aspect
@Component
public class ApplicationExceptionAspect {
    @Generated
    @Pointcut("execution(public * *(..))")
    private void inApplication() {
    }

    @Generated
    @Pointcut("within(cn.vgonet..*) && @within(cn.vgonet.mirror.frameworks.application.core.UseCase)")
    private void archegosOperation() {
    }

    @AfterThrowing(pointcut = "inApplication() && archegosOperation()", throwing = "throwable")
    public void translate(Throwable throwable) {
        if (throwable instanceof AggregateNotFoundException) {
            throw new Application404Exception(throwable.getMessage(), throwable);
        }

        if (throwable instanceof AuthenticationException) {
            throw new Application401Exception(throwable.getMessage(), throwable);
        }

        if (throwable instanceof IllegalArgumentException) {
            throw new Application400Exception(throwable.getMessage(), throwable);
        }

        if (throwable instanceof IllegalStateException) {
            throw new Application409Exception(throwable.getMessage(), throwable);
        }

        if (throwable instanceof AccessDeniedException) {
            throw new Application401Exception(throwable.getMessage(), throwable);
        }

        throw new ApplicationException(throwable.getMessage(), throwable);
    }
}

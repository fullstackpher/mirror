package cn.vgonet.mirror.frameworks.application.core;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Transactional
public @interface UseCase {
    @AliasFor(attribute = "authorities")
    String[] value() default {};

    @AliasFor(attribute = "value")
    String[] authorities() default {};

    boolean authenticated() default false;
}

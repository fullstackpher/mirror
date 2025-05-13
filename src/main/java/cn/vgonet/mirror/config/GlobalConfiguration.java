package cn.vgonet.mirror.config;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAsync
@EnableMongock
@ComponentScan(basePackageClasses = GlobalConfiguration.class)
public class GlobalConfiguration {
//    @Bean
//    public IdentityService idService() {
//        return new UuidBasedIdentityService();
//    }

    @Bean
    public ExceptionTranslator exceptionTranslator(MessageSource messageSource) {
        return new ExceptionTranslator(new SpringMessageResolver(messageSource));
    }
}

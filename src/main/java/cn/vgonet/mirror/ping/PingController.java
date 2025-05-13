package cn.vgonet.mirror.ping;

import com.google.common.collect.ImmutableMap;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.vgonet.mirror.frameworks.application.core.UseCase;

import javax.annotation.Resource;

@UseCase
@RestController
public class PingController {
    private @Resource MongoTemplate mongoTemplate;

    @GetMapping("/ping")
    public Object ping() {
        return mongoTemplate.findById("ping", Pong.class, "pong");
    }

    @GetMapping("/ci")
    public Object ci() {
        return ImmutableMap.of("ci", "2021-09-30-001");
    }
}
